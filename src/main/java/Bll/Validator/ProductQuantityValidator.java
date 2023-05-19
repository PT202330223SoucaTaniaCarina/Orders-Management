package Bll.Validator;

import Model.Product;

/**
 * Aceasta clasa verifica daca cantitatea produsului din baza de data este pozitiva sau negativa.
 * @author tania
 */

public class ProductQuantityValidator implements Validator<Product> {
    public void validate(Product p) {
        if (p.getInitQuantity()<0)
            throw new IllegalArgumentException("Negative quantity");
    }
}
