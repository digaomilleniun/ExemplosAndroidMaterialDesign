package animation.rpires.com.br.exemplosmaterialdesign.activity.transition;

import android.animation.Animator;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.ViewPropertyAnimator;

import android.widget.RelativeLayout;

import animation.rpires.com.br.exemplosmaterialdesign.BaseDetailActivity;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.AnimatorAdapter;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.TransitionAdapter;

public class MudancaLayoutActivity2_1 extends BaseDetailActivity {

    private static final int SCALE_DELAY = 30;
    private RelativeLayout rowContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudanca_layout_activity2_1);
        setupToolbar();

        rowContainer = (RelativeLayout) findViewById(R.id.row_container3);

        Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);

        getWindow().getEnterTransition().addListener(new TransitionAdapter() {

            @Override
            public void onTransitionEnd(Transition transition) {

                super.onTransitionEnd(transition);

                getWindow().getEnterTransition().removeListener(this);

                for (int i = 0; i < rowContainer.getChildCount(); i++) {

                    View rowView = rowContainer.getChildAt(i);
                    rowView.animate().setStartDelay(i * SCALE_DELAY)
                            .scaleX(1).scaleY(1);
                }
            }
        });
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

    @Override
    public void onBackPressed() {

        for (int i = 0; i < rowContainer.getChildCount(); i++) {

            View rowView = rowContainer.getChildAt(i);

            ViewPropertyAnimator propertyAnimator = rowView.animate()
                    .setStartDelay(i * SCALE_DELAY)
                    .scaleX(0).scaleY(0)
                    .setListener(new AnimatorAdapter() {

                        @Override
                        public void onAnimationEnd(Animator animation) {

                            super.onAnimationEnd(animation);
                            finishAfterTransition();
                        }
                    });
        }
    }
}
