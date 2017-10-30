package nl.jordysipkema.mad.contactcardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import nl.jordysipkema.mad.contactcardapp.User.User;

public class MainActivity extends AppCompatActivity {

    private UserListPresenter presenter;
    private ListView listView;
    private List<User> users = new ArrayList<>();
    private ArrayAdapter<User> userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupList();

        this.presenter = new UserListPresenter(this);
        this.presenter.fetchUsers();
    }

    private void setupList(){
        this.listView = (ListView) findViewById(R.id.userList);

        this.userAdapter =
                new ArrayAdapter<User>(this, 0, users) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        // Inflate only once
                        //android.R.layout.simple_list_item_2
                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext())
                                    .inflate(android.R.layout.simple_list_item_2, parent, false);
                        }

                        User currentUser = users.get(position);

                        // Lookup view for data population
                        TextView title = (TextView) convertView.findViewById(android.R.id.text1);
                        TextView description = (TextView) convertView.findViewById(android.R.id.text2);

                        // Populate the data into the template view using the data object
                        title.setText(currentUser.getName().getLast());
                        description.setText(currentUser.getName().getFirst());

                        // Return the completed view to render on screen
                        return convertView;
                    }
                };

        listView.setAdapter(this.userAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SelectedItem: ", position + "");
                showDetailView(position);
            }
        });
    }

    public void setUsers(final List<User> users){
        Log.d("View", "setUsers: "+users.size());
        this.users = users;
        this.userAdapter.addAll(this.users);
        this.userAdapter.notifyDataSetChanged();
    }

    public void showDetailView(int user_id){
        User user = this.users.get(user_id);
        Gson gson = new Gson();
        Intent i = new Intent(getApplicationContext(), DetailActivity.class);
        i.putExtra("USER", gson.toJson(user));

        startActivity(i);
    }




}
