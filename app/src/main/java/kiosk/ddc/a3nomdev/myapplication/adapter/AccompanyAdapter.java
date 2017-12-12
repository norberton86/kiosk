package kiosk.ddc.a3nomdev.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.AccompaniesActivity;
import kiosk.ddc.a3nomdev.myapplication.R;
import kiosk.ddc.a3nomdev.myapplication.ResultActivity;
import kiosk.ddc.a3nomdev.myapplication.model.User;

/**
 * Created by 3nomdev on 10/17/17.
 */

public class AccompanyAdapter  extends RecyclerView.Adapter<AccompanyAdapter.MainViewHolder> {
    private List<User> data;
    private AccompaniesActivity activity;


    public AccompanyAdapter(@NonNull List<User> data, AccompaniesActivity activity) {
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
        final User accompany = data.get(position);
        holder.getTextViewName().setText(accompany.getLastName()+", "+ accompany.getFirstName()+" "+accompany.getTitle());

        if(accompany.getTable()!=-1)
        holder.getTextViewTable().setText("Table - "+accompany.getTable().toString());
        else
        holder.getTextViewTable().setText("N/A");

        holder.getTextViewAddress().setText(accompany.getFullAddress());

        holder.getCheckBoxStatus().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                  activity.ChangeState(accompany.getPersonID(),b);
            }
        });
        holder.getCheckBoxStatus().setChecked(accompany.getAttended());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder  {
        private CheckBox checkBoxStatus;
        private TextView textViewName;
        private TextView textViewTable;
        private TextView textViewAddress;

        public MainViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewNameAccompany);
            textViewTable = (TextView) itemView.findViewById(R.id.textViewNameAccompanyTable);
            textViewAddress = (TextView) itemView.findViewById(R.id.textViewNameAccompanyAddress);
            checkBoxStatus=(CheckBox)itemView.findViewById(R.id.checkBoxStatus);
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public TextView getTextViewTable() {
            return textViewTable;
        }

        public CheckBox getCheckBoxStatus() {
            return checkBoxStatus;
        }

        public TextView getTextViewAddress() {
            return textViewAddress;
        }

    }
}