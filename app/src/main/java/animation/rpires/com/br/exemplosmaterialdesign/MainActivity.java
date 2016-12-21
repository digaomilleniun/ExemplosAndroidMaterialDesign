package animation.rpires.com.br.exemplosmaterialdesign;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import animation.rpires.com.br.exemplosmaterialdesign.activity.BotaoCustomizadoActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.ToolbarSocialActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.transition.ListaTransitionsActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.transition.SlideTransitionActivity;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.CarroCardViewFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.CarroRecyclerViewFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.FragmentBase;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.SobreFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.SwipeRefreshFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.CarroTabsCustomizadaFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.CarroTabsFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.TabsFloatingButtonsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SobreFragment fragment = new SobreFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            Intent it = new Intent(this, ToolbarSocialActivity.class);
            startActivity(it);
            //NavUtils.navigateUpTo(this, it);
        } else if (id == R.id.nav_recycle_view) {
            CarroRecyclerViewFragment carroFragment =
                    (CarroRecyclerViewFragment) getSupportFragmentManager().findFragmentByTag("fragment_container");
            if (carroFragment == null) {
                carroFragment = new CarroRecyclerViewFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, carroFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_botao_customizado) {
            Intent it = new Intent(this, BotaoCustomizadoActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_card_view) {
            CarroCardViewFragment carroFragment =
                    (CarroCardViewFragment) getSupportFragmentManager().findFragmentByTag("fragment_container");
            if (carroFragment == null) {
                carroFragment = new CarroCardViewFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, carroFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_tabs) {
            CarroTabsFragment carroFragment =
                    (CarroTabsFragment) getSupportFragmentManager().findFragmentByTag("fragment_container");
            if (carroFragment == null) {
                carroFragment = new CarroTabsFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, carroFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_tabs_customizada) {
            CarroTabsCustomizadaFragment carroFragment =
                    (CarroTabsCustomizadaFragment) getSupportFragmentManager().findFragmentByTag("fragment_container");
            if (carroFragment == null) {
                carroFragment = new CarroTabsCustomizadaFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, carroFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_tabs_customizada_s_icones) {
            CarroTabsCustomizadaFragment carroFragment =
                    (CarroTabsCustomizadaFragment) getSupportFragmentManager().findFragmentByTag("fragment_container");
            if (carroFragment == null) {
                Bundle args = new Bundle();
                args.putBoolean("UTILIZAR_ICONES", true);
                carroFragment = new CarroTabsCustomizadaFragment();
                carroFragment.setArguments(args);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, carroFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_tabs_floating_button) {
            TabsFloatingButtonsFragment carroFragment =
                    (TabsFloatingButtonsFragment) getSupportFragmentManager().findFragmentByTag("fragment_container");
            if (carroFragment == null) {
                carroFragment = new TabsFloatingButtonsFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, carroFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_swipe_refresh) {
            SwipeRefreshFragment carroFragment =
                    (SwipeRefreshFragment) getSupportFragmentManager().findFragmentByTag("fragment_container");
            if (carroFragment == null) {
                carroFragment = new SwipeRefreshFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, carroFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_transitions_activity) {
            Intent it = new Intent(this, ListaTransitionsActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_transitions_fragment) {

        }

        /**
         * Quando é selecionado no menu do aplicativo a opção de tabs
         * eu troco os padding para não haver espaços entre a toolbar e as tabs.
         * Quando é selecionado qualquer outro menu, volto para os paddings normais da página.
         */
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        double scale = displayMetrics.density;
        View view = findViewById(R.id.content_main);

        if (id == R.id.nav_tabs_customizada || id == R.id.nav_tabs_customizada_s_icones || id == R.id.nav_tabs) {
            view.setPadding(0,0,0,0);
        } else {
            //Calculo para DP
            int num1 = (int) (16 * scale * 0.5f);
            int num2 = (int) (64 * scale * 0.5f);
            int ele = (int) (4 * scale * 0.5f);
            view.setPadding(num1,num2,num2,num1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
