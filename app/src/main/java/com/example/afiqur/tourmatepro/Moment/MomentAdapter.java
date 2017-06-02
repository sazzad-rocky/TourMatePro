package com.example.afiqur.tourmatepro.Moment;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.afiqur.tourmatepro.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Istiyak on 07/05/17.
 */


public class MomentAdapter extends ArrayAdapter<DataModelMoment> implements View.OnClickListener{

    private ArrayList<DataModelMoment> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView tvMomentDetails;
        ImageView ivMomentImage;
        ProgressBar progressBar2;
    }



    public MomentAdapter(ArrayList<DataModelMoment> data, Context context) {
        super(context, R.layout.custom_moment_row, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModelMoment dataModelMoment =(DataModelMoment)object;

        switch (v.getId())
        {

            case R.id.item_info:

                Snackbar.make(v, dataModelMoment.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();

                break;


        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModelMoment dataModelMoment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_moment_row, parent, false);
            viewHolder.tvMomentDetails = (TextView) convertView.findViewById(R.id.tvMomentDetails);
            viewHolder.ivMomentImage = (ImageView) convertView.findViewById(R.id.ivMomentImage);
            viewHolder.progressBar2 = (ProgressBar) convertView.findViewById(R.id.progressBar2);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        viewHolder.tvMomentDetails.setText(dataModelMoment.getName());
      //  viewHolder.progressBar2.setVisibility(View.GONE);

        Picasso.with(mContext)
                .load(dataModelMoment.getLocation())
                .into(viewHolder.ivMomentImage,  new ImageLoadedCallback(viewHolder.progressBar2) {
                    @Override
                    public void onSuccess() {
                        if (viewHolder.progressBar2 != null) {
                            viewHolder.progressBar2.setVisibility(View.GONE);
                        }
                    }
                });


        // Return the completed view to render on screen
        return convertView;
    }


    private class ImageLoadedCallback implements Callback {
        ProgressBar progressBar;

        public  ImageLoadedCallback(ProgressBar progBar){
            progressBar = progBar;
        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }
    }

}
