package animation.rpires.com.br.exemplosmaterialdesign.service;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import animation.rpires.com.br.exemplosmaterialdesign.CommonActivity;
import animation.rpires.com.br.exemplosmaterialdesign.domain.User;

/**
 * Created by rpires on 16/02/2017.
 */

public class FirebaseService extends CommonActivity implements DatabaseReference.CompletionListener {

    private CommonActivity activity;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private User user;

    public FirebaseService(CommonActivity activity) {
        this.activity = activity;
        initFirebase();
    }

//    public void build(User user) {
//        this.user = user;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFirebase();
//        setupView();
    }

//    @Override
    public void onStart() {
//        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

//    @Override
    public void onStop() {
//        super.onStop();
        if( mAuthStateListener != null ){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void initFirebase() {
        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if( firebaseUser == null || (user != null && user.getId() != null )){
                    return;
                }

                if (user != null) {
                    user.setId( firebaseUser.getUid() );
                    user.saveDB( FirebaseService.this );
                }
            }
        };

//        if( databaseReferenceFirebase == null ){
//            databaseReferenceFirebase = FirebaseDatabase.getInstance().getReference();
//        }
    }

    public void createUser(User user) {
        this.user = user;
//        activity.openProgressBar();
        showProgressDialog();
        saveUser();
//        activity.closeProgressBar();
//        hideProgressDialog();
    }

    private void saveUser(){

        mAuth.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if( !task.isSuccessful() ){
//                    activity.closeProgressBar();
                    hideProgressDialog();
                }
            }
        })
        .addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                FirebaseCrash.report( e );
//                activity.showSnackbar( e.getMessage() );
                showSnackbar(e.getMessage());
            }
        });
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        mAuth.signOut();
//        activity.showToast( "Conta criada com sucesso!" );
//        activity.closeProgressBar();
        showToast("Conta criada com sucesso!");
        hideProgressDialog();
        activity.finish();
    }

    @Override
    protected int getIdToolbar() {
        return 0;
    }

    @Override
    protected String getTitleToolbar() {
        return null;
    }

    @Override
    protected boolean isHomeAsUpEnabled() {
        return false;
    }

    @Override
    protected boolean isTitleEnabled() {
        return false;
    }

    public interface FirebaseServiceListener {
        void onStart();
        void onStop();
    }
}
