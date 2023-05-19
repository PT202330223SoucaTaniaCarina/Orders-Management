import java.io.IOException;
import java.sql.SQLException;
import Bll.ClientBLL;
import Bll.OrderBLL;
import Bll.ProductBLL;
import Dao.ReflectionOperator;
import Model.Client;
import Model.Orders;
import Model.Product;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    protected static final Logger logger = Logger.getLogger(Main.class.getName());
    private static Object clientDAO;

    public static void main(String[] args) throws SQLException, IOException{
        Parser p = new Parser();
        p.readData(args[0]);
    }

}
