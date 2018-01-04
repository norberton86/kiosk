package kiosk.ddc.a3nomdev.myapplication.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.AccompaniesActivity;
import kiosk.ddc.a3nomdev.myapplication.R;
import kiosk.ddc.a3nomdev.myapplication.model.User;
import kiosk.ddc.a3nomdev.myapplication.util.FontManager;

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
    public void onBindViewHolder(final AccompanyAdapter.MainViewHolder holder, int position) {
        final User accompany = data.get(position);

        String table=accompany.getTable().toString();

        holder.getTextViewName().setText(accompany.getLastName()+", "+accompany.getTitle()+" "+accompany.getFirstName()+" ("+table+")");



        holder.getCheckBoxImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Report(accompany,holder.getCheckBoxImage());
            }
        });



        holder.getTextViewName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Report(accompany,holder.getCheckBoxImage());
            }
        });


        if(accompany.getAttended())
        holder.getCheckBoxImage().setTextColor(Color.parseColor("#25b82f"));

    }

     void Report(User accompany,View view )
    {
        boolean value= !accompany.getAttended();

        if(!value)
            ((TextView)view).setTextColor(Color.parseColor("#f3f3f3"));
        else
            ((TextView)view).setTextColor(Color.parseColor("#25b82f"));

        activity.ChangeState(accompany.getPersonID(),value);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder  {
        private TextView checkboxImage;
        private TextView textViewName;



        public MainViewHolder(View itemView) {
            super(itemView);

            Typeface iconFont = FontManager.getTypeface(activity, FontManager.FONTAWESOME);
            FontManager.markAsIconContainer(itemView.findViewById(R.id.checkboxImage), iconFont);

            textViewName = (TextView) itemView.findViewById(R.id.textViewNameAccompany);

            checkboxImage = (TextView) itemView.findViewById(R.id.checkboxImage);

        }

        public TextView getTextViewName() {
            return textViewName;
        }





        public TextView getCheckBoxImage() {
            return checkboxImage;
        }

    }
}