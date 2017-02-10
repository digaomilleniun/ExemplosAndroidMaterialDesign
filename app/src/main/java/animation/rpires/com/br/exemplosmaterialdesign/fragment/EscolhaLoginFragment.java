package animation.rpires.com.br.exemplosmaterialdesign.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.gms.common.SignInButton;

import animation.rpires.com.br.exemplosmaterialdesign.MainActivity;
import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.service.LoginService;


public class EscolhaLoginFragment extends Fragment implements  View.OnClickListener, View.OnTouchListener {

    private Activity activity;
    private LoginService loginService;
    private Animation _animUp;
    private Animation _animDown;
    private ImageView imageGoogle = null;
    private ImageView imageFacebook = null;
    private ImageView imageFirebase = null;
    private ImageView imageEmail = null;
    private SignInButton btnGooglePlus;

    private OnFragmentInteractionListener mListener;

    public EscolhaLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_escolha_login, container, false);
        initView(view);
        configureEffectImage();
        configureClick();
        return view;
    }

    private void initView(View view) {
        _animDown = AnimationUtils.loadAnimation(getActivity(), R.anim.button_scale_down);
        _animUp = AnimationUtils.loadAnimation(getActivity(), R.anim.button_scale_up);
        imageGoogle = (ImageView) view.findViewById(R.id.btnSignGoogleCustom);
        imageFacebook = (ImageView) view.findViewById(R.id.btnSignFacebook);
        imageFirebase = (ImageView) view.findViewById(R.id.btnSignFirebase);
        imageEmail = (ImageView) view.findViewById(R.id.btnSignEmail);
        btnGooglePlus = (SignInButton) view.findViewById(R.id.btnSignGooglePlus);
    }


    private void configureEffectImage() {
        imageGoogle.setOnTouchListener(this);
        imageFacebook.setOnTouchListener(this);
        imageFirebase.setOnTouchListener(this);
        imageEmail.setOnTouchListener(this);

    }

    private void configureClick() {
        imageGoogle.setOnClickListener(this);
        imageFacebook.setOnClickListener(this);
        imageFirebase.setOnClickListener(this);
        imageEmail.setOnClickListener(this);
        btnGooglePlus.setOnClickListener(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        Intent it = null;
        MainActivity activityMain = (MainActivity) activity;
        switch (v.getId()) {
            case R.id.btnSignGoogleCustom:
//                activityMain.solicitarLogin();
                loginService.signIn();
                break;
            case R.id.btnSignGooglePlus:
//                activityMain.solicitarLogin();
                loginService.signIn();
                break;
        }
//        startActivity(it);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()) {
            case (android.view.MotionEvent.ACTION_DOWN) :
                v.startAnimation(_animDown);
            case (android.view.MotionEvent.ACTION_UP):
                v.startAnimation(_animUp);
                return false;
            case (android.view.MotionEvent.ACTION_CANCEL) :
                v.startAnimation(_animUp);
        }
        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
