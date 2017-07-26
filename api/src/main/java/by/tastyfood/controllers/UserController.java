package by.tastyfood.controllers;

import by.tastyfood.dao.entity.Users;

import by.tastyfood.model.users.UserControllerHandler;
import by.tastyfood.recepies.response.LoginCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Nikolay on 25.07.2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserControllerHandler userControllerHandler;

    @RequestMapping(value = "/isLoginExist", method = RequestMethod.GET)
    @ResponseBody
    public LoginCheckResponse isLoginExist(@RequestParam String login) {
        return userControllerHandler.isLoginExist(login);


    }
}
