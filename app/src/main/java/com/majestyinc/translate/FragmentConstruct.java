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

    EditText size1,size2,item1,itemSize1,itemAns1;
    EditText item2,itemSize2,itemAns2;
    EditText item3,itemSize3,itemAns3;
    EditText item4,itemSize4,itemAns4;
    EditText item5,itemSize5,itemAns5;
    Button calculate,clear;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentconstruct,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        size1 = getActivity().findViewById(R.id.size1);
        size2 = getActivity().findViewById(R.id.size2);
        item1 = getActivity().findViewById(R.id.item1);
        itemSize1 = getActivity().findViewById(R.id.itemSize1);
        itemAns1 = getActivity().findViewById(R.id.item1ans);
        itemAns1.setEnabled(false);
        item2 = getActivity().findViewById(R.id.item2);
        itemSize2 = getActivity().findViewById(R.id.itemSize2);
        itemAns2 = getActivity().findViewById(R.id.item2ans);
        itemAns2.setEnabled(false);
        item3 = getActivity().findViewById(R.id.item3);
        itemSize3 = getActivity().findViewById(R.id.itemSize3);
        itemAns3 = getActivity().findViewById(R.id.item3ans);
        itemAns3.setEnabled(false);
        item4 = getActivity().findViewById(R.id.item4);
        itemSize4 = getActivity().findViewById(R.id.itemSize4);
        itemAns4 = getActivity().findViewById(R.id.item4ans);
        itemAns4.setEnabled(false);
        item5 = getActivity().findViewById(R.id.item5);
        itemSize5 = getActivity().findViewById(R.id.itemSize5);
        itemAns5 = getActivity().findViewById(R.id.item5ans);
        itemAns5.setEnabled(false);
        clear = getActivity().findViewById(R.id.constClear);
        calculate = getActivity().findViewById(R.id.constCalculate);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(size1.getText().toString()) && !TextUtils.isEmpty(size2.getText().toString())
                        && !TextUtils.isEmpty(itemSize1.getText().toString())  && !TextUtils.isEmpty(itemSize2.getText().toString())
                        && !TextUtils.isEmpty(itemSize3.getText().toString()) && !TextUtils.isEmpty(itemSize4.getText().toString())
                        && !TextUtils.isEmpty(itemSize5.getText().toString()))
                {
                    double a = Double.parseDouble(size1.getText().toString());
                    double b = Double.parseDouble(size2.getText().toString());
                    double i1 = Double.parseDouble(itemSize1.getText().toString());
                    double i2 = Double.parseDouble(itemSize2.getText().toString());
                    double i3 = Double.parseDouble(itemSize3.getText().toString());
                    double i4 = Double.parseDouble(itemSize4.getText().toString());
                    double i5 = Double.parseDouble(itemSize5.getText().toString());
                    itemAns1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i1)/a))));
                    itemAns2.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i2)/a))));
                    itemAns3.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i3)/a))));
                    itemAns4.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i4)/a))));
                    itemAns5.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i5)/a))));


                }

                else if(!TextUtils.isEmpty(size1.getText().toString()) && !TextUtils.isEmpty(size2.getText().toString())
                        && !TextUtils.isEmpty(itemSize1.getText().toString())  && !TextUtils.isEmpty(itemSize2.getText().toString())
                        && !TextUtils.isEmpty(itemSize3.getText().toString()) && !TextUtils.isEmpty(itemSize4.getText().toString()))
                {
                    double a = Double.parseDouble(size1.getText().toString());
                    double b = Double.parseDouble(size2.getText().toString());
                    double i1 = Double.parseDouble(itemSize1.getText().toString());
                    double i2 = Double.parseDouble(itemSize2.getText().toString());
                    double i3 = Double.parseDouble(itemSize3.getText().toString());
                    double i4 = Double.parseDouble(itemSize4.getText().toString());
                    itemAns1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i1)/a))));
                    itemAns2.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i2)/a))));
                    itemAns3.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i3)/a))));
                    itemAns4.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i4)/a))));
                }

                else if(!TextUtils.isEmpty(size1.getText().toString()) && !TextUtils.isEmpty(size2.getText().toString())
                        && !TextUtils.isEmpty(itemSize1.getText().toString())  && !TextUtils.isEmpty(itemSize2.getText().toString())
                        && !TextUtils.isEmpty(itemSize3.getText().toString()))
                {
                    double a = Double.parseDouble(size1.getText().toString());
                    double b = Double.parseDouble(size2.getText().toString());
                    double i1 = Double.parseDouble(itemSize1.getText().toString());
                    double i2 = Double.parseDouble(itemSize2.getText().toString());
                    double i3 = Double.parseDouble(itemSize3.getText().toString());
                    itemAns1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i1)/a))));
                    itemAns2.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i2)/a))));
                    itemAns3.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i3)/a))));
                }

                else if(!TextUtils.isEmpty(size1.getText().toString()) && !TextUtils.isEmpty(size2.getText().toString())
                        && !TextUtils.isEmpty(itemSize1.getText().toString())  && !TextUtils.isEmpty(itemSize2.getText().toString()))
                {
                    double a = Double.parseDouble(size1.getText().toString());
                    double b = Double.parseDouble(size2.getText().toString());
                    double i1 = Double.parseDouble(itemSize1.getText().toString());
                    double i2 = Double.parseDouble(itemSize2.getText().toString());
                    itemAns1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i1)/a))));
                    itemAns2.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b*i2)/a))));

                }


                else if(!TextUtils.isEmpty(size1.getText().toString()) && !TextUtils.isEmpty(size2.getText().toString())
                        && !TextUtils.isEmpty(itemSize1.getText().toString())) {
                    double a = Double.parseDouble(size1.getText().toString());
                    double b = Double.parseDouble(size2.getText().toString());
                    double i1 = Double.parseDouble(itemSize1.getText().toString());
                    itemAns1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b * i1) / a))));
                }


                else
                {
                    Toast.makeText(getActivity(),"A SA ADADIN KAYA KAFIN a DANNA",Toast.LENGTH_SHORT).show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size1.setText("");
                size2.setText("");
                item1.setText("");
                itemSize1.setText("");
                itemAns1.setText("");
                item2.setText("");
                itemSize2.setText("");
                itemAns2.setText("");
                item3.setText("");
                itemSize3.setText("");
                itemAns3.setText("");
                item4.setText("");
                itemSize4.setText("");
                itemAns4.setText("");
                item5.setText("");
                itemSize5.setText("");
                itemAns5.setText("");
            }
        });

    }
}
