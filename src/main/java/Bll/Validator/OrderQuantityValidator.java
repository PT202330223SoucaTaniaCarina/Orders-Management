package Bll.Validator;

import Bll.ProductBll;
import Model.Orders;
import Model.Product;

/**
 * Se verifica daca cantitatea dorita de client este disponibila in baza de date.
 * @author tania
 */

public class OrderQuantityValidator implements  Validator<Orders> {
    @Override
    public void validate(Orders t) {
        if (t.getQuantity()<0) {
            throw new IllegalArgumentException("Error:negative quantity");
        } else {
            ProductBll productBLL = new ProductBll();
            Product product = productBLL.findProductById(t.getProductID());
            if(product==null) {
                throw new IllegalArgumentException("Product with id = " + t.getProductID() + " was not found!");
            } else {
                if(t.getQuantity()>product.getInitQuantity())
                    throw new IllegalArgumentException("Error:doesn't exist this quantity");
                else
                    System.out.println("Order taken!");
            }
        }
    }

}
