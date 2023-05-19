package Bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import Bll.Validator.ProductPriceValidator;
import Bll.Validator.ProductQuantityValidator;
import Bll.Validator.Validator;
import DAO.ProductDAO;
import Model.Product;

/**
 * Clasa Business --> pentru produse
 * @author tania
 */

public class ProductBll {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductBll() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductPriceValidator());
        validators.add(new ProductQuantityValidator());
        productDAO = new ProductDAO();
    }

    public List<Product> findAllProducts() {
        List<Product> list = productDAO.findAll();
        if(list==null) {
            throw new NoSuchElementException("Error:Doesn't exist products in db");
        }
        return list;
    }

    public Product findProductByName(String name){
        Product prod = productDAO.findByName(name);
        if(prod == null)
            throw new NoSuchElementException("The product named '"+name+"' doesn't exist!");
        return prod;
    }

    public Product findProductById(int id) {
        Product prod = productDAO.findById(id);
        if (prod == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return prod;
    }

    public Product insertProduct(Product product) {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return productDAO.insert(product);
    }

    public boolean deleteProductById(int id) {
        Product p = productDAO.findById(id);
        if (p == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return productDAO.deleteById(id);
    }

    public void updateProduct(Product product, int id){
        Product existentProduct = productDAO.findById(id);
        if (existentProduct == null)
            throw new NoSuchElementException("Product with id " + id + " not existent.");
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        product.setId(id);
        productDAO.update(product);
    }

    public List<Validator<Product>> getValidators() {
        return validators;
    }
    public void setValidators(List<Validator<Product>> validators) {
        this.validators = validators;
    }
    public ProductDAO getProductDAO() {
        return productDAO;
    }
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

}

