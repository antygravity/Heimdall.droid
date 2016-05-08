package de.rheinfabrik.heimdalldroid.actvities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Bind;
import de.rheinfabrik.heimdall.OAuth2AccessToken;
import de.rheinfabrik.heimdalldroid.R;
import de.rheinfabrik.heimdalldroid.network.oauth2.PasswordOAuth2Grant;
import de.rheinfabrik.heimdalldroid.network.oauth2.TraktTvOauth2AccessTokenManager;
import de.rheinfabrik.heimdalldroid.utils.AlertDialogFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Activity used to let the user login with his GitHub credentials.
 * You may want to move most of this code to your presenter class or view model.
 * For the sake of simplicity the code is inside the activity.
 */
public class LoginActivity extends RxAppCompatActivity {

    // Members

    @Bind(R.id.login_view)
    protected RelativeLayout loginView;

    @Bind(R.id.edit_username)
    protected EditText editLogin;

    @Bind(R.id.edit_password)
    protected EditText editPassword;

    @Bind(R.id.loginButton)
    protected Button loginButton;

    // Activity lifecycle

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view
        setContentView(R.layout.activity_login);

        // Inject views
        ButterKnife.bind(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start authorization
                authorize();
            }
        });
    }

    // Private Api

    private void authorize() {

        // Grab a new token manger
        TraktTvOauth2AccessTokenManager tokenManager = TraktTvOauth2AccessTokenManager.from(this);

        // Grab a new grant
//        TraktTvAuthorizationCodeGrant grant = tokenManager.newAuthorizationCodeGrant();
        PasswordOAuth2Grant grant = tokenManager.newAuthorizationPasswordGrant(editLogin.getText().toString(), editPassword.getText().toString());

//        // Listen for the authorization url and load it once needed
//        grant.authorizationUri()
//                .map(Uri::toString)
//                .compose(bindToLifecycle())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(loginView::loadUrl);
//
//        // Sent loaded website to grant so it can check if we have an access token
//        loginView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//
//                grant.onUriLoadedCommand.onNext(Uri.parse(url));
//
//                // Hide redirect page from user
//                if (url.startsWith(grant.redirectUri)) {
//                    loginView.setVisibility(View.GONE);
//                }
//            }
//        });

        // Start authorization and listen for success

        Observable observable = Observable.create(subscriber -> tokenManager.grantNewAccessToken(grant));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(x -> handleSuccess(), x -> handleError());
    }

    // Set the result to ok and finish this activity
    private void handleSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    // Build an error dialog and show it
    private void handleError() {
        AlertDialogFactory.errorAlertDialog(this).show();
    }
}
