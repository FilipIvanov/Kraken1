package filipivanov.kraken;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by WakingBliss on 9/21/2015.
 */
public class OrderDoneActivity extends AppCompatActivity implements View.OnClickListener {

    TextView etOrderDone;
    String orderNumber;
    Button etBackToMainMenu;
    Customer customer;
    Payment payment;
    String delivery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_done);


        etOrderDone = (TextView) findViewById(R.id.etShowOrder);

        etBackToMainMenu = (Button) findViewById(R.id.etBackToMainMenu);
        etBackToMainMenu.setOnClickListener(this);




    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            orderNumber = extras.getString("ORDER_NUMBER");
            customer = (Customer) extras.get("Customer");
            payment = (Payment) extras.get("PAYMENT_METHOD");
          //  delivery = extras.getString("ON_DELIVERY");

        }

        new ServerRequests(this).fetchOrderByOrderNumber(orderNumber, new Callback<List<Order>>() {
            @Override
            public void done(List<Order> orders) {
                StringBuilder s = new StringBuilder();
                s.append("CUSTOMER: ").append(customer.customerName).append(" ").append(customer.customerSurname).append("\n").append("\n");
                s.append("PAYMENT_METHOD: ").append(payment.cardNumber).append(" ").append(payment.cardName).append("\n").append("\n");
              //  s.append("ON_DELIVERY").append(delivery).append(" ").append("\n").append("\n");
                ;
                for (Order order : orders) {
                    s.append(order.menu.toString() + "\n");
                }
                etOrderDone.setText(s.toString());

            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.action_bar);
        toolbar.setNavigationIcon(R.drawable.orderdone);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case R.id.etBackToMainMenu:

                Toast.makeText(getApplicationContext(), "Purchase has been made!",
                        Toast.LENGTH_LONG).show();

                startActivity(new Intent(OrderDoneActivity.this, MainActivity.class));

        }



    }
}
