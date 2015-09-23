package filipivanov.kraken;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by WakingBliss on 9/23/2015.
 */
public class SelectCustomerActivity extends AppCompatActivity  {

    Spinner etSelectCustomerSpinner;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_customer);

        new ServerRequests(this).fetchCustomerInfoInBackground(new Callback<CustomerList>() {
            @Override
            public void done(CustomerList customerList) {
                ArrayAdapter<Customer> customer = new ArrayAdapter<Customer>(SelectCustomerActivity.this, R.layout.spinner_layout, R.id.etTxt, customerList.customer);
                etSelectCustomerSpinner.setAdapter(customer);
            }
        });


        }
