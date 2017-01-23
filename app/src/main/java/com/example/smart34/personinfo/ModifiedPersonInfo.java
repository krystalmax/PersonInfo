package com.example.smart34.personinfo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by smart34 on 2016-11-01.
 */
public class ModifiedPersonInfo extends Activity {
    Button btnSave, btnCancel;
    EditText name, phone, email, address, group;
    PersonInfoManager infoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_info);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        group = (EditText) findViewById(R.id.group);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        phone.setText(intent.getStringExtra("phone"));
        email.setText(intent.getStringExtra("email"));
        address.setText(intent.getStringExtra("address"));
        group.setText(intent.getStringExtra("group"));

        infoManager = PersonInfoManager.getInfoManager(getApplicationContext());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((name.getText().toString().equals(""))||(phone.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "이름과 연락처를 입력하여 주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                ContentValues values = new ContentValues();
                values.put("Name", name.getText().toString());
                values.put("Phone", phone.getText().toString());
                values.put("Email", email.getText().toString());
                values.put("Address", address.getText().toString());
                values.put("gName", group.getText().toString());
                infoManager.update(values);
                Toast.makeText(getApplicationContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
