package cn.example.chart.charts;

import java.util.ArrayList;

/**
 * one MultipleDataEntry is one x coordinates
 * every x coordinates has multiple bar chart
 * one bar chart has split sub bar chart
 */
public class MultipleDataEntry
{
    private ArrayList<DataEntry> entries;

    public ArrayList<DataEntry> getEntries()
    {
        return entries;
    }

    public void setEntries(ArrayList<DataEntry> entries)
    {
        this.entries = entries;
    }

    /**
     * calculate every brother node max sum of vertical bar
     * @return
     */
    public int getMaxSum()
    {
        int maxSum=0;
        for (int i=0;i<entries.get(0).getDatas().size();i++)
        {
            int sum=0;
            for (int j=0;j<entries.size();j++)
            {
                sum+=entries.get(j).getDatas().get(i);
            }
            if(sum>=maxSum)
            {
                maxSum=sum;
            }
        }
        return  maxSum;
    }
}
