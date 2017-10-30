package nl.jordysipkema.mad.contactcardapp;

import nl.jordysipkema.mad.contactcardapp.User.ApiResponse;

/**
 * Created by jordysipkema on 27/10/2017.
 */

public interface UserServiceCallback {
    void resultDelegate(ApiResponse response);
}
