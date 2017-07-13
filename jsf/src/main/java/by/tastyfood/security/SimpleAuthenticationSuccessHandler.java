package by.tastyfood.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Nikolay on 26.06.2017.
 */
@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final String PREV_URI_PARAM_NAME = "prevUri";

    private final String DEFAULT_REDIRECT_URI = "/recepiesCategory.jsf";

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication)
            throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        authorities.forEach(authority -> {
            if (authority.getAuthority().equals("ROLE_admin")) {
                try {
                    redirectStrategy.sendRedirect(req, res, "/admin.xhtml");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {

                try {

                    String prevUri = req.getParameter(PREV_URI_PARAM_NAME);
                    System.out.println("prevUri = "+prevUri);


                    if(prevUri == null){
                        redirectStrategy.sendRedirect(req, res,DEFAULT_REDIRECT_URI);
                    }
                    if(prevUri.indexOf("login")>0){
                        redirectStrategy.sendRedirect(req, res,DEFAULT_REDIRECT_URI);
                    }

                    redirectStrategy.sendRedirect(req, res, prevUri);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }


}
