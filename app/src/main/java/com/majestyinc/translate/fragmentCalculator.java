package com.majestyinc.translate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class fragmentCalculator extends Fragment {
    TextView numbers;
    TextView english;
    TextView calculations,hiddin;
    static String sofar;
    TextView hausa;
    String first,second;
    boolean bracketChecker = false;
    FragmentTransaction ft;
    SwipeRefreshLayout swipeRefreshLayout;
    fragmentCalculator fragmentCalculator;
    CardView cnum1,cnum2,cnum3,cnum4,cnum5,cnum6,cnum7,cnum8,cnum9,cnum0,cclear,cbracket,cmodulu,cdivide,cplus,cmul,cminus,cequal,cdot;
    TextView num1,num2,num3,num4,num5,num6,num7,num8,num9,num0,plus,minus,divide,multiply,modulu,bracket;
    private static final String [] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen",


    };


    private static final String [] hausanumNames = {
            "",
            " daya",
            " biyu",
            " ukku",
            " hudu",
            " biyar",
            " shidda",
            " bakwai",
            " takwas",
            " tara",
            " goma",
            " goma sha daya",
            " goma sha biyu",
            " goma sha uku",
            " goma sha hudu",
            " goma sha biyar",
            " goma sha shidda",
            " goma sha bakwai",
            " goma sha takwas",
            " goma sha tara",


    };

    private static final String [] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety",


    };


    private static final String [] hausatensNames = {
            "",
            " goma",
            " ashirin",
            " talatin",
            " arba'in",
            " hamsin",
            " sittin",
            " saba'in",
            " tamanin",
            " chis'in",

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentcalculator,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        numbers = getActivity().findViewById(R.id.number);
        fragmentCalculator = new fragmentCalculator();
        english =  getActivity().findViewById(R.id.english);
        hausa =  getActivity().findViewById(R.id.hausa);
        calculations =  getActivity().findViewById(R.id.calculations);
        cnum0 =  getActivity().findViewById(R.id.number0);
        cnum1 =  getActivity().findViewById(R.id.number1);
        cnum2 =  getActivity().findViewById(R.id.number2);
        cnum3 =  getActivity().findViewById(R.id.number3);
        cnum4 =  getActivity().findViewById(R.id.number4);
        cnum5 =  getActivity().findViewById(R.id.number5);
        cnum6 =  getActivity().findViewById(R.id.number6);
        cnum7 =  getActivity().findViewById(R.id.number7);
        cnum8 =  getActivity().findViewById(R.id.number8);
        cnum9 =  getActivity().findViewById(R.id.number9);
        cclear =  getActivity().findViewById(R.id.clear);
        cbracket =  getActivity().findViewById(R.id.bracket);
        cmodulu =  getActivity().findViewById(R.id.modulo);
        cdivide =  getActivity().findViewById(R.id.divide);
        cplus =  getActivity().findViewById(R.id.plus);
        cmul =  getActivity().findViewById(R.id.multiply);
        cminus =  getActivity().findViewById(R.id.minus);
        cequal =  getActivity().findViewById(R.id.equal);
        cdot =  getActivity().findViewById(R.id.dot);


        num0 =  getActivity().findViewById(R.id.tnumber0);
        num1 =  getActivity().findViewById(R.id.tnumber1);
        num2 =  getActivity().findViewById(R.id.tnumber2);
        num3 =  getActivity().findViewById(R.id.tnumber3);
        num4 =  getActivity().findViewById(R.id.tnumber4);
        num5 =  getActivity().findViewById(R.id.tnumber5);
        num6 =  getActivity().findViewById(R.id.tnumber6);
        num7 =  getActivity().findViewById(R.id.tnumber7);
        num8 =  getActivity().findViewById(R.id.tnumber8);
        num9 =  getActivity().findViewById(R.id.tnumber9);
        plus =  getActivity().findViewById(R.id.tplus);
        minus =  getActivity().findViewById(R.id.tminus);
        multiply =  getActivity().findViewById(R.id.tmultiply);
        divide =  getActivity().findViewById(R.id.tdivide);
        modulu =  getActivity().findViewById(R.id.tmodulu);
        hiddin =  getActivity().findViewById(R.id.hiddin);
        bracket =  getActivity().findViewById(R.id.tbracket);
        swipeRefreshLayout = getActivity().findViewById(R.id.swipe);
        ft = getActivity().getSupportFragmentManager().beginTransaction();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              ft.replace(R.id.frame_layout,fragmentCalculator).commit();
                swipeRefreshLayout.setRefreshing(false);
            }
        });





        cnum0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String val = num0.getText().toString();
                    numbers.append(val);
                    hiddin.append(val);
                    if (hiddin.getText().toString().contains(".")) {
                        double s = Double.parseDouble(hiddin.getText().toString());
                        int n = (int) Double.parseDouble(hiddin.getText().toString());
                        double c = Double.parseDouble(hiddin.getText().toString());
                        String o = String.valueOf(c);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o))+" da digo "+hausaconvert(Double.parseDouble(somenumber)));
                    }
                    else {
                    english.setText(convert(Integer.parseInt(hiddin.getText().toString())));
                    hausa.setText(hausaconvert(Integer.parseInt(hiddin.getText().toString())));
                    double c = Double.parseDouble(hiddin.getText().toString());
                    numbers.setText(getFormatedAmount(c));
                    calculations.append(val);
                    }

                }
                catch (Exception e)
                {

                }
            }
        });


        cnum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                String val = num1.getText().toString();
                numbers.append(val);
                hiddin.append(val);
                double c = Double.parseDouble(hiddin.getText().toString());
                if (hiddin.getText().toString().contains(".")) {
                    double s = Double.parseDouble(hiddin.getText().toString());
                    int n = (int) Double.parseDouble(hiddin.getText().toString());
                    String o = String.valueOf(n);
                    String somenumber = String.valueOf(s);
                    somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                    numbers.setText(getFormatedAmount(c));
                    calculations.append(val);
                    english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                    hausa.setText(hausaconvert(Double.parseDouble(o))+" da digo "+hausaconvert(Double.parseDouble(somenumber)));
                } else {
                    english.setText(convert(Double.parseDouble(hiddin.getText().toString())));
                    hausa.setText(hausaconvert(Double.parseDouble(hiddin.getText().toString())));
                    numbers.setText(getFormatedAmount(c));
                    calculations.append(val);
                }
            }
                catch (Exception e)
                {

                }
            }
        });


        cnum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String val = num2.getText().toString();
                    numbers.append(val);
                    hiddin.append(val);
                    if (hiddin.getText().toString().contains(".")) {
                        double s = Double.parseDouble(hiddin.getText().toString());
                        int n = (int) Double.parseDouble(hiddin.getText().toString());
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                    } else {
                        english.setText(convert(Double.parseDouble(hiddin.getText().toString())));
                        hausa.setText(hausaconvert(Double.parseDouble(hiddin.getText().toString())));
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                    }
                }
                catch (Exception e)
                {

                }
            }
        });

        cnum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String val = num3.getText().toString();
                    numbers.append(val);
                    hiddin.append(val);
                    if (hiddin.getText().toString().contains(".")) {
                        double s = Double.parseDouble(hiddin.getText().toString());
                        int n = (int) Double.parseDouble(hiddin.getText().toString());
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                    } else {
                        english.setText(convert(Double.parseDouble(hiddin.getText().toString())));
                        hausa.setText(hausaconvert(Double.parseDouble(hiddin.getText().toString())));
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                    }
                }
                catch (Exception e)
                {

                }
            }
        });

        cnum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    String val = num4.getText().toString();
                    numbers.append(val);
                    hiddin.append(val);
                    if (hiddin.getText().toString().contains(".")) {
                        double s = Double.parseDouble(hiddin.getText().toString());
                        int n = (int) Double.parseDouble(hiddin.getText().toString());
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                    } else {
                        english.setText(convert(Double.parseDouble(hiddin.getText().toString())));
                        hausa.setText(hausaconvert(Double.parseDouble(hiddin.getText().toString())));
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                    }
                }
                catch (Exception e)
                {

                }
            }
        });


        cnum5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                String val = num5.getText().toString();
                numbers.append(val);
                hiddin.append(val);
                if (hiddin.getText().toString().contains(".")) {
                    double s = Double.parseDouble(hiddin.getText().toString());
                    int n = (int) Double.parseDouble(hiddin.getText().toString());
                    String o = String.valueOf(n);
                    String somenumber = String.valueOf(s);
                    somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                    double c = Double.parseDouble(hiddin.getText().toString());
                    numbers.setText(getFormatedAmount(c));
                    calculations.append(val);
                    english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                    hausa.setText(hausaconvert(Double.parseDouble(o))+" da digo "+hausaconvert(Double.parseDouble(somenumber)));
                }
                else{
                english.setText(convert(Double.parseDouble(hiddin.getText().toString())));
                hausa.setText(hausaconvert(Double.parseDouble(hiddin.getText().toString())));
                double c = Double.parseDouble(hiddin.getText().toString());
                numbers.setText(getFormatedAmount(c));
                calculations.append(val);
            }
            }
                catch (Exception e)
                {

                }
            }
        });


        cnum6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String val = num6.getText().toString();
                    numbers.append(val);
                    hiddin.append(val);
                    if (hiddin.getText().toString().contains(".")) {
                        double s = Double.parseDouble(hiddin.getText().toString());
                        int n = (int) Double.parseDouble(hiddin.getText().toString());
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                    } else {
                        english.setText(convert(Double.parseDouble(hiddin.getText().toString())));
                        hausa.setText(hausaconvert(Double.parseDouble(hiddin.getText().toString())));
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                    }
                }
                catch (Exception e)
                {

                }
            }
        });

        cnum7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String val = num7.getText().toString();
                    numbers.append(val);
                    hiddin.append(val);
                    if (hiddin.getText().toString().contains(".")) {
                        double s = Double.parseDouble(hiddin.getText().toString());
                        int n = (int) Double.parseDouble(hiddin.getText().toString());
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                    } else {
                        english.setText(convert(Double.parseDouble(hiddin.getText().toString())));
                        hausa.setText(hausaconvert(Double.parseDouble(hiddin.getText().toString())));
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                    }

                }
                catch (Exception e)
                {

                }
            }
        });

        cnum8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                String val = num8.getText().toString();
                numbers.append(val);
                hiddin.append(val);
                if (hiddin.getText().toString().contains(".")) {
                    double s = Double.parseDouble(hiddin.getText().toString());
                    int n = (int) Double.parseDouble(hiddin.getText().toString());;
                    String o = String.valueOf(n);
                    String somenumber = String.valueOf(s);
                    somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                    double c = Double.parseDouble(hiddin.getText().toString());
                    numbers.setText(getFormatedAmount(c));
                    calculations.append(val);
                    english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                    hausa.setText(hausaconvert(Double.parseDouble(o))+" da digo "+hausaconvert(Double.parseDouble(somenumber)));
                }
                else {
                    english.setText(convert(Double.parseDouble(hiddin.getText().toString())));
                    hausa.setText(hausaconvert(Double.parseDouble(hiddin.getText().toString())));
                    double c = Double.parseDouble(hiddin.getText().toString());
                    numbers.setText(getFormatedAmount(c));
                    calculations.append(val);
                }
            }
                catch (Exception e)
                {

                }
            }
        });

        cnum9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String val = num9.getText().toString();
                    numbers.append(val);
                    hiddin.append(val);
                    if (hiddin.getText().toString().contains(".")) {
                        double s = Double.parseDouble(hiddin.getText().toString());
                        int n = (int) Double.parseDouble(hiddin.getText().toString());
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                    } else {
                        english.setText(convert(Double.parseDouble(hiddin.getText().toString())));
                        hausa.setText(hausaconvert(Double.parseDouble(hiddin.getText().toString())));
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                    }
                }
                catch (Exception e)
                {

                }
            }

        });

        cclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    calculations.setText("");
                    hiddin.setText("");

                }
                catch (Exception e)
                {

                }
            }

        });

        cplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    first = calculations.getText().toString();
                    String ok = plus.getText().toString();
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    if (calculations.getText().toString().contains("+")) {

                    } else {
                        calculations.setText(calculations.getText().toString() + ok);
                    }
                }
                catch (Exception e)
                {

                }
            }
        });

        cminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                numbers.setText("");
                english.setText("");
                hausa.setText("");
                hiddin.setText("");
                String ok = minus.getText().toString();
                if (calculations.getText().toString().contains("-")) {

                } else {
                    calculations.setText(calculations.getText().toString() + ok);
                }
            }
                catch (Exception e)
                {

                }
            }
        });

        cmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    String ok = multiply.getText().toString();
                    if (calculations.getText().toString().contains("x")) {

                    } else {
                        calculations.setText(calculations.getText().toString() + ok);
                    }
                }
                catch (Exception e)
                {

                }
            }
        });

        cdivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    String ok = divide.getText().toString();
                    if (calculations.getText().toString().contains("÷")) {

                    } else {
                        calculations.setText(calculations.getText().toString() + ok);
                    }
                }
                catch (Exception e)
                {

                }
            }
        });

        cmodulu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    String ok = modulu.getText().toString();
                    if (calculations.getText().toString().contains("%")) {

                    } else {
                        calculations.setText(calculations.getText().toString() + ok);
                    }
                }
                catch (Exception e)
                {

                }
            }
        });

        cequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    String process = calculations.getText().toString();

                    process = process.replaceAll("x", "*");
                    process = process.replaceAll("%", "/100");
                    process = process.replaceAll("÷", "/");


                    Context rhino = Context.enter();

                    rhino.setOptimizationLevel(-1);

                    String finalResult = "";


                    try {
                        Scriptable scriptale = rhino.initStandardObjects();
                        finalResult = rhino.evaluateString(scriptale, process, "javascript", 1, null).toString();

                    } catch (Exception e) {
                        finalResult = "0";

                    }

                    int s = (int) Double.parseDouble(finalResult);
                    double k = Double.parseDouble(finalResult);
                    String o = String.valueOf(k);
                    String f = String.valueOf(s);
                    hiddin.setText(finalResult);
                    double c = Double.parseDouble(finalResult);
                    String fnumber = finalResult;
                    fnumber = fnumber.substring(fnumber.indexOf(".") + 1);
                    String somenumber = hiddin.getText().toString();
                    somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                    int sn = Integer.parseInt(somenumber);

                    if (finalResult.contains(".") && somenumber.equals("0") ) {
                        numbers.setText(getFormatedAmount(c));
                        calculations.setText(getFormatedAmount(Double.parseDouble(String.valueOf(k))));
                        hiddin.setText(getFormatedAmount(Double.parseDouble(String.valueOf(k))));
                        english.setText(convert(Double.parseDouble(String.valueOf(s))));
                        hausa.setText(hausaconvert(Double.parseDouble(String.valueOf(s))));
                        numbers.setText(getFormatedAmount(c));

                    }

                    else if(finalResult.contains(".") && !somenumber.equals("0"))
                    {
                        hiddin.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
                        String somenumberi = hiddin.getText().toString();
                        somenumberi = somenumberi.substring(somenumberi.indexOf(".") + 1);
                        english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumberi)));
                        hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumberi)));
                        calculations.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
                        numbers.setText(hiddin.getText().toString());

                    }

                    else if(finalResult.contains(".") && !somenumber.equals("0") ){
                        hiddin.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
                        String somenumberi = hiddin.getText().toString();
                        somenumberi = somenumberi.substring(somenumberi.indexOf(".") + 1, somenumberi.indexOf(".") + 3);
                        english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumberi)));
                        hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumberi)));
                        calculations.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
                        numbers.setText(hiddin.getText().toString());

                    }
                    }



                catch(Exception e)
                    {

                    }
                }

        });

        cdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String hdot = " da digo ";
                    String dot = " point ";
                    String num = hiddin.getText().toString();
                    String cal = calculations.getText().toString();
                    hiddin.setText(num + ".");
                    numbers.setText(num + ".");
                    calculations.setText(cal + ".");
                }
                catch (Exception e)
                {

                }


            }
        });

        cbracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    if (bracketChecker) {
                        String ok = ")";
                        calculations.setText(calculations.getText().toString() + ok);
                        bracketChecker = false;
                    } else {
                        String ok = "(";
                        calculations.setText(calculations.getText().toString() + ok);
                        bracketChecker = true;

                    }

                }
                catch (Exception e)
                {

                }
            }
        });


    }
    private static String convertLessThanOneThousand (int number)
    {



        if (number % 100 < 20) {
            sofar = numNames[ (number % 100)];
            number /= 100;

        }

        else {

            sofar = numNames[(number % 10)];
            number /= 10;

            sofar = tensNames[ (number % 10)] + sofar;
            number /= 10;

        }



        if(number == 0) return sofar;
        if(sofar.isEmpty()){
            return numNames[ number] + " hundred " + sofar;}
        else {return numNames[ number] + " hundred " + " and " + sofar;}

    }



    private static String hausaconvertLessThanOneThousand (int number) {


        if (number % 100 < 20) {
            sofar = hausanumNames[(number % 100)];
            number /= 100;
        }

        else {

            sofar = hausanumNames[ (number % 10)];
            number /= 10;

            if(sofar.isEmpty())
            {
                sofar = hausatensNames[(number % 10)]  + sofar;
                number /= 10;
            }
            else {

                sofar = hausatensNames[(number % 10)] + " da " + sofar;
                number /= 10;
            }
        }




        if(number == 0) return sofar;

        if(sofar.isEmpty()){
            return  " dari "+hausanumNames[ number]  + sofar;}
        else { return  " dari "+hausanumNames[ number] + " da " + sofar;}

    }






    public static String convert(double number)
    {
        if(number == 0)
        {
            return "zero";
        }

        String snumber = Double.toString(number);

        String mask = "000000000000";
        DecimalFormat decimalFormat = new DecimalFormat(mask);

        snumber = decimalFormat.format(number);

        int billion = Integer.parseInt(snumber.substring(0,3));

        int millions = Integer.parseInt(snumber.substring(3,6));

        int thousands = Integer.parseInt(snumber.substring(6,9));

        int hundred = Integer.parseInt(snumber.substring(9,12));


        String tradbillion;

        switch (billion)
        {
            case 0:
                tradbillion = "";
                break;

            case 1:
                tradbillion = convertLessThanOneThousand(billion)+ " billion ";
                break;
            default:
                tradbillion = convertLessThanOneThousand(billion)+ " billion ";
        }
        String result = tradbillion;



        String tradmilllion;

        switch (millions)
        {
            case 0:
                tradmilllion = "";
                break;

            case 1:
                tradmilllion = convertLessThanOneThousand(millions)+ " million ";
                break;
            default:
                tradmilllion = convertLessThanOneThousand(millions)+ " million ";
        }
        if (result.isEmpty()){
            result = result + tradmilllion;}
        else if(!tradmilllion.isEmpty())
        {
            result = result + " and "+ tradmilllion;
        }



        String tradthousand;

        switch (thousands)
        {
            case 0:
                tradthousand = "";
                break;

            case 1:
                tradthousand = "one thousand";

                break;
            default:
                tradthousand = convertLessThanOneThousand(thousands)+ " thousand ";



        }

        if (result.isEmpty()){
            result = result + tradthousand;}
        else if (!tradthousand.isEmpty())
        {
            result = result + " and " + tradthousand;
        }


        String tradhundred;
        tradhundred = convertLessThanOneThousand(hundred);
        if(result.isEmpty()){
            result = result + tradhundred;}
        else if(!tradhundred.isEmpty())
        {
            result = result + " and "+ tradhundred;
        }



        return result.replaceAll("^\\s+","").replaceAll("\\b\\s[2,]\\b","");

    }




    public static String hausaconvert(double number)
    {
        if(number == 0)
        {
            return "sifili";
        }

        String snumber = Double.toString(number);

        String mask = "000000000000";
        DecimalFormat decimalFormat = new DecimalFormat(mask);

        snumber = decimalFormat.format(number);

        int billion = Integer.parseInt(snumber.substring(0,3));

        int millions = Integer.parseInt(snumber.substring(3,6));

        int thousands = Integer.parseInt(snumber.substring(6,9));

        int hundred = Integer.parseInt(snumber.substring(9,12));


        String tradbillion;

        switch (billion)
        {
            case 0:
                tradbillion = "";
                break;

            case 1:
                tradbillion =  " biliyan " + hausaconvertLessThanOneThousand(billion);
                break;
            default:
                tradbillion = " biliyan " + hausaconvertLessThanOneThousand(billion);
        }
        String result = tradbillion;



        String tradmilllion;

        switch (millions)
        {
            case 0:
                tradmilllion = "";
                break;

            case 1:
                tradmilllion =  " miliyan " + hausaconvertLessThanOneThousand(millions);
                break;
            default:
                tradmilllion =  " miliyan " + hausaconvertLessThanOneThousand(millions);
        }
        if(result.isEmpty()){
            result = result + tradmilllion;}
        else if(!tradmilllion.isEmpty())
        {
            result = result + " da " + tradmilllion;
        }


        String tradthousand;

        switch (thousands)
        {
            case 0:
                tradthousand = "";
                break;

            case 1:
                tradthousand = "dubu daya";
                break;
            default:
                tradthousand =  " dubu " + hausaconvertLessThanOneThousand(thousands);
        }
        if(result.isEmpty()){
            result = result + tradthousand;}
        else if (!tradthousand.isEmpty())
        {
            result = result + " da " + tradthousand;
        }


        String tradhundred;
        tradhundred = hausaconvertLessThanOneThousand(hundred);
        if(result.isEmpty()){
            result = result + tradhundred;}
        else if(!tradhundred.isEmpty())
        {
            result = result + " da "+ tradhundred;
        }


        return result.replaceAll("^\\s+","").replaceAll("\\b\\s[2,]\\b","");

    }

    private String getFormatedAmount(double amount){

        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }



}
