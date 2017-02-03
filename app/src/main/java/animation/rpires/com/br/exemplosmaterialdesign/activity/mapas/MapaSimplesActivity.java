package animation.rpires.com.br.exemplosmaterialdesign.activity.mapas;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.service.PermissaoService;

public class MapaSimplesActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap googleMap;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;
    private LocationManager lm;
    private Location location;
    private double longitude = -23.595833;
    private double latitude = -46.686944;
    private boolean mShowPermissionDeniedDialog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_simples);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void iniciarLocalizacao() {
        if (PermissaoService.isPossuiPermissaoGPS(this, MY_PERMISSIONS_REQUEST_LOCATION)) {
            if (lm == null && location == null) {
                lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 60000, this);
            }

            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

            if (isGoogleMapsReady()) {
                googleMap.setMyLocationEnabled(true);
                googleMap.setTrafficEnabled(true);

                googleMap.addMarker(new MarkerOptions().position(new LatLng(-23.595833, -46.686944)).title("Shopping Vila Olimpia"));
                googleMap.addMarker(new MarkerOptions().position(new LatLng(-23.97245019, -46.31767273)).title("Santos"));

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.595833, -46.686944), 11));
            }
        }
    }

    private boolean isGoogleMapsReady() {
        if (googleMap == null) {
            return false;
        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        iniciarLocalizacao();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mShowPermissionDeniedDialog) {
            iniciarLocalizacao();
            mShowPermissionDeniedDialog = false;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    iniciarLocalizacao();

                } else {
                    mShowPermissionDeniedDialog = true;
                }
                return;
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
