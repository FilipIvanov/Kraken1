package filipivanov.kraken;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.action_bar);
        toolbar.setNavigationIcon(R.drawable.login);
        //toolbar.setTitle("Login");





        userLocalStore = new UserLocalStore(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();


                    User user = new User(username, password);

                    authenticate(user);

                break;

            case R.id.tvRegisterLink:

                startActivity(new Intent(this, Register.class));

                break;

        }
    }

    private void authenticate(User user) {

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });

    }



    private void showErrorMessage() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {

        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("USERNAME", returnedUser.username);
        startActivity(i);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        finish();
        System.exit(0);

    }

//    @Override
//    public void onClick(View arg0) {
//
//        final String email = emailEditText.getText().toString();
//        if (!isValidEmail(email)) {
//            emailEditText.setError("Invalid Email");
//        }
//
//        final String pass = passEditText.getText().toString();
//        if (!isValidPassword(pass)) {
//            passEditText.setError("Invalid Password");
//        }
//
//    }
//});
//        }
//

//private boolean isValidEmail(String email) {
//        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//        }
//

//private boolean isValidPassword(String pass) {
//        if (pass != null && pass.length() > 6) {
//        return true;
//        }
//        return false;
//        }
}
