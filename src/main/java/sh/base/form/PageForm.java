package sh.base.form;

import com.alibaba.fastjson.annotation.JSONField;

public class PageForm {
    @JSONField(name = "TotalRows")
    private String TotalRows;
    @JSONField(name = "TotalPages")
    private String TotalPages = "1";

    public String getTotalRows() {
        return TotalRows;
    }

    public void setTotalRows(String totalRows) {
        TotalRows = totalRows;
    }

    public String getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(String totalPages) {
        TotalPages = totalPages;
    }
}
