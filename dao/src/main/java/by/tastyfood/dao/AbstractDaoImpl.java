/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.tastyfood.dao;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nikolay
 */
@Transactional
public abstract class AbstractDaoImpl<T, PK extends Serializable> implements IDao<T, PK> {


        private static final Logger log = Logger.getLogger(AbstractDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Class<T> clazz;

	public AbstractDaoImpl() {
		super();
	}

	public AbstractDaoImpl(Class<T> clazz) {

		this.clazz = clazz;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

		public List<T> getAll() throws DaoException {

            log.debug("start getAll for"+ clazz);

            List<T> list = null;
		try {
			list = getSession().createCriteria(clazz).addOrder(Order.asc("id"))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			log.debug("list size = "+list.size() );
			return list;
		} catch (HibernateException e) {
			throw new DaoException( "DBA_getAll", e);
		}
	}


	public T get(PK id) throws DaoException {

		try {
			T object = (T) getSession().get(clazz, id);
			return object;
		} catch (HibernateException e) {
			throw new DaoException( "get", e);
		}
	}


	public PK add(T object) throws  DaoException {

		PK id = null;
		try {
			id = (PK) getSession().save(object);


			return (PK) id;
		} catch (HibernateException e) {
			throw new DaoException( "add", e);
		}
	}


	public void update(T object) throws  DaoException {

		try {
			getSession().update(object);
		} catch (HibernateException e) {
			throw new DaoException( "update", e);
		}
	}


	public void delete(T object) throws  DaoException {

		try {
			getSession().delete(object);
		} catch (HibernateException e) {
			throw new DaoException( "delete", e);
		}
	}

	protected Query getQuery(String hql) throws  DaoException {
		try {
			Query query = getSession().createQuery(hql);
			return query;
		} catch (HibernateException e) {
			throw new DaoException( "getQuery", e);
		}
	}
}