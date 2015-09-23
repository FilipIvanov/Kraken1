package filipivanov.kraken;

import java.io.Serializable;

/**
 * Created by WakingBliss on 9/15/2015.
 */
public class Customer implements Serializable {


            String  customerName,customerSurname,customerAddress,customerPhone;


    public Customer(String customerName, String customerSurname, String customerAddress, String customerPhone   ){

      //  this.customerId = customerId;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;


    }

    public Customer (String customerName, String customerSurname){



        this.customerName = customerName;
        this.customerSurname = customerSurname;

    }

    @Override
    public String toString() {
        return customerName+ "   " + customerSurname;
    }


}
