package animation.rpires.com.br.exemplosmaterialdesign.fragment;

import android.app.Fragment;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.view.SamplePagerAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.tabLayout.SlidingTabLayout;

/**
 * Created by rpires on 30/01/2017.
 */

public class SlidingTabFragment extends Fragment {

    static final String LOG_TAG = "SlidingTabsBasicFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dialer_sliding, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter(getActivity()));

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }
}
