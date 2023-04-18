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
    EditText buyPrice1,buyPrice2,sellPrice1,sellPrice2,percentageProfit1,percentageProfit2,profitInPercentage1,profitInPercentage2;
    Button calculateResult,clearResult;
    EditText percentagePrice,priceBought,priceSold;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentstats, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buyPrice1 = getActivity().findViewById(R.id.bp1);
        buyPrice2 = getActivity().findViewById(R.id.bp2);
        sellPrice1 = getActivity().findViewById(R.id.sp1);
        sellPrice2 =  getActivity().findViewById(R.id.sp2);
        sellPrice2.setEnabled(false);
        percentageProfit1 =  getActivity().findViewById(R.id.pp1);
        percentageProfit1.setEnabled(false);
        percentageProfit2 =  getActivity().findViewById(R.id.pp2);
        percentageProfit2.setEnabled(false);
        profitInPercentage1 =  getActivity().findViewById(R.id.ppp1);
        profitInPercentage1.setEnabled(false);
        profitInPercentage2 =  getActivity().findViewById(R.id.ppp2);
        profitInPercentage2.setEnabled(false);
        calculateResult = getActivity().findViewById(R.id.calculate);
        clearResult = getActivity().findViewById(R.id.btnClear);
        percentagePrice = getActivity().findViewById(R.id.hundredet);
        priceBought = getActivity().findViewById(R.id.bppet);
        priceSold = getActivity().findViewById(R.id.sppet);
        priceSold.setEnabled(false);

        
        calculateResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(buyPrice1.getText().toString())
                    && !TextUtils.isEmpty(buyPrice2.getText().toString())
                    && !TextUtils.isEmpty(sellPrice1.getText().toString())
                    && !TextUtils.isEmpty(percentagePrice.getText().toString())
                    && !TextUtils.isEmpty(priceBought.getText().toString()))
                {
                    double a = Double.parseDouble(buyPrice1.getText().toString());
                    double b = Double.parseDouble(sellPrice1.getText().toString());
                    double c = b - a;
                    double e = Double.parseDouble(buyPrice2.getText().toString());

                    percentageProfit1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format(c))));
                    profitInPercentage1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((c / a) * 100))));
                    sellPrice2.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b * e) / a))));

                    double f = Double.parseDouble(sellPrice2.getText().toString());

                    percentageProfit2.setText((String.format("%s", new java.text.DecimalFormat("##.##").format(f - e))));

                    double g = Double.parseDouble(percentageProfit2.getText().toString());

                    profitInPercentage2.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((g / e) * 100))));

                    double i = Double.parseDouble(percentagePrice.getText().toString());
                    double j = Double.parseDouble(priceBought.getText().toString());

                    priceSold.setText((String.format("%s", new java.text.DecimalFormat("##.##").format(((j/100)*i) + j))));

                }

                else if(!TextUtils.isEmpty(buyPrice1.getText().toString())
                        && !TextUtils.isEmpty(buyPrice2.getText().toString())
                        && !TextUtils.isEmpty(sellPrice1.getText().toString()))
                {
                    double a = Double.parseDouble(buyPrice1.getText().toString());
                    double b = Double.parseDouble(sellPrice1.getText().toString());
                    double c = b - a;
                    double e = Double.parseDouble(buyPrice2.getText().toString());

                    percentageProfit1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format(c))));
                    profitInPercentage1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((c / a) * 100))));
                    sellPrice2.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((b * e) / a))));

                    double f = Double.parseDouble(sellPrice2.getText().toString());

                    percentageProfit2.setText((String.format("%s", new java.text.DecimalFormat("##.##").format(f - e))));

                    double g = Double.parseDouble(percentageProfit2.getText().toString());

                    profitInPercentage2.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((g / e) * 100))));

                }


                else if(!TextUtils.isEmpty(buyPrice1.getText().toString())
                        && !TextUtils.isEmpty(sellPrice1.getText().toString()))
                {
                    double a = Double.parseDouble(buyPrice1.getText().toString());
                    double b = Double.parseDouble(sellPrice1.getText().toString());
                    double c = b-a;

                    percentageProfit1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format(c))));
                    profitInPercentage1.setText((String.format("%s", new java.text.DecimalFormat("##.##").format((c/a)*100))));
                }

                else if(!TextUtils.isEmpty(percentagePrice.getText().toString())
                        && !TextUtils.isEmpty(priceBought.getText().toString()))
                {
                    double i = Double.parseDouble(percentagePrice.getText().toString());
                    double j = Double.parseDouble(priceBought.getText().toString());

                    priceSold.setText((String.format("%s", new java.text.DecimalFormat("##.##").format(((j/100)*i) + j))));
                }


                else
                {
                    Toast.makeText(getActivity(), "Asa Farashi Kafin a Danna ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clearResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buyPrice1.setText("");
                buyPrice2.setText("");
                sellPrice1.setText("");
                sellPrice2.setText("");
                percentageProfit1.setText("");
                percentageProfit2.setText("");
                profitInPercentage1.setText("");
                profitInPercentage2.setText("");
                percentagePrice.setText("");
                priceBought.setText("");
                priceSold.setText("");

            }
        });
    }
}
