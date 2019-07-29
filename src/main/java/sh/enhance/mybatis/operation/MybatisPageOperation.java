package sh.enhance.mybatis.operation;

import sh.enhance.mybatis.MybatisQueryConstants;

public class MybatisPageOperation extends MybatisOperation{


    public MybatisPageOperation(int startIndex, int size) {
        super(MybatisQueryConstants.Paging, startIndex, size);
    }

    public void setStartIndex(int startIndex){
        setArg1(startIndex);
    }

    public void setSize(int size){
        setArg2(size);
    }

    public int getStartIndex(){
        return (int) getArg1();
    }

    public int getSize(){
        return (int) getArg2();
    }
}
