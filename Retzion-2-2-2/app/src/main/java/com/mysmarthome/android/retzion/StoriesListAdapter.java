package com.mysmarthome.android.retzion;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.mysmarthome.android.retzion.Backendless.Blog;

import java.util.ArrayList;
import java.util.List;

public class StoriesListAdapter extends BaseAdapter {
     Context context;
    List<Blog> list;
    //constructor
    public StoriesListAdapter(final Context context) {
        this.context = context;
        list=new ArrayList<>();
        Backendless.initApp(context,Defaults.APPLICATION_ID,Defaults.API_KEY);
        Backendless.Data.of(Blog.class).find(new AsyncCallback<List<Blog>>() {
            @Override
            public void handleResponse(List<Blog> response) {
                list=response;
                notifyDataSetChanged();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
            }
        });
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.story_list_item, null);
        TextView txtPublisher = view.findViewById(R.id.txtPublisher);
        TextView txtDescription = view.findViewById(R.id.txtDesc);
        TextView txtDate = view.findViewById(R.id.txtDate);
        txtPublisher.setText(list.get(position).getName());
        txtDescription.setText(list.get(position).getDescription());
        txtDate.setText(list.get(position).getCreated().toString());
        return view;
    }
}
