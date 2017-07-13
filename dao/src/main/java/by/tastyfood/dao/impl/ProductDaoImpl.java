package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.Product;
import by.tastyfood.dao.i.IProductDao;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 05.06.2017.
 */
@Service
public class ProductDaoImpl extends AbstractDaoImpl<Product, Long> implements IProductDao {

    public ProductDaoImpl(){
        super(Product.class);
    }
}
