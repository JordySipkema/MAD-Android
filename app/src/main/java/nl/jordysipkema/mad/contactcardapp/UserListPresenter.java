package nl.jordysipkema.mad.contactcardapp;

import nl.jordysipkema.mad.contactcardapp.User.ApiResponse;

/**
 * Created by jordysipkema on 27/10/2017.
 */

public class UserListPresenter implements UserServiceCallback {

    private static final String api_url = "https://randomuser.me/api/";

    private MainActivity view;

    public UserListPresenter(MainActivity view){
        this.view = view;

    }

    public void fetchUsers(){
        new UserService(this).requestUsers(15);
    }


    @Override
    public void resultDelegate(ApiResponse response) {
        this.view.setUsers(response.getResults());
    }
}
