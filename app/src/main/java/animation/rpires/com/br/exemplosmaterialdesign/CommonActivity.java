package animation.rpires.com.br.exemplosmaterialdesign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.activity.BaseApp;
import animation.rpires.com.br.exemplosmaterialdesign.service.GoogleLoginService;
import animation.rpires.com.br.exemplosmaterialdesign.service.LoginService;

/**
 * Created by rpires on 16/02/2017.
 */

public abstract class CommonActivity extends AppCompatActivity {

    protected BaseApp myBase;
    protected ProgressBar progressBar;
    protected ProgressDialog mProgressDialog;
    protected Toolbar toolbar;
    protected LoginService loginService;

    protected abstract int getIdToolbar();

    protected abstract String getTitleToolbar();

    protected abstract boolean isHomeAsUpEnabled();

    protected abstract boolean isTitleEnabled();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.myBase = (BaseApp) getApplication();
        iniciarServicosLogin();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (loginService != null) {
            loginService.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (loginService != null) {
            loginService.onStop();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GoogleLoginService.SIGN_IN && loginService != null) {
            loginService.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected void iniciarServicosLogin() {
        if (loginService == null) {
            loginService = new LoginService(this, new GoogleLoginService(this, myBase));
        }
        loginService.initializerIfUserLogged();
    }

    protected void showSnackbar(String message ){
        Snackbar.make(progressBar,
                message,
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    protected void showToast( String message ){
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }

    protected void openProgressBar(){
        progressBar.setVisibility( View.VISIBLE );
    }

    protected void closeProgressBar(){
        progressBar.setVisibility( View.GONE );
    }

    protected void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    protected void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(getIdToolbar());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isHomeAsUpEnabled());
        getSupportActionBar().setDisplayShowTitleEnabled(isTitleEnabled());
        toolbar.setTitle(getTitleToolbar());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
