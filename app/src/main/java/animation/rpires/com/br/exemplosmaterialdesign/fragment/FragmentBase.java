package animation.rpires.com.br.exemplosmaterialdesign.fragment;

import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.CarroTabsCustomizadaFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.CarroTabsFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs.TabsFloatingButtonsFragment;

/**
 * Created by rpires on 13/12/2016.
 */

public interface FragmentBase extends SobreFragment.OnFragmentInteractionListener,
        CarroRecyclerViewFragment.OnFragmentInteractionListener,
        CarroCardViewFragment.OnFragmentInteractionListener,
        CarroTabsFragment.OnFragmentInteractionListener,
        CarroTabsCustomizadaFragment.OnFragmentInteractionListener,
        TabsFloatingButtonsFragment.OnFragmentInteractionListener {
}
