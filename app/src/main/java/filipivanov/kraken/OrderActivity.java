package filipivanov.kraken;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

/**
 * Created by WakingBliss on 8/31/2015.
 */
public class OrderActivity extends AppCompatActivity implements View.OnClickListener {



    Button bOrder;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        bOrder = (Button) findViewById(R.id.bOrder);

        bOrder.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bOrder:








        }

    }

    private void orderMeal (Order order){

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeOrderMealInBackground(order, new Callback<Order>() {
            @Override
            public void done(Order returnedOrder) {

                startActivity(new Intent(OrderActivity.this, CustomerActivity.class));
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(OrderActivity.this, MainActivity.class));
    }
}
