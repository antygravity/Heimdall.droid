package de.rheinfabrik.heimdalldroid.network.oauth2;

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

    public PasswordOAuth2Grant(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Single<OAuth2AccessToken> grantNewAccessToken() {

        AccessTokenPasswordRequestBody body = new AccessTokenPasswordRequestBody(username, password, clientId, redirectUri, clientSecret, GRANT_TYPE);
        Single<OAuth2AccessToken> token = TraktTvApiFactory.newApiService().grantNewAccessToken(body);
        return token;
    }
}
