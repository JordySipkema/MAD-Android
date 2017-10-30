
package nl.jordysipkema.mad.contactcardapp.User;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("results")
    @Expose
    private List<User> users = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public List<User> getResults() {
        return users;
    }

    public void setResults(List<User> results) {
        this.users = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
