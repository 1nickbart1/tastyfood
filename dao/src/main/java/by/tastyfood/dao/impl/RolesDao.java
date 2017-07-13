package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.Roles;
import by.tastyfood.dao.i.IRolesDao;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 19.06.2017.
 */
@Service
public class RolesDao extends AbstractDaoImpl<Roles, Integer> implements IRolesDao {

    RolesDao(){
        super(Roles.class);
    }
}
