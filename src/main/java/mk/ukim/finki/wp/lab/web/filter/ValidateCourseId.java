//package mk.ukim.finki.wp.lab.web.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter
//public class ValidateCourseId implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String id = (String) request.getSession().getAttribute("selectedCourse");
//        String path = request.getServletPath();
//
//        if((path.equals("/createStudent") && id == null) || (path.equals("/studentEnrollmentSummary") && id == null) || "/".equals(path) || "/listCourses".equals(path)){
//            response.sendRedirect("/courses");
//        }
//        else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
