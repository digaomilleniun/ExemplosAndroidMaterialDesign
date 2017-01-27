package animation.rpires.com.br.exemplosmaterialdesign.utilitarios;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;

/**
 * Created by rpires on 27/01/2017.
 */

public class TransactionUtils {

    public static void transitionToActivity(Activity activity, Class target, ExemploCor sample, Pair... otherParticipants) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, true, otherParticipants);
        startActivity(activity, target, pairs, sample);
    }

    private static void startActivity(Activity activity, Class target, Pair<View, String>[] pairs, ExemploCor sample) {
        Intent i = new Intent(activity, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        i.putExtra("sample", sample);
        activity.startActivity(i, transitionActivityOptions.toBundle());
    }
}
