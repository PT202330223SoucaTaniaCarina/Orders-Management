package Bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import Bll.Validator.ClientEmailValidator;
import Bll.Validator.Validator;
import DAO.ClientDAO;
import Model.Client;

/**
 * Clasa Business --> pentru clienti
 * @author tania
 */

public class ClientBll {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBll() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientEmailValidator());
        clientDAO = new ClientDAO();
    }

    public List<Client> findAllClients() {
        List<Client> list = clientDAO.findAll();
        if(list==null) {
            throw new NoSuchElementException("Error:Doesn't exist clients in db");
        }
        return list;
    }

    public Client findClientById(int id) {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return cl;
    }

    public void insertClientById(Client client) {
        Client cl = clientDAO.insert(client);
        if (cl == null) {
            throw new NoSuchElementException("The client cannot be inserted");
        }
    }

    public boolean deleteClientById(int id) {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return clientDAO.deleteById(id);
    }

    public void updateClientById(Client client, int id){
        Client existentClient = clientDAO.findById(id);
        if (existentClient == null)
            throw new NoSuchElementException("Product with id " + id + " not existent.");
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        client.setId(id);
        clientDAO.update(client);
    }

    public List<Validator<Client>> getValidators() {
        return validators;
    }

    public void setValidators(List<Validator<Client>> validators) {
        this.validators = validators;
    }

    public ClientDAO getClientDAO() {
        return clientDAO;
    }

    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

}
