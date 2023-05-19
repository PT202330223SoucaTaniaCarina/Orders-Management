package Bll.Validator;

import Bll.ProductBll;
import Model.Product;

/**
 * Se verifica daca numele produsului se afla sau nu in baza de date.
 * @author tania
 */

public class ProductNameValidator implements Validator<Product>{
    public void validate(Product t) {
        ProductBll productBLL = new ProductBll();
        Product product = productBLL.findProductByName(t.getName());
        if(product!=null)
            throw new IllegalArgumentException("Error: This product exists in data base.");
    }
}
