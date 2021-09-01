package pj210728.pj;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
            String userName = (String) session.getAttribute("userName");
            if (userName != null) {
                return true;
            }
        }
        response.sendRedirect(request.getContextPath() + "/");
        return false;
    }

}
