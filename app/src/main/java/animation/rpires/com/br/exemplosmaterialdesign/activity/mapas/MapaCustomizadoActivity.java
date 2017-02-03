package animation.rpires.com.br.exemplosmaterialdesign.activity.mapas;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.PermissionUtils;

public class MapaCustomizadoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = "CUSTOM_GPS";
    private GoogleMap googleMap;
    private Marker marker;
    private boolean mShowPermissionDeniedDialog = false;
    LatLng latLngCustomizado = new LatLng(-23.5945035, -46.6869146);
    LatLng latLngPadrao = new LatLng(-23.595833, -46.686944);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_customizado);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG,"onMapReady()");
        this.googleMap = googleMap;
        updateMyLocation();
        adicionarMarcadorPadrao();
        adicionarMarcadorCustomizado();
        adicionarInforWindowCustomizado();
        adicionarEventos();
        adicionarAnimacaoCamera();
        //moverCameraParaMarcadorCustomizado();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {
        Log.i(TAG,"onRequestPermissionsResult()");
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, results,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            googleMap.setMyLocationEnabled(true);
        } else {
            mShowPermissionDeniedDialog = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        Log.i(TAG,"onResumeFragments()");
        super.onResumeFragments();
        if (mShowPermissionDeniedDialog) {
            PermissionUtils.PermissionDeniedDialog
                    .newInstance(false).show(getSupportFragmentManager(), "dialog");
            mShowPermissionDeniedDialog = false;
        }
    }

    private boolean checkReady() {
        if (googleMap == null) {
            return false;
        }
        return true;
    }

    private void updateMyLocation() {
        if (!checkReady()) {
            return;
        }

        // Enable the location layer. Request the location permission if needed.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, false);
        }
    }

    private void adicionarMarcadorPadrao() {
        googleMap.addMarker(new MarkerOptions().position(latLngPadrao).title("Padrão: Shopping Vila Olimpia"));
    }

    private void adicionarMarcadorCustomizado() {
        criarMarcadorCustomizado(latLngCustomizado, "Customizado: Fundação Ezute", "Teste");
    }

    public void criarMarcadorCustomizado(LatLng latLng, String title, String snippet) {
        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.title(title);
        options.snippet(snippet);
        options.draggable(true);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_verde));
        marker = googleMap.addMarker(options);
    }

    private void adicionarEventos() {
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i(TAG,"setOnMarkerClickListener()");
                return false;
            }
        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Log.i(TAG,"setOnInfoWindowClickListener()");
                marker.hideInfoWindow();
            }
        });
    }

    private void adicionarInforWindowCustomizado() {
        googleMap.setInfoWindowAdapter(new InfoWindowCustomAdapter(this));
    }

    private void adicionarAnimacaoCamera() {
        CameraPosition position = new CameraPosition.Builder().target(latLngCustomizado).zoom(14).bearing(0).tilt(90).build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        googleMap.animateCamera(update, 300, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                Log.i(TAG,"CancelableCallback.onFinish()");
            }

            @Override
            public void onCancel() {
                Log.i(TAG,"CancelableCallback.onCancel()()");
            }
        });
    }

    private void moverCameraParaMarcadorCustomizado() {

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngCustomizado, 11));
    }

    public class InfoWindowCustomAdapter implements GoogleMap.InfoWindowAdapter {

        private Activity activity;

        public InfoWindowCustomAdapter(Activity activity) {
            this.activity = activity;
        }

        //Utilizar apenas um dos métodos abaixo.
        // O primeiro vc só customizada o que está dentro da janela
        // O segundo vc cria uma janela customizada e todo o conteúdo.

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            LinearLayout ln = new LinearLayout(this.activity);
            ln.setPadding(20,20,20,20);
            ln.setBackgroundColor(Color.DKGRAY);

            TextView txt = new TextView(this.activity);
            txt.setText(marker.getTitle());
            txt.setTextColor(Color.WHITE);

            ln.addView(txt);

            return ln;
        }
    }
}
