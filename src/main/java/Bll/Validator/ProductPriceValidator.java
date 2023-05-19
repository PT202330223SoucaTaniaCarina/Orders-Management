package Bll.Validator;

import Model.Product;

/**
 * Se verifica daca pretul produsului din baza de data este pozitiv sau negativ.
 * @author tania
 */

public class ProductPriceValidator implements Validator<Product> {
    public void validate(Product t) {
        if (t.getPrice()<0.0)
            throw new IllegalArgumentException("Error: negative price!");
    }
}
