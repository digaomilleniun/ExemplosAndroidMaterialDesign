package animation.rpires.com.br.exemplosmaterialdesign.activity.transition;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.View;
import android.widget.Button;


import animation.rpires.com.br.exemplosmaterialdesign.BaseDetailActivity;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.databinding.ActivityTransition1Binding;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;

public class TransitionActivity1 extends BaseDetailActivity {

    private ExemploCor exemploCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void bindData() {
        ActivityTransition1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_transition1);
        exemploCor = (ExemploCor) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setTransition1Sample(exemploCor);
    }

    private void setupWindowAnimations() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }


    private void setupLayout() {
        Button btnSample1 = (Button) findViewById(R.id.sample1_button1);
        btnSample1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransitionActivity1.this, TransitionActivity2.class);
                i.putExtra(EXTRA_SAMPLE, exemploCor);
                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);
            }
        });

        Button btnSample2 = (Button) findViewById(R.id.sample1_button2);
        btnSample2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransitionActivity1.this, TransitionActivity2.class);
                i.putExtra(EXTRA_SAMPLE, exemploCor);
                i.putExtra(EXTRA_TYPE, TYPE_XML);
                transitionTo(i);
            }
        });

        Button btnSample3 = (Button) findViewById(R.id.sample1_button3);
        btnSample3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransitionActivity1.this, TransitionActivity3.class);
                i.putExtra(EXTRA_SAMPLE, exemploCor);
                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);
            }
        });

        Button btnSample4 = (Button) findViewById(R.id.sample1_button4);
        btnSample4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransitionActivity1.this, TransitionActivity3.class);
                i.putExtra(EXTRA_SAMPLE, exemploCor);
                i.putExtra(EXTRA_TYPE, TYPE_XML);
                transitionTo(i);
            }
        });

        Button btnSample5 = (Button) findViewById(R.id.sample1_button5);
        btnSample5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visibility returnTransition = buildReturnTransition();
                getWindow().setReturnTransition(returnTransition);

                finishAfterTransition();
            }
        });

        Button btnSample6 = (Button) findViewById(R.id.sample1_button6);
        btnSample6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * If no return transition is defined Android will use reversed enter transition
                 * In this case, return transition will be a reversed Slide (defined in buildEnterTransition)
                 */
                finishAfterTransition();
            }
        });
    }

    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // This view will not be affected by enter transition animation
        enterTransition.excludeTarget(R.id.square_red, true);
        return enterTransition;
    }

    private Visibility buildReturnTransition() {
        Visibility enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        return enterTransition;
    }
}
