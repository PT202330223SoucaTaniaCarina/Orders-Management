package presentation.view.controller;

import presentation.view.*;

/**
 * Aceasta clasa controleaza ce fac butoanele din fereastra principala.
 * @author tania
 */
public class MainController {

    private MainScreen mainScreen;

    public MainController() {
        mainScreen = new MainScreen();
        initializeListeners();
    }

    public void initializeListeners(){
        mainScreen.getManageClientsButton().addActionListener(e->{
            new ClientController();
        });

        mainScreen.getManageProductsButton().addActionListener(e-> {
            new ProductController();
        });

        mainScreen.getManageOrdersButton().addActionListener(e->{
            new OrderController();
        });

        mainScreen.getExitButton().addActionListener(e->{
            System.exit(0);
        });
    }
}