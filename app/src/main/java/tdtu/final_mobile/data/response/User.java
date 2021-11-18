package tdtu.final_mobile.data.response;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}