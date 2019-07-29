package sh.base.page;

import com.alibaba.fastjson.JSONObject;
import sh.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageInterceptor implements HandlerInterceptor {

    @Value("${list.pagesize}")
    private int pagesize;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String pageNum = request.getParameter("PageNum");
        String pageSize = request.getParameter("PageSize");
        if (StringUtils.isNotEmpty(pageNum)){
            PageBean pageBean = new PageBean();
            pageBean.setPageSize(pagesize);
            if (StringUtils.isNotEmpty(pageSize)){
                pageBean.setPageSize(Integer.parseInt(pageSize));
            }
            pageBean.setPageNum(Integer.parseInt(pageNum));
            request.setAttribute("pageBean",pageBean);
        }
        return true;
    }


    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public void addPage(JSONObject jsonObject){
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
