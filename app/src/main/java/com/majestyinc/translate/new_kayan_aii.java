package com.majestyinc.translate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class new_kayan_aii extends AppCompatActivity {

    EditText sunankaya, girmankaya1,girmankaya2,ka1,ka2,ka3;
    int count;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference dr;
    Button save,add;
    kaya ky;
    String id;
    String deviceId;
    Bundle bundle;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_kayan_aii);

        bundle = getIntent().getExtras();
        sunankaya = findViewById(R.id.nkayaName);
        girmankaya1 = findViewById(R.id.ngirmaEt1);
        ka1 = findViewById(R.id.nka1et1);
        ka2 = findViewById(R.id.size1);
        save = findViewById(R.id.nsave);
        add = findViewById(R.id.add);
        imageButton = findViewById(R.id.back);
        count = 1;

        if(bundle!=null){
        if(bundle.getString("name")  !=null)
        {
            String name =  bundle.getString("name");
            sunankaya.setText(name);
            String size =  bundle.getString("size");
            girmankaya1.setText(size);
            count = 0;
        }
        if( bundle.getString("size") !=null )
        {
            String size =  bundle.getString("size");
            girmankaya1.setText(size);
            count = 0;
        }
        }
        ky = new kaya();


        dr = db.collection("Kayan Aiki").document();

         deviceId = android.provider.Settings.Secure.getString(
               this.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new_kayan_aii.this,MainActivity.class);
                intent.putExtra("fragmentc","construct");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(sunankaya.getText().toString()) && !TextUtils.isEmpty(girmankaya1.getText().toString())
                        &&!TextUtils.isEmpty(ka1.getText().toString())
                         && !TextUtils.isEmpty(ka2.getText().toString())) {
                HashMap<String, Object> uploadData = new HashMap<>();
                uploadData.put("name", sunankaya.getText().toString());
                uploadData.put("size", girmankaya1.getText().toString());
                uploadData.put("item", ka1.getText().toString());
                uploadData.put("itemsize", ka2.getText().toString());
                uploadData.put("PID",deviceId);
                uploadData.put("count", count);
                count =0;
                dr.set(uploadData);
                Toast.makeText(new_kayan_aii.this, "an yi sebin kayan aikin ka, idan zaka kara kaya ka rubuta ka danna add ko ka danna save idan ka gama", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(new_kayan_aii.this,new_kayan_aii.class);
                intent.putExtra("name",sunankaya.getText().toString());
                intent.putExtra("size",girmankaya1.getText().toString());
                intent.putExtra("count",count);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0,0);
                startActivity(intent);
                ka1.setText("");
                ka2.setText("");}
                else

                {
                    Toast.makeText(new_kayan_aii.this, "ka saka kayan aiki kafin kayi sebin", Toast.LENGTH_SHORT).show();
                }





            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(new_kayan_aii.this, "Anyi sebin kayan aikin ka", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(new_kayan_aii.this,MainActivity.class);
                intent.putExtra("fragmentc","construct");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);


            }
        });


    }
}