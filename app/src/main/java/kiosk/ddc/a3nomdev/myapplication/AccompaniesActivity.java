package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import kiosk.ddc.a3nomdev.myapplication.adapter.AccompanyAdapter;
import kiosk.ddc.a3nomdev.myapplication.model.User;
import kiosk.ddc.a3nomdev.myapplication.model.UserCollection;
import kiosk.ddc.a3nomdev.myapplication.service.ddcService;
import rx.Observer;

public class AccompaniesActivity extends AppCompatActivity {

    @InjectView(R.id.recyclerViewAccompany) RecyclerView recyclerViewAccompany;
    @InjectView(R.id.UserConfirm) Button UserConfirm;

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

        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);

        initFriends(userSelected);
    }

    ArrayList<Integer> getIds()
    {
        ArrayList<Integer> result=new ArrayList<Integer>();
        for(User u: friends)  //collect all selected ids
        {
            if(u.getAttended())
            {
                result.add(u.getPersonID());
            }
        }

        return result;
    }

    @OnClick(R.id.buttonSelectAll)
    void All() {

        all=!all;

        for(User u : friends)
        {
            u.setAttended(all);
        }
        recyclerViewAccompany.setAdapter(new AccompanyAdapter(friends,AccompaniesActivity.this));
        recyclerViewAccompany.setLayoutManager(new LinearLayoutManager(AccompaniesActivity.this));
        recyclerViewAccompany.addItemDecoration(new DividerItemDecoration(AccompaniesActivity.this,0));
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

        ddcService.GetFriends(u.getPersonID(),u.getFamilyId())
                .subscribe(new Observer<List<User>>() {

                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                                   HideLoading();
                                   Toast.makeText(AccompaniesActivity.this, "Error trying to get Accompanies", Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public void onNext(List<User> users) {

                                   HideLoading();

                                   users.add(0,userSelected);
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
        if(ids.size()==0)
        {
            Toast.makeText(this,"Select at least one person",Toast.LENGTH_LONG).show();
            Shake(UserConfirm);
            return;
        }

        UserConfirm.setEnabled(false);
        UserConfirm.setText("Checking...");

        ShowLoading();

        ddcService.Post(Integer.parseInt(userSelected.getReservationId()) ,ids)
                .subscribe(new Observer<String>() {

                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                                   HideLoading();
                                   EnableButton();
                                   Toast.makeText(AccompaniesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
