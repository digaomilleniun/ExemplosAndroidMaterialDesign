package animation.rpires.com.br.exemplosmaterialdesign.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private TextView txtQuantidadeItens;
    private CoordinatorLayout coordinatorLayout;

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

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.cordinator_layout_swipe);

        txtQuantidadeItens = (TextView) view.findViewById(R.id.txtQuantidadeItens);

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

        adicionarLabelQuantidadeItens(listCarros.size(), listCarros.size());

        //SwipeRefreshLayout
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);

        // Configure the refreshing colors
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (ConectividadeUtils.isConnected(getActivity())) {
                    refresh();
                } else {
                    abrirSnackBar();
                }
            }
        });
        //SwipeRefreshLayout

        return view;
    }

    private void abrirSnackBar() {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Sem conexão de internet!", Snackbar.LENGTH_LONG)
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

    private void refresh() {
        LinearLayoutManager lnm = (LinearLayoutManager) recyclerView.getLayoutManager();
        CarroCardViewAdapter adapter = (CarroCardViewAdapter) recyclerView.getAdapter();
        int totalItemCount = lnm.getItemCount();

        if (totalItemCount < 50) {
            List<Carro> listAux = CarroCardViewFragment.getListaCarros();
            for (int i = 0; i < listAux.size(); i++) {
                Carro car = listAux.get(i);
                adapter.addItem(car, 0);
                lnm.smoothScrollToPosition(recyclerView, null, 0);
            }

            adicionarLabelQuantidadeItens(totalItemCount, listAux.size());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(1500);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            }).start();
        } else {
            txtQuantidadeItens.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            txtQuantidadeItens.setText("Não existe mais itens para ser adicionado na lista!");
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void adicionarLabelQuantidadeItens(int totalItemCount, int listSize) {
        int totalItens = totalItemCount + listSize;
        txtQuantidadeItens.setText("Quantidade de itens na lista: " + totalItens);
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
