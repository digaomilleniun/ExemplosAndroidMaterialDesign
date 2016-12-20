package animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.fragment.CarroCardViewFragment;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.CarroRecyclerViewFragment;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.CarroAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.domain.Carro;
import animation.rpires.com.br.exemplosmaterialdesign.domain.RecyclerViewOnClickListener;

/**
 * Created by rpires on 20/12/2016.
 */

public class CarroTabFabButtonFixaFragment extends Fragment implements RecyclerViewOnClickListener, View.OnClickListener {

    private RecyclerView recyclerView;
    private List<Carro> listCarros;
    private FloatingActionMenu fab;

    // Para fazer o cálculo da quantidade de valores que tem na lista
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_carro_tabs_fab_button_fixa, container, false);


        //RecycleView
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_fab);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager lnm = new LinearLayoutManager(getActivity());
        lnm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lnm);

        listCarros = CarroRecyclerViewFragment.getListaCarros();
        CarroAdapter adapter = new CarroAdapter(getActivity(), listCarros);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                adicionarMaisValores();
            }
        });
        //RecycleView


        //Fab
        fab = (FloatingActionMenu) view.findViewById(R.id.menu_yellow_fab);
        fab.setClosedOnTouchOutside(true);
        fab.setVerticalScrollBarEnabled(true);

        FloatingActionButton fabGoogle = (FloatingActionButton) view.findViewById(R.id.fabGoogle);
        FloatingActionButton fabFace = (FloatingActionButton) view.findViewById(R.id.fabFace);

        fabGoogle.setOnClickListener(this);
        fabFace.setOnClickListener(this);
        //Fab

        return view;
    }

    //RecycleView Click do item da lista
    @Override
    public void onClickListener(View view, int position) {

    }

    //RecycleView Long Click do item da lista
    @Override
    public void onLongClickListener(View view, int position) {

    }

    public void adicionarMaisValores() {
        LinearLayoutManager lnm = (LinearLayoutManager) recyclerView.getLayoutManager();
        CarroAdapter adapter = (CarroAdapter) recyclerView.getAdapter();

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
                    adapter.addItem(car, listCarros.size());
                }

                // Do something

                loading = true;
            }
        }
    }

    @Override
    //Fab - Click do Fab
    public void onClick(View v) {
        Intent it = null;
        switch (v.getId()) {
            case R.id.fabGoogle:
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("http://www.facebook.com"));
                break;
            case R.id.fabFace:
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("http://plus.google.com"));
                break;
        }
        fab.close(true);
        startActivity(it);
    }
}
