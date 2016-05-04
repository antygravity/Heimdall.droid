package de.rheinfabrik.heimdalldroid.network.oauth2;

import android.net.Uri;

import de.rheinfabrik.heimdall.OAuth2AccessToken;
import de.rheinfabrik.heimdall.grants.OAuth2ResourceOwnerPasswordCredentialsGrant;
import de.rheinfabrik.heimdalldroid.network.TraktTvApiFactory;
import de.rheinfabrik.heimdalldroid.network.models.AccessTokenPasswordRequestBody;
import rx.Single;

/**
 * Created by marcin on 03.05.16.
 */
public class PasswordOAuth2Grant extends OAuth2ResourceOwnerPasswordCredentialsGrant<OAuth2AccessToken> {


    // Properties

    public String clientSecret;
    public String clientId;
    public String redirectUri;



    @Override
    public Single<OAuth2AccessToken> grantNewAccessToken() {
        username = "antygravity@wp.pl";
        password = "test123";

        AccessTokenPasswordRequestBody body = new AccessTokenPasswordRequestBody(username, password, clientId, redirectUri, clientSecret, GRANT_TYPE);
        return TraktTvApiFactory.newApiService().grantNewAccessToken(body);
    }
}
