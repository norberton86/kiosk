package kiosk.ddc.a3nomdev.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.R;
import kiosk.ddc.a3nomdev.myapplication.ResultActivity;
import kiosk.ddc.a3nomdev.myapplication.model.Accompany;
import kiosk.ddc.a3nomdev.myapplication.model.Client;

/**
 * Created by 3nomdev on 10/17/17.
 */

public class AccompanyAdapter  extends RecyclerView.Adapter<AccompanyAdapter.MainViewHolder> {
    private List<Accompany> data;
    private ResultActivity activity;


    public AccompanyAdapter(@NonNull List<Accompany> data, ResultActivity activity) {
        this.data = data;
        this.activity=activity;
    }

    @Override
    public AccompanyAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.accompany_item_layout, parent, false);
        return new AccompanyAdapter.MainViewHolder(row);
    }

    @Override
    public void onBindViewHolder(AccompanyAdapter.MainViewHolder holder, int position) {
        final Accompany accompany = data.get(position);
        holder.getTextViewName().setText(accompany.getName());
        holder.getCheckBoxStatus().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder  {
        private CheckBox checkBoxStatus;
        private TextView textViewName;


        public MainViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewNameAccompany);
            checkBoxStatus=(CheckBox)itemView.findViewById(R.id.checkBoxStatus);
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public CheckBox getCheckBoxStatus() {
            return checkBoxStatus;
        }



    }
}