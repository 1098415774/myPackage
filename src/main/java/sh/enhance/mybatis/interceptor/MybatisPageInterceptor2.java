package sh.enhance.mybatis.interceptor;

import sh.base.utils.ReflectUtil;
import sh.base.utils.StringUtils;
import sh.enhance.mybatis.MybatisQueryConstants;
import sh.enhance.mybatis.operation.MybatisOperation;
import sh.enhance.mybatis.operation.MybatisOperationGroup;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class})})
public class MybatisPageInterceptor2 implements Interceptor {
    //每页显示的条目数
    private int pageSize = 10;
    //数据库类型
    private String dbType;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler,"delegate");
        BoundSql boundSql = delegate.getBoundSql();
        Object object = (Object) boundSql.getParameterObject();
        if (object instanceof Map){
            String sql = addQuery(boundSql);
            ReflectUtil.setFieldValue(boundSql,"sql",sql);
        }
        return invocation.proceed();
    }

    private String addQuery(BoundSql boundSql) {
        Map map = (Map) boundSql.getParameterObject();
        MybatisOperationGroup operations = null;
        try {
            operations = (MybatisOperationGroup) map.get(MybatisQueryConstants.OPERATION);
            if (operations == null){
                return boundSql.getSql();
            }
        }catch (Exception e){
            return boundSql.getSql();
        }
        String sql = boundSql.getSql();
        if (StringUtils.isEmpty(sql)){
            return boundSql.getSql();
        }
        StringBuilder sqlbuilder = new StringBuilder(sql);
        if (operations.getIntervals() != null){
            List<MybatisOperation> intervals = operations.getIntervals();
            interval(sqlbuilder,intervals);
        }
        if (operations.getSorts() != null){
            List<MybatisOperation> sorts = operations.getSorts();
            sort(sqlbuilder,sorts);
        }
        if (operations.getPaging() != null){
            paging(sqlbuilder,operations.getPaging());
        }
        return sqlbuilder.toString();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private void interval(StringBuilder sqlbuilder, List<MybatisOperation> intervals){
        if (intervals.size() <= 0){
            return;
        }
        int index = sqlbuilder.indexOf("WHERE");
        if (sqlbuilder.indexOf("where") <= 0 && sqlbuilder.indexOf("WHERE") <= 0){
            sqlbuilder.append(" where ");
        }else {
            sqlbuilder.append(" and ");
        }
        String title = null;
        String interval = null;
        String arg = null;
        for (MybatisOperation operation : intervals){
            title = StringUtils.getString(operation.getArg1());
            interval = StringUtils.getString(operation.getArg2());
            arg = StringUtils.getString(operation.getArg3());
            if (StringUtils.isEmpty(title) ||
                    StringUtils.isEmpty(interval) ||
                    StringUtils.isEmpty(arg))
                continue;

            switch (interval){
                case MybatisQueryConstants.INTERVAL_START:
                    sqlbuilder.append(title + " >= '" + arg + "' ");
                    break;
                case MybatisQueryConstants.INTERVAL_END:
                    sqlbuilder.append(title + " <= '" + arg + "' ");
                    break;
            }
            sqlbuilder.append(" and ");
        }
        sqlbuilder.delete(sqlbuilder.length() - 5,sqlbuilder.length());
    }

    private void sort(StringBuilder sqlbuilder, List<MybatisOperation> sorts){
//        title = StringUtils.isEmpty(title) ? "id" : title;
        if (sorts.size() <= 0){
            return;
        }
        StringBuilder order = new StringBuilder();
        String sortorder = null;
        for (MybatisOperation operation : sorts){
            try {
                switch ((int)operation.getArg2()){
                    case MybatisQueryConstants.SORT_ORDER_ASC:
                        sortorder = " asc ";
                        break;
                    case MybatisQueryConstants.SORT_ORDER_DESC:
                        sortorder = " desc ";
                        break;
                }
            }catch (NullPointerException e){
                sortorder = " asc ";
            }
            order.append(" " + operation.getArg1() + sortorder + ",");
        }
        order.delete(order.length() - 1,order.length());
        switch (this.dbType){
            case "sqlserver":
                sqlbuilder.insert(0,"select row_number() over(order by " + order.toString() + ") as rownumber,* from (");
                sqlbuilder.append(") as oo");
                break;
            case "mysql":
                sqlbuilder.append(" order by " + order.toString());
                break;
        }
    }

    private void paging(StringBuilder sqlbuilder,MybatisOperation operation){
        int pagesize = (int) operation.getArg2();
        pagesize = pagesize > 0 ? pagesize : pageSize;
        switch (this.dbType){
            case "sqlserver":
                sqlbuilder.insert(0,"select top " + pagesize + " o.* from (");
                sqlbuilder.append(") as o where rownumber > " + operation.getArg1() + ";");
                break;
            case "mysql":
                sqlbuilder.append("limit " + operation.getArg1() + " , " + pagesize);
                break;
        }

    }
}
