package by.tastyfood.model.users;

import by.tastyfood.dao.entity.Users;
import by.tastyfood.recepies.response.LoginCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Nikolay on 25.07.2017.
 */
@Service
public class UserControllerHandler {
    @Autowired
    UserService userService;

    public LoginCheckResponse isLoginExist(String login) {
        LoginCheckResponse loginCheckResponse = new LoginCheckResponse();
        if (login != null) {
            Users existingUser = userService.findByUsername(login);
            if (existingUser != null) {
                loginCheckResponse.setValid(false);
            }
        }

        return loginCheckResponse;
    }
}