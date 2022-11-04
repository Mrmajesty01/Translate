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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentConstruct extends Fragment {

    Button newpage, save, update;
    RecyclerView recyclerView;
    ArrayList<kaya> arrayList;
    MyAdapter myAdapter;
    MyAdapter.MyViewHolder myViewHolder;
    Double last;
    FirebaseFirestore fr = FirebaseFirestore.getInstance();
    DocumentReference dr;
    CollectionReference all;
    Spinner spinner;
    String deviceId;
    ArrayList<String> spinnerData,len,newlen,id;
    ArrayAdapter<String> adapter;
    String name,length,itemSize,newLength,sId;
    public static String nam;
    String Id;
    EditText sunanKaya,girma1,newgirma,newestgirma;
    ListView list;
    String itemname,itemsize;
    double ngir;
    ArrayAdapter<String> arrayAdapter;
    List<String> fieldNames = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentconstruct,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dr = fr.collection("Kayan Aiki").document();
        all = fr.collection("Kayan Aiki");

        recyclerView = getActivity().findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList = new ArrayList<kaya>();
        sunanKaya = getActivity().findViewById(R.id.cnkayaName);
        girma1 = getActivity().findViewById(R.id.cngirmaEt1);
//        newgirma = getActivity().findViewById(R.id.newgirma);
        newestgirma = getActivity().findViewById(R.id.newestgirma);
        myAdapter = new MyAdapter(getActivity(), arrayList);
//        update = getActivity().findViewById(R.id.cupate);
        recyclerView.setAdapter(myAdapter);
        myViewHolder = new MyAdapter.MyViewHolder(getView());





        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_selectable_list_item, fieldNames);


        newpage = getActivity().findViewById(R.id.cadd);
        save = getActivity().findViewById(R.id.cnsave);

         deviceId = android.provider.Settings.Secure.getString(
                getActivity().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        newpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                Intent intent = new Intent(getActivity(), new_kayan_aii.class);
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

                    if (!TextUtils.isEmpty(newestgirma.getText().toString())) {
                        all.whereEqualTo("PID", deviceId).whereEqualTo("name", name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                    itemSize = snapshot.getString("itemsize");
                                    double is = Double.parseDouble(itemSize);
                                    double gir = Double.parseDouble(girma1.getText().toString());
                                    ngir = Double.parseDouble(newestgirma.getText().toString());
                                    String sngir = newestgirma.getText().toString();
                                    last = is / gir * ngir;

                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("itemSize1", String.valueOf(last));
                                    map.put("newSize", sngir);
                                    snapshot.getReference().update(map);
                                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                                    Intent o = new Intent(getActivity(), MainActivity.class);
                                    o.putExtra("fragmentc", "construct");
                                    o.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    o.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    getActivity().finish();
                                    getActivity().overridePendingTransition(0, 0);
                                    startActivity(o);
                                }


                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "Sa sabon girma kafin kayi saving", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e)
                {}
            }
        });

//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////
////                HashMap<String,Object> map = new HashMap<>();
////                map.put("name",sunanKaya.getText().toString());
////                map.put("size",girma1.getText().toString());
////                map.put( "newSize",newestgirma.getText().toString());
////                map.put("item",myViewHolder.item.getText().toString());
////                map.put("itemsize",myViewHolder.itemSize.getText().toString());
////
////         all.whereEqualTo("name",sunanKaya.getText().toString()).whereEqualTo("coun")
//            }
//        });
        
        
        spinner = getActivity().findViewById(R.id.cspinner);
        spinnerData = new ArrayList<>();
        len = new ArrayList<>();
        newlen = new ArrayList<>();
        id = new ArrayList<>();

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinnerData);
        spinner.setAdapter(adapter);
        retriveData();
        retrieve();



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                try {

                name = spinnerData.get(i);
                length = len.get(i);
                newLength = newlen.get(i);
                sId = id.get(i);
                sunanKaya.setText(name);
                girma1.setText(length);
                newestgirma.setText(newLength);


                retrieve();

            }
                catch (Exception e)
                {}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner.setAdapter(adapter);
        retriveData();




    }
    public void retriveData() {
        try {
            all.whereEqualTo("PID", deviceId).whereEqualTo("count", 1).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    try {

                    spinnerData.clear();
                    for (DocumentSnapshot item : value.getDocuments()) {
                        nam = item.getString("name");
                        spinnerData.add(item.getString("name"));
                        len.add(item.getString("size"));
                        newlen.add(String.valueOf(item.getString("newSize")));
                        id.add(item.getId());

                    }
                    adapter.notifyDataSetChanged();
                    spinner.setAdapter(adapter);


                }
                    catch (Exception e)
                    {}
                }
            });

        }
        catch (Exception e){}
    }
    private void retrieve() {
      try{
            all.whereEqualTo("PID", deviceId).whereEqualTo("name", sunanKaya.getText().toString()).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    try {


                    if (error != null) {

                    }
                    arrayList.clear();
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            arrayList.add(dc.getDocument().toObject(kaya.class));


                        }
                        myAdapter.notifyDataSetChanged();


                    }

                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        nam = snapshot.getString("name");
                    }
                }
                    catch (Exception e)
                    {}
                }
            });
        }
      catch (Exception e)
      {}
    }
}
