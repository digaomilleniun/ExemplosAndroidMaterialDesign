package animation.rpires.com.br.exemplosmaterialdesign.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionMenu;

import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.CarroCardViewAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.domain.Carro;
import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.ConectividadeUtils;


public class SwipeRefreshFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    private List<Carro> listCarros;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionMenu fab;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public SwipeRefreshFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_swipe_refresh, container, false);

        //RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_swipe_refresh);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lnm = new LinearLayoutManager(getActivity());
        lnm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lnm);

        listCarros = CarroCardViewFragment.getListaCarros();
        CarroCardViewAdapter adapter = new CarroCardViewAdapter(getActivity(), listCarros);
        //adapter.setRecyclerViewOnClickListener(this);//Umas das formas de gerar envendo de click na tela
        recyclerView.setAdapter(adapter);
        //RecyclerView

        //FloatingActionMenu
        fab = (FloatingActionMenu) view.findViewById(R.id.menu_yellow_fab1);
        fab.setClosedOnTouchOutside(true);
        fab.setVerticalScrollBarEnabled(true);
        //FloatingActionMenu


        //SwipeRefreshLayout
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (ConectividadeUtils.isConnected(getActivity())) {
                    LinearLayoutManager lnm = (LinearLayoutManager) recyclerView.getLayoutManager();
                    CarroCardViewAdapter adapter = (CarroCardViewAdapter) recyclerView.getAdapter();

                    visibleItemCount = recyclerView.getChildCount();
                    totalItemCount = lnm.getItemCount();
                    firstVisibleItem = lnm.findFirstVisibleItemPosition();

                    //Limita até 50 registros
                    if (totalItemCount < 50) {
                        if (loading) {
                            if (totalItemCount > previousTotal) {
                                loading = false;
                                previousTotal = totalItemCount;
                            }
                        }

                        if (!loading && (totalItemCount - visibleItemCount)
                                <= (firstVisibleItem + visibleThreshold)) {
                            // End has been reached

                            List<Carro> listAux = CarroCardViewFragment.getListaCarros();
                            for (int i = 0; i < listAux.size(); i++) {
                                Carro car = listAux.get(i);
                                adapter.addItem(car, 0);
                                lnm.smoothScrollToPosition(recyclerView, null, 0);
                            }

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    SystemClock.sleep(2000);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRefreshLayout.setRefreshing(false);
                                        }
                                    });
                                }
                            }).start();
                            loading = true;
                        }
                    }
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    Snackbar snackbar = Snackbar
                            .make(fab, "Sem conexão de internet!", Snackbar.LENGTH_LONG)
                            .setAction("Conectar", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent it = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                    startActivity(it);
                                }


                            });

                    // Changing message text color
                    snackbar.setActionTextColor(Color.RED);
                    snackbar.show();
                }
            }
        });
        //SwipeRefreshLayout

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
