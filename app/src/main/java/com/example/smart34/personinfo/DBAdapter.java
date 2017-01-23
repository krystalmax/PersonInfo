package com.example.smart34.personinfo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by smart34 on 2016-10-18.
 */
public class DBAdapter extends BaseAdapter{

    Context             context = null;
    LayoutInflater      inflater = null;
    Cursor              cursor = null;
    PersonInfoManager   infoManager = null;

    public DBAdapter(Context context, PersonInfoManager manager) {
        this.context = context;
        infoManager = manager;
        cursor = infoManager.getAllData();
        inflater = LayoutInflater.from(context);
    }

    public void changed() {
        cursor = infoManager.getAllData();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View itemView = inflater.inflate(R.layout.list_item_view, null);

        TextView tvName = (TextView) itemView.findViewById(R.id.name);
        TextView tvNumber = (TextView) itemView.findViewById(R.id.number);
        TextView tvEmail = (TextView) itemView.findViewById(R.id.email);
        ImageView iPhoto = (ImageView) itemView.findViewById(R.id.photo);

        cursor.moveToPosition(i);
        tvName.setText(cursor.getString(cursor.getColumnIndex("Name")));
        tvNumber.setText(cursor.getString(cursor.getColumnIndex("Phone")));
        tvEmail.setText(cursor.getString(cursor.getColumnIndex("Email")));
        iPhoto.setImageResource(R.drawable.person);

        return itemView;
    }

}
