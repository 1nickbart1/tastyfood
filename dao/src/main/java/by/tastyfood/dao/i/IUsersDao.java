package by.tastyfood.dao.i;

import by.tastyfood.dao.IDao;
import by.tastyfood.dao.entity.Users;

/**
 * Created by Nikolay on 19.06.2017.
 */
public interface IUsersDao extends IDao<Users, Integer> {
    public Users findById(int id);
    public Users findByUsername(String username);
}
