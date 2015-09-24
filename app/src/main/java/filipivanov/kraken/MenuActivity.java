package filipivanov.kraken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by WakingBliss on 9/18/2015.
 */
public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {


    Button populateMenuBtn, finishOrderBtn;

    Spinner etSpinner, etSpinnerMenu;

    String orderNumber;

    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        etSpinner = (Spinner) findViewById(R.id.etSpinner);
        etSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MenuType menuType = (MenuType) adapterView.getSelectedItem();
                new ServerRequests(MenuActivity.this).fetchMenuByMenuTypeInBackground(menuType, new Callback<MenuList>() {
                    @Override
                    public void done(MenuList menuList) {
                        ArrayAdapter<Menu> menuTypes = new ArrayAdapter<Menu>(
                                MenuActivity.this, R.layout.spinner_layout,
                                R.id.etTxt, menuList.menuList);
                        etSpinnerMenu.setAdapter(menuTypes);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etSpinnerMenu = (Spinner) findViewById(R.id.etSpinnerMenu);


        populateMenuBtn = (Button) findViewById(R.id.populateMenuBtn);
        populateMenuBtn.setOnClickListener(this);

        finishOrderBtn = (Button) findViewById(R.id.finishOrderBtn);
        finishOrderBtn.setOnClickListener(this);

        orderNumber = UUID.randomUUID().toString();

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            customer = (Customer) extras.get("Customer");
        }

        new ServerRequests(this).fetchMenuTypesInBackground(new Callback<MenuTypeList>() {
            @Override
            public void done(MenuTypeList menuTypeList) {
                ArrayAdapter<MenuType> menuTypes = new ArrayAdapter<MenuType>(MenuActivity.this, R.layout.spinner_layout, R.id.etTxt, menuTypeList.menuTypes);
                etSpinner.setAdapter(menuTypes);
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.action_bar);
        toolbar.setNavigationIcon(R.drawable.waitericon);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.populateMenuBtn:


                Menu orderedMenu = (Menu) etSpinnerMenu.getSelectedItem();
                new ServerRequests(this).storeOrderMealInBackground(new Order(0, orderedMenu, orderNumber), new Callback<Order>() {
                    @Override
                    public void done(Order order) {

                    }
                });

                break;

            case R.id.finishOrderBtn:

                Intent i = new Intent(this, PaymentActivity.class);
                i.putExtra("ORDER_NUMBER", orderNumber);
                i.putExtra("Customer", customer);
                startActivity(i);
                break;


        }


    }




}

