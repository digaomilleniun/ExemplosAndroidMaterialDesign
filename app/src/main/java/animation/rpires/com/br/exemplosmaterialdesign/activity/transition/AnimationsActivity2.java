package animation.rpires.com.br.exemplosmaterialdesign.activity.transition;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import animation.rpires.com.br.exemplosmaterialdesign.BaseDetailActivity;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.databinding.ActivityAnimations2Binding;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;

public class AnimationsActivity2 extends BaseDetailActivity {

    private static final int DELAY = 100;
    private Scene scene0;
    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private Scene scene4;
    private final List<View> viewsToAnimate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupLayout();
        setupToolbar();
        setupWindowAnimations();
    }

    private void bindData() {
        ActivityAnimations2Binding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_animations2);
        ExemploCor sample = (ExemploCor) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setAnimationsSample(sample);
    }

    private void setupWindowAnimations() {
        getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(
                R.transition.slide_from_bottom));
        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                getWindow().getEnterTransition().removeListener(this);
                TransitionManager.go(scene0);
            }
        });
    }

    private void setupLayout() {
        final ViewGroup activityRoot = (ViewGroup) findViewById(R.id.buttons_group);
        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);

        scene0 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene0, this);
        scene0.setEnterAction(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < viewsToAnimate.size(); i++) {
                    View child = viewsToAnimate.get(i);
                    child.animate()
                            .setStartDelay(i * DELAY)
                            .scaleX(1)
                            .scaleY(1);

                }
            }
        });
        scene0.setExitAction(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(activityRoot);
                View title = scene0.getSceneRoot().findViewById(R.id.scene0_title);
                title.setScaleX(0);
                title.setScaleY(0);
            }
        });


        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene1, this);
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene2, this);
        scene3 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene3, this);
        scene4 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene4, this);

        View button1 = findViewById(R.id.sample3_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene1, new ChangeBounds());
            }
        });
        View button2 = findViewById(R.id.sample3_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene2, TransitionInflater.from(AnimationsActivity2.this).
                        inflateTransition(R.transition.slide_and_changebounds));
            }
        });

        View button3 = findViewById(R.id.sample3_button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene3, TransitionInflater.from(AnimationsActivity2.this).
                        inflateTransition(R.transition.slide_and_changebounds_sequential));
            }
        });

        View button4 = findViewById(R.id.sample3_button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene4, TransitionInflater.from(AnimationsActivity2.this).
                        inflateTransition(R.transition.slide_and_changebounds_sequential_with_interpolators));
            }
        });

        viewsToAnimate.add(button1);
        viewsToAnimate.add(button2);
        viewsToAnimate.add(button3);
        viewsToAnimate.add(button4);
    }
}
