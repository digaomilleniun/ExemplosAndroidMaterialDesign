package animation.rpires.com.br.exemplosmaterialdesign.domain;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.SharedPreferencesUtils;

/**
 * Created by rpires on 16/02/2017.
 */

public class User {

    private static final String TIPO_MD5 = "MD5";
    public static String PROVIDER = "animation.rpires.com.br.exemplosmaterialdesign.domain.User.PROVIDER";

    private String id;
    private String name;
    private String email;
    private String password;
    private String newPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setNameInMap( Map<String, Object> map ) {
        if( getName() != null ){
            map.put( "name", getName() );
        }
    }

    public void setNameIfNull(String name) {
        if( this.name == null ){
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setEmailInMap( Map<String, Object> map ) {
        if( getEmail() != null ){
            map.put( "email", getEmail() );
        }
    }

    public void setEmailIfNull(String email) {
        if( this.email == null ){
            this.email = email;
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String se = criptografarSenha(password);
        this.password = se;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        String se = criptografarSenha(password);
        this.newPassword = se;
    }

    public void saveProviderSP(Context context, String token ){
        SharedPreferencesUtils.saveSP( context, PROVIDER, token );
    }
    public String getProviderSP(Context context ){
        return( SharedPreferencesUtils.getSP( context, PROVIDER) );
    }

    public boolean isSocialNetworkLogged( Context context ){
        String token = getProviderSP( context );
        return( token.contains("facebook") || token.contains("google") || token.contains("twitter") || token.contains("github") );
    }

    public void saveDB( DatabaseReference.CompletionListener... completionListener ){
        DatabaseReference firebase = SharedPreferencesUtils.getFirebase().child("users").child( getId() );
        if( completionListener.length == 0 ){
            firebase.setValue(this);
        } else{
            firebase.setValue(this, completionListener[0]);
        }
    }

    public void updateDB( DatabaseReference.CompletionListener... completionListener ){

        DatabaseReference firebase = SharedPreferencesUtils.getFirebase().child("users").child( getId() );

        Map<String, Object> map = new HashMap<>();
        setNameInMap(map);
        setEmailInMap(map);

        if( map.isEmpty() ){
            return;
        }

        if( completionListener.length > 0 ){
            firebase.updateChildren(map, completionListener[0]);
        } else{
            firebase.updateChildren(map);
        }
    }

    public void removeDB( DatabaseReference.CompletionListener completionListener ){
        DatabaseReference firebase = SharedPreferencesUtils.getFirebase().child("users").child( getId() );
        firebase.setValue(null, completionListener);
    }

    public void contextDataDB( Context context ){
        DatabaseReference firebase = SharedPreferencesUtils.getFirebase().child("users").child( getId() );
        firebase.addListenerForSingleValueEvent( (ValueEventListener) context );
    }

    public String criptografarSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance(TIPO_MD5);
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            String senhaCrip = hash.toString(16);
            return senhaCrip;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("PROBLEMA CRIPTOGRAFANDO SENHA...");
        }

    }
}
