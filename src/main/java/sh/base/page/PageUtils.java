package sh.base.page;

import com.alibaba.fastjson.JSONObject;
import sh.base.utils.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class PageUtils {

    public static int pagesize = 0;

    public static void addPage(JSONObject jsonObject){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String pageNum = jsonObject.getString("PageNum");
        String pageSize = jsonObject.getString("PageSize");
        if (StringUtils.isNotEmpty(pageNum)){
            PageBean pageBean = new PageBean();
            pageBean.setPageSize(pagesize);
            if (StringUtils.isNotEmpty(pageSize)){
                pageBean.setPageSize(Integer.parseInt(pageSize));
            }
            pageBean.setPageNum(Integer.parseInt(pageNum));
            request.setAttribute("pageBean",pageBean);
        }
    }
}
