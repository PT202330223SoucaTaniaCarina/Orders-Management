package Bll;

import Bll.Validator.OrderClientValidator;
import Bll.Validator.OrderProductValidator;
import Bll.Validator.OrderQuantityValidator;
import Bll.Validator.Validator;
import DAO.ClientDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Client;
import Model.Orders;
import Model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa Business --> pentru comenzi
 * @author tania
 */

public class OrderBll {
    private List<Validator<Orders>> validators;
    private OrderDAO orderDAO;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;

    public OrderBll() {
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new OrderClientValidator());
        validators.add(new OrderProductValidator());
        validators.add(new OrderQuantityValidator());
        orderDAO = new OrderDAO();
        clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
    }

    public Orders findOrderById(int id) {
        Orders ord = orderDAO.findById(id);
        if (ord == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return ord;
    }

    public void deleteOrder(int id) {
        Orders order = orderDAO.findById(id);
        if (order == null)
            throw new NoSuchElementException("Order with id " + id + " not existent.");
        Product product = productDAO.findById(order.getProductID());
        product.setInitQuantity(product.getInitQuantity() + order.getQuantity());
        productDAO.update(product);
        orderDAO.deleteById(order.getId());
    }

    public void updateOrder(Orders order, int id){
        Orders existentOrder = orderDAO.findById(id);
        if (existentOrder == null)
            throw new NoSuchElementException("Order with id " + id + " not existent.");
        order.setId(id);
        Product product = productDAO.findById(order.getProductID());
        if (product.getInitQuantity() + existentOrder.getQuantity() < order.getQuantity()) {
            throw new NoSuchElementException("Stock for product " + product.getName() + " is not sufficient.");
        }
        product.setInitQuantity(product.getInitQuantity() + existentOrder.getQuantity() - order.getQuantity());
        productDAO.update(product);
        orderDAO.update(order);
    }

    public Orders insertOrder(Orders order) {
        Product product = productDAO.findById(order.getProductID());
        Client client = clientDAO.findById(order.getClientID());
        if (product.getInitQuantity() < order.getQuantity()) {
            throw new NoSuchElementException("Stock for product "+product.getName()+" is not sufficient.");
        }
        product.setInitQuantity(product.getInitQuantity() - order.getQuantity());
        productDAO.update(product);
        ArrayList<Orders> orders = (ArrayList<Orders>) orderDAO.findAll();
        Orders lastOrder = orders.get(orders.size()-1);
        order.setId(lastOrder.getId()+1);
        return orderDAO.insert(order);
    }

    public List<Validator<Orders>> getValidators() {
        return validators;
    }
    public void setValidators(List<Validator<Orders>> validators) {
        this.validators = validators;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

}

