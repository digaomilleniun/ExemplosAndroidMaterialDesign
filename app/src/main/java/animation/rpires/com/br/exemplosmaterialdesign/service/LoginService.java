package animation.rpires.com.br.exemplosmaterialdesign.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.activity.BaseApp;

/**
 * Created by rpires on 10/02/2017.
 */

public class LoginService extends AppCompatActivity {

    private Activity activity;
    private LoginListener listener;
    private ProgressDialog mProgressDialog;
    private BaseApp myBase;

    public LoginService(Activity activity, LoginListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.myBase = (BaseApp) getApplication();
    }

    @Override
    public void onStart() {
//        super.onStart();
        this.listener.onStart();
    }

    @Override
    public void onStop() {
//        super.onStop();
        this.listener.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        listener.onActivityResult(requestCode,resultCode, data);
        if (resultCode == 0) {
            hideProgressDialog();
        }
    }

    public void initializerIfUserLogged() {
        showProgressDialog();
        listener.initializerIfUserLogged();
        hideProgressDialog();
    }

    public void signIn() {
        showProgressDialog();
        this.listener.signIn();
    }

    public void signOut() {
        showProgressDialog();
        this.listener.signOut();
    }

    public void revokeAccess() {
        showProgressDialog();
        this.listener.revokeAccess();
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

    public interface LoginListener {
        void onStart();
        void onStop();
        void onActivityResult(int requestCode, int resultCode, Intent data);
//        void onConnected(@Nullable Bundle bundle);
//        void onConnectionSuspended(int i);
//        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
        boolean isConnected();
        void initializerIfUserLogged();
        void signIn();
        void signOut();
        void revokeAccess();
    }
}
