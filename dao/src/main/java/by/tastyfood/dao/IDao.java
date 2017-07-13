/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.tastyfood.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Nikolay
 * @param <T>
 * @param <PK>
 */
public interface IDao <T, PK extends Serializable> {
    List<T> getAll() throws DaoException;
    T get(PK id) throws DaoException;
    PK add(T object) throws DaoException;
    void update(T object) throws DaoException;
    void delete(T object) throws DaoException;
}
