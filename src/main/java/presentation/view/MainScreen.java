package presentation.view;

import javax.swing.*;
import java.awt.*;

/**
 * GUI  pentru managementul Main.
 * @author tania
 */

public class MainScreen extends JFrame {

    private JButton manageClientsButton;
    private JButton manageProductsButton;
    private JButton manageOrdersButton;
    private JButton exitButton;

    public MainScreen() {
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

    private void initializeForm(JPanel panel) {
        JLabel titleLabel = new JLabel("ORDERS MANAGEMENT");
        titleLabel.setBounds(170, 40, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        panel.add(titleLabel);

        manageClientsButton = new JButton("Manage Clients");
        manageClientsButton.setBounds(250, 100, 210, 30);
        panel.add(manageClientsButton);

        manageProductsButton = new JButton("Manage Products");
        manageProductsButton.setBounds(250, 160, 210, 30);
        panel.add(manageProductsButton);

        manageOrdersButton = new JButton("Manage Orders");
        manageOrdersButton.setBounds(250, 220, 210, 30);
        panel.add(manageOrdersButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(250, 400, 210, 30);
        panel.add(exitButton);
    }
    public JButton getManageClientsButton() {
        return manageClientsButton;
    }
    public JButton getManageProductsButton() {
        return manageProductsButton;
    }
    public JButton getManageOrdersButton() {
        return manageOrdersButton;
    }
    public JButton getExitButton() {
        return exitButton;
    }
    public void setManageClientsButton(JButton manageClientsButton) {
        this.manageClientsButton = manageClientsButton;
    }
    public void setManageProductsButton(JButton manageProductsButton) {
        this.manageProductsButton = manageProductsButton;
    }
    public void setManageOrdersButton(JButton manageOrdersButton) {
        this.manageOrdersButton = manageOrdersButton;
    }
    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }
}