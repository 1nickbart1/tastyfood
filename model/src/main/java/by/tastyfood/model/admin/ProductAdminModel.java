package by.tastyfood.model.admin;

import by.tastyfood.dao.entity.Product;
import by.tastyfood.dao.entity.ProductDescription;
import by.tastyfood.dao.entity.ProductUnit;
import by.tastyfood.dao.i.IProductDao;
import by.tastyfood.dao.i.IProductDescriptionDao;
import by.tastyfood.dao.i.IProductUnitDao;
import by.tastyfood.dao.impl.ProductUnitDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nikolay on 08.06.2017.
 */
@Service
public class ProductAdminModel {

    @Autowired
    private IProductUnitDao productUnitDao;
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IProductDescriptionDao productDescriptionDao;

    public List<ProductUnit> getAvaliableProductUnits(){

        System.out.println("start getAvaliableProductUnits. productUnitDao = "+ productUnitDao);

        System.out.println("all =" + productUnitDao.getAll());
        System.out.println("sizeee =" + productUnitDao.getAll().size());
        return  productUnitDao.getAll();

    }

    public Product getProduct(Long id){
        return productDao.get(id);
    }

    public Long saveOrUpdateProduct(Product product){

        if(product.getId() != null){
            productDao.update(product);
            productDescriptionDao.update(product.getProductDescription());
            return product.getId();
        }
        Long productId = null;
            Long descId = productDescriptionDao.add(product.getProductDescription());

        productId =  productDao.add(product);
         return productId;
    }

    public List<Product> getAllproducts(){
        return productDao.getAll();
    }

    public void deleteProduct(Product product){
        System.out.println("start deleting product");
        productDao.delete(product);
        productDescriptionDao.delete(product.getProductDescription());

    }
}
