package sh.enhance.mybatis.operation;

import sh.base.utils.StringUtils;
import sh.enhance.mybatis.MybatisQueryConstants;

public class MybatisSortOperation extends MybatisOperation{

    public MybatisSortOperation(String title, int order) {
        super(MybatisQueryConstants.Sort, title, order);
    }

    public void setTitle(String title){
        setArg1(title);
    }

    public void setOrder(String order){
        setArg2(order);
    }

    public String getTitle(){
        return StringUtils.getString(getArg1());
    }

    public int getOrder(){
        return (int) getArg2();
    }
}
