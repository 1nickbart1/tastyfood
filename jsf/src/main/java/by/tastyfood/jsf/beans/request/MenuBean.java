package by.tastyfood.jsf.beans.request;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import by.tastyfood.dao.entity.Menu;
import by.tastyfood.dao.i.IMenuDao;
import by.tastyfood.model.menu.MenuLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * @author Nikolay
 */
@Component
@ManagedBean(name = "menuBean")
@RequestScoped
//@Scope("request")
public class MenuBean {


    @Autowired
    private MenuLoader menuLoader;

    private List<Menu> menu;

    public MenuBean() {

    }

    @PostConstruct
    public void init() {
        menu = menuLoader.getMenuItems();

    }

    public void setMenuLoader(MenuLoader menuLoader) {
        this.menuLoader = menuLoader;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }
}
