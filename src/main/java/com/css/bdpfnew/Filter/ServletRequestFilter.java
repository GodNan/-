package com.css.bdpfnew.Filter;

import com.css.bdpfnew.BodyReaderHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Auther: GodNan
 * @Date: 2018/6/21 11:30
 * @Version: 1.0
 * @Description:
 * 处理拦截器中使用流读取参数之后controller无法接收参数
 */
@WebFilter(filterName="servletRequestFilter",urlPatterns="/*")
public class ServletRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("--------------过滤器初始化------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("--------------执行过滤操作------------");

        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
//        System.out.println("--------------过滤器销毁------------");
    }
}
