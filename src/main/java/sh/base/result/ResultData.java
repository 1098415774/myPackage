package sh.base.result;

/*
* 回传类
* */
public class ResultData {
    /*
    * 状态标志位
    * error:ResultConstants.ERROR
    * success:ResultConstants.SUCCESS
    * */
    private String code;
    //回传信息
    private String msg;
    //查询结果
    private Object data ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

