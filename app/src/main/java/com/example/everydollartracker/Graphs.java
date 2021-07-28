package com.example.everydollartracker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Graphs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Graphs extends Fragment {
    static double  pN=50, pW=30, pS=20, d=45,e=45,f=10;
    /////////graph var////////////
    TextView tvR, tvPython, tvCPP, tvJava;
    EditText etN,etW,etS;
    PieChart pieChartPlanned;
    PieChart pieChartActual;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Graphs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Graphs.
     */
    // TODO: Rename and change types and number of parameters
    public static Graphs newInstance(String param1, String param2) {
        Graphs fragment = new Graphs();
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
        View view = inflater.inflate(R.layout.fragment_graphs, container, false);
        etN=(EditText) view.findViewById(R.id.etN);
        etW=(EditText) view.findViewById(R.id.etW);
        etS=(EditText) view.findViewById(R.id.etS);
        pieChartPlanned = (PieChart)  view.findViewById(R.id.piechartPlanned);
        pieChartActual = (PieChart)  view.findViewById(R.id.piechartActual);

        String etNtoS = etN.getText().toString().trim();
        String etWtoS = etW.getText().toString().trim();
        String etStoS = etS.getText().toString().trim();
        pN=Double.parseDouble(etNtoS);
        pW=Double.parseDouble(etWtoS);
        pS=Double.parseDouble(etStoS);
        setData(pieChartPlanned,pN,pW,pS);
        setData(pieChartActual,d,e,f);
        return view;
    }
    void setData(PieChart pieChart, double ds, double dw, double dn)
    {
        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "NEEDS", (float) ds
                        ,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "WANTS",
                        (float) dn,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "SAVINGS",
                        (float) dw,



                        Color.parseColor("#EF5350")));


        // To animate the pie chart
        pieChart.startAnimation();
    }
    void updateG(View view){
        pieChartPlanned = (PieChart)  view.findViewById(R.id.piechartPlanned);
        etN=(EditText) view.findViewById(R.id.etN);
        etW=(EditText) view.findViewById(R.id.etW);
        etS=(EditText) view.findViewById(R.id.etS);
        pieChartPlanned = (PieChart)  view.findViewById(R.id.piechartPlanned);

        String etNtoS = etN.getText().toString().trim();
        String etWtoS = etW.getText().toString().trim();
        String etStoS = etS.getText().toString().trim();
        pN=Double.parseDouble(etNtoS);
        pW=Double.parseDouble(etWtoS);
        pS=Double.parseDouble(etStoS);
        setData(pieChartPlanned,pN,pW,pS);
    }

}