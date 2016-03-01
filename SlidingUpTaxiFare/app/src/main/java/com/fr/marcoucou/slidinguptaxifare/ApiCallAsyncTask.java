package com.fr.marcoucou.slidinguptaxifare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Marc on 17/02/2016.
 */

public class ApiCallAsyncTask extends AsyncTask<String, Integer, String> {
    private ApiCallResponse delegate = null ;
    private ProgressDialog progressBar;
    private Context activityContext;
    public ApiCallAsyncTask(ApiCallResponse delegate, Context context){
        this.delegate = delegate;
        this.activityContext = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Do something like display a progress bar
        progressBar = new ProgressDialog(activityContext);
        progressBar.setCancelable(false);
        progressBar.setMessage("How much will cost this trip ... ");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(true);
        progressBar.show();
    }

    // This is run in a background thread
    @Override
    protected String doInBackground(String... params) {
        // get the string from params, which is an array
        String myString = params[0];
        Log.d("params",myString);
        StringBuffer chaine = new StringBuffer("");
        try{
            URL url = new URL("https://api.taxifarefinder.com/entity?key=fEfaswEWrUZ4&location=42.356261,-71.065334");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                chaine.append(line);
            }
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }

        return chaine.toString();
    }

    // This is called from background thread but runs in UI
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        // Do things like update the progress bar
    }

    // This runs in UI when background thread finishes
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        delegate.onApiCallCompleted(result);
        progressBar.dismiss();
        // Do things like hide the progress bar or change a TextView
    }

}
