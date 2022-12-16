package com.majestyinc.translate;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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
    TextToSpeech speech;
    String first,second;
    boolean bracketChecker = false;
    FragmentTransaction ft;
    SwipeRefreshLayout swipeRefreshLayout;
    fragmentCalculator fragmentCalculator;
    CardView cnum1,cnum2,cnum3,cnum4,cnum5,cnum6,cnum7,cnum8,cnum9,cnum0,cclear,cbracket,cmodulu,cdivide,cplus,cmul,cminus,cequal,cdot,cdelete;
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
        cdelete = getActivity().findViewById(R.id.delete);


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
        ft = getActivity().getSupportFragmentManager().beginTransaction();




        speech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR)
                {
                    speech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        hausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hs = hausa.getText().toString();
                speech.speak(hs,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eng = english.getText().toString();
                speech.speak(eng,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        cnum0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String val = num0.getText().toString();
                    numbers.append(val);
                    hiddin.append(val);
                    if (hiddin.getText().toString().contains(".")) {
                        String collect = hiddin.getText().toString();
                        String zero = collect.replaceAll(",","");
                        double s = Double.parseDouble(zero);
                        int n = (int) Double.parseDouble(zero);
                        double c = Double.parseDouble(zero);
                        String o = String.valueOf(n);
                        String somenumber = hiddin.getText().toString().replaceAll(",","");
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);

                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o))+" da digo "+hausaconvert(Double.parseDouble(somenumber)));

                    }
                    else {
                    english.setText(convert(Double.parseDouble(hiddin.getText().toString())));
                    hausa.setText(hausaconvert(Double.parseDouble(hiddin.getText().toString())));
                    double c = Double.parseDouble(hiddin.getText().toString());
                    int n = (int) Double.parseDouble(hiddin.getText().toString());
                    numbers.setText(getFormatedAmount(c));
                    calculations.append(val);
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
                if (hiddin.getText().toString().contains(".")) {

                    String collect = hiddin.getText().toString();
                    String one = collect.replaceAll(",","");
                    double s = Double.parseDouble(one);
                    int n = (int) Double.parseDouble(one);
                    double c = Double.parseDouble(one);
                    String o = String.valueOf(n);
                    String somenumber = String.valueOf(s);
                    somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                    numbers.setText(getFormatedAmount(c));
                    calculations.append(val);
                    english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                    hausa.setText(hausaconvert(Double.parseDouble(o))+" da digo "+hausaconvert(Double.parseDouble(somenumber)));
                    equalTo();
                } else {
                    double c = Double.parseDouble(hiddin.getText().toString());
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
                        String collect = hiddin.getText().toString();
                        String two = collect.replaceAll(",","");
                        double s = Double.parseDouble(two);
                        int n = (int) Double.parseDouble(two);
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(two);
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                        equalTo();
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
                        String collect = hiddin.getText().toString();
                        String three = collect.replaceAll(",","");
                        double s = Double.parseDouble(three);
                        int n = (int) Double.parseDouble(three);
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(three);
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                        equalTo();
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
                        String collect = hiddin.getText().toString();
                        String four = collect.replaceAll(",","");
                        double s = Double.parseDouble(four);
                        int n = (int) Double.parseDouble(four);
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(hiddin.getText().toString());
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                        equalTo();
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
                    String collect = hiddin.getText().toString();
                    String five = collect.replaceAll(",","");
                    double s = Double.parseDouble(five);
                    int n = (int) Double.parseDouble(five);
                    String o = String.valueOf(n);
                    String somenumber = String.valueOf(s);
                    somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                    double c = Double.parseDouble(five);
                    numbers.setText(getFormatedAmount(c));
                    calculations.append(val);
                    english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                    hausa.setText(hausaconvert(Double.parseDouble(o))+" da digo "+hausaconvert(Double.parseDouble(somenumber)));
                    equalTo();
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
                        String collect = hiddin.getText().toString();
                        String six = collect.replaceAll(",","");
                        double s = Double.parseDouble(six);
                        int n = (int) Double.parseDouble(six);
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(six);
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                        equalTo();
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
                        String collect = hiddin.getText().toString();
                        String seven = collect.replaceAll(",","");
                        double s = Double.parseDouble(seven);
                        int n = (int) Double.parseDouble(seven);
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(seven);
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                        equalTo();
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
                    String collect = hiddin.getText().toString();
                    String eight = collect.replaceAll(",","");
                    double s = Double.parseDouble(eight);
                    int n = (int) Double.parseDouble(eight);;
                    String o = String.valueOf(n);
                    String somenumber = String.valueOf(s);
                    somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                    double c = Double.parseDouble(eight);
                    numbers.setText(getFormatedAmount(c));
                    calculations.append(val);
                    english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                    hausa.setText(hausaconvert(Double.parseDouble(o))+" da digo "+hausaconvert(Double.parseDouble(somenumber)));
                    equalTo();
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
                        String collect = hiddin.getText().toString();
                        String nine = collect.replaceAll(",","");
                        double s = Double.parseDouble(nine);
                        int n = (int) Double.parseDouble(nine);
                        String o = String.valueOf(n);
                        String somenumber = String.valueOf(s);
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        double c = Double.parseDouble(nine);
                        numbers.setText(getFormatedAmount(c));
                        calculations.append(val);
                        english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                        hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                        equalTo();
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
                    bracketChecker = false;
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
                    second = first.substring(first.length()-1);
                    String ok = plus.getText().toString();
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    if (second.equals("+")) {

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
                first = calculations.getText().toString();
                second = first.substring(first.length()-1);
                numbers.setText("");
                english.setText("");
                hausa.setText("");
                hiddin.setText("");
                String ok = minus.getText().toString();
                if (second.equals("-")) {

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
                    first = calculations.getText().toString();
                    second = first.substring(first.length()-1);
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    String ok = multiply.getText().toString();
                    if (second.equals("x")) {

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
                    first = calculations.getText().toString();
                    second = first.substring(first.length()-1);
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    String ok = divide.getText().toString();
                    if (second.equals("÷")) {

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
                    first = calculations.getText().toString();
                    second = first.substring(first.length()-1);
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    String ok = modulu.getText().toString();
                    if (second.equals("%")) {

                    } else {
                        calculations.setText(calculations.getText().toString() + ok);
                    }
                }
                catch (Exception e)
                {

                }
            }
        });

        cdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(hiddin.getText().toString().isEmpty() )
                {

                    try {
                        String c = calculations.getText().toString();
                        c = c.substring(0, c.length() - 1);
                        calculations.setText(c);
                    }
                    catch (Exception e)
                    {

                    }
                }

                else
                {
                    try {


                        String n = numbers.getText().toString().replaceAll(",","");
                        n = n.substring(0, n.length() - 1);
                        numbers.setText(n);
//                        numbers.setText(getFormatedAmount(Double.parseDouble(n)));

                        String c = calculations.getText().toString().replaceAll(",","");
                        c = c.substring(0, c.length() - 1);
                        calculations.setText(c);
//                        calculations.setText(getFormatedAmount(Double.parseDouble(c)));

                        String h = hiddin.getText().toString().replaceAll(",", "");
                        h = h.substring(0, h.length() - 1);
                        hiddin.setText(h);
//                        hiddin.setText(getFormatedAmount(Double.parseDouble(h)));

                         if(hiddin.getText().toString().isEmpty())
                         {
                             numbers.setText("");
                             hausa.setText("");
                             english.setText("");
                         }

                         else {
                             double s = Double.parseDouble(hiddin.getText().toString().replaceAll(",", ""));
                             int nu = (int) Double.parseDouble(hiddin.getText().toString().replaceAll(",", ""));
                             String o = String.valueOf(nu);
                             String somenumber = String.valueOf(s);
                             somenumber = somenumber.substring(somenumber.indexOf(".") + 1);

                             if (hiddin.getText().toString().contains(".") && !somenumber.equals("0")) {
                                 somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                                 double cu = Double.parseDouble(hiddin.getText().toString().replaceAll(",", ""));
                                 double cal = Double.parseDouble(calculations.getText().toString().replaceAll(",", ""));
                                 hiddin.setText(getFormatedAmount(cu));
                                 numbers.setText(getFormatedAmount(cu));
                                 calculations.setText(getFormatedAmount(cal));
                                 english.setText(convert(Double.parseDouble(o)) + " point " + convert(Double.parseDouble(somenumber)));
                                 hausa.setText(hausaconvert(Double.parseDouble(o)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
                                 equalTo();
                             } else if (hiddin.getText().toString().contains(".") && somenumber.equals("0")) {

                                 double cu = Double.parseDouble(hiddin.getText().toString().replaceAll(",", ""));
                                 double nuk =  Double.parseDouble(hiddin.getText().toString().replaceAll(",", ""));
                                 double cal =  Double.parseDouble(calculations.getText().toString().replaceAll(",", ""));
                                 hiddin.setText(getFormatedAmount(nuk));
                                 numbers.setText(getFormatedAmount(nuk));
                                 calculations.setText(getFormatedAmount(cal));
                                 english.setText(convert(Double.parseDouble(o)));
                                 hausa.setText(hausaconvert(Double.parseDouble(o)));
                                 equalTo();
                             } else {
                                 double su = Double.parseDouble(hiddin.getText().toString().replaceAll(",", ""));
                                 int nuk = (int) Double.parseDouble(hiddin.getText().toString().replaceAll(",", ""));
                                 String on = String.valueOf(nuk);
                                 english.setText(convert(Double.parseDouble(on)));
                                 hausa.setText(hausaconvert(Double.parseDouble(on)));
                                 double cu = Double.parseDouble(hiddin.getText().toString().replaceAll(",", ""));
                                 double cal = Double.parseDouble(calculations.getText().toString().replaceAll(",", ""));
                                 calculations.setText(getFormatedAmount(cal));
                                 numbers.setText(getFormatedAmount(cu));
                                 hiddin.setText(getFormatedAmount(cu));
                                 equalTo();
                             }
                         }
                    }
                    catch (Exception e)
                    {

                    }
                }

            }
        });

        cequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    bracketChecker = false;
                    numbers.setText("");
                    english.setText("");
                    hausa.setText("");
                    hiddin.setText("");
                    String temp = calculations.getText().toString();
                    String process = temp.replaceAll(",","");
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


                    double k = Double.parseDouble(finalResult);
//                    String o = String.valueOf(k);
//
//                    hiddin.setText(finalResult);
//                    double c = Double.parseDouble(finalResult);
//                    String somenumber = hiddin.getText().toString();
//                    somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
//                    int sn = Integer.parseInt(somenumber);


//                        calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
//                        numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
//                        hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))))

                        String convert = finalResult;
                        String somenumber = convert;
                        somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
                        Double sn = Double.parseDouble(somenumber);
                        long s = (long) Double.parseDouble(convert);
                        String f = String.valueOf(s);
                        String format = getFormatedAmount(Double.parseDouble(finalResult));
                        String formatnumber = format;
                        formatnumber = formatnumber.substring(formatnumber.indexOf(".") + 1);


                           if (somenumber.equals("0"))
                            {
                                calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
                                numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                                hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                                english.setText(convert(Double.parseDouble(finalResult)));
                                hausa.setText(hausaconvert(Double.parseDouble(finalResult)));

                            }

                           else if (somenumber.contains("E") && !format.contains("."))
                           {
                               calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
                               numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                               hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                               english.setText(convert(Double.parseDouble(finalResult)));
                               hausa.setText(hausaconvert(Double.parseDouble(finalResult)));


                           }


                           else if (somenumber.contains("E") && format.contains(".") && formatnumber.length()==1)
                           {
                               calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
                               numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                               hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                               english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(formatnumber)));
                               hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(formatnumber)));

                           }


                           else if (somenumber.contains("E") && format.contains(".") && formatnumber.length()>1)
                           {
                               calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
                               numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                               hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                               String dp = format.replaceAll(",","");
                               dp = dp.substring(dp.indexOf(".")+1, dp.indexOf(".")+3);
                               english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(dp)));
                               hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(dp)));

                           }



                           else if (!somenumber.equals("0") && somenumber.length()==1)
                            {
                                calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
                                numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                                hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                                english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumber)));
                                hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));

                            }


                            else
                            {
                                numbers.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
                                hiddin.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
                                calculations.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
                                String somenumberi = hiddin.getText().toString();
                                somenumberi = somenumberi.substring(somenumberi.indexOf(".") + 1, somenumberi.indexOf(".") + 3);
                                english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumberi)));
                                hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumberi)));

                            }




//                    if (finalResult.contains(".")) {
//                       if ( somenumber.equals("0")) {
//                            numbers.setText(getFormatedAmount(c));
//                            calculations.setText(getFormatedAmount(Double.parseDouble(String.valueOf(k))));
//                            hiddin.setText(getFormatedAmount(Double.parseDouble(String.valueOf(k))));
//                            english.setText(convert(Double.parseDouble(String.valueOf(s))));
//                            hausa.setText(hausaconvert(Double.parseDouble(String.valueOf(s))));
//                            numbers.setText(getFormatedAmount(c));
//
//                       }
////                       else if(!somenumber.equals("0"))  {
////                            numbers.setText(getFormatedAmount(c));
////                            hiddin.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
////                            english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumber)));
////                            hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
////                            calculations.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
////
////                        }
//
//                       else  {
//                            numbers.setText(getFormatedAmount(c));
//                            hiddin.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
//                            String somenumberi = hiddin.getText().toString();
//                            somenumberi = somenumberi.substring(somenumberi.indexOf(".") + 1, somenumberi.indexOf(".") + 3);
//                            english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumberi)));
//                            hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumberi)));
//                            calculations.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
//
//                        }
//                    }

//                    else
//                    {
//                        numbers.setText(getFormatedAmount(c));
//                        hiddin.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
//                        String somenumberi = hiddin.getText().toString();
//                        somenumberi = somenumberi.substring(somenumberi.indexOf(".") + 1, somenumberi.indexOf(".") + 3);
//                        english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumberi)));
//                        hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumberi)));
//                        calculations.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
//
//                    }


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
                    if(num.contains("."))
                    {

                    }
                    else
                    {
                    hiddin.setText(num + ".");
                    numbers.setText(num + ".");
                    calculations.setText(cal + ".");
                }
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

        String mask = "000000000000000";
        DecimalFormat decimalFormat = new DecimalFormat(mask);

        snumber = decimalFormat.format(number);

        int trillion = Integer.parseInt(snumber.substring(0,3));

        int billion = Integer.parseInt(snumber.substring(3,6));

        int millions = Integer.parseInt(snumber.substring(6,9));

        int thousands = Integer.parseInt(snumber.substring(9,12));

        int hundred = Integer.parseInt(snumber.substring(12,15));


        String tradtrillion;

        switch (trillion)
        {
            case 0:
                tradtrillion = "";
                break;

            case 1:
                tradtrillion = convertLessThanOneThousand(trillion)+ " trillion ";
                break;
            default:
                tradtrillion = convertLessThanOneThousand(trillion)+ " trillion ";
        }
        String result = tradtrillion;



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

        if (result.isEmpty()){
            result = result + tradbillion;}
        else if(!tradbillion.isEmpty())
        {
            result = result + " and "+ tradbillion;
        }
//         result = result + "and "+tradbillion;



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

        String mask = "000000000000000";
        DecimalFormat decimalFormat = new DecimalFormat(mask);

        snumber = decimalFormat.format(number);

        int trillion = Integer.parseInt(snumber.substring(0,3));

        int billion = Integer.parseInt(snumber.substring(3,6));

        int millions = Integer.parseInt(snumber.substring(6,9));

        int thousands = Integer.parseInt(snumber.substring(9,12));

        int hundred = Integer.parseInt(snumber.substring(12,15));


        String tradtrillion;

        switch (trillion)
        {
            case 0:
                tradtrillion = "";
                break;

            case 1:
                tradtrillion = " triliyan "+hausaconvertLessThanOneThousand(trillion);
                break;
            default:
                tradtrillion = " triliyan "+hausaconvertLessThanOneThousand(trillion);
        }
        String result = tradtrillion;




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
        if(result.isEmpty()){
            result = result + tradbillion;}
        else if(!tradbillion.isEmpty())
        {
            result = result + " da " + tradbillion;
        }
//        String result = tradbillion;



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

    public void equalTo()
    {

        try {
            bracketChecker = false;
            numbers.setText("");
            english.setText("");
            hausa.setText("");
            hiddin.setText("");
            String temp = calculations.getText().toString();
            String process = temp.replaceAll(",","");
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


            double k = Double.parseDouble(finalResult);
//                    String o = String.valueOf(k);
//
//                    hiddin.setText(finalResult);
//                    double c = Double.parseDouble(finalResult);
//                    String somenumber = hiddin.getText().toString();
//                    somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
//                    int sn = Integer.parseInt(somenumber);


//                        calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
//                        numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
//                        hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))))

            String convert = finalResult;
            String somenumber = convert;
            somenumber = somenumber.substring(somenumber.indexOf(".") + 1);
            Double sn = Double.parseDouble(somenumber);
            int s = (int) Double.parseDouble(convert);
            String f = String.valueOf(s);
            String format = getFormatedAmount(Double.parseDouble(finalResult));
            String formatnumber = format;
            formatnumber = formatnumber.substring(formatnumber.indexOf(".") + 1);


            if (somenumber.equals("0"))
            {
                calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
                numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                english.setText(convert(Double.parseDouble(finalResult)));
                hausa.setText(hausaconvert(Double.parseDouble(finalResult)));

            }

            else if (somenumber.contains("E") && !format.contains("."))
            {
                calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
                numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                english.setText(convert(Double.parseDouble(finalResult)));
                hausa.setText(hausaconvert(Double.parseDouble(finalResult)));

            }


            else if (somenumber.contains("E") && format.contains(".") && formatnumber.length()==1)
            {
                calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
                numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(formatnumber)));
                hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(formatnumber)));
            }


            else if (somenumber.contains("E") && format.contains(".") && formatnumber.length()>1)
            {
                calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
                numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                String dp = format.replaceAll(",","");
                dp = dp.substring(dp.indexOf(".")+1, dp.indexOf(".")+3);
                english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(dp)));
                hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(dp)));
            }



            else if (!somenumber.equals("0") && somenumber.length()==1)
            {
                calculations.setText(getFormatedAmount(Double.parseDouble(finalResult)));
                numbers.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                hiddin.setText(getFormatedAmount(Double.parseDouble((finalResult))));
                english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumber)));
                hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));

            }


            else
            {
                numbers.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
                hiddin.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
                calculations.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
                String somenumberi = hiddin.getText().toString();
                somenumberi = somenumberi.substring(somenumberi.indexOf(".") + 1, somenumberi.indexOf(".") + 3);
                english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumberi)));
                hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumberi)));

            }




//                    if (finalResult.contains(".")) {
//                       if ( somenumber.equals("0")) {
//                            numbers.setText(getFormatedAmount(c));
//                            calculations.setText(getFormatedAmount(Double.parseDouble(String.valueOf(k))));
//                            hiddin.setText(getFormatedAmount(Double.parseDouble(String.valueOf(k))));
//                            english.setText(convert(Double.parseDouble(String.valueOf(s))));
//                            hausa.setText(hausaconvert(Double.parseDouble(String.valueOf(s))));
//                            numbers.setText(getFormatedAmount(c));
//
//                       }
////                       else if(!somenumber.equals("0"))  {
////                            numbers.setText(getFormatedAmount(c));
////                            hiddin.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
////                            english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumber)));
////                            hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumber)));
////                            calculations.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
////
////                        }
//
//                       else  {
//                            numbers.setText(getFormatedAmount(c));
//                            hiddin.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
//                            String somenumberi = hiddin.getText().toString();
//                            somenumberi = somenumberi.substring(somenumberi.indexOf(".") + 1, somenumberi.indexOf(".") + 3);
//                            english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumberi)));
//                            hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumberi)));
//                            calculations.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
//
//                        }
//                    }

//                    else
//                    {
//                        numbers.setText(getFormatedAmount(c));
//                        hiddin.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
//                        String somenumberi = hiddin.getText().toString();
//                        somenumberi = somenumberi.substring(somenumberi.indexOf(".") + 1, somenumberi.indexOf(".") + 3);
//                        english.setText(convert(Double.parseDouble(f)) + " point " + convert(Double.parseDouble(somenumberi)));
//                        hausa.setText(hausaconvert(Double.parseDouble(f)) + " da digo " + hausaconvert(Double.parseDouble(somenumberi)));
//                        calculations.setText(getFormatedAmount(Double.parseDouble(String.format("%s", new DecimalFormat("##.##").format(k)))));
//
//                    }


        }



        catch(Exception e)
        {

        }

    }



}
