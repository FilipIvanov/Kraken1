package filipivanov.kraken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {


    Button orderBtn, fillMenuBtn, enterCustomerBtn;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderBtn = (Button) findViewById(R.id.orderBtn);
        orderBtn.setOnClickListener(this);

        fillMenuBtn = (Button) findViewById(R.id.fillMenuBtn);
        fillMenuBtn.setOnClickListener(this);

        enterCustomerBtn = (Button) findViewById( R.id.enterCustomerBtn);
        enterCustomerBtn.setOnClickListener(this);


        userLocalStore = new UserLocalStore(this);

    }


    @Override
    protected void onStart() {
        super.onStart();


        if (authenticate() == true) {


        } else
            startActivity(new Intent(MainActivity.this, Login.class));  //ukoliko user nije ulogovan pokrece login activity


    }

    private boolean authenticate() {

        return userLocalStore.getUserLogggedIn();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.orderBtn:

//                userLocalStore.clearUserData();
//                userLocalStore.setUserLoggedIn(false);


                startActivity(new Intent(this, MenuActivity.class));

                break;

            case R.id.fillMenuBtn:

                startActivity(new Intent(this, PopulateMenuActivity.class));
                break;

            case R.id.enterCustomerBtn:

                startActivity(new Intent(this,CustomerActivity.class));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (authenticate()) {
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);

        }


    }


}
