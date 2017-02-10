package animation.rpires.com.br.exemplosmaterialdesign.utilitarios;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by rpires on 10/02/2017.
 */

public class EncerramentoActivity extends AppCompatActivity {

    private static EncerramentoActivity activity;
    private ProgressDialog progressDialog;

    public static EncerramentoActivity newInstance() {
        if (activity == null) {
            activity = new EncerramentoActivity();
        }
        return activity;
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }
}
