package sh.enhance.mybatis;

public class MybatisQueryConstants {
    public static final String OPERATION = "MybatisQueryConstants_operation";    //选择操作类型

    public static final int Paging = 4;    //分页操作
    public static final int Sort = 2;    //排序查询
    public static final int Interval = 1;    //区间查询

    public static final String PAGE_START_INDEX = "MybatisQueryConstants_page_start_index";     //开始页
    public static final String PAGE_SIZE = "MybatisQueryConstants_page_size";   //页面大小

    public static final String INTERVAL_START = "MybatisQueryConstants_interval_start";     //大于
    public static final String INTERVAL_END = "MybatisQueryConstants_interval_end";     //小于
    public static final String INTERVAL_TITLE = "MybatisQueryConstants_interval_title";     //查询字段

    public static final String SORT_TITLE = "MybatisQueryConstants_sort_title";     //排序字段
    public static final String SORT_ORDER = "MybatisQueryConstants_sort_order";     //排序顺序
    public static final int SORT_ORDER_ASC = 0;     //正序
    public static final int SORT_ORDER_DESC = 1;    //倒序
}
