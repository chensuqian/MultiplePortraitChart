package cn.example.chart.charts;

/**
 * store x.y coordinate's label and value
 */
public class XYEntry
{

    public XYEntry()
    {
    }

    public XYEntry(String k, double v)
    {
        this.LableName = k;
        this.LabelValue = v;
    }

    private String LableName;
    private double LabelValue;

    public String getLableName()
    {
        return LableName;
    }

    public void setLableName(String lableName)
    {
        LableName = lableName;
    }

    public double getLabelValue()
    {
        return LabelValue;
    }

    public void setLabelValue(double labelValue)
    {
        LabelValue = labelValue;
    }
}
