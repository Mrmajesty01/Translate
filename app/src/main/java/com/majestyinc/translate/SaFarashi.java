package com.majestyinc.translate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SaFarashi extends AppCompatActivity {

    EditText et1,et2,et3;
    Button save;

    DocumentReference add;
    FirebaseFirestore dr = FirebaseFirestore.getInstance();
    data dat;
    ImageButton imageButton;
    String deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sa_farashi);

        et1 = findViewById(R.id.addname);
        et2 = findViewById(R.id.addnewprice);
        et3 = findViewById(R.id.addoldprice);
        save = findViewById(R.id.sabing);
        imageButton = findViewById(R.id.fback);
        add = dr.collection("Goods").document();


        deviceId = android.provider.Settings.Secure.getString(
                this.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        dat = new data();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaFarashi.this,MainActivity.class);
                intent.putExtra("fragments","stats");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(et1.getText().toString()) && !TextUtils.isEmpty(et2.getText().toString()) && !TextUtils.isEmpty(et3.getText().toString())){
                HashMap<String,Object>map = new HashMap<>();
                map.put("name",et1.getText().toString());
                map.put("price",et2.getText().toString());
                map.put("oldPrice",et3.getText().toString());
                map.put("PID",deviceId);
                add.set(map);
                Toast.makeText(SaFarashi.this, "an yi sebin kayan ka", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SaFarashi.this,MainActivity.class);
                intent.putExtra("fragments","stats");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
                }
                else
                {

                    Toast.makeText(SaFarashi.this, "ka sa sunan kaya kafin kayi sebin", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}