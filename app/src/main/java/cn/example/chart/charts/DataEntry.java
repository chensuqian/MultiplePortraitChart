package cn.example.chart.charts;

import android.content.Context;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * chart data entity
 */
public class DataEntry
{
    public DataEntry(String label, ArrayList<Integer> datas)
    {
        this.label = label;
        this.datas = datas;
    }


    public DataEntry(String lable, ArrayList<Integer> datas,int textColor,int textSize,int rectColor)
    {
        this.label = lable;
        this.datas = datas;
        this.textColor=textColor;
        this.textSize=textSize;
        this.rectColor=rectColor;
    }

    private String label;

    private ArrayList<Integer> datas;


    private int textColor;
    private Paint textPaint;
    private float textSize=10;


    private int rectColor;
    private Paint rectPaint;

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public ArrayList<Integer> getDatas()
    {
        return datas;
    }

    public void setDatas(ArrayList<Integer> datas)
    {
        this.datas = datas;
    }

    public Paint getTextPaint(Context context)
    {
        if(textPaint==null)
        {
            textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setColor(this.textColor);
            textPaint.setTextSize(ChartUtil.dip2px(context,this.textSize));
        }
        return textPaint;
    }

    public void setTextColor(int color)
    {
        this.textColor = color;
    }

    public Paint getRectPaint()
    {
        if(rectPaint==null)
        {
            rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            rectPaint.setColor(this.rectColor);
            rectPaint.setStyle(Paint.Style.FILL);
        }
        return rectPaint;
    }

    public void setRectColor(int color)
    {
        this.rectColor = color;
    }


    public void setTextSize(float textSize)
    {
        this.textSize = textSize;
    }
}
