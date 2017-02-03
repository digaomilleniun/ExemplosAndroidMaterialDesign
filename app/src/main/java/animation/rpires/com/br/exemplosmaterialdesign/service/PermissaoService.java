package animation.rpires.com.br.exemplosmaterialdesign.service;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by rpires on 02/02/2017.
 */

public class PermissaoService {

    public static Boolean isPossuiPermissaoGPS(Activity activity, int myPermissionRequest) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            solicitarPermissao(activity, myPermissionRequest);
            return false;
        }
        return true;
    }

    private static void solicitarPermissao(Activity activity, int myPermissionRequest) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            // Show an expanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            abrirDialogoExplicativo(activity, myPermissionRequest);
        } else {
            // No explanation needed, we can request the permission.
            abrirDialogoNativoPermissao(activity, myPermissionRequest);
        }
    }

    private static void abrirDialogoNativoPermissao(final Activity activity, int myPermissionRequest) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                myPermissionRequest);
    }

    private static void abrirDialogoExplicativo(final Activity activity, final int myPermissionRequest) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                abrirDialogoNativoPermissao(activity, myPermissionRequest);
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });

        final AlertDialog alerta = builder.create();
        alerta.setTitle("Permissões do aplicativo");
        alerta.setMessage("Seu aplicativo não tem as permissões necessárias para o correto funcionamento. Deseja habilitar a permissão de GPS?");
        alerta.setCancelable(true);
        alerta.show();
    }
}
