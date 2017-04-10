package animation.rpires.com.br.exemplosmaterialdesign.observable;

import android.net.Uri;

import java.util.Observable;

import animation.rpires.com.br.exemplosmaterialdesign.service.TipoLogin;

/**
 * Created by rpires on 08/02/2017.
 */

public class LoginObservable extends Observable {

    private Uri profileImage;
    private String profileName;
    private String profileEmail;
    private Boolean isLogado;
    private Boolean isCloseProgress;
    private TipoLogin tipoLogin;

    public LoginObservable() {

    }

    public LoginObservable(Uri profileImage,
                           String profileName,
                           String profileEmail,
                           Boolean isLogado,
                           Boolean isCloseProgress,
                           TipoLogin tipoLogin) {
        this.profileImage = profileImage;
        this.profileName = profileName;
        this.profileEmail = profileEmail;
        this.isLogado = isLogado;
        this.isCloseProgress = isCloseProgress;
        this.tipoLogin = tipoLogin;
        setChanged();
        notifyObservers();
    }

    public void atualizarENotificar(Uri profileImage,
                                    String profileName,
                                    String profileEmail,
                                    Boolean isLogado,
                                    Boolean isCloseProgress,
                                    TipoLogin tipoLogin) {
        this.profileImage = profileImage;
        this.profileName = profileName;
        this.profileEmail = profileEmail;
        this.isLogado = isLogado;
        this.isCloseProgress = isCloseProgress;
        this.tipoLogin = tipoLogin;
        setChanged();
        notifyObservers();
    }

    public Uri getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Uri profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileEmail() {
        return profileEmail;
    }

    public void setProfileEmail(String profileEmail) {
        this.profileEmail = profileEmail;
    }

    public Boolean getLogado() {
        return isLogado;
    }

    public void setLogado(Boolean logado) {
        isLogado = logado;
    }

    public Boolean getCloseProgress() {
        return isCloseProgress;
    }

    public void setCloseProgress(Boolean closeProgress) {
        isCloseProgress = closeProgress;
    }

    public TipoLogin getTipoLogin() {
        return tipoLogin;
    }

    public void setTipoLogin(TipoLogin tipoLogin) {
        this.tipoLogin = tipoLogin;
    }
}
