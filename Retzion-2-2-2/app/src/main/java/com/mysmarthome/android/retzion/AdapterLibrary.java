package com.mysmarthome.android.retzion;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class AdapterLibrary extends BaseAdapter {
    TextView vodName;
    List<BroadcastModel> libraryList;
    Context context;


    public AdapterLibrary(List<BroadcastModel> libraryList, Context context) {
        this.libraryList = libraryList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return libraryList.size();
    }

    @Override
    public Object getItem(int position) {
        return libraryList.get(position).vodName;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemlibrary, null);
        //declare pointers between java and the view that we have
        vodName = view.findViewById(R.id.vodName);
        TextView date = view.findViewById(R.id.date);

        vodName.setText(libraryList.get(position).vodName);
        Log.e("timeStamp:", libraryList.get(position).creationDate+"");
        date.setText(getDate( libraryList.get(position).creationDate));

        return view;
    }




    private String getDate(long time) {
        Log.e("time:", time+"");
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int mYear = cal.get(Calendar.YEAR);
        int mMonth = cal.get(Calendar.MONTH);
        int mDay = cal.get(Calendar.DAY_OF_MONTH);
        String date = mDay+":"+mMonth+":"+mYear;
        Log.e("date:", date);
        return date;
    }

}