package animation.rpires.com.br.exemplosmaterialdesign;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;



import java.io.InputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import animation.rpires.com.br.exemplosmaterialdesign.activity.BaseApp;
import animation.rpires.com.br.exemplosmaterialdesign.activity.BotaoCustomizadoActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.MenuSlidingActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.ToolbarSocialActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.transition.ListaTransitionsActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.transition.MudancaLayoutActivity;
import animation.rpires.com.br.exemplosmaterialdesign.activity.transition.SwitchThemeActivity;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.CarroCardViewFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.CarroRecyclerViewFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.EscolhaLoginFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.FragmentBase;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.ListaCollapsingToolbarFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.ListaMapasFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.SobreFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.SwipeRefreshFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.CarroTabsCustomizadaFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.CarroTabsFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.TabsFloatingButtonsFragment;
import animation.rpires.com.br.exemplosmaterialdesign.observable.LoginObservable;
import animation.rpires.com.br.exemplosmaterialdesign.service.ConnectionLoginBase;
import animation.rpires.com.br.exemplosmaterialdesign.service.GoogleLoginService;
import animation.rpires.com.br.exemplosmaterialdesign.service.LoginService;
import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.CircleImageView2;

public class MainActivity extends AppCompatActivity //ConnectionLoginBase
        implements NavigationView.OnNavigationItemSelectedListener, FragmentBase, Observer {

    private NavigationView navigationView;
    private CircleImageView2 profileImage;
    private TextView profileName;
    private TextView profileEmail;
    private MenuItem navEntrar;
    private MenuItem navSair;
    private MenuItem navRevogar;

    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        buildView();

        BaseApp myBase = (BaseApp) getApplication();
        myBase.getLoginObservable().addObserver(this);

//        super.build(this);
//        inicializarUsuarioSeLogado();

        loginService = new LoginService(this, new GoogleLoginService(this, myBase));
        loginService.initializerIfUserLogged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (loginService != null) {
            loginService.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (loginService != null) {
            loginService.onStop();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GoogleLoginService.SIGN_IN && loginService != null) {
            loginService.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void buildView() {
        View navHeader = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        profileImage = (CircleImageView2) navHeader.findViewById(R.id.profile_image);
        profileName = (TextView) navHeader.findViewById(R.id.profile_name);
        profileEmail = (TextView) navHeader.findViewById(R.id.profile_email);

        Menu menu = navigationView.getMenu();
        navEntrar = menu.findItem(R.id.nav_login);
        navSair = menu.findItem(R.id.nav_logout);
        navRevogar = menu.findItem(R.id.nav_revogar_login);
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
        } else if (id == R.id.nav_transitions_troca_tema) {
            Intent it = new Intent(this, SwitchThemeActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_transitions_troca_layout) {
            Intent it = new Intent(this, MudancaLayoutActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_menu_slinding) {
            Intent it = new Intent(this, MenuSlidingActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_collapsing) {
            ListaCollapsingToolbarFragment fragment =
                    (ListaCollapsingToolbarFragment) getSupportFragmentManager().findFragmentByTag("fragment_container");
            if (fragment == null) {
                fragment = new ListaCollapsingToolbarFragment();
                fragment.setActivity(this);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_mapas) {
            ListaMapasFragment fragment =
                    (ListaMapasFragment) getSupportFragmentManager().findFragmentByTag("fragment_container");
            if (fragment == null) {
                fragment = new ListaMapasFragment();
                fragment.setActivity(this);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_login) {
            EscolhaLoginFragment fragment =
                    (EscolhaLoginFragment) getSupportFragmentManager().findFragmentByTag("fragment_container");
            if (fragment == null) {
                fragment = new EscolhaLoginFragment();
                fragment.setActivity(this);
                fragment.setLoginService(loginService);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_logout) {
//            deslogar();
            loginService.signOut();
            limparFrame();
        } else if (id == R.id.nav_revogar_login) {
//            regovarAcesso();
            loginService.revokeAccess();
            limparFrame();
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
    public void update(Observable o, Object arg) {
        if (o != null) {
            LoginObservable login = (LoginObservable) o;
            View navHeader = navigationView.getHeaderView(0);
            profileImage = (CircleImageView2) navHeader.findViewById(R.id.profile_image);
            profileName = (TextView) navHeader.findViewById(R.id.profile_name);
            profileEmail = (TextView) navHeader.findViewById(R.id.profile_email);
            Menu menu = navigationView.getMenu();
            navEntrar = menu.findItem(R.id.nav_login);
            navSair = menu.findItem(R.id.nav_logout);
            navRevogar = menu.findItem(R.id.nav_revogar_login);
            if (login.getLogado()) {
                navEntrar.setVisible(false);
                navSair.setVisible(true);
                navRevogar.setVisible(true);
                profileName.setText(login.getProfileName());
                profileEmail.setText(login.getProfileEmail());
                new LoadProfileImage().execute(login.getProfileImage());
                limparFrame();
            } else {
                navEntrar.setVisible(true);
                navSair.setVisible(false);
                navRevogar.setVisible(false);
                profileName.setText(login.getProfileName());
                profileEmail.setText(login.getProfileEmail());
                profileImage.setImageResource(R.drawable.ic_user_default);
//                hideProgressDialog();
                if (login.getCloseProgress()) {
//                    hideProgressDialog();
                    loginService.hideProgressDialog();
                }
            }
        }
    }

    private void limparFrame() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        frameLayout.removeAllViews();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class LoadProfileImage extends AsyncTask<Uri, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Uri... params) {
            Uri urldisplay = params[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new URL(urldisplay.toString()).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
//            hideProgressDialog();
            loginService.hideProgressDialog();
            profileImage.setImageBitmap(result);
        }
    }

}
