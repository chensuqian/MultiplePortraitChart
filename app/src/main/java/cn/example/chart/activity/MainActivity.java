package cn.example.chart.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import cn.example.chart.R;
import cn.example.chart.charts.ChartUtil;
import cn.example.chart.charts.DataEntry;
import cn.example.chart.charts.MultipleDataEntry;
import cn.example.chart.charts.MultiplePortraitBarChartView;

public class MainActivity extends Activity
{
    private MultiplePortraitBarChartView bar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=findViewById(R.id.bar);


        int h = getResources().getDisplayMetrics().heightPixels - ChartUtil.getTopHeight(this)-ChartUtil.dip2px(this,150) ;
        bar.setxScale(ChartUtil.dip2px(this,220));
        bar.setyScale((h-ChartUtil.dip2px(this, 35+35))/10);
        bar.setXCoordinateSize(10);
        bar.setMaxWidth(getResources().getDisplayMetrics().widthPixels - ChartUtil.dip2px(this,20));
        bar.setBackgroundColor(getResources().getColor(R.color.white));
        bar.setXCoordinateColor(getResources().getColor(R.color.grey_333333));
        bar.setXAxisColor(getResources().getColor(R.color.grey_333333));
        bar.setYCoordinateColor(getResources().getColor(R.color.grey_333333));
        bar.setYAxisColor(getResources().getColor(R.color.grey_333333));
        bar.setGridColor(getResources().getColor(R.color.grey_333333));


        ArrayList<String> xVals = new ArrayList<>();

        ArrayList<MultipleDataEntry> multipleDataEntries=new ArrayList<>();

        MultipleDataEntry multipleDataEntry1=new MultipleDataEntry();
        MultipleDataEntry multipleDataEntry2=new MultipleDataEntry();
        MultipleDataEntry multipleDataEntry3=new MultipleDataEntry();

        ArrayList<DataEntry> dataEntryList1=new ArrayList<>();
        ArrayList<DataEntry> dataEntryList2=new ArrayList<>();
        ArrayList<DataEntry> dataEntryList3=new ArrayList<>();

        ArrayList<Integer> valueList11=new ArrayList<>();
        ArrayList<Integer> valueList12=new ArrayList<>();
        ArrayList<Integer> valueList13=new ArrayList<>();
        ArrayList<Integer> valueList21=new ArrayList<>();
        ArrayList<Integer> valueList22=new ArrayList<>();
        ArrayList<Integer> valueList23=new ArrayList<>();
        ArrayList<Integer> valueList24=new ArrayList<>();
        ArrayList<Integer> valueList31=new ArrayList<>();
        ArrayList<Integer> valueList32=new ArrayList<>();

        Random r=new Random();
        for (int i=0;i<10;i++)
        {
            xVals.add("name_"+(i+1));
            int v=r.nextInt(20);
            Log.i("MultipleBarChart",String.valueOf(v));
            valueList11.add(r.nextInt(20));
            valueList12.add(r.nextInt(20));
            valueList13.add(r.nextInt(20));
            valueList21.add(r.nextInt(20));
            valueList22.add(r.nextInt(20));
            valueList23.add(r.nextInt(20));
            valueList24.add(r.nextInt(20));
            valueList31.add(r.nextInt(20));
            valueList32.add(r.nextInt(20));
        }

        DataEntry dataEntry11=new DataEntry("AAA",valueList11,getResources().getColor(R.color.black),8,getResources().getColor(R.color.color_11));
        DataEntry dataEntry12=new DataEntry("BBB",valueList12,getResources().getColor(R.color.black),8,getResources().getColor(R.color.color_12));
        DataEntry dataEntry13=new DataEntry("CCC",valueList13,getResources().getColor(R.color.black),8,getResources().getColor(R.color.color_13));
        DataEntry dataEntry21=new DataEntry("DDD",valueList21,getResources().getColor(R.color.black),8,getResources().getColor(R.color.color_21));
        DataEntry dataEntry22=new DataEntry("EEE",valueList22,getResources().getColor(R.color.black),8,getResources().getColor(R.color.color_22));
        DataEntry dataEntry23=new DataEntry("FFF",valueList23,getResources().getColor(R.color.black),8,getResources().getColor(R.color.color_23));
        DataEntry dataEntry24=new DataEntry("GGG",valueList24,getResources().getColor(R.color.black),8,getResources().getColor(R.color.color_24));
        DataEntry dataEntry31=new DataEntry("HHH",valueList31,getResources().getColor(R.color.black),8,getResources().getColor(R.color.color_31));
        DataEntry dataEntry32=new DataEntry("III",valueList32,getResources().getColor(R.color.black),8,getResources().getColor(R.color.color_32));

        dataEntryList1.add(dataEntry11);
        dataEntryList1.add(dataEntry12);
        dataEntryList1.add(dataEntry13);
        dataEntryList2.add(dataEntry21);
        dataEntryList2.add(dataEntry22);
        dataEntryList2.add(dataEntry23);
        dataEntryList2.add(dataEntry24);
        dataEntryList3.add(dataEntry31);
        dataEntryList3.add(dataEntry32);

        multipleDataEntry1.setEntries(dataEntryList1);
        multipleDataEntry2.setEntries(dataEntryList2);
        multipleDataEntry3.setEntries(dataEntryList3);

        multipleDataEntries.add(multipleDataEntry1);
        multipleDataEntries.add(multipleDataEntry2);
        multipleDataEntries.add(multipleDataEntry3);


        bar.setData(xVals, multipleDataEntries);
        bar.invalidate();

    }
}
