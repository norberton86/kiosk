package kiosk.ddc.a3nomdev.myapplication.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import kiosk.ddc.a3nomdev.myapplication.R;
import kiosk.ddc.a3nomdev.myapplication.SettingsActivity;


public class LoginFragment extends Fragment {

    @InjectView(R.id.buttonloginSettings) Button buttonloginSettings;
    SettingsActivity mCallback;

    @OnClick(R.id.buttonloginSettings)
    void userClick() {

        mCallback.onSucces();
    }

    public interface OnLogin {
        public void onSucces();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // De esta forma te aseguras que la activity tenga la interfaz
        // Sino saltará una excepción
        try {
            mCallback = (SettingsActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " debes implementar OnVarChangedFromFragment");
        }
    }

    public LoginFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this,view);



        return view;
    }

    void Shake(View v)
    {
        final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        v.startAnimation(animShake);
    }


}
