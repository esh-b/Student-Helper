package com.example.eshwanth.myapplication;

import android.app.AlertDialog;
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
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

import static org.apache.http.params.HttpConnectionParams.setConnectionTimeout;
import static org.apache.http.params.HttpConnectionParams.setSoTimeout;

/**
 * Created by Eshwanth on 11/1/2015.
 */

    public class queryAsyncTask_vin extends AsyncTask<Void, Void, String[][]> {
        User_vin user;
    String x;
    AlertDialog.Builder builder;

        ProgressDialog pDialog;
        public MainActivity1_vin delegate = null;
        public static final int CONNECTION_TIMEOUT = 1000 * 15;
        public static final String SERVER_ADDRESS = "http://my-world.ueuo.com/";
        String[][] array = {};

        public queryAsyncTask_vin(Context context, User_vin user)
        {
           // builder = new AlertDialog.Builder(context);
           // pDialog = new ProgressDialog(context);
            this.user = user;
        }
   // @Override
  /*  protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog.setTitle("Loading courses...");
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }*/

        @Override
        protected String[][] doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", user.username));
//            dataToSend.add(new BasicNameValuePair("user_type", user.user_type));

            HttpParams httpRequestParams = new BasicHttpParams();
            setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS
                    + "index-2.php");


            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                x = result;
                JSONObject finalResult = new JSONObject(result);
                               //Get the jsonObject containing course details
                //x = String .valueOf(finalResult.length());
                if(finalResult.length() > 0)
                {
                    array = new String[finalResult.length()][7];

                    for(int i=0 ; i < finalResult.length() ; i++)
                    {

                        JSONObject obj1 = finalResult.getJSONObject(String.valueOf(i));     //Access each json Object using their key
                        array[i][0] = obj1.getString("course_name");
                        array[i][1] = obj1.getString("DAY");
                        array[i][2] = obj1.getString("TIME");
                        array[i][3] = obj1.getString("seg");
                        array[i][4] = obj1.getString("course_id");
                        array[i][5] = obj1.getString("prof_name");
                        array[i][6] = obj1.getString("credits");//Get the value using the key for jObject
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return array;
        }


        protected void onPostExecute(String[][] array1) {
           super.onPostExecute(array1);
          // pDialog.dismiss();

          // builder.setCancelable(true);
          // builder.setIcon(R.drawable.warning);
           //builder.setTitle("Confirmation");
          // builder.setMessage(x + "hi" + user.username);
          // builder.show();
           delegate.processFinish(array1);
        }
    }
