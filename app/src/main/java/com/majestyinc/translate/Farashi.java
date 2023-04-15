package com.majestyinc.translate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Farashi extends AppCompatActivity {
    CollectionReference all;
    DocumentReference suna,olsuna;
    FirebaseFirestore dr = FirebaseFirestore.getInstance();
    EditText et1,et2,et3,et4,et5,et6,et7,et8,et9;
    Button save,sabonKaya;
    data dat;
    String price,oldPrice,dboldPrice,dbprice,itemSelected,id;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerdata;
    ArrayList<String> arrayPrice;
    ArrayList<String> arrayOldPrice;
    ArrayList<String> arrayId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farashi);
//        et1 = findViewById(R.id.name);
//        et2 = findViewById(R.id.oldprice);
//        et3 = findViewById(R.id.newprice);
//        et4 = findViewById(R.id.profit);
//        et5 = findViewById(R.id.profitper);
//        et6 = findViewById(R.id.lastestprice);
//        et7 = findViewById(R.id.estimateselling);
//        et8 = findViewById(R.id.estprofit);
//        et9 = findViewById(R.id.estprofitper);
//        save = findViewById(R.id.save);
//        spinner = findViewById(R.id.spinner);
//        sabonKaya = findViewById(R.id.newKaya);
//        suna = dr.collection("Goods").document();
//
//        all = dr.collection("Goods");
//
//
//        dat = new data();
//
//
//        retrieveData();
//        spinnerdata = new ArrayList<>();
//        arrayPrice = new ArrayList<>();
//        arrayOldPrice = new ArrayList<>();
//        arrayId = new ArrayList<>();
//
//        adapter = new ArrayAdapter<>(Farashi.this, android.R.layout.simple_spinner_dropdown_item,spinnerdata);
//
//
//
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//                itemSelected = spinner.getSelectedItem().toString();
//
//                String name = spinnerdata.get(i);
//                dbprice = arrayPrice.get(i);
//                dboldPrice = arrayOldPrice.get(i);
//                id = arrayId.get(i);
//
//
//                et1.setText(name);
//                et2.setText(dboldPrice);
//                et3.setText(dbprice);
//
//                if(!TextUtils.isEmpty(et2.getText().toString())){
//                    int y = Integer.parseInt(et2.getText().toString());
//                    int x = Integer.parseInt(et3.getText().toString());
//                    int z = x-y;
//                    int per = (z/x)*100;
//                    String j = String.valueOf(per);
//                    String k = String.valueOf(z);
//                    et4.setText(k);
//                    et5.setText(j+"%");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spinner.setAdapter(adapter);
//        retrieveData();
//
//        et6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus && !TextUtils.isEmpty(et6.getText().toString())) {
//                    int c = Integer.parseInt(et2.getText().toString());
//                    int d =Integer.parseInt(et3.getText().toString());
//                    int e =Integer.parseInt(et6.getText().toString());
//                    int de = d*e;
//                    int dd = de/c;
//                    String s = String.valueOf(dd);
//                    et7.setText(s);
//                    int x = Integer.parseInt(et6.getText().toString());
//                    int y = Integer.parseInt(et7.getText().toString());
//                    int l = y-x;
//                    int k = (l/x)*100;
//                    String ks = String.valueOf(k);
//                    et8.setText(String.valueOf(l));
//                    et9.setText(ks+"%");
//                } else {
//
//                }
//            }
//        });
//
//
//
//
//
//
//        sabonKaya.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Farashi.this, SaFarashi.class);
//                startActivity(intent);
//            }
//        });
//
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//    }
//
//    public void retrieveData()
//    {
//
//
//        all.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                spinnerdata.clear();
//                for( DocumentSnapshot item: value.getDocuments())
//                {
//                    spinnerdata.add(item.getString("name"));
//                    arrayPrice.add(item.getString("price"));
//                    arrayOldPrice.add(item.getString("oldPrice"));
//                    arrayId.add(item.getId());
//                }
//                adapter.notifyDataSetChanged();
//                spinner.setAdapter(adapter);
//
//            }
//        });
//
//
//
//
    }
}