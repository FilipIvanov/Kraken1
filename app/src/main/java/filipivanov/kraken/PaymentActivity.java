package filipivanov.kraken;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WakingBliss on 9/16/2015.
 */
public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etCardName, etCardNumber, etExpDate, etVerNumber;
    Button finishOrderBtn;

    String orderNumber;
    Customer customer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        etCardName = (EditText) findViewById(R.id.etCardName);
        etCardNumber = (EditText) findViewById(R.id.etCardNumber);
        etExpDate = (EditText) findViewById(R.id.etExpDate);
        etVerNumber = (EditText) findViewById(R.id.etVerNumber);
        etCardNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        etVerNumber.setInputType(InputType.TYPE_CLASS_NUMBER);

        finishOrderBtn = (Button) findViewById(R.id.finishOrderBtn);
        finishOrderBtn.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderNumber = extras.getString("ORDER_NUMBER");
            customer = (Customer) extras.get("Customer");
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.action_bar);
        toolbar.setNavigationIcon(R.drawable.paymenticon);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.finishOrderBtn:



//                final String pass = passEditText.getText().toString();
//                if (!isValidPassword(pass)) {
//                    passEditText.setError("Invalid Password");
//                }

                int cardNumber = Integer.parseInt(etCardNumber.getText().toString().trim());
                int verificationNumber = Integer.parseInt(etVerNumber.getText().toString().trim());
                String cardName = etCardName.getText().toString();
                String expirationDate = etExpDate.getText().toString();



                Payment payment = new Payment(cardNumber, verificationNumber, cardName, expirationDate);


                enterPayment(payment);

                Intent i = new Intent(PaymentActivity.this, OrderDoneActivity.class);

                i.putExtra("ORDER_NUMBER", orderNumber);
                i.putExtra("Customer", customer);
                i.putExtra("PAYMENT_METHOD", payment);


                startActivity(i);

                break;


        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderNumber = extras.getString("ORDER_NUMBER");
        }


    }


    private void enterPayment(Payment payment) {

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storePaymentInBackground(payment, new Callback<Payment>() {
            @Override
            public void done(Payment returnedPayment) {


            }


        });

    }



}