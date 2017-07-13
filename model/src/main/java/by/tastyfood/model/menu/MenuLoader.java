/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.tastyfood.model.menu;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.tastyfood.dao.entity.Menu;

import by.tastyfood.dao.i.IMenuDao;
import by.tastyfood.dao.impl.MenuDaoImpl;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author Nikolay
 */
@Service
public class MenuLoader {

     private static final Logger log = Logger.getLogger(MenuLoader.class);
    
    private List<Menu> menuItems;
    @Autowired
    private IMenuDao menuDao;

    public MenuLoader(){
        log.debug("start MenuLoader ");
//         menuItems = companyDAO.getAll();
    }

    public List<Menu> getMenuItems() {
        menuItems = menuDao.getAll();
        Collections.sort(menuItems);

        return menuItems;
    }





}
