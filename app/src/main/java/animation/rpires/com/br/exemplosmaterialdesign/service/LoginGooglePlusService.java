package animation.rpires.com.br.exemplosmaterialdesign.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.Serializable;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.activity.BaseApp;

/**
 * Created by rpires on 07/02/2017.
 */

public class LoginGooglePlusService
        implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, Serializable {

    private static final String TAG = "LoginGooglePlusService";
    private static final int SIGN_IN = 9001;

    private BaseApp myBase;
    private Activity activity;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private boolean isConsentScreenOpened;
    private boolean isSignInButtonClicked;

    public LoginGooglePlusService(Activity activity, BaseApp myBase) {
        this.activity = activity;
        this.myBase = myBase;

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected:");
        isSignInButtonClicked = false;
        initializerIfUserLogged();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended:" + i);
        mGoogleApiClient.connect();
        updateUI(false, null, false);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: hasResolution: " + connectionResult.hasResolution());
        if (!connectionResult.hasResolution()) {
            GooglePlayServicesUtil.showErrorDialogFragment(connectionResult.getErrorCode(), activity,0);
            return;
        }
    }

    public boolean isConnected() {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            if (result.isSuccess()) {
                Log.d(TAG, "isConnected:" + result.isSuccess());
                return true;
            }
        }
        return false;
    }

    public void initializerIfUserLogged() {
        if (isConnected()) {
            OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
            Log.d(TAG, "initializerIfUserLogged:" + result.getStatus());
        }
    }

    public void requestedLogin() {
        Log.d(TAG, "requestedLogin:");
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            Log.d(TAG, "getSignInIntent:");
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            signIn();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    //hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void connect() {
        if(mGoogleApiClient != null){
            mGoogleApiClient.connect();
        }
    }

    public void onStart() {
        Log.d(TAG, "onStart:");
        connect();
    }

    public void onStop() {
        Log.d(TAG, "onStop:");
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * metodo para inicar o login
     */
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        activity.startActivityForResult(signInIntent, SIGN_IN);
    }

    /**
     * metodo para encerrar o login
     */
    public void signOut() {
        Log.d(TAG, "signOut:");
        showProgressDialog();
        connect();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false, null, true);
                        // [END_EXCLUDE]
                        //hideProgressDialog();
                    }
                });
    }

    /**
     * metodo para revogar o login     *
     */
    public void revokeAccess() {
        Log.d(TAG, "revokeAccess:");
        showProgressDialog();
        connect();
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false, null, true);
                        // [END_EXCLUDE]
                        //hideProgressDialog();
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: requestCode:" + requestCode + " resultCode:" + resultCode);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == SIGN_IN) {
            isConsentScreenOpened = false;

            if(resultCode != activity.RESULT_OK){
                isSignInButtonClicked = false;
            }

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        if(!mGoogleApiClient.isConnecting()){
            mGoogleApiClient.connect();
        }
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(activity);
            mProgressDialog.setMessage(activity.getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            updateUI(true, acct, false);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false, null, false);
        }
    }

    private void updateUI(boolean signedIn, GoogleSignInAccount acct, boolean closeProgress) {
        Log.d(TAG, "updateUI: isSuccess:" + signedIn);
        if (signedIn) {
            myBase.getLoginObservable().atualizarENotificar(acct.getPhotoUrl(), acct.getDisplayName(), acct.getEmail(), true, closeProgress);
        } else {
            myBase.getLoginObservable().atualizarENotificar(null, "Android Studio", "android.studio@android.com", false, closeProgress);
        }
    }
}
