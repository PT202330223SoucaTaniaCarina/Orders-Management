package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.util.NoSuchElementException;

/**
 * GUI  pentru managementul produselor.
 * @author tania
 */
public class ProductScreen extends JFrame{

    private JButton cancelButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton viewButton;
    private JTextField idTextField;
    private JTextField priceTextField;
    private JTextField nameTextField;
    private JTextField stockTextField;

    public ProductScreen(){
        this.setTitle("Products");
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
        JLabel idLabel = new JLabel("ID: ");
        idLabel.setBounds(20,20,70,30);
        panel.add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(80, 20, 300, 30);
        idTextField.setBackground(new Color(197,170,212));
        panel.add(idTextField);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20,60,70,30);
        panel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(80, 60, 300, 30);
        nameTextField.setBackground(new Color(197,170,212));
        panel.add(nameTextField);

        JLabel stockLabel = new JLabel("Stock: ");
        stockLabel.setBounds(20,100,70,30);
        panel.add(stockLabel);

        stockTextField = new JTextField();
        stockTextField.setBounds(80, 100, 300, 30);
        stockTextField.setBackground(new Color(197,170,212));
        panel.add(stockTextField);

        JLabel priceLabel = new JLabel("Price: ");
        priceLabel.setBounds(20,140,70,30);
        panel.add(priceLabel);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(240, 420, 210, 30);
        panel.add(cancelButton);

        priceTextField = new JTextField();
        priceTextField.setBounds(80, 140, 300, 30);
        priceTextField.setBackground(new Color(197,170,212));
        panel.add(priceTextField);

        insertButton = new JButton("Insert Product");
        insertButton.setBounds(470, 20, 130, 30);
        panel.add(insertButton);

        updateButton = new JButton("Update Product");
        updateButton.setBounds(470, 60, 130, 30);
        panel.add(updateButton);

        deleteButton = new JButton("Delete Product");
        deleteButton.setBounds(470, 100, 130, 30);
        panel.add(deleteButton);

        viewButton = new JButton("View Products");
        viewButton.setBounds(470, 140, 130, 30);
        panel.add(viewButton);
    }

    public void addTable(JTable productsTable) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 230, 650, 170);
        scrollPane.setBackground(new Color(197,170,212));

        productsTable.setVisible(true);
        productsTable.setEnabled(true);
        scrollPane.setViewportView(productsTable);
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

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public void setPriceTextField(JTextField priceTextField) {
        this.priceTextField = priceTextField;
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
    public JTextField getIdTextField() {
        return idTextField;
    }
    public JTextField getNameTextField() {
        return nameTextField;
    }
    public JTextField getStockTextField() {
        return stockTextField;
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
    public void setIdTextField(JTextField idTextField) {
        this.idTextField = idTextField;
    }
    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }
    public void setStockTextField(JTextField stockTextField) {
        this.stockTextField = stockTextField;
    }
}