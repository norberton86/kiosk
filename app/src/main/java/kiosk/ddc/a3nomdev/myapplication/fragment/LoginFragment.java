package kiosk.ddc.a3nomdev.myapplication.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import kiosk.ddc.a3nomdev.myapplication.R;
import kiosk.ddc.a3nomdev.myapplication.SettingsActivity;
import kiosk.ddc.a3nomdev.myapplication.util.LocalStorage;


public class LoginFragment extends Fragment {

    @InjectView(R.id.buttonloginSettings) Button buttonloginSettings;

    @InjectView(R.id.editUser)
    EditText editUser;

    @InjectView(R.id.editPass)
    EditText editPass;


    SettingsActivity mCallback;

    @OnClick(R.id.buttonloginSettings)
    void userClick() {

       Login();

    }


    void Login()
    {
        if(editPass.getText().toString().equals(LocalStorage.getPass(getActivity()))&&editUser.getText().toString().equals(LocalStorage.getUser(getActivity())))
            mCallback.onSucces();
        else{
            Shake(buttonloginSettings);
            Toast.makeText(getActivity(),"User or Password incorrect",Toast.LENGTH_SHORT).show();
        }
    }

    boolean F(TextView v, int actionId, KeyEvent event)
    {
        if(getActivity().getCurrentFocus()!=null)  //this block is to hide the keyboard
        {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }

        if (actionId == EditorInfo.IME_ACTION_DONE) {

            Login();
            return true;
        }
        else {
            return false;
        }
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

        editPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return F( v,  actionId,  event);
            }
        });

        return view;
    }

    void Shake(View v)
    {
        final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        v.startAnimation(animShake);
    }


}
