package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import animation.rpires.com.br.exemplosmaterialdesign.CarroCardViewFragment;

/**
 * Created by rpires on 16/12/2016.
 *
 * A classe FragmentPagerAdapter é para ser usada somente quando possuímos poucos dados no fragment.
 * Caso possua muitos dados é recomentado utilizar a classe FragmentStatePagerAdapter que mantem o estado do fragment.
 *
 */

public class TabsAdapterCustomizadas extends FragmentPagerAdapter {

    private Context context;
    private String[] titles = {"Card View", "Nenhum"};

    public TabsAdapterCustomizadas(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new CarroCardViewFragment();
        } else if (position == 1) {
            return null;
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
