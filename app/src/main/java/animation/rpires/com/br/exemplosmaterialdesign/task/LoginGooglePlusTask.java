package animation.rpires.com.br.exemplosmaterialdesign.task;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import java.io.Serializable;

import animation.rpires.com.br.exemplosmaterialdesign.observable.LoginObservable;
import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.EncerramentoActivity;
import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.Tarefa;

/**
 * Created by rpires on 10/02/2017.
 */

public class LoginGooglePlusTask extends Tarefa
        implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, Serializable {

    private static final String TAG = "LoginGooglePlusTask";
    private static final int SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;

    public LoginGooglePlusTask() {
        super();
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
        mGoogleApiClient = new GoogleApiClient.Builder(getAtividade())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    protected EncerramentoActivity getAtividade() {
        return EncerramentoActivity.newInstance();
    }

    @Override
    protected boolean isBarraProcessoCancelavel() {
        return true;
    }

    @Override
    protected String getMensagemBarraProcesso() {
        return "Aguarde";
    }

    @Override
    protected boolean executarConteudoSegundoPlano() {

        if (isConnected()) {
            initializerIfUserLogged();
        } else {
            requestedLogin();
        }

        return true;
    }

    @Override
    protected boolean isFinalizarExecucaoSegundoPlano() {
        return false;
    }

    @Override
    protected boolean isRetornoExecucaoSegundoPlano() {
        return false;
    }

    @Override
    protected void executarConteudoAposSegundoPlano(Boolean sucesso) {
        disconnect();
    }

    @Override
    protected void executarConteudoInicioCancelamento() {
        disconnect();
    }

    @Override
    protected NivelEsperaTotal getNivelEsperaTotal() {
        return NivelEsperaTotal.MAXIMO;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        connect();
    }

    @Override
    protected void onCancelled(Boolean aBoolean) {
        super.onCancelled(aBoolean);
        disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected:");
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
            GooglePlayServicesUtil.showErrorDialogFragment(connectionResult.getErrorCode(), getAtividade(),0);
            return;
        }
    }

    private void connect() {
        if(mGoogleApiClient != null){
            mGoogleApiClient.connect();
        }
    }

    private void disconnect() {
        Log.d(TAG, "onStop:");
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
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
//            showProgressDialog();
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

    /**
     * metodo para inicar o login
     */
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        getAtividade().startActivityForResult(signInIntent, SIGN_IN);
    }

    private void updateUI(boolean signedIn, GoogleSignInAccount acct, boolean closeProgress) {
        Log.d(TAG, "updateUI: isSuccess:" + signedIn);
        if (signedIn) {
//            new LoginObservable(acct.getPhotoUrl(), acct.getDisplayName(), acct.getEmail(), true, closeProgress);
        } else {
//            new LoginObservable(null, "Android Studio", "android.studio@android.com", false, closeProgress);
        }
    }


}
