package kiosk.ddc.a3nomdev.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import kiosk.ddc.a3nomdev.myapplication.fragment.LoginFragment;
import kiosk.ddc.a3nomdev.myapplication.fragment.SettingFragment;

public class SettingsActivity extends AppCompatActivity implements LoginFragment.OnLogin {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        fragmentManager = getSupportFragmentManager();
        Transaction(new LoginFragment());

    }

    private void Transaction(Fragment dest)
    {
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();


        fragmentTransaction.replace(android.R.id.content, dest).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSucces() {

      Transaction(new SettingFragment());
    }
}
