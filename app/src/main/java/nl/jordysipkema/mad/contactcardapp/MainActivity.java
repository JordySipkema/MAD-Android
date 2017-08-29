package nl.jordysipkema.mad.contactcardapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView firstNameLabel;
    TextView lastNameLabel;
    Button refreshButton;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameLabel = (TextView) findViewById(R.id.lblUserFirstName);
        lastNameLabel = (TextView) findViewById(R.id.lblUserLastName);
        profileImage = (ImageView) findViewById(R.id.userProfileImage);
        refreshButton = (Button) findViewById(R.id.refreshButton);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserData();
            }
        });
    }

    private void getUserData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://randomuser.me/api";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("info", response);
                        handleJsonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Show error?
                        Log.e("Volley", error.toString());
                    }
                });

        queue.add(stringRequest);
    }

    private void handleJsonResponse(String json_string){
        try {
            JSONObject jObject = new JSONObject(json_string);

            JSONArray results = jObject.getJSONArray("results");
            JSONObject person = results.getJSONObject(0);
            JSONObject person_name = person.getJSONObject("name");
            JSONObject person_picture = person.getJSONObject("picture");

            String lastName = person_name.getString("last");
            String firstName = person_name.getString("first");
            String image_url = person_picture.getString("large");


            firstNameLabel.setText(firstName);
            lastNameLabel.setText(lastName);

            new DownloadImageTask(profileImage).execute(image_url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
