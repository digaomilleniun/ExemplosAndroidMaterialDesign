package animation.rpires.com.br.exemplosmaterialdesign.activity.transition;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.ExemploCorAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;

public class ListaTransitionsActivity extends AppCompatActivity {

    private List<ExemploCor> exemploCors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_transitions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setupWindowAnimations();
        setupSamples();
        setupLayout();
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
                new ExemploCor(android.R.color.holo_red_dark, "Transitions"),
                new ExemploCor(android.R.color.holo_blue_dark, "Shared Elements"),
                new ExemploCor(android.R.color.holo_green_dark, "View animations"),
                new ExemploCor(android.R.color.darker_gray, "Circular Reveal Animation")
        );
    }

    private void setupLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExemploCorAdapter exemploCorAdapter = new ExemploCorAdapter(this, exemploCors);
        recyclerView.setAdapter(exemploCorAdapter);
    }

}
