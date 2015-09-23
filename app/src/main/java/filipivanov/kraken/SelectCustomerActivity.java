package filipivanov.kraken;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by WakingBliss on 9/23/2015.
 */
public class SelectCustomerActivity extends AppCompatActivity implements View.OnClickListener  {

    Spinner etSelectCustomerSpinner;
    Button goToMenuBtn;
    String spinnerValue;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_customer);

        goToMenuBtn = (Button) findViewById(R.id.goToMenuBtn);
        goToMenuBtn.setOnClickListener(this);

        spinnerValue.toString();

        etSelectCustomerSpinner = (Spinner) findViewById(R.id.etSelectCustomerSpinner);

        new ServerRequests(this).fetchCustomerInfoInBackground(new Callback<CustomerList>() {
            @Override
            public void done(CustomerList customerList) {
                ArrayAdapter<Customer> customer = new ArrayAdapter<Customer>(SelectCustomerActivity.this, R.layout.spinner_layout, R.id.etTxt, customerList.customerList);
                etSelectCustomerSpinner.setAdapter(customer);
            }
        });


    }




    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case R.id.goToMenuBtn:



                Intent i = new Intent(this, MenuActivity.class);

                i.putExtra("Surname Name", spinnerValue);

                startActivity(i);

            break;
        }


    }
}