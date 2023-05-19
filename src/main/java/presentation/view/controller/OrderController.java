package presentation.view.controller;

import Bll.OrderBll;
import Model.*;
import presentation.view.OrderScreen;
import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aceasta clasa controleaza ce fac butoanele din fereastra Order.
 * @author tania
 */
public class OrderController {

    private OrderScreen orderScreen;
    private OrderBll orderBLL;

    public OrderController(){
        orderScreen = new OrderScreen();
        orderBLL = new OrderBll();
        initializeListeners();
    }

    public void initializeListeners() {
        orderScreen.getCancelButton().addActionListener(e->{
            orderScreen.setVisible(false);
        });

        orderScreen.getViewButton().addActionListener(e->{
            List<Orders> orders = orderBLL.getOrderDAO().findAll();
            JTable ordersTable = orderBLL.getOrderDAO().createTable(orders);
            orderScreen.addTable(ordersTable);
        });

        orderScreen.getViewClientsButton().addActionListener(e->{
            List<Client> clients = orderScreen.getClientDAO().findAll();
            JTable clientsTable = orderScreen.getClientDAO().createTable(clients);
            orderScreen.addTable(clientsTable);
        });

        orderScreen.getViewProductsButton().addActionListener(e->{
            List<Product> products = orderScreen.getProductDAO().findAll();
            JTable productsTable = orderScreen.getProductDAO().createTable(products);
            orderScreen.addTable(productsTable);
        });

        orderScreen.getInsertButton().addActionListener(e->{
            try{
                validateTextField(orderScreen.getQuantityTextField().getText());
                int quantity = Integer.parseInt(orderScreen.getQuantityTextField().getText());
                orderBLL.insertOrder(new Orders(Integer.parseInt(orderScreen.getjComboBoxClient().getSelectedItem().toString()),
                        Integer.parseInt(orderScreen.getjComboBoxProduct().getSelectedItem().toString()), quantity));
            } catch (NumberFormatException exception) {
                orderScreen.displayInformationMessage("Complete all the fields!");
            } catch (NoSuchElementException exception) {
                orderScreen.displayErrorMessage(exception);
            }
        });

        orderScreen.getDeleteButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(orderScreen.getOrderIDTextField().getText());
                orderBLL.deleteOrder(id);
            } catch (NumberFormatException exception) {
                orderScreen.displayInformationMessage("Please enther the ID.");
            } catch (NoSuchElementException exception) {
                orderScreen.displayErrorMessage(exception);
            }
        });

        orderScreen.getUpdateButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(orderScreen.getOrderIDTextField().getText());
                validateTextField(orderScreen.getOrderIDTextField().getText());
                validateTextField(orderScreen.getQuantityTextField().getText());
                int quantity = Integer.parseInt(orderScreen.getQuantityTextField().getText());
                orderBLL.updateOrder(new Orders(Integer.parseInt(orderScreen.getjComboBoxClient().getSelectedItem().toString()),
                        Integer.parseInt(orderScreen.getjComboBoxProduct().getSelectedItem().toString()), quantity), id);
            } catch (NumberFormatException exception) {
                orderScreen.displayInformationMessage("Please enther the ID and the quantity.");
            } catch (NoSuchElementException exception) {
                orderScreen.displayErrorMessage(exception);
            }
        });
    }

    public void validateTextField(String string) {
        if(string.compareTo("") == 0)
            throw new NoSuchElementException("Input cannot be empty.");
    }
}