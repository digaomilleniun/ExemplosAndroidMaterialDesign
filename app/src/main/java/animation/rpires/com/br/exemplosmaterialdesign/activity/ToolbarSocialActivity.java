package animation.rpires.com.br.exemplosmaterialdesign.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import animation.rpires.com.br.exemplosmaterialdesign.R;

/**
 * Created by rpires on 12/12/2016.
 */

public class ToolbarSocialActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Toolbar toolbarButton;
    private ImageView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_social);

        toolbar = (Toolbar) findViewById(R.id.toolbarTopSocial);
        toolbar.setTitle("Toolbar");
        toolbar.setSubtitle("Teste de exemplo");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarButton = (Toolbar) findViewById(R.id.inc_bar_button);
        toolbarButton.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            Intent it = null;

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_button_facebook:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.facebook.com"));
                        break;
                    case R.id.menu_button_google_plus:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://plus.google.com"));
                        break;
                }

                startActivity(it);

                return true;
            }
        });
        toolbarButton.inflateMenu(R.menu.menu_button);

        settings = (ImageView) toolbarButton.findViewById(R.id.img_settings_button);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ToolbarSocialActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
