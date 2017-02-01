package animation.rpires.com.br.exemplosmaterialdesign.activity;


import android.support.design.widget.CollapsingToolbarLayout;

import android.support.v4.graphics.drawable.DrawableCompat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;

import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.BaseDetailActivity;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.CarroCardViewAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.domain.Carro;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;
import animation.rpires.com.br.exemplosmaterialdesign.fragment.CarroRecyclerViewFragment;

public class PinCollapsingToolbarLayoutActivity extends BaseDetailActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private ExemploCor sample;
    private View mFabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_collapsing_toolbar_layout);
        sample = (ExemploCor) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        setupToolbar();
        setupLayout();
        setupWindowAnimations();
        setupListCars();
    }

    @Override
    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setBackgroundColor(getResources().getColor(sample.getColor()));
    }

    private void setupLayout() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_pin);
        collapsingToolbarLayout.setTitle(sample.getName());

        mFabButton = findViewById(R.id.fab_button);
        DrawableCompat.setTint(mFabButton.getBackground(), getResources().getColor(sample.getColor()));
    }

    private void setupListCars() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list_pin);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager lnm = new LinearLayoutManager(this);
        lnm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lnm);
        List<Carro> listCarros = CarroRecyclerViewFragment.getListaCarros();
        CarroCardViewAdapter adapter = new CarroCardViewAdapter(this, listCarros);
        recyclerView.setAdapter(adapter);
    }

    private void setupWindowAnimations() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }

    private Visibility buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}
