/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.tastyfood.dao.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Nikolay
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable,  Comparable<Menu> {
    private static final long serialVersionUID = 1L;


    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "menu", nullable = false, length = 50)
	private String menu;

	@Column(name = "url", nullable = false)
	private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu1 = (Menu) o;

        if (id != null ? !id.equals(menu1.id) : menu1.id != null) return false;
        return menu != null ? menu.equals(menu1.menu) : menu1.menu == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (menu != null ? menu.hashCode() : 0);
        return result;
    }
    public int compareTo(Menu menuForCompare) {
        if(menuForCompare.getId() > this.getId()){
            return -1;
        }
        if(menuForCompare.getId() < this.getId()){
            return 1;
        }

        return 0;
    }
}
