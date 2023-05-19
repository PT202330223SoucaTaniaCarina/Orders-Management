package presentation.view.controller;

import Bll.ClientBll;
import Model.Client;
import presentation.view.ClientScreen;
import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aceasta clasa controleaza ce fac butoanele din fereastra Client.
 * @author tania
 */
public class ClientController {

    private ClientScreen clientScreen;
    private ClientBll clientBLL;

    public ClientController() {
        clientScreen = new ClientScreen();
        clientBLL = new ClientBll();
        initializeListeners();
    }

    public void initializeListeners(){
        clientScreen.getCancelButton().addActionListener(e->{
            clientScreen.setVisible(false);
        });

        clientScreen.getViewButton().addActionListener(e->{
            List<Client> clients = clientBLL.getClientDAO().findAll();
            JTable clientsTable = clientBLL.getClientDAO().createTable(clients);
            clientScreen.addTable(clientsTable);
        });

        clientScreen.getInsertButton().addActionListener(e->{
            try {
                validateTextField(clientScreen.getNameTextField().getText());
                validateTextField(clientScreen.getEmailTextField().getText());
                validateTextField(clientScreen.getAddressTextField().getText());
                Client client = new Client(clientScreen.getNameTextField().getText(), clientScreen.getEmailTextField().getText(),
                        clientScreen.getAddressTextField().getText());
                clientBLL.insertClientById(client);
            } catch(NoSuchElementException exception) {
                clientScreen.displayErrorMessage(exception);
            }
        });

        clientScreen.getDeleteButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(clientScreen.getIdTextField().getText());
                clientBLL.deleteClientById(id);
            } catch (NumberFormatException exception) {
                clientScreen.displayInformationMessage("Please enter the ID.");
            } catch (NoSuchElementException exception) {
                clientScreen.displayErrorMessage(exception);
            }
        });

        clientScreen.getUpdateButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(clientScreen.getIdTextField().getText());
                validateTextField(clientScreen.getNameTextField().getText());
                validateTextField(clientScreen.getEmailTextField().getText());
                validateTextField(clientScreen.getAddressTextField().getText());
                Client client = new Client(clientScreen.getNameTextField().getText(), clientScreen.getEmailTextField().getText()
                        ,clientScreen.getAddressTextField().getText());
                clientBLL.updateClientById(client, id);
            } catch (NumberFormatException exception) {
                clientScreen.displayInformationMessage("Please enter the ID.");
            } catch (NoSuchElementException exception) {
                clientScreen.displayErrorMessage(exception);
            }
        });
    }

    public void validateTextField(String string) {
        if(string.compareTo("") == 0)
            throw new NoSuchElementException("Input cannot be empty.");
    }
}