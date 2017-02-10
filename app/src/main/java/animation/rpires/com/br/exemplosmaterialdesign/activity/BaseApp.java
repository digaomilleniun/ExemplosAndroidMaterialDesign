package animation.rpires.com.br.exemplosmaterialdesign.activity;

import android.app.Activity;
import android.app.Application;

import java.io.Serializable;

import animation.rpires.com.br.exemplosmaterialdesign.observable.LoginObservable;
import animation.rpires.com.br.exemplosmaterialdesign.service.LoginGooglePlusService;

/**
 * Created by rpires on 08/02/2017.
 */

public class BaseApp extends Application implements Serializable {

    private LoginObservable loginObservable;
    private LoginGooglePlusService loginGooglePlusService;

    @Override
    public void onCreate() {
        super.onCreate();
        loginObservable = new LoginObservable();

    }

    public void build(Activity activity) {
        loginGooglePlusService = new LoginGooglePlusService(activity, this);
    }

    public LoginObservable getLoginObservable() {
        return loginObservable;
    }

    public LoginGooglePlusService getLoginGooglePlusService() {
        return loginGooglePlusService;
    }
}
