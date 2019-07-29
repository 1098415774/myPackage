package sh.enhance.mybatis.operation;

import sh.enhance.mybatis.MybatisQueryConstants;

import java.util.LinkedList;
import java.util.List;

public class MybatisOperationGroup {

    private List<MybatisOperation> intervals;
    private List<MybatisOperation> sorts;
    private MybatisOperation paging;

    public MybatisOperationGroup addOperation(MybatisOperation operation){
        if (operation == null)
            return this;

        switch (operation.getOperation()){
            case MybatisQueryConstants
                    .Interval:
                if (intervals == null){
                    intervals = new LinkedList<>();
                }
                intervals.add(operation);
                break;
            case MybatisQueryConstants
                    .Sort:
                if (sorts == null){
                    sorts = new LinkedList<>();
                }
                sorts.add(operation);
                break;
            case MybatisQueryConstants
                    .Paging:
                paging = operation;
                break;
            default:
                break;
        }
        return this;
    }

    public List<MybatisOperation> getIntervals() {
        return intervals;
    }

    public List<MybatisOperation> getSorts() {
        return sorts;
    }

    public MybatisOperation getPaging() {
        return paging;
    }
}
