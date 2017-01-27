package animation.rpires.com.br.exemplosmaterialdesign.activity.transition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.BaseDetailActivity;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.ExemploCorAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;
import animation.rpires.com.br.exemplosmaterialdesign.domain.RecyclerViewOnClickListener;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.CarroRecyclerViewFragment;
import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.TransitionHelper;

public class ListaTransitionsActivity extends BaseDetailActivity /*implements RecyclerViewOnClickListener*/ {

    private List<ExemploCor> exemploCors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_transitions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        setupWindowAnimations();
        setupSamples();
        setupLayout();
        setupToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(500);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    private void setupSamples() {
        exemploCors = Arrays.asList(
                new ExemploCor(R.color.sample_red, "Transitions"),
                new ExemploCor(R.color.sample_blue, "Shared Elements"),
                new ExemploCor(R.color.sample_green, "View animations"),
                new ExemploCor(R.color.sample_yellow, "Circular Reveal Animation")
        );
    }

    private void setupLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExemploCorAdapter exemploCorAdapter = new ExemploCorAdapter(this, exemploCors);
        recyclerView.setAdapter(exemploCorAdapter);
        //recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(this, recyclerView, this));
    }

    /*
    @Override
    public void onClickListener(View view, int position) {
        Intent it = null;
        ExemploCor cor = exemploCors.get(position);
        switch (position) {
            case 0:
                transitionToActivity(TransitionActivity1.class, cor);
                break;
            case 1:
                transitionToActivity(SharedElementActivity.class, cor);
                break;
        }
    }

    @Override
    public void onLongClickListener(View view, int position) {

    }

    private void transitionToActivity(Class target, ExemploCor exemploCor) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        startActivity(target, pairs, exemploCor);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, ExemploCor exemploCor) {
        Intent i = new Intent(this, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        i.putExtra(EXTRA_SAMPLE, exemploCor);
        startActivity(i, transitionActivityOptions.toBundle());
    }

    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {

        private Context contex;
        private GestureDetector gestureDetector;
        private RecyclerViewOnClickListener recyclerViewOnClickListener;

        public RecyclerViewTouchListener(Context context, final RecyclerView recyclerView, final RecyclerViewOnClickListener recyclerViewOnClickListener) {
            this.contex = context;
            this.recyclerViewOnClickListener = recyclerViewOnClickListener;

            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                //Evento de click simples
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

                    if (childView != null && recyclerViewOnClickListener != null) {
                        recyclerViewOnClickListener.onClickListener(childView, recyclerView.getChildAdapterPosition(childView));
                    }
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            //Necessária para execução da implementação acima.
            gestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }*/

}
