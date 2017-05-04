package org.gerejajkt.remaja.data.api.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huteri on 4/26/17.
 */

public class UserResponse {

    public int id;
    public String name, email, phone, nationality;

    @SerializedName("phone_code")
    public String phoneCode;

    @SerializedName("profile_picture")
    public String profilePicture;

}
