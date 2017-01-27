package animation.rpires.com.br.exemplosmaterialdesign.activity.transition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;

import animation.rpires.com.br.exemplosmaterialdesign.BaseDetailActivity;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.databinding.ActivityTransition3Binding;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;

public class TransitionActivity3 extends BaseDetailActivity {

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void bindData() {
        ActivityTransition3Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_transition3);
        ExemploCor sample = (ExemploCor) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
        binding.setTransition3Sample(sample);
    }

    private void setupWindowAnimations() {
        Transition transition;

        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        }  else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom);
        }
        getWindow().setEnterTransition(transition);
    }

    private void setupLayout() {
        findViewById(R.id.exit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }

    private Visibility buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }
}