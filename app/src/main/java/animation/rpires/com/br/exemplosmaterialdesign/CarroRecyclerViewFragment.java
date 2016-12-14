package animation.rpires.com.br.exemplosmaterialdesign;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.adapter.CarroAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.domain.Carro;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarroRecyclerViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarroRecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarroRecyclerViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private List<Carro> listCarros;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public CarroRecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarroRecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarroRecyclerViewFragment newInstance(String param1, String param2) {
        CarroRecyclerViewFragment fragment = new CarroRecyclerViewFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_carro_recycler_view, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_list_car);
        //Deixa fixo o tamanho da view e mais otimizado
        recyclerView.setHasFixedSize(true);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager lnm = (LinearLayoutManager) recyclerView.getLayoutManager();
                CarroAdapter adapter = (CarroAdapter) recyclerView.getAdapter();

                //Verifica se está no último item da lista
                if (listCarros.size() == lnm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Carro> listAux = getListaCarros();
                    for (int i = 0; i < listAux.size(); i++) {
                        Carro car = listAux.get(i);
                        adapter.addItem(car, listCarros.size());
                    }
                }
            }
        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                LinearLayoutManager lnm = (LinearLayoutManager) recyclerView.getLayoutManager();
//                CarroAdapter adapter = (CarroAdapter) recyclerView.getAdapter();
//
//                visibleItemCount = recyclerView.getChildCount();
//                totalItemCount = lnm.getItemCount();
//                firstVisibleItem = lnm.findFirstVisibleItemPosition();
//
//                if (loading) {
//                    if (totalItemCount > previousTotal) {
//                        loading = false;
//                        previousTotal = totalItemCount;
//                    }
//                }
//                if (!loading && (totalItemCount - visibleItemCount)
//                        <= (firstVisibleItem + visibleThreshold)) {
//                    // End has been reached
//
//                    List<Carro> listAux = getListaCarros();
//                    for (int i = 0; i < listAux.size(); i++) {
//                        Carro car = listAux.get(i);
//                        adapter.addItem(car, listAux.size());
//                    }
//
//                    // Do something
//
//                    loading = true;
//                }
//
//
//            }
//        });


        // Verifica se está no último item da lista
                /*if (listCarros.size() == lnm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Carro> listAux = getListaCarros();
                    for (int i = 0; i < listAux.size(); i++) {
                        Carro car = listAux.get(i);
                        adapter.addItem(car, listAux.size());
                    }
                }*/

        LinearLayoutManager lnm = new LinearLayoutManager(getActivity());
        lnm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lnm);

        listCarros = getListaCarros();
        CarroAdapter adapter = new CarroAdapter(getActivity(), listCarros);
        recyclerView.setAdapter(adapter);


        // Inflate the layout for this fragment
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

    public List<Carro> getListaCarros() {
        List<Carro> list = new ArrayList<Carro>();
        Carro car1 = new Carro("Aventador", "Lamborghini", R.drawable.lamborghini_100);
        Carro car2 = new Carro("Ferrari Vermelha", "Ferrari", R.drawable.ferrai_vermelha_100);
        Carro car3 = new Carro("Ferrari Amarela", "Ferrari", R.drawable.ferrai_amarela_100);
        Carro car4 = new Carro("Camaro Amarelo", "Camaro", R.drawable.camaro_amarelo_100);
        Carro car5 = new Carro("Mustang Vermelho", "Mustang", R.drawable.mustang_vermelho_100);

        list.add(car1);
        list.add(car2);
        list.add(car3);
        list.add(car4);
        list.add(car5);
        return list;
    }
}