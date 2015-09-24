package filipivanov.kraken;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by WakingBliss on 9/15/2015.
 */
public class CustomerActivity extends AppCompatActivity implements View.OnClickListener{


    EditText etCustomerName, etCustomerSurname, etCustomerAddress, etCustomerPhone;
    Button bProceedWithOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        etCustomerName = (EditText) findViewById(R.id.etCustomerName);
        etCustomerSurname = (EditText) findViewById(R.id.etCustomerSurname);
        etCustomerAddress = (EditText) findViewById(R.id.etCustomerAddress);
        etCustomerPhone = (EditText) findViewById(R.id.etCustomerPhone);

        bProceedWithOrder = (Button) findViewById(R.id.bProceedWithOrder);
        bProceedWithOrder.setOnClickListener(this);

        bProceedWithOrder.setOnClickListener(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.action_bar);
        toolbar.setNavigationIcon(R.drawable.customericon);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case R.id.bProceedWithOrder:

                String customerName = etCustomerName.getText().toString();
                String customerSurname = etCustomerSurname.getText().toString();
                String customerAddress = etCustomerAddress.getText().toString();
                String customerPhone = etCustomerPhone.getText().toString();
//                try {
//                    Integer.parseInt(customerPhone);
//                    if(customerName.length() == 0) {
//                        //show error
//                    }
//
//
//                } catch (NumberFormatException e) {
//                    //error prikazi ovde
//                }

                Customer customer = new Customer(customerName,customerSurname,customerAddress, customerPhone);
                enterCustomer(customer);

                Toast.makeText(getApplicationContext(), "Customer has been entered!",
                        Toast.LENGTH_LONG).show();



                break;


        }
    }

    private void enterCustomer (Customer customer){


        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeCustomerInBackground(customer, new Callback<Customer>() {
            @Override
            public void done(Customer returnedCustomer) {
                startActivity(new Intent(CustomerActivity.this,MainActivity.class));;

            }
        });

    }

}
