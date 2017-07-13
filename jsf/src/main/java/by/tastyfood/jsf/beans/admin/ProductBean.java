package by.tastyfood.jsf.beans.admin;

import by.tastyfood.dao.entity.Product;
import by.tastyfood.dao.entity.ProductDescription;
import by.tastyfood.dao.entity.ProductUnit;
import by.tastyfood.enums.ParamNamesEnum;
import by.tastyfood.jsf.utils.JsfUtils;
import by.tastyfood.model.admin.ProductAdminModel;
import by.tastyfood.utils.LongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Nikolay on 08.06.2017.
 */
@Component
@ManagedBean(name = "productBean")
@ViewScoped
@PropertySource(value = "classpath:messages.properties")
@Scope("view")
public class ProductBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ProductUnit> avaliableUnits;

    @Autowired
    private ProductAdminModel adminModel;
    private Product curProduct;
    private List<Product> allProducts;

    private ProductDescription productDescription;
    private boolean editMode = false;

    @Resource
    private Environment environment;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();

        String idFromReq = params.get(ParamNamesEnum.PRODUCT_ID.getParam());

        Long productId = LongUtils.getLongFromString(idFromReq);
        avaliableUnits = adminModel.getAvaliableProductUnits();
        allProducts = adminModel.getAllproducts();
        if (productId != null) {

            curProduct = adminModel.getProduct(productId);
            productDescription = curProduct.getProductDescription();
            return;

        }


        curProduct = new Product();
        curProduct.setProductUnit(new ProductUnit());
        productDescription = new ProductDescription();


    }


    public List<ProductUnit> getAvaliableUnits() {
        return avaliableUnits;
    }

    public void setAvaliableUnits(List<ProductUnit> avaliableUnits) {
        this.avaliableUnits = avaliableUnits;
    }


    public void socClicked(javax.faces.event.AjaxBehaviorEvent actionEvent) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", environment.getRequiredProperty("save.succes")));

    }

    public void saveProduct(ActionEvent actionEvent) {

        System.out.println("start save curProduct");

        curProduct.setProductDescription(productDescription);
        Long productId = adminModel.saveOrUpdateProduct(curProduct);

        System.out.println("new id = " + productId);

//        FacesContext context = FacesContext.getCurrentInstance();
        if (productId != null) {
            curProduct.setId(productId);

//            context.addMessage(null, new FacesMessage("Successful",  environment.getRequiredProperty("save.succes")));
            JsfUtils.showMsg(environment.getRequiredProperty("save.succes"));
            editMode = false;
            System.out.println("currEditMode = " + editMode);
        } else {
//            context.addMessage(null, new FacesMessage("Error",  environment.getRequiredProperty("save.err")));
            JsfUtils.showMsg("Error", environment.getRequiredProperty("save.err"));
        }


    }

    public void editProduct(AjaxBehaviorEvent actionEvent) {

        System.out.println("start edit listener");

        Object prodFromEvent = actionEvent.getComponent().getAttributes().get("product");


        if (prodFromEvent != null) {
            curProduct = (Product) prodFromEvent;
            productDescription = curProduct.getProductDescription();

        }
        editMode = true;
        System.out.println("end edit listener");
    }

    public void deleteProduct(ActionEvent actionEvent) {
        System.out.println("ajax deleteProduct");
        Object prodFromEvent = actionEvent.getComponent().getAttributes().get("product");

        if (prodFromEvent != null) {

            adminModel.deleteProduct((Product) prodFromEvent);
            allProducts.remove((Product) prodFromEvent);

            curProduct = new Product();
            curProduct.setProductUnit(new ProductUnit());
            productDescription = new ProductDescription();


        }

    }

    private boolean test = true;

    public void testListener(AjaxBehaviorEvent actionEvent) {
        System.out.println("start test listener");
//        editMode = true;
//        allProducts = adminModel.getAllproducts();
        System.out.println("test; val = " + test);
        test = !test;
        System.out.println("test; val = " + test);
    }

    public boolean isTest() {
        System.out.println("start is test; val = " + test);
        return test;
    }

    public void setTest(boolean test) {
        System.out.println("start set test; val = " + test);
        this.test = test;
    }

    public Product getCurProduct() {
        return curProduct;
    }

    public void setCurProduct(Product curProduct) {
        this.curProduct = curProduct;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }
}
