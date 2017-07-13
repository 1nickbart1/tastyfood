package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.ProductUnit;
import by.tastyfood.dao.i.IProductDao;
import by.tastyfood.dao.i.IProductUnitDao;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 08.06.2017.
 */
@Service
public class ProductUnitDaoImpl extends AbstractDaoImpl<ProductUnit, Integer> implements IProductUnitDao {

    public ProductUnitDaoImpl(){
        super(ProductUnit.class);
    }
}
