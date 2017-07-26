package by.tastyfood.model.users;

import by.tastyfood.dao.entity.Roles;
import by.tastyfood.dao.entity.Users;
import by.tastyfood.dao.i.IUsersDao;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 19.06.2017.
 */
@Service
public class UserService {
    private  final int DEFAULT_USER_ROLE_ID = 1;

    @Autowired
    private IUsersDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(Users user){
        user. setPassword(passwordEncoder.encode(user.getPassword()));

         if(user.getId() != null){
            userDao.update(user);
        }else{
             Roles newUserRole = new Roles();
             newUserRole.setId(DEFAULT_USER_ROLE_ID);
             user.setRole(newUserRole);
            userDao.add(user);
        }

    }

    public Users findById(int id) {
        return userDao.findById(id);
    }

    public Users findByUsername(String username) {

        System.out.println("start findByUsername");
        System.out.println("username ="+username);
        return userDao.findByUsername(username);
    }
}
