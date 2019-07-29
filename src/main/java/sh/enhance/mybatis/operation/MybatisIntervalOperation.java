package sh.enhance.mybatis.operation;

import sh.base.utils.StringUtils;
import sh.enhance.mybatis.MybatisQueryConstants;

public class MybatisIntervalOperation extends MybatisOperation{

    public MybatisIntervalOperation(String title, String interval, Object arg) {
        super(MybatisQueryConstants.Interval, title, interval, arg);
    }

    public void setTitle(String title){
        setArg1(title);
    }

    public void setInterval(String interval){
        setArg2(interval);
    }

    public void setArg(Object arg){
        setArg3(arg);
    }

    public String getTitle(){
        return StringUtils.getString(getArg1());
    }

    public String getInterval(){
        return StringUtils.getString(getArg2());
    }

    public Object getArg(){
        return getArg3();
    }

}
