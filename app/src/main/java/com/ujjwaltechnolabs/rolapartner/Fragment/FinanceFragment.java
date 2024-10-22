package com.ujjwaltechnolabs.rolapartner.Fragment;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.FragmentFinanceBinding;

import java.util.ArrayList;
import java.util.Calendar;


public class FinanceFragment extends Fragment {


    FragmentFinanceBinding binding;

    private BarChart barChart;
    private BarDataSet barDataSet1;
    private BarDataSet barDataSet2;
    private String[] days = { "Mon", "Tue", "Wed", "Thu", "Fri","Sat","Sun"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentFinanceBinding.inflate(inflater, container, false);

        barChart = binding.barChart;

        initClicks();


        binding.imgbtnCalendar.setOnClickListener(v -> {
            showDatePickerDialog();

        });
        binding.radioIncome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        binding.radioExpenses.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.light_black_3)));

        setupBarChart();
        loadBarChartData();

        return binding.getRoot();


    }

    private void initClicks() {
        binding.btnDaily.setOnClickListener(view -> handleToolClick(view, binding.btnMonthly, binding.btnWeekly, binding.btnYear));
        binding.btnWeekly.setOnClickListener(view -> handleToolClick(view, binding.btnMonthly, binding.btnDaily, binding.btnYear));
        binding.btnMonthly.setOnClickListener(view -> handleToolClick(view, binding.btnDaily, binding.btnWeekly, binding.btnYear));
        binding.btnYear.setOnClickListener(view -> handleToolClick(view, binding.btnMonthly, binding.btnWeekly, binding.btnDaily));

        binding.radioIncome.setOnClickListener(v -> {
            setActiveView(binding.radioIncome, binding.radioExpenses);
        });

        binding.radioExpenses.setOnClickListener(v -> {
            setActiveView(binding.radioExpenses, binding.radioIncome);
        });
    }

    private void setupBarChart() {
        barChart.getDescription().setEnabled(false);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(7f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setAxisMinimum(0f);

        barChart.animateY(1000);
    }

//    private void loadBarChartData() {
//        barDataSet1 = new BarDataSet(getBarChartDataForSet1(), "Income");
//        barDataSet1.setColor(ContextCompat.getColor(getContext(), R.color.green));
//        barDataSet1.setDrawValues(false); // Optional: Hide value labels on top
//
//        barDataSet2 = new BarDataSet(getBarChartDataForSet2(), "Expenses");
//        barDataSet2.setColor(ContextCompat.getColor(getContext(), R.color.light_black_3));
//        barDataSet2.setDrawValues(false); // Optional: Hide value labels on top
//
//        BarData data = new BarData(barDataSet1, barDataSet2);
//        data.setBarWidth(0.3f); // Adjust as necessary
//
//        barChart.setData(data);
//        barChart.groupBars(0f, 0.5f, 0.1f);
//
//        // Set the custom drawable as the background for the bars
//        barDataSet1.setDrawValues(false);
//        barDataSet2.setDrawValues(false);
//        barDataSet1.setValueTextColor(Color.TRANSPARENT);
//        barDataSet2.setValueTextColor(Color.TRANSPARENT);
//
//        // Refresh the chart
//        barChart.invalidate();
//    }


    private void loadBarChartData() {
        barDataSet1 = new BarDataSet(getBarChartDataForSet1(), "Income");
        barDataSet1.setColor(ContextCompat.getColor(getContext(), R.color.green));

        barDataSet2 = new BarDataSet(getBarChartDataForSet2(), "Expenses");
        barDataSet2.setColor(ContextCompat.getColor(getContext(), R.color.light_black_3));

        BarData data = new BarData(barDataSet1, barDataSet2);
        data.setBarWidth(0.13f);

        barChart.setData(data);
        barChart.groupBars(0f, 0.5f, 0.1f);
        barChart.invalidate(); // Refresh the chart
    }

    private ArrayList<BarEntry> getBarChartDataForSet1() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 1f)); // Mon
        entries.add(new BarEntry(1f, 2f)); // Tue
        entries.add(new BarEntry(2f, 3f)); // Wed
        entries.add(new BarEntry(3f, 4f)); // Thu
        entries.add(new BarEntry(4f, 5f)); // Fri
        entries.add(new BarEntry(5f, 6f)); // Sat
        entries.add(new BarEntry(6f, 7f)); // Sun

        return entries;
    }

    private ArrayList<BarEntry> getBarChartDataForSet2() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 1f)); // Mon
        entries.add(new BarEntry(1f, 2f)); // Tue
        entries.add(new BarEntry(2f, 5f)); // Wed
        entries.add(new BarEntry(3f, 10f)); // Thu
        entries.add(new BarEntry(4f, 15f)); // Fri
        entries.add(new BarEntry(5f, 20f)); // Sat
        entries.add(new BarEntry(6f, 25f)); // Sun

        return entries;
    }
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;

                    }
                },
                year, month, day
        );

        datePickerDialog.show();
    }
    private void handleToolClick(View clickedView, View... views) {

        clickedView.setBackgroundResource(R.drawable.rounded_green);
        TextView clickedTextView = (TextView) ((ViewGroup) clickedView).getChildAt(0);
        clickedTextView.setTextColor(Color.WHITE);

        // Set the inactive cards background to white
        for (View view : views) {
            view.setBackgroundResource(R.drawable.rounded_white);
            TextView otherTextView = (TextView) ((ViewGroup) view).getChildAt(0);
            otherTextView.setTextColor(Color.BLACK); // Change text color back to black
        }
    }

    private void setActiveView(View activeView, View inactiveView) {
        activeView.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green))); // Set active color
        inactiveView.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.light_black_3))); // Set inactive color
    }


}