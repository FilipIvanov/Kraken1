package filipivanov.kraken;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
            customer.customerName = extras.getString("Customer name  - surname");
           // selecteditem = extras.getString("Customer name  - surname");

        }

        new ServerRequests(this).fetchOrderByOrderNumber(orderNumber, new Callback<List<Order>>() {
            @Override
            public void done(List<Order> orders) {
                StringBuilder s = new StringBuilder();
                for (Order order : orders) {
                    s.append(order.menu.toString() + "\n");
                }
                etOrderDone.setText(s.toString());

            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case R.id.etBackToMainMenu:


                startActivity(new Intent(OrderDoneActivity.this, MainActivity.class));

        }



    }
}
