package com.example.everydollartracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Statistics#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Statistics extends Fragment {
    TextView textViewOI,textViewOE,textViewOS,textViewOW,textViewON,textViewDA,textViewMA,textViewDE,textViewME;
    static double oS,oN,oW;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Statistics() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Statistics.
     */
    // TODO: Rename and change types and number of parameters
    public static Statistics newInstance(String param1, String param2) {
        Statistics fragment = new Statistics();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        textViewOI= (TextView) view.findViewById(R.id.textViewOI);
        textViewOE= (TextView) view.findViewById(R.id.textViewOE);
        textViewOS= (TextView) view.findViewById(R.id.textViewOS);
        textViewOW= (TextView) view.findViewById(R.id.textViewOW);
        textViewON= (TextView) view.findViewById(R.id.textViewON);
        textViewDA= (TextView) view.findViewById(R.id.textViewDA);
        textViewMA= (TextView) view.findViewById(R.id.textViewMA);
        textViewDE= (TextView) view.findViewById(R.id.textViewDE);
        textViewME= (TextView) view.findViewById(R.id.textViewME);

        textViewOI.setText(funOI());
        textViewOE.setText(funOE());
        textViewOS.setText(funOS());
        textViewOW.setText(funOW());
        textViewON.setText(funON());
        textViewDA.setText(funDA());
        textViewMA.setText(funMA());
        textViewDE.setText(funDE());
        textViewME.setText(funME());

        return view;
    }

    private String funME() {
        double xa = 0.0;
        Calendar cal = Calendar.getInstance();
        int thisMonth = cal.get(Calendar.MONTH)+1;
        String result = "";

        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("EXPENSES")){
                String dateString = i.getDate().substring(0,2); /*  08/07/2021 */
                int dateInt = Integer.parseInt(dateString);

                if(thisMonth == dateInt){
                    xa=xa+ i.getAmount();
                }

            }
        }
        result = String.format("%.2f",xa);
        return result;
    }

    private String funDE() {
        double xa = 0.0;
        Calendar cal = Calendar.getInstance();
        int thisMonth = cal.get(Calendar.MONTH)+1;
        int numDays =0;
        int thisDay = cal.get(Calendar.DAY_OF_MONTH);
        String result = "";


        if(thisMonth==1){
            numDays=31;
        }else if(thisMonth==2){
            numDays=28;
        }else if(thisMonth==3){
            numDays=31;
        }else if(thisMonth==4){
            numDays=30;
        }else if(thisMonth==5){
            numDays=31;
        }else if(thisMonth==6){
            numDays=30;
        }else if(thisMonth==7){
            numDays=31;
        }else if(thisMonth==8){
            numDays=31;
        }else if(thisMonth==9){
            numDays=30;
        }else if(thisMonth==10){
            numDays=31;
        }else if(thisMonth==11){
            numDays=30;
        }else if(thisMonth==12){
            numDays=31;
        }

        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("EXPENSES")){
                String dateString = i.getDate().substring(3,5); /*  08/07/2021 */
                int dateInt = Integer.parseInt(dateString);

                if(thisDay == dateInt){
                    xa=xa+ i.getAmount();
                }

            }
        }
        result = String.format("%.2f",xa);
        return result;

    }

    private String funMA() {
        double xa = 0.0;
        Calendar cal = Calendar.getInstance();
        int thisMonth = cal.get(Calendar.MONTH)+1;
        String result = "";

        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("INCOME")){
                String dateString = i.getDate().substring(0,2); /*  08/07/2021 */
                int dateInt = Integer.parseInt(dateString);

                if(thisMonth == dateInt){
                    xa=xa+ i.getAmount();
                }

            }
        }
        result = String.format("%.2f",xa);
        return result;
    }

    private String funDA() {
        double xa = 0.0;
        Calendar cal = Calendar.getInstance();
        int thisMonth = cal.get(Calendar.MONTH)+1;
        int numDays =0;
        int thisDay = cal.get(Calendar.DAY_OF_MONTH);
        String result = "";


        if(thisMonth==1){
            numDays=31;
        }else if(thisMonth==2){
            numDays=28;
        }else if(thisMonth==3){
            numDays=31;
        }else if(thisMonth==4){
            numDays=30;
        }else if(thisMonth==5){
            numDays=31;
        }else if(thisMonth==6){
            numDays=30;
        }else if(thisMonth==7){
            numDays=31;
        }else if(thisMonth==8){
            numDays=31;
        }else if(thisMonth==9){
            numDays=30;
        }else if(thisMonth==10){
            numDays=31;
        }else if(thisMonth==11){
            numDays=30;
        }else if(thisMonth==12){
            numDays=31;
        }

        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("INCOME")){
                String dateString = i.getDate().substring(0,2); /*  08/07/2021 */
                int dateInt = Integer.parseInt(dateString);

                if(thisDay == dateInt){
                    xa=xa+ i.getAmount();
                }

            }
        }
        xa=xa/numDays;
        result = String.format("%.2f",xa);
        return result;
    }

    String funOI(){
        String textA="";
        double xa=0.0;
        //for (int i = 0; i < App_Page.inExArray.size(); i++)
        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("INCOME")){
                xa=xa+ i.getAmount();
            }
        }
        textA=Double.toString(xa);
        return textA;
    }
    String funOE(){
        String textB="";
        double xb=0.0;
        //for (int i = 0; i < App_Page.inExArray.size(); i++)
        for (InExStore i : App_Page.inExArray){
            if(i.getType().equals("EXPENSES")){
                xb=xb+i.getAmount();
            }
        }
        textB=Double.toString(xb);

        return textB;
    }
    public static String funOS(){
        String textB="";
        double xb=0.0;
        //for (int i = 0; i < App_Page.inExArray.size(); i++)
        for (InExStore i : App_Page.inExArray){
            if(i.getSource().equals("SAVINGS")){
                xb=xb+i.getAmount();
            }
        }
        oS=xb;
        textB=Double.toString(xb);

        return textB;
    }
    static String funOW(){
        String textB="";
        double xb=0.0;
        //for (int i = 0; i < App_Page.inExArray.size(); i++)
        for (InExStore i : App_Page.inExArray){
            if(i.getSource().equals("WANTS")){
                xb=xb+i.getAmount();
            }
        }
        oW=xb;
        textB=Double.toString(xb);

        return textB;
    }
    static String funON(){
        String textB="";
        double xb=0.0;
        //for (int i = 0; i < App_Page.inExArray.size(); i++)
        for (InExStore i : App_Page.inExArray){
            if(i.getSource().equals("NEEDS")){
                xb=xb+i.getAmount();
            }
        }
        oN=xb;
        textB=Double.toString(xb);

        return textB;
    }
}

