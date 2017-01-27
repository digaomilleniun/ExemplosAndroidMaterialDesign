package animation.rpires.com.br.exemplosmaterialdesign.activity.transition;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.domain.ExemploCor;


public class SharedElementFragment2 extends Fragment {

    private static final String EXTRA_SAMPLE = "sample";

    public static SharedElementFragment2 newInstance(ExemploCor sample) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, sample);
        SharedElementFragment2 fragment = new SharedElementFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shared_element_fragment2, container, false);
        ExemploCor sample = (ExemploCor) getArguments().getSerializable(EXTRA_SAMPLE);

        ImageView squareBlue = (ImageView) view.findViewById(R.id.square_blue);
        DrawableCompat.setTint(squareBlue.getDrawable(), getResources().getColor(sample.getColor()));

        return view;
    }
}
