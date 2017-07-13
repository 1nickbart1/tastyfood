package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.RecipiesProduct;
import by.tastyfood.dao.entity.Users;
import by.tastyfood.dao.i.IUsersDao;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolay on 19.06.2017.
 */
@Service
public class UsersDao extends AbstractDaoImpl<Users, Integer> implements IUsersDao {

    UsersDao(){
        super(Users.class);
    }

    public Users findById(int id) {
        Query query =getSession().createQuery("from Users  users where users.id = :id")
                .setLong("id", id);
        Users user = (Users) query.uniqueResult();

        return user;
    }


    public Users findByUsername(String username) {
        Query query =getSession().createQuery("from Users  users where users.username = :username")
                .setString("username", username);
        Users user = (Users) query.uniqueResult();

        return user;

    }
}
