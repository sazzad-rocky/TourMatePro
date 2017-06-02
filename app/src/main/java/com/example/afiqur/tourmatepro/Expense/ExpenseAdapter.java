package com.example.afiqur.tourmatepro.Expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.afiqur.tourmatepro.R;

import java.util.ArrayList;


/**
 * Created by Istiyak on 07/05/17.
 */


public class ExpenseAdapter extends ArrayAdapter<Expense> implements View.OnClickListener{

    private ArrayList<Expense> dataSet;
    Context mContext;
    ListView lvExpenseList;

    // View lookup cache
    private static class ViewHolder {
        TextView tvExpenseTitle;
        TextView tvExpenseAmount;
    }



    public ExpenseAdapter(ArrayList<Expense> data, Context context) {
        super(context, R.layout.custom_expense_row, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Expense dataModel=(Expense)object;



    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Expense dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_expense_row, parent, false);
            viewHolder.tvExpenseTitle = (TextView) convertView.findViewById(R.id.tvExpenseTitle);
            viewHolder.tvExpenseAmount = (TextView) convertView.findViewById(R.id.tvExpenseAmount);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        viewHolder.tvExpenseTitle.setText(dataModel.getExpenseTitle());
        viewHolder.tvExpenseAmount.setText(dataModel.getExpenseCost());
        // Return the completed view to render on screen
        return convertView;
    }


}
