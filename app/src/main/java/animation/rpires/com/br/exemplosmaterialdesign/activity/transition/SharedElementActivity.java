package animation.rpires.com.br.exemplosmaterialdesign.activity.transition;


import android.os.Bundle;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;

import animation.rpires.com.br.exemplosmaterialdesign.BaseDetailActivity;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.databinding.ActivitySharedElementBinding;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;

public class SharedElementActivity extends BaseDetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExemploCor sample = (ExemploCor) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        bindData(sample);
        setupWindowAnimations();
        setupLayout(sample);
        setupToolbar();
    }

    private void bindData(ExemploCor sample) {
        ActivitySharedElementBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_shared_element);
        binding.setSharedSample(sample);
    }

    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }

    private void setupLayout(ExemploCor sample) {
        // Transition for fragment1
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // Create fragment and define some of it transitions
        SharedElementFragment1 sharedElementFragment1 = SharedElementFragment1.newInstance(sample);
        sharedElementFragment1.setReenterTransition(slideTransition);
        sharedElementFragment1.setExitTransition(slideTransition);
        sharedElementFragment1.setSharedElementEnterTransition(new ChangeBounds());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment1)
                .commit();
    }
}
