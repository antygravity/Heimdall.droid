package de.rheinfabrik.heimdalldroid.network.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Body object used to exchange a code with an access token.
 */
public class AccessTokenPasswordRequestBody implements Serializable {

    // Properties

    @SerializedName("username")
    public String username;

    @SerializedName("password")
    public String password;

    @SerializedName("client_id")
    public String clientId;

    @SerializedName("client_secret")
    public String clientSecret;

    @SerializedName("redirect_uri")
    public String redirectUri;

    @SerializedName("grant_type")
    public String grantType;

    // Constructor

    public AccessTokenPasswordRequestBody(String username, String password, String clientId, String redirectUri, String clientSecret, String grantType) {
        super();

        this.username = username;
        this.password = password;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
    }
}
