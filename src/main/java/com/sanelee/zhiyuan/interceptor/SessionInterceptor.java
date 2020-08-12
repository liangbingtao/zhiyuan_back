package com.sanelee.zhiyuan.interceptor;//package com.sanelee.zhiyuan.interceptor;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Service
//public class SessionInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
////        Object user = request.getSession().getAttribute("loginUser");
////        if (user == null){
////            request.setAttribute("name","无权限，请先登录");
////            request.getRequestDispatcher("/front/login").forward(request,response);
////            return false;
////        }else{
////            return true;
////        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
