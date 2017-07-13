package by.tastyfood.servlets;

import by.tastyfood.dao.entity.Users;
import by.tastyfood.model.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Nikolay on 21.06.2017.
 */
@WebServlet(name = "loginCheck", urlPatterns = {"/login/check"})
public class UserChekerServlet extends HttpServlet {
   private final String LOGIN_PARAM ="login";

    @Autowired
   private UserService userService;

    public void init(ServletConfig config) {

        try {
            super.init(config);

        } catch (ServletException e) {
            e.printStackTrace();
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN_PARAM);

        Writer out = resp.getWriter();
        Boolean valid = false;

        Users existingUser= null;
        if(login != null){
           existingUser = userService.findByUsername(login);
        }
        System.out.println("login = "+login);
        if(existingUser == null){
            valid = true;
        }

        out.append("{ \"valid\": ").append(valid.toString()).append(" }");
        out.flush();
        out.close();

    }
}