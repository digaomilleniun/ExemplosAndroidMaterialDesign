package animation.rpires.com.br.exemplosmaterialdesign.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import animation.rpires.com.br.exemplosmaterialdesign.fragment.CarroCardViewFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.CarroRecyclerViewFragment;
import animation.rpires.com.br.exemplosmaterialdesign.R;

/**
 * Created by rpires on 16/12/2016.
 *
 * A classe FragmentPagerAdapter é para ser usada somente quando possuímos poucos dados no fragment.
 * Caso possua muitos dados é recomentado utilizar a classe FragmentStatePagerAdapter que mantem o estado do fragment.
 *
 */

public class TabsAdapterCustomizadas extends FragmentPagerAdapter {

    private Context context;
    private String[] titles = {"Tab 1", "Tab 2"};
    private int [] icons = {R.drawable.ic_menu_carro, R.drawable.ic_menu_card_view};
    private int heightIcon;
    private boolean isUtilizarIcones = false;

    public TabsAdapterCustomizadas(FragmentManager fm, Context context, Boolean utilizarIcones) {
        super(fm);
        this.context = context;
        this.isUtilizarIcones = utilizarIcones;
        double scale = context.getResources().getDisplayMetrics().density;
        heightIcon = (int) (48 * scale * 0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new CarroRecyclerViewFragment();
        } else if (position == 1) {
            fragment = new CarroCardViewFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (isUtilizarIcones) {
            Drawable d = context.getResources().getDrawable(icons[position]);
            d.setBounds(0,0,heightIcon,heightIcon);

            ImageSpan span = new ImageSpan(d);
            SpannableString sp = new SpannableString(" ");
            sp.setSpan(span, 0, sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sp;
        } else {
            return titles[position];
        }
    }
}
