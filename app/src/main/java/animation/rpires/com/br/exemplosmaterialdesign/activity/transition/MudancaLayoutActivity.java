package animation.rpires.com.br.exemplosmaterialdesign.activity.transition;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;

import java.util.Arrays;
import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.BaseDetailActivity;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.MudancaLayoutAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;

public class MudancaLayoutActivity extends BaseDetailActivity {

    private List<ExemploCor> exemploCors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudanca_layout);
        setupToolbar();
        setupWindowAnimations();
        setupSamples();
        setupLayout();
    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Toolbar");
        toolbar.setSubtitle("Troca de Layouts");
        toolbar.setLogo(R.mipmap.ic_launcher);
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
                new ExemploCor(R.color.sample_red, "Layout 1"),
                new ExemploCor(R.color.sample_blue, "Layout 2")
        );
    }

    private void setupLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MudancaLayoutAdapter exemploCorAdapter = new MudancaLayoutAdapter(this, exemploCors);
        recyclerView.setAdapter(exemploCorAdapter);
        //recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(this, recyclerView, this));
    }
}
