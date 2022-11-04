package com.majestyinc.translate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FragmentStats extends Fragment {

    CollectionReference all;
    DocumentReference suna, olsuna;
    FirebaseFirestore dr = FirebaseFirestore.getInstance();
    EditText et1, et2, et3, et4, et5, et6, et7, et8, et9;
    Button save, sabonKaya;
    data dat;
    String price, oldPrice, dboldPrice, dbprice, itemSelected, id;
    Spinner spinner;
    String deviceId;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerdata;
    ArrayList<String> arrayPrice;
    ArrayList<String> arrayOldPrice;
    ArrayList<String> arrayId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentstats, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        et1 = getActivity().findViewById(R.id.name);
        et2 = getActivity().findViewById(R.id.oldprice);
        et3 = getActivity().findViewById(R.id.newprice);
        et4 = getActivity().findViewById(R.id.profit);
        et5 = getActivity().findViewById(R.id.profitper);
        et6 = getActivity().findViewById(R.id.lastestprice);
        et7 = getActivity().findViewById(R.id.estimateselling);
        et8 = getActivity().findViewById(R.id.estprofit);
        et9 = getActivity().findViewById(R.id.estprofitper);
        save = getActivity().findViewById(R.id.save);
        spinner = getActivity().findViewById(R.id.spinner);
        sabonKaya = getActivity().findViewById(R.id.newKaya);
        suna = dr.collection("Goods").document();

        all = dr.collection("Goods");


        dat = new data();

         deviceId = android.provider.Settings.Secure.getString(
                getActivity().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);


        retrieveData();
        spinnerdata = new ArrayList<>();
        arrayPrice = new ArrayList<>();
        arrayOldPrice = new ArrayList<>();
        arrayId = new ArrayList<>();

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinnerdata);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                try {

                    String name = spinnerdata.get(i);
                    dbprice = arrayPrice.get(i);
                    dboldPrice = arrayOldPrice.get(i);
                    id = arrayId.get(i);


                    et1.setText(name);
                    et2.setText(dboldPrice);
                    et3.setText(dbprice);

                    if (!TextUtils.isEmpty(et2.getText().toString())) {
                        double y = Double.parseDouble(et2.getText().toString());
                        double x = Double.parseDouble(et3.getText().toString());
                        double z = x - y;
                        double per = (z / x) * 100;
                        String j = String.valueOf(per);
                        String k = String.valueOf(z);
                        et4.setText((String.format("%s", new java.text.DecimalFormat("##.##").format(z))));
                        et5.setText((String.format("%s", new java.text.DecimalFormat("##.##").format(per))));
                    }
                }
                catch (Exception e)
                {}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner.setAdapter(adapter);
        retrieveData();

        et6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !TextUtils.isEmpty(et6.getText().toString())) {
                    double c = Double.parseDouble(et2.getText().toString());
                    double d = Double.parseDouble(et3.getText().toString());
                    double e = Double.parseDouble(et6.getText().toString());
                    double de = d * e;
                    double dd = de / c;
                    String s = String.valueOf(dd);
                    et7.setText(String.format("%s", new java.text.DecimalFormat("##.##").format(dd)));
                    double x = Double.parseDouble(et6.getText().toString());
                    double y = Double.parseDouble(et7.getText().toString());
                    double l = y - x;
                    double k = (l / y) * 100;
                    String ks = String.valueOf(k);
                    et8.setText((String.format("%s", new java.text.DecimalFormat("##.##").format(l))));
                    et9.setText(String.format("%s", new java.text.DecimalFormat("##.##").format(k)));
                } else {

                }
            }
        });


        sabonKaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Intent intent = new Intent(getActivity(), SaFarashi.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {}
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (!TextUtils.isEmpty(et1.getText().toString()) && !TextUtils.isEmpty(et2.getText().toString())
                            && !TextUtils.isEmpty(et3.getText().toString()) && !TextUtils.isEmpty(et6.getText().toString())) {
                        dr.collection("Goods")
                                .document(id)
                                .update("price", et7.getText().toString(), "oldPrice", et6.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                                        et1.setText("");
                                        et2.setText("");
                                        et3.setText("");
                                        et4.setText("");
                                        et5.setText("");
                                        et6.setText("");
                                        et7.setText("");
                                        et8.setText("");
                                        et9.setText("");
                                        spinnerdata.clear();

                                    }

                                });
                    } else {
                        Toast.makeText(getActivity(), "Kasa sabon farashi kafin kayi sebin", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {}
            }
        });
    }

    public void retrieveData() {

        try{
        all.whereEqualTo("PID",deviceId).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                spinnerdata.clear();
                try {

                    for (DocumentSnapshot item : value.getDocuments()) {
                        spinnerdata.add(item.getString("name"));
                        arrayPrice.add(item.getString("price"));
                        arrayOldPrice.add(item.getString("oldPrice"));
                        arrayId.add(item.getId());
                    }
                    adapter.notifyDataSetChanged();
                    spinner.setAdapter(adapter);

                }
                catch (Exception e)
                {}
            }
        });


    }
        catch(Exception e){}
    }
}
