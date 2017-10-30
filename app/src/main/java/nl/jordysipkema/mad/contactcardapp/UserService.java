package nl.jordysipkema.mad.contactcardapp;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import nl.jordysipkema.mad.contactcardapp.User.ApiResponse;

/**
 * Created by jordysipkema on 07/09/2017.
 */

public class UserService extends AsyncTask<String, String, String> {

    private static final String api_url = "https://randomuser.me/api/";

    private ApiResponse cache = null;
    private UserServiceCallback callback = null;

    public UserService(UserServiceCallback callback) {
        this.callback = callback;
    }

    public void requestUsers(int amount){
        this.execute(String.format(Locale.ENGLISH, "%s?results=%d", api_url, amount));
    }

    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String line;
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder buffer = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Gson gson = new Gson();
        ApiResponse response = gson.fromJson(result, ApiResponse.class);

        callback.resultDelegate(response);
    }

}
