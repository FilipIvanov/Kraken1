package filipivanov.kraken;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * Created by WakingBliss on 9/16/2015.
 */
public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etCardName, etCardNumber, etExpDate, etVerNumber;
    Button finishOrderBtn;

    String orderNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        etCardName = (EditText) findViewById(R.id.etCardName);
        etCardNumber = (EditText) findViewById(R.id.etCardNumber);
        etExpDate = (EditText) findViewById(R.id.etExpDate);
        etVerNumber = (EditText) findViewById(R.id.etVerNumber);


        finishOrderBtn = (Button) findViewById(R.id.finishOrderBtn);
        finishOrderBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.finishOrderBtn:

                Intent i = new Intent(PaymentActivity.this, OrderDoneActivity.class);
                i.putExtra("ORDER_NUMBER", orderNumber);
                startActivity(i);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            orderNumber = extras.getString("ORDER_NUMBER");
        }



    }
}
