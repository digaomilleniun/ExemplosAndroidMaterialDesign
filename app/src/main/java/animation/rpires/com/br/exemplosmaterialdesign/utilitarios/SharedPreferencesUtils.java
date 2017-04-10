package animation.rpires.com.br.exemplosmaterialdesign.utilitarios;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rpires on 16/02/2017.
 */

public class SharedPreferencesUtils {

    public static String PREF = "animation.rpires.com.br.exemplosmaterialdesign.PREF";
    private static DatabaseReference databaseReferenceFirebase;

    public static DatabaseReference getFirebase(){
        if( databaseReferenceFirebase == null ){
            databaseReferenceFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return( databaseReferenceFirebase );
    }

    static public void saveSP(Context context, String key, String value ){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    static public String getSP(Context context, String key ){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String token = sp.getString(key, "");
        return( token );
    }
}
