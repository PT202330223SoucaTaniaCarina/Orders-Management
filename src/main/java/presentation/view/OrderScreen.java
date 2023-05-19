package presentation.view;

import DAO.*;
import Model.Client;
import Model.Product;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * GUI  pentru managementul comenzilor.
 * @author tania
 */
public class OrderScreen extends JFrame {
    private JButton cancelButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton viewButton;
    private JButton viewClientsButton;
    private JButton viewProductsButton;
    private JTextField orderIDTextField;
    private JTextField quantityTextField;
    private JComboBox<Integer> jComboBoxClient;
    private JComboBox<Integer> jComboBoxProduct;
    private ClientDAO clientDAO = new ClientDAO();
    private ProductDAO productDAO = new ProductDAO();

    public OrderScreen(){
        this.setTitle("Orders Management");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setLocationRelativeTo(null);
        initializeForm(panel);
        this.setContentPane(panel);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(197,170,212));
    }

    public void initializeForm(JPanel panel) {
        JLabel idLabel = new JLabel("Order ID: ");
        idLabel.setBounds(20,20,70,30);
        panel.add(idLabel);

        orderIDTextField = new JTextField();
        orderIDTextField.setBounds(90, 20, 290, 30);
        orderIDTextField.setBackground(new Color(197,170,212));
        panel.add(orderIDTextField);

        JLabel clientIdLabel = new JLabel("Client ID: ");
        clientIdLabel.setBounds(20,60,70,30);
        panel.add(clientIdLabel);

        List<Client> clients = clientDAO.findAll();
        jComboBoxClient = new JComboBox<>();
        jComboBoxClient.setBounds(90, 60, 290, 30);
        jComboBoxClient.setBackground(new Color(197,170,212));
        for(Client c : clients){
            jComboBoxClient.addItem(c.getId());
        }
        panel.add(jComboBoxClient);

        JLabel productIDLabel = new JLabel("Product ID: ");
        productIDLabel.setBounds(20,100,70,30);
        panel.add(productIDLabel);

        List<Product> products = productDAO.findAll();
        jComboBoxProduct = new JComboBox<>();
        jComboBoxProduct.setBounds(90, 100, 290, 30);
        jComboBoxProduct.setBackground(new Color(197,170,212));
        for(Product p : products){
            jComboBoxProduct.addItem(p.getId());
        }
        panel.add(jComboBoxProduct);

        JLabel quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setBounds(20,140,70,30);
        panel.add(quantityLabel);

        quantityTextField = new JTextField();
        quantityTextField.setBounds(90, 140, 290, 30);
        quantityTextField.setBackground(new Color(197,170,212));
        panel.add(quantityTextField);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(240, 420, 210, 30);
        panel.add(cancelButton);

        insertButton = new JButton("Insert Order");
        insertButton.setBounds(470, 20, 130, 30);
        panel.add(insertButton);

        updateButton = new JButton("Update Order");
        updateButton.setBounds(470, 60, 130, 30);
        panel.add(updateButton);

        deleteButton = new JButton("Delete Order");
        deleteButton.setBounds(470, 100, 130, 30);
        panel.add(deleteButton);

        viewButton = new JButton("View Orders");
        viewButton.setBounds(470, 140, 130, 30);
        panel.add(viewButton);

        viewClientsButton = new JButton("View Clients");
        viewClientsButton.setBounds(90, 180, 130, 30);
        panel.add(viewClientsButton);

        viewProductsButton = new JButton("View Products");
        viewProductsButton.setBounds(250, 180, 130, 30);
        panel.add(viewProductsButton);
    }

    public void addTable(JTable jTable) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 230, 650, 170);
        scrollPane.setBackground(new Color(197,170,212));

        jTable.setVisible(true);
        jTable.setEnabled(true);
        scrollPane.setViewportView(jTable);
        this.getContentPane().add(scrollPane);
    }

    public void displayErrorMessage(NoSuchElementException exception) {
        if (exception != null) {
            String message = exception.getMessage();
            UIManager UI = new UIManager();
            UI.put("OptionPane.background", new Color(197,170,212));
            UI.put("Panel.background", new Color(197,170,212));
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayInformationMessage(String message) {
        if (!message.isEmpty()) {
            UIManager UI = new UIManager();
            UI.put("OptionPane.background", new Color(197,170,212));
            UI.put("Panel.background", new Color(197,170,212));
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
    public JButton getInsertButton() {
        return insertButton;
    }
    public JButton getDeleteButton() {
        return deleteButton;
    }
    public JButton getUpdateButton() {
        return updateButton;
    }
    public JButton getViewButton() {
        return viewButton;
    }
    public JButton getViewClientsButton() {
        return viewClientsButton;
    }
    public JButton getViewProductsButton() {
        return viewProductsButton;
    }
    public JTextField getOrderIDTextField() {
        return orderIDTextField;
    }
    public JTextField getQuantityTextField() {
        return quantityTextField;
    }
    public JComboBox<Integer> getjComboBoxClient() {
        return jComboBoxClient;
    }
    public JComboBox<Integer> getjComboBoxProduct() {
        return jComboBoxProduct;
    }
    public ClientDAO getClientDAO() {
        return clientDAO;
    }
    public ProductDAO getProductDAO() {
        return productDAO;
    }
    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
    public void setInsertButton(JButton insertButton) {
        this.insertButton = insertButton;
    }
    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }
    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }
    public void setViewButton(JButton viewButton) {
        this.viewButton = viewButton;
    }
    public void setViewClientsButton(JButton viewClientsButton) {
        this.viewClientsButton = viewClientsButton;
    }
    public void setViewProductsButton(JButton viewProductsButton) {
        this.viewProductsButton = viewProductsButton;
    }
    public void setOrderIDTextField(JTextField orderIDTextField) {
        this.orderIDTextField = orderIDTextField;
    }
    public void setQuantityTextField(JTextField quantityTextField) {
        this.quantityTextField = quantityTextField;
    }
    public void setjComboBoxClient(JComboBox<Integer> jComboBoxClient) {
        this.jComboBoxClient = jComboBoxClient;
    }
    public void setjComboBoxProduct(JComboBox<Integer> jComboBoxProduct) {
        this.jComboBoxProduct = jComboBoxProduct;
    }
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}