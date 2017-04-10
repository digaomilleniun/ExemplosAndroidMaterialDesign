package animation.rpires.com.br.exemplosmaterialdesign.service;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import animation.rpires.com.br.exemplosmaterialdesign.activity.BaseApp;
import animation.rpires.com.br.exemplosmaterialdesign.domain.User;

/**
 * Created by rpires on 16/02/2017.
 */

public class FirebaseEmailLoginService
        implements //DatabaseReference.CompletionListener,
        LoginService.LoginListener {

    private static final String TAG = "FirebaseEmailLoginServ";

    private Activity activity;
    private BaseApp baseApp;
    private FirebaseAuth mAuth;
//    private DatabaseReference databaseReferenceFirebase;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private UsuarioListner listner;
    private User user;

    public FirebaseEmailLoginService(Activity activity, BaseApp baseApp, UsuarioListner listner) {
        this.activity = activity;
        this.baseApp = baseApp;
        this.listner = listner;
        initFirebase();
    }

    private void initFirebase() {
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = getFirebaseAuthResultHandler();
//        if( databaseReferenceFirebase == null ){
//            databaseReferenceFirebase = FirebaseDatabase.getInstance().getReference();
//        }
    }

    @Override
    public void onStart() {
        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            updateUI(true, mAuth.getCurrentUser());
        }
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        if( mAuthStateListener != null ){
            mAuth.removeAuthStateListener( mAuthStateListener );
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void initializerIfUserLogged() {

    }

    @Override
    public void signIn() {
        user = listner.getUser();
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signIn:onComplete:" + task.isSuccessful());
//                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            user.saveDB();
                            updateUI(true, task.getResult().getUser());
//                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(activity, "Sign In Failed",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        FirebaseCrash.report( e );
                    }
                });
    }

    @Override
    public void signOut() {
        mAuth.signOut();
    }

    @Override
    public void revokeAccess() {

    }

//    @Override
//    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//
//    }

    private FirebaseAuth.AuthStateListener getFirebaseAuthResultHandler(){
        FirebaseAuth.AuthStateListener callback = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser userFirebase = firebaseAuth.getCurrentUser();

                if( userFirebase == null ){
                    return;
                }

                if( user.getId() == null
                        && isNameOk( user, userFirebase ) ){

                    user.setId( userFirebase.getUid() );
                    user.setNameIfNull( userFirebase.getDisplayName() );
                    user.setEmailIfNull( userFirebase.getEmail() );
                    user.saveDB();
                }

                //callMainActivity();
            }
        };
        return( callback );
    }

    private boolean isNameOk( User user, FirebaseUser firebaseUser ){
        return(user.getName() != null
                        || firebaseUser.getDisplayName() != null);
    }



    private void updateUI(boolean signedIn, FirebaseUser userFirebase) {
        Log.d(TAG, "updateUI: isSuccess:" + signedIn);
        if (signedIn) {
            baseApp.getLoginObservable().atualizarENotificar(userFirebase.getPhotoUrl(), userFirebase.getDisplayName(), userFirebase.getEmail(), true, false, TipoLogin.EMAIL_SENHA);
            activity.finish();
        } else {
            baseApp.getLoginObservable().atualizarENotificar(null, "Android Studio", "android.studio@android.com", false, false, TipoLogin.EMAIL_SENHA);
        }
    }

    public interface UsuarioListner {
        User getUser();
    }


}
