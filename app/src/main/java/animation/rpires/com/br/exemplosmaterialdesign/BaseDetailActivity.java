package animation.rpires.com.br.exemplosmaterialdesign;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import animation.rpires.com.br.exemplosmaterialdesign.utilitarios.TransitionHelper;

/**
 * Created by rpires on 24/01/2017.
 */

public class BaseDetailActivity extends AppCompatActivity {

    protected static final String EXTRA_SAMPLE = "sample";
    protected static final String EXTRA_TYPE = "type";
    protected static final int TYPE_PROGRAMMATICALLY = 0;
    protected static final int TYPE_XML = 1;

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    protected@SuppressWarnings("unchecked") void transitionTo(Intent i) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }
}
