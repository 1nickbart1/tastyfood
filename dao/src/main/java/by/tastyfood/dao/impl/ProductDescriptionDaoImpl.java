package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.ProductDescription;
import by.tastyfood.dao.i.IProductDescriptionDao;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 08.06.2017.
 */
@Service
public class ProductDescriptionDaoImpl  extends AbstractDaoImpl<ProductDescription, Long> implements IProductDescriptionDao{

    public ProductDescriptionDaoImpl(){
        super(ProductDescription.class);
    }
}
