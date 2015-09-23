package filipivanov.kraken;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by WakingBliss on 9/23/2015.
 */
public class PopulateMenuActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText etItemName, etItemSize, etItemDescription, etItemType;
    Button enterItembtn, finishItemEntriesBtn;
    Spinner etShotMenuTypesSpinner;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populate_menu);


        etItemType = (EditText) findViewById(R.id.etItemType);
        etItemName = (EditText) findViewById(R.id.etItemName);
        etItemSize = (EditText) findViewById(R.id.etItemSize);
        etItemDescription = (EditText) findViewById(R.id.etItemDescription);

        enterItembtn = (Button) findViewById(R.id.enterItembtn);
        finishItemEntriesBtn = (Button) findViewById(R.id.finishItemEntriesBtn);
        finishItemEntriesBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.populateMenuBtn:

                //ovde procitas manu type kao sto je food recimo
                //nekako iz drop down menija
                //a onda iz liste MenuTypeList moras da nadjes koji je MenuType


                MenuType selectedMenuType = (MenuType) etShotMenuTypesSpinner.getSelectedItem();
//
//                String itemName = etItemName.getText().toString();
//                String itemSize = etItemSize.getText().toString();
//                String itemDescription = etItemDescription.getText().toString();
//
//                Menu menu = new Menu(0, selectedMenuType, itemName, itemSize, itemDescription);
//
//                populateMenu(menu);
                break;
        }
    }




    private void populateMenu(Menu menu) {

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeMenuInBackground(menu, new Callback<Menu>() {
            @Override
            public void done(Menu returnedMenu) {

            }
        });


    }


}