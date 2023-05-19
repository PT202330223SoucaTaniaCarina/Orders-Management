package Model;

/**
 *  Order Class.
 * @author tania
 */

    public class Orders {
        private int id;
        private int clientID;
        private int productID;
        private int quantity;

        public Orders() {
        }

        public Orders(int id, int clientID, int productID, int quantity) {
            this.id = id;
            this.clientID = clientID;
            this.productID = productID;
            this.quantity = quantity;
        }

        public Orders(int clientID, int productID, int quantity) {
            this.clientID = clientID;
            this.productID = productID;
            this.quantity = quantity;
        }


        public String toString() {
            return "~Order --> ID = " + id + ", idClient = " + clientID + ", idProduct = " + productID + ", quantity = " + quantity + "~";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getClientID() {
            return clientID;
        }

        public void setClientID(int clientID) {
            this.clientID = clientID;
        }


        public void setProductID(int productID) {
            this.productID = productID;
        }

        public int getProductID() {
            return productID;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

