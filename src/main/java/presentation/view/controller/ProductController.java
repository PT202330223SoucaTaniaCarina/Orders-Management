package presentation.view.controller;

import Bll.ProductBll;
import Model.Product;
import presentation.view.ProductScreen;
import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aceasta clasa controleaza ce fac butoanele din fereastra Product.
 * @author tania
 */
public class ProductController {

    private ProductScreen productScreen;
    private ProductBll productBLL;

    public ProductController(){
        productScreen = new ProductScreen();
        productBLL = new ProductBll();
        initializeListeners();
    }

    public void initializeListeners() {
        productScreen.getCancelButton().addActionListener(e->{
            productScreen.setVisible(false);
        });

        productScreen.getViewButton().addActionListener(e->{
            List<Product> products = productBLL.getProductDAO().findAll();
            JTable productsTable = productBLL.getProductDAO().createTable(products);
            productScreen.addTable(productsTable);
        });

        productScreen.getInsertButton().addActionListener(e->{
            try {
                validateTextField(productScreen.getNameTextField().getText());
                validateTextField(productScreen.getStockTextField().getText());
                int stock = Integer.parseInt(productScreen.getStockTextField().getText());
                int price = Integer.parseInt(productScreen.getPriceTextField().getText());
                productBLL.insertProduct(new Product( productScreen.getNameTextField().getText(), stock, price));
            }  catch(NumberFormatException exception) {
                productScreen.displayInformationMessage("Please enter the stock.");
            }  catch(NoSuchElementException exception) {
                productScreen.displayErrorMessage(exception);
            }
        });

        productScreen.getDeleteButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(productScreen.getIdTextField().getText());
                productBLL.deleteProductById(id);
            } catch (NumberFormatException exception) {
                productScreen.displayInformationMessage("Please enter the ID.");
            } catch (NoSuchElementException exception) {
                productScreen.displayErrorMessage(exception);
            }
        });

        productScreen.getUpdateButton().addActionListener(e->{
            try {
                validateTextField(productScreen.getIdTextField().getText());
                int id = Integer.parseInt(productScreen.getIdTextField().getText());
                validateTextField(productScreen.getNameTextField().getText());
                validateTextField(productScreen.getStockTextField().getText());
                int stock = Integer.parseInt(productScreen.getStockTextField().getText());
                int price = Integer.parseInt(productScreen.getPriceTextField().getText());
                productBLL.updateProduct(new Product( productScreen.getNameTextField().getText(), stock, price), id);
            }catch (NumberFormatException exception) {
                productScreen.displayInformationMessage("Please complete all the fields!");
            } catch (NoSuchElementException exception) {
                productScreen.displayErrorMessage(exception);
            }
        });
    }

    public void validateTextField(String string) {
        if(string.compareTo("") == 0)
            throw new NoSuchElementException("Input cannot be empty.");
    }
}