package kiosk.ddc.a3nomdev.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import kiosk.ddc.a3nomdev.myapplication.R;
import kiosk.ddc.a3nomdev.myapplication.util.LocalStorage;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    @InjectView(R.id.buttonSaveSettings) Button buttonSaveSettings;

    @InjectView(R.id.editUrlSettings) EditText editUrlSettings;
    @InjectView(R.id.editPassSetting) EditText editPassSetting;
    @InjectView(R.id.editUserSetting) EditText editUserSetting;

    @InjectView(R.id.editID) EditText editID;
    @InjectView(R.id.editDescription) EditText editDescription;


    public SettingFragment() {
        // Required empty public constructor
    }


    @OnClick(R.id.buttonSaveSettings)
    void userClick() {

        if(editPassSetting.getText().toString().equals("")||editUrlSettings.getText().toString().equals("")||editUserSetting.getText().toString().equals("")||editDescription.getText().toString().equals(""))
        {
            Shake(buttonSaveSettings);
            showDialog("Error","Not allowed empty fields");
        }
        else
        {
            LocalStorage.setLogin(getActivity(),editUserSetting.getText().toString(),editPassSetting.getText().toString());


            String url=editUrlSettings.getText().toString();
            String uuid=editID.getText().toString();
            String description=editDescription.getText().toString();

            LocalStorage.setSettings(getActivity(),url,uuid,description);
            getActivity().finish();
        }
    }

    void showDialog(String title,String message)
    {
        new LovelyInfoDialog(getActivity())
                .setTopColorRes(R.color.colorPrimary)
                .setIcon(R.mipmap.information)
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.inject(this,view);

        editPassSetting.setText(LocalStorage.getPass(getActivity()));
        editUserSetting.setText(LocalStorage.getUser(getActivity()));
        editUrlSettings.setText(LocalStorage.getServer(getActivity()));
        editID.setText(LocalStorage.getID(getActivity()));
        editDescription.setText(LocalStorage.getDescription(getActivity()));
        // Inflate the layout for this fragment;

        return view;
    }

    void Shake(View v)
    {
        final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        v.startAnimation(animShake);
    }

}
