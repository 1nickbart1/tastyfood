/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.Menu;
import by.tastyfood.dao.i.IMenuDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nikolay
 */
@Service
public class MenuDaoImpl  extends AbstractDaoImpl<Menu, Long> implements IMenuDao{

    private static final Logger log = Logger.getLogger(MenuDaoImpl.class);

    public MenuDaoImpl() {

      super(Menu.class);
    }

}
