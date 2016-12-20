package animation.rpires.com.br.exemplosmaterialdesign.fragments.tabs;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;

import java.util.LinkedList;
import java.util.Locale;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.adapter.LanguageAdapter;

/**
 * Created by rpires on 20/12/2016.
 */

public class LanguageTabFabButtonProgressBackgroundFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private int mMaxProgress = 100;
    private int mScrollOffset = 4;
    private LinkedList<ProgressType> mProgressTypes;
    private Handler mUiHandler = new Handler();

    private enum ProgressType {
        INDETERMINATE, PROGRESS_POSITIVE, PROGRESS_NEGATIVE, HIDDEN, PROGRESS_NO_ANIMATION, PROGRESS_NO_BACKGROUND
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Locale[] availableLocales = Locale.getAvailableLocales();
        mProgressTypes = new LinkedList<>();
        for (ProgressType type : ProgressType.values()) {
            mProgressTypes.offer(type);
        }

        View view =  inflater.inflate(R.layout.fragment_language_tabs_fab_button_progress_background, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_fab_progress);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new LanguageAdapter(availableLocales));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > mScrollOffset) {
                    if (dy > 0) {
                        fab.hide(true);
                    } else {
                        fab.show(true);
                    }
                }
            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.fab_progress_background);
        fab.setMax(mMaxProgress);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressType type = mProgressTypes.poll();
                switch (type) {
                    case INDETERMINATE:
                        fab.setShowProgressBackground(true);
                        fab.setIndeterminate(true);
                        mProgressTypes.offer(ProgressType.INDETERMINATE);
                        break;
                    case PROGRESS_POSITIVE:
                        fab.setIndeterminate(false);
                        fab.setProgress(70, true);
                        mProgressTypes.offer(ProgressType.PROGRESS_POSITIVE);
                        break;
                    case PROGRESS_NEGATIVE:
                        fab.setProgress(30, true);
                        mProgressTypes.offer(ProgressType.PROGRESS_NEGATIVE);
                        break;
                    case HIDDEN:
                        fab.hideProgress();
                        mProgressTypes.offer(ProgressType.HIDDEN);
                        break;
                    case PROGRESS_NO_ANIMATION:
                        increaseProgress(fab, 0);
                        break;
                    case PROGRESS_NO_BACKGROUND:
                        fab.setShowProgressBackground(false);
                        fab.setIndeterminate(true);
                        mProgressTypes.offer(ProgressType.PROGRESS_NO_BACKGROUND);
                        break;
                }
            }
        });
        return view;
    }

    private void increaseProgress(final FloatingActionButton fab, int i) {
        if (i <= mMaxProgress) {
            fab.setProgress(i, false);
            final int progress = ++i;
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    increaseProgress(fab, progress);
                }
            }, 30);
        } else {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    fab.hideProgress();
                }
            }, 200);
            mProgressTypes.offer(ProgressType.PROGRESS_NO_ANIMATION);
        }
    }
}
