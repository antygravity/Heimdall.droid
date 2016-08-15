package de.rheinfabrik.heimdalldroid.network;

import java.util.List;

import de.rheinfabrik.heimdall.OAuth2AccessToken;
import de.rheinfabrik.heimdalldroid.network.models.AccessTokenPasswordRequestBody;
import de.rheinfabrik.heimdalldroid.network.models.AccessTokenRequestBody;
import de.rheinfabrik.heimdalldroid.network.models.RefreshTokenRequestBody;
import de.rheinfabrik.heimdalldroid.network.models.RevokeAccessTokenBody;
import de.rheinfabrik.heimdalldroid.network.models.TraktTvList;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Interface for communicating to the TraktTv API (http://docs.trakt.apiary.io/#).
 */
public interface TraktTvApiService {

    // POST

    @POST("oauth/v2/token")
    Observable<OAuth2AccessToken> grantNewAccessToken(@Body AccessTokenPasswordRequestBody body);

    @POST("oauth/token")
    Observable<OAuth2AccessToken> grantNewAccessToken(@Body AccessTokenRequestBody body);

    @POST("oauth/token")
    Observable<OAuth2AccessToken> refreshAccessToken(@Body RefreshTokenRequestBody body);

    @POST("oauth/revoke")
    Observable<Void> revokeAccessToken(@Body RevokeAccessTokenBody body);

    // GET

    @GET("users/me/lists")
    Observable<List<TraktTvList>> getLists(@Header("Authorization") String authorizationHeader);
}
