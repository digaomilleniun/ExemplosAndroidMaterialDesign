package animation.rpires.com.br.exemplosmaterialdesign.utilitarios;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

/**
 * Created by rpires on 20/12/2016.
 */

public class FloatingActionButtonBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public FloatingActionButtonBehavior(Context context, AttributeSet attrs) {
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        child.setTranslationY(translationY);
        return true;
    }

}
