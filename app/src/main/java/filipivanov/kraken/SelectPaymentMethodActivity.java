package filipivanov.kraken;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by WakingBliss on 9/24/2015.
 */
public class SelectPaymentMethodActivity extends AppCompatActivity implements View.OnClickListener {

    Button deliveryBtn, creditCardBtn;
    String orderNumber;
    Customer customer;

    String delivery = "Payment on delivery";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment_method);


        deliveryBtn = (Button) findViewById(R.id.deliveryBtn);
        deliveryBtn.setOnClickListener(this);

        creditCardBtn = (Button) findViewById(R.id.deliveryBtn);
        creditCardBtn.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderNumber = extras.getString("ORDER_NUMBER");
            customer = (Customer) extras.get("Customer");
        }



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.deliveryBtn:


                Intent i = new Intent(SelectPaymentMethodActivity.this, OrderDoneActivity.class);

                i.putExtra("ORDER_NUMBER", orderNumber);
                i.putExtra("Customer", customer);
                i.putExtra("ON_DELIVERY", delivery);


                startActivity(i);

                break;

            case  R.id.creditCardBtn:

                Intent intent = new Intent(SelectPaymentMethodActivity.this, PaymentActivity.class);

                intent.putExtra("ORDER_NUMBER", orderNumber);
                intent.putExtra("Customer", customer);


                startActivity(intent);

                break;

        }

    }
}
