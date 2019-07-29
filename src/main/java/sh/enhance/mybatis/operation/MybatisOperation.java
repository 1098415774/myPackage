package sh.enhance.mybatis.operation;

public class MybatisOperation {

    private Integer operation = null;

    private Object arg1;

    private Object arg2;

    private Object arg3;

    public MybatisOperation(Integer operation, Object arg1, Object arg2, Object arg3){
        this.operation = operation;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }
    public MybatisOperation(Integer operation, Object arg1, Object arg2){
        this(operation,arg1,arg2,null);
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Object getArg1() {
        return arg1;
    }

    public void setArg1(Object arg1) {
        this.arg1 = arg1;
    }

    public Object getArg2() {
        return arg2;
    }

    public void setArg2(Object arg2) {
        this.arg2 = arg2;
    }

    public Object getArg3() {
        return arg3;
    }

    public void setArg3(Object arg3) {
        this.arg3 = arg3;
    }
}
