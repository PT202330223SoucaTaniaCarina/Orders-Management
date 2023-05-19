package Bll.Validator;

import Bll.ClientBll;
import Model.Client;
import Model.Orders;

/**
 * Se verifica daca clientul care plaseaza comanda exista sau nu in baza de date.
 * @author tania
 */

public class OrderClientValidator implements Validator<Orders> {

    public void validate(Orders t) {
        ClientBll clientBLL = new ClientBll();
        Client client = clientBLL.findClientById(t.getClientID());
        if(client==null)
            throw new IllegalArgumentException("Client with id = " + t.getClientID() + " was not found!");
        else
            System.out.println("Order taken!");
    }

}
