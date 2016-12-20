package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.CarroTabFabButtonFixaFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.CarroTabFabButtonHideShowFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.LanguageTabFabButtonProgressBackgroundFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.MenusTabFabFragment;

/**
 * Created by rpires on 19/12/2016.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private String[] titles = {"Fab Fixo Social", "Fab Hide/Show", "Fab Progress Background", "Menu Fabs"};

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new CarroTabFabButtonFixaFragment();
        } else if (position == 1) {
            fragment = new CarroTabFabButtonHideShowFragment();
        } else if (position == 2) {
            fragment = new LanguageTabFabButtonProgressBackgroundFragment();
        } else if (position == 3) {
            fragment = new MenusTabFabFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
