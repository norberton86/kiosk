package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import kiosk.ddc.a3nomdev.myapplication.adapter.AccompanyAdapter;
import kiosk.ddc.a3nomdev.myapplication.model.User;
import kiosk.ddc.a3nomdev.myapplication.model.UserCollection;
import kiosk.ddc.a3nomdev.myapplication.service.ddcService;
import kiosk.ddc.a3nomdev.myapplication.util.LocalStorage;
import rx.Observer;

public class AccompaniesActivity extends AppCompatActivity {

    @InjectView(R.id.recyclerViewAccompany) RecyclerView recyclerViewAccompany;
    @InjectView(R.id.UserConfirm) Button UserConfirm;

    @InjectView(R.id.textViewNameAccompanyMain) TextView textViewNameAccompanyMain;
    @InjectView(R.id.textViewNameAccompanyAddressMain) TextView textViewNameAccompanyAddressMain;
    @InjectView(R.id.textViewNameAccompanyTableMain) TextView textViewNameAccompanyTableMain;


    @InjectView(R.id.textViewNameLetter) LinearLayout textViewNameLetter;



    User userSelected;
    List<User> friends;

    boolean all=false;


    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

    FrameLayout progressBarHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_accompanies);


        ButterKnife.inject(this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        friends=new ArrayList<User>();

        Intent i = getIntent();
        userSelected = (User)i.getSerializableExtra("User");
        userSelected.setAttended(true);

        textViewNameAccompanyMain.setText(userSelected.getLastName()+", "+userSelected.getTitle()+" "+userSelected.getFirstName());
        textViewNameAccompanyAddressMain.setText(userSelected.getFullAddress());
        textViewNameAccompanyTableMain.setText(userSelected.getTable()!=-1?"Table - "+userSelected.getTable().toString():"Table - N/A");

        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);

        initFriends(userSelected);
    }

    void hideTable()
    {

        recyclerViewAccompany.setVisibility(View.INVISIBLE);
        textViewNameLetter.setVisibility(View.INVISIBLE);
    }

    void ShowTable()
    {

        recyclerViewAccompany.setVisibility(View.VISIBLE);
        textViewNameLetter.setVisibility(View.VISIBLE);
    }

    ArrayList<Integer> getIds()
    {
        ArrayList<Integer> result=new ArrayList<Integer>();

        result.add( Integer.parseInt(userSelected.getReservationId())); //add the main user(always is by default)

        for(User u: friends)  //collect all selected ids
        {
            if(u.getAttended())
            {
                result.add(Integer.parseInt( u.getReservationId()));
            }
        }

        return result;
    }



    public void ChangeState(int id,boolean state)
    {
        for(User f :  friends){
            if(f.getPersonID().equals(id))
            {
                f.setAttended(state);
                break;
            }
        }
    }


    void ShowLoading()
    {
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);
    }

    public void initFriends(User u) {

        ShowLoading();

        ddcService.getService(LocalStorage.getServer(this)).GetFriends(u.getPersonID(),u.getFamilyId())
                .subscribe(new Observer<List<User>>() {

                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                                   HideLoading();
                                   showDialog("Error","Error trying to get Accompanies");
                               }

                               @Override
                               public void onNext(List<User> users) {

                                   HideLoading();
                                   if(users.size()>0)
                                       ShowTable();

                                   friends=users;
                                   recyclerViewAccompany.setAdapter(new AccompanyAdapter(users,AccompaniesActivity.this));
                                   recyclerViewAccompany.setLayoutManager(new LinearLayoutManager(AccompaniesActivity.this));
                                   recyclerViewAccompany.addItemDecoration(new DividerItemDecoration(AccompaniesActivity.this,0));

                               }
                           }
                );

    }


    @OnClick(R.id.UserConfirm)
    void check() {

        ArrayList<Integer>ids=getIds();

        UserConfirm.setEnabled(false);
        UserConfirm.setText("Checking...");

        ShowLoading();

        ddcService.getService(LocalStorage.getServer(this)).Post(Integer.parseInt(userSelected.getReservationId()) ,ids)
                .subscribe(new Observer<String>() {

                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                                   HideLoading();
                                   EnableButton();
                                  showDialog("Error","Error trying to checking");
                               }

                               @Override
                               public void onNext(String result) {

                                   EnableButton();

                                   if(result.equalsIgnoreCase("Done"))
                                   {

                                       HideLoading();
                                       Intent intent = new Intent(AccompaniesActivity.this, CheckInActivity.class);
                                       UserCollection uc = new UserCollection();
                                       uc.setUser(userSelected);
                                       uc.setUsers(friends);
                                       intent.putExtra("UserCollection", uc);
                                       startActivity(intent);
                                   }

                               }
                           }
                );




    }

    void showDialog(String title,String message)
    {
        new LovelyInfoDialog(this)
                .setTopColorRes(R.color.colorPrimary)
                .setIcon(R.mipmap.information)
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    void EnableButton()
    {
        UserConfirm.setEnabled(true);
        UserConfirm.setText("Confirm");
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

    void HideLoading()
    {
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolder.setAnimation(outAnimation);
        progressBarHolder.setVisibility(View.GONE);
    }

    void Shake(View v)
    {
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        v.startAnimation(animShake);
    }

}
