package animation.rpires.com.br.exemplosmaterialdesign.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.ExemploCorAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.ListaCollapsingAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;


public class ListaCollapsingToolbarFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Activity activity;
    private List<ExemploCor> exemploCors;

    public ListaCollapsingToolbarFragment() {
        // Required empty public constructor
//        setupSamples();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_collapsing_toolbar, container, false);

        setupWindowAnimations(view);
        setupSamples();
        setupLayout(view);
        return view;
    }

    private void setupSamples() {
        exemploCors = Arrays.asList(
                new ExemploCor(R.color.sample_green, "Collapsing Pin"),
                new ExemploCor(R.color.sample_yellow, "Collapsing Parallax"),
                new ExemploCor(R.color.sample_blue, "Collapsing Tabs")
        );
    }

    private void setupWindowAnimations(View view) {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(500);
//        getWindow().setReenterTransition(slideTransition);
//        getWindow().setExitTransition(slideTransition);
    }

    private void setupLayout(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ListaCollapsingAdapter adapter = new ListaCollapsingAdapter(activity, exemploCors);
        recyclerView.setAdapter(adapter);
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

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setExemploCors(List<ExemploCor> exemploCors) {
        this.exemploCors = exemploCors;
    }
}
