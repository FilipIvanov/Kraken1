package filipivanov.kraken;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WakingBliss on 8/5/2015.
 */
public class ServerRequests {

    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADRESS = "http://filipivanov.webuda.com";

    public ServerRequests(Context context) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
    }


    public void storeUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback callback) {
        progressDialog.show();
        new fetchUserDataAsyncTask(user, callback).execute();
    }

    public void storeOrderMealInBackground(Order order, Callback<Order> orderCallback) {

        progressDialog.show();
        new StoreOrderMealDataAsyncTask(order, orderCallback).execute();
    }

    public void storeCustomerInBackground(Customer customer, Callback<Customer> customerCallback) {

        progressDialog.show();
        new StoreCustomerAsyncTask(customer, customerCallback).execute();
    }

    public void storeMenuInBackground(Menu menu, Callback<Menu> menuCallback) {

        progressDialog.show();
        new StoreMenuAsyncTask(menu, menuCallback).execute();
    }

    public void fetchMenuTypesInBackground(Callback<MenuTypeList> menuTypeListCallback) {
        progressDialog.show();
        new fetchMenuTypesDataAsyncTask(menuTypeListCallback).execute();
    }

    public void fetchMenuByMenuTypeInBackground(MenuType menuType, Callback<MenuList> menuListCallback) {
        progressDialog.show();
        new FetchMenuByMenuTypeDataAsyncTask(menuType, menuListCallback).execute();
    }

    public void fetchOrderByOrderNumber(String orderNumber, Callback<List<Order>> callback){
        progressDialog.show();
        new FetchOrderByOrderNumber(orderNumber, callback).execute();
    }

    public class StoreMenuAsyncTask extends AsyncTask<Void, Void, Void> {

        Menu menu;
        Callback<Menu> menuCallback;

        public StoreMenuAsyncTask(Menu menu, Callback<Menu> menuCallback) {

            this.menu = menu;
            this.menuCallback = menuCallback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("menuTypeId", ""+menu.menuType.menuTypeId));
           // dataToSend.add(new BasicNameValuePair("menuTypeId", ""+menu.menuType));
            dataToSend.add(new BasicNameValuePair("itemName", menu.itemName));
            dataToSend.add(new BasicNameValuePair("itemSize", menu.itemSize));
            dataToSend.add(new BasicNameValuePair("itemDescription", menu.itemDescription));



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADRESS + "/Menu.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
            menuCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }


    public class StoreCustomerAsyncTask extends AsyncTask<Void, Void, Void> {

        Customer customer;
        Callback<Customer> customerCallback;

        public StoreCustomerAsyncTask(Customer customer, Callback<Customer> customerCallback) {

            this.customer = customer;
            this.customerCallback = customerCallback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("customerName", customer.customerName));
            dataToSend.add(new BasicNameValuePair("customerSurname", customer.customerSurname));
            dataToSend.add(new BasicNameValuePair("customerAddress", customer.customerAddress));
            dataToSend.add(new BasicNameValuePair("customerPhone", customer.customerPhone));



            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADRESS + "/Customer.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
            customerCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class StoreOrderMealDataAsyncTask extends AsyncTask<Void, Void, Void> {

        Order order;
        Callback<Order> orderCallback;

        public StoreOrderMealDataAsyncTask(Order order, Callback<Order> orderCallback) {

            this.order = order;
            this.orderCallback = orderCallback;

        }

        @Override
        protected Void doInBackground(Void... voids) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("orderNumber", order.orderNumber));
            dataToSend.add(new BasicNameValuePair("menuId", ""+order.menu.menuId));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADRESS + "/Order.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
            orderCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }


    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {

        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;

        }


        @Override
        protected Void doInBackground(Void... params) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("name", user.name));
            dataToSend.add(new BasicNameValuePair("age", user.age + ""));
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADRESS + "/Register.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {

        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;

        }

        @Override
        protected User doInBackground(Void... params) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADRESS + "/FetchUserData.php");

            User returnedUser = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);   //dekodira iz JSON

                if (jObject.length() == 0) {

                    returnedUser = null;

                } else {

                    String name = jObject.getString("name");
                    int age = jObject.getInt("age");

                    returnedUser = new User(name, age, user.username, user.password);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {

            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }

    public class fetchMenuTypesDataAsyncTask extends AsyncTask<Void, Void, MenuTypeList> {

        MenuTypeList menuTypeList;
        Callback<MenuTypeList> callback;

        public fetchMenuTypesDataAsyncTask(Callback<MenuTypeList> callback) {

            this.callback = callback;

        }

        @Override
        protected MenuTypeList doInBackground(Void... params) {

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADRESS + "/FetchMenuType.php");

            MenuTypeList menuTypeList = null;

            try {

                HttpResponse httpResponse = client.execute(post);
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONArray jTypes = new JSONArray(result);   //dekodira iz JSON

                if (jTypes.length() == 0) {

                    menuTypeList = null;

                } else {
                    List<MenuType> items = new ArrayList<>();
                    for(int i=0;i<jTypes.length();i++) {
                        JSONObject menuType = jTypes.getJSONObject(i);
                        items.add(new MenuType(menuType.getInt("menuTypeId"), menuType.getString("menuType")));
                    }
                    menuTypeList = new MenuTypeList(items);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return menuTypeList;
        }

        @Override
        protected void onPostExecute(MenuTypeList menuTypes) {

            progressDialog.dismiss();
            callback.done(menuTypes);
            super.onPostExecute(menuTypes);
        }
    }

    public class FetchMenuByMenuTypeDataAsyncTask extends AsyncTask<Void, Void, MenuList> {

        MenuList menuList;
        MenuType menuType;
        Callback<MenuList> callback;

        public FetchMenuByMenuTypeDataAsyncTask(MenuType menuType, Callback<MenuList> callback) {
            this.menuType = menuType;
            this.callback = callback;

        }

        @Override
        protected MenuList doInBackground(Void... params) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("menuTypeId", ""+menuType.menuTypeId));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADRESS + "/FetchMenuByMenuType.php");

            MenuList menuList = null;

            try {

                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONArray jTypes = new JSONArray(result);   //dekodira iz JSON

                if (jTypes.length() == 0) {

                    menuList = null;

                } else {
                    List<Menu> items = new ArrayList<>();
                    for(int i=0;i<jTypes.length();i++) {
                        JSONObject o = jTypes.getJSONObject(i);

                        Menu m = new Menu(o.getInt("menuId"), menuType, o.getString("itemName"),
                                o.getString("itemSize"), o.getString("itemDescription"));
                        items.add(m);
                    }
                    menuList = new MenuList(items);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return menuList;
        }

        @Override
        protected void onPostExecute(MenuList menuList) {

            progressDialog.dismiss();
            callback.done(menuList);
            super.onPostExecute(menuList);
        }
    }

    public class FetchOrderByOrderNumber extends AsyncTask<Void, Void, List<Order>> {

        Callback<List<Order>> callback;
        String orderNumber;

        public FetchOrderByOrderNumber(String orderNumber, Callback<List<Order>> callback) {
            this.orderNumber = orderNumber;
            this.callback = callback;

        }

        @Override
        protected List<Order> doInBackground(Void... params) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("orderNumber", orderNumber));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADRESS + "/FetchOrder.php");

            List<Order> orderList = null;

            try {

                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONArray jTypes = new JSONArray(result);   //dekodira iz JSON

                if (jTypes.length() == 0) {

                    orderList = null;

                } else {
                    orderList = new ArrayList<>();
                    for(int i=0;i<jTypes.length();i++) {
                        JSONObject o = jTypes.getJSONObject(i);

                        Order order = new Order(0,
                                new Menu(0, null, o.getString("itemName"), o.getString("itemSize"),
                                        o.getString("itemDescription")),
                                orderNumber);
                        orderList.add(order);
                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return orderList;
        }

        @Override
        protected void onPostExecute(List<Order> orderList) {

            progressDialog.dismiss();
            callback.done(orderList);
            super.onPostExecute(orderList);
        }
    }
}