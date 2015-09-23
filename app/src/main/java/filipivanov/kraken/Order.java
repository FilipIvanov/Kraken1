package filipivanov.kraken;

/**
 * Created by WakingBliss on 9/14/2015.
 */
public class Order {

    int orderId;
    String orderNumber;
    Menu menu;

    public Order (int orderId, Menu menu, String orderNumber){

        this.orderId = orderId;
        this.menu = menu;
        this.orderNumber = orderNumber;

    }

    @Override
    public String toString() {
        return menu.toString();
    }
}
