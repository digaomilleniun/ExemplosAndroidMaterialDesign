package animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.TabsAdapterCustomizadas;
import animation.rpires.com.br.exemplosmaterialdesign.tabLayout.SlidingTabLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarroTabsCustomizadaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarroTabsCustomizadaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarroTabsCustomizadaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    FragmentManager fragmentManager;
    Boolean utilizarIcones = false;

    public CarroTabsCustomizadaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarroTabsCustomizadaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarroTabsCustomizadaFragment newInstance(String param1, String param2) {
        CarroTabsCustomizadaFragment fragment = new CarroTabsCustomizadaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            utilizarIcones = getArguments().getBoolean("UTILIZAR_ICONES");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_carro_tabs_customizada, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();
        viewPager = (ViewPager) view.findViewById(R.id.view_page_tab_carros_customizadas);
        viewPager.setAdapter(new TabsAdapterCustomizadas(fragmentManager, getActivity(), utilizarIcones));

        slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.tab_carros_customizadas);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabView(R.layout.tab_view_icone, R.id.tab_icone);
        slidingTabLayout.setViewPager(viewPager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getActivity().getTheme()));
            slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent, getActivity().getTheme()));
        } else {
            slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        }


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
