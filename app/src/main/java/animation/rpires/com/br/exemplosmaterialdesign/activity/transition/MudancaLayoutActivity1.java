package animation.rpires.com.br.exemplosmaterialdesign.activity.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.util.Pair;

import animation.rpires.com.br.exemplosmaterialdesign.BaseDetailActivity;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;

public class MudancaLayoutActivity1 extends BaseDetailActivity {

    private View mFabButton;
    private View mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudanca_layout1);
        setupToolbar();

        mFabButton = findViewById(R.id.fab_button);
        mHeader = findViewById(R.id.activity_transition_header);

        ExemploCor sample = (ExemploCor) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        DrawableCompat.setTint(mFabButton.getBackground(), getResources().getColor(sample.getColor()));

        Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);
        slideExitTransition.excludeTarget(R.id.activity_transition_header, true);
        getWindow().setExitTransition(slideExitTransition);
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

    public void onFabPressed(View view) {

        Intent i  = new Intent (MudancaLayoutActivity1.this, MudancaLayoutActivity1_2.class);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                MudancaLayoutActivity1.this, Pair.create(mFabButton, "fab"), Pair.create(mHeader, "holder1"));

        startActivity(i, transitionActivityOptions.toBundle());
    }
}
