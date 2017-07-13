package by.tastyfood.jsf.beans.auth;

import by.tastyfood.dao.entity.Users;
import by.tastyfood.model.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

/**
 * Created by Nikolay on 20.06.2017.
 */
@Component
@ManagedBean(name = "authBean")
@Scope("request")
public class AuthBean {
    private final String ANONYMOUS_USER = "anonymousUser";

    @Autowired
    UserService userService;

    private String usernameInput;
    private String password;
    private String repeatPass;
    private String currentUserName;


    public String getUsernameInput() {
        return usernameInput;
    }

    public void setUsernameInput(String usernameInput) {
        this.usernameInput = usernameInput;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void saveUser(ActionEvent event) {
        System.out.println("start save");
        Users user = new Users();
        user.setEnabled(1);
        user.setUsername(usernameInput);
        user.setPassword(password);


        System.out.println(usernameInput + " " + password);
        userService.save(user);
    }

    public String getRepeatPass() {
        return repeatPass;
    }

    public void setRepeatPass(String repeatPass) {
        this.repeatPass = repeatPass;
    }

    public String getCurrentUserName() {
        findUserName();
        return currentUserName;
//        return isAnonymousUser(currentUserName) ? "" : currentUserName;
    }

    public boolean isAnonymousUser(String userName) {
        return ANONYMOUS_USER.equals(userName) ? true : false;
    }

    public boolean isAnonymousUser() {
        findUserName();
        return isAnonymousUser(currentUserName);
    }

    private void findUserName() {
        if (currentUserName == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null) {
                currentUserName = ANONYMOUS_USER;
                return;
            }
            currentUserName = auth.getName();
        }

    }

    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = false;

        for (GrantedAuthority authority : auth.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_admin")) {
                return true;
            }
        }

        return false;
    }



    public String getANONYMOUS_USER() {
        return ANONYMOUS_USER;
    }
}
