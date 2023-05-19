package Bll.Validator;

import Bll.ProductBll;
import Model.Orders;
import Model.Product;


/**
 * Se verifica daca produsul dorit de client exista sau nu in baza de date.
 * @author user
 */

public class OrderProductValidator implements Validator<Orders> {
    @Override
    public void validate(Orders t) {
        ProductBll productBLL=new ProductBll();
        Product product =productBLL.findProductById(t.getProductID());
        if(product==null)
            throw new IllegalArgumentException("Product with id = " + t.getProductID() + " was not found!");
        else
            System.out.println("Order taken!");
    }

}
