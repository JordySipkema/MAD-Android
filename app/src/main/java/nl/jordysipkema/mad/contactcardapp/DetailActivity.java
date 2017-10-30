package nl.jordysipkema.mad.contactcardapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

import nl.jordysipkema.mad.contactcardapp.User.User;

public class DetailActivity extends AppCompatActivity {

    TextView firstNameLabel;
    TextView lastNameLabel;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        firstNameLabel = (TextView) findViewById(R.id.lblUserFirstName);
        lastNameLabel = (TextView) findViewById(R.id.lblUserLastName);
        profileImage = (ImageView) findViewById(R.id.userProfileImage);

        Gson gson = new Gson();
        Intent intent = getIntent();
        User user = (User) gson.fromJson(intent.getStringExtra("USER"), User.class);
        firstNameLabel.setText(user.getName().getFirst());
        lastNameLabel.setText(user.getName().getLast());
        new DownloadImageTask(profileImage).execute(user.getPicture().getLarge());
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
