package animation.rpires.com.br.exemplosmaterialdesign.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;

import animation.rpires.com.br.exemplosmaterialdesign.activity.BaseApp;

/**
 * Created by rpires on 09/02/2017.
 */

public abstract class ConnectionLoginBase extends AppCompatActivity
        implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, Serializable {

    protected BaseApp myBase;

    public void build(Activity activity) {
        myBase.build(activity);
    }

    public void inicializarUsuarioSeLogado() {
        myBase.getLoginGooglePlusService().initializerIfUserLogged();
    }

    public void solicitarLogin() {
        myBase.getLoginGooglePlusService().requestedLogin();
    }

    public void deslogar() {
        myBase.getLoginGooglePlusService().signOut();
    }

    public void regovarAcesso() {
        myBase.getLoginGooglePlusService().revokeAccess();
    }

    public void hideProgressDialog() {
        myBase.getLoginGooglePlusService().hideProgressDialog();
    }

    @Override
    public void onStart(){
        super.onStart();
        myBase.getLoginGooglePlusService().onStart();
    }

    @Override
    public void onStop(){
        super.onStop();
        myBase.getLoginGooglePlusService().onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        myBase.getLoginGooglePlusService().onConnected(bundle);
    }

    @Override
    public void onConnectionSuspended(int i) {
        myBase.getLoginGooglePlusService().onConnectionSuspended(i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        myBase.getLoginGooglePlusService().onConnectionFailed(connectionResult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        myBase.getLoginGooglePlusService().onActivityResult(requestCode, resultCode, data);
    }


}
