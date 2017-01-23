package com.example.smart34.personinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

//    Button          btnInsert, btnDisplay;
    PersonInfoManager   infoManager;
    DBAdapter           adapter = null;
    ListView            listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoManager = PersonInfoManager.getInfoManager(getApplicationContext());
        listView = (ListView)findViewById(R.id.listView);
        registerForContextMenu(listView);

        adapter = new DBAdapter(getApplicationContext(), infoManager);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView)view.findViewById(R.id.number);
                final String phone = tv.getText().toString();
                Toast.makeText(getApplicationContext(), phone, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        adapter.changed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R .menu.opt_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insert:
                Intent intent = new Intent(getApplicationContext(), PersonInfo.class);
                startActivity(intent);
                break;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.cnt_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterMenuInfo =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        View view = adapterMenuInfo.targetView;
        TextView tv = (TextView)view.findViewById(R.id.name);
        String name = tv.getText().toString();

        switch (item.getItemId()) {
            case R.id.modify:
                Cursor cursor = infoManager.getData(name);
                cursor.moveToFirst();
                Intent intent = new Intent(getApplicationContext(), ModifiedPersonInfo.class);
                intent.putExtra("name", cursor.getString(0));
                intent.putExtra("phone", cursor.getString(1));
                intent.putExtra("email", cursor.getString(2));
                intent.putExtra("address", cursor.getString(3));
                intent.putExtra("group", cursor.getString(4));
                startActivity(intent);
                break;
            case R.id.delete:
                infoManager.delete(name);
                adapter.changed();
                break;
        }
        return super.onContextItemSelected(item);
    }


}
