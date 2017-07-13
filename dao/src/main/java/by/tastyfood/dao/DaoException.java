/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.tastyfood.dao;

import java.io.Serializable;

/**
 *
 * @author Nikolay
 */
class DaoException extends RuntimeException  implements Serializable{
    private static final long serialVersionUID = 1L;

	public DaoException(String message, Throwable throwable) {
		super( message, throwable);
               
	}
	public DaoException(String message) {
		super( message);
               
	}


}
