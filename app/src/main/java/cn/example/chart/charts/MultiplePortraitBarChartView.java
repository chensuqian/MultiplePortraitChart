package cn.example.chart.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * draw view
 */
public class MultiplePortraitBarChartView extends View
{
    private Context context;

    public MultiplePortraitBarChartView(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public MultiplePortraitBarChartView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MultiplePortraitBarChartView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    /**
     * x Coordinate label text Size
     */
    private int xCoordinateSize = 12;
    /**
     * y Coordinate label text Size
     */
    private int yCoordinateSize = 12;
    /**
     * landscape grid line paint
     */
    protected Paint paint_Grid;
    /**
     * x Coordinate line paint
     */
    protected Paint paint_XCoordinate;
    /**
     * x Coordinate label text paint
     */
    protected Paint paint_XAxis;
    /**
     * y Coordinate line paint
     */
    protected Paint paint_YCoordinate;
    /**
     * y Coordinate label text paint
     */
    protected Paint paint_YAxis;

    /**
     * foreground paint
     */
    protected Paint paint_Bg;

    /**
     * x Coordinate lebels
     */
    private ArrayList<String> XLables;
    /**
     * y Coordinate lebels and values
     */
    private ArrayList<XYEntry> YLables;
    /**
     * datas
     */
    private ArrayList<MultipleDataEntry> multipleDataEntries;

    /**
     * canvas's margin
     */
    private ChartMargin margin = new ChartMargin(80, 80, 80, 120);
    /**
     *  x Coordinate line total length
     */
    private float xAxisLength;

    /**
     * y Coordinate line total length
     */
    private float yAxisLength;

    /**
     * canvas's background color
     */
    private int BackgroundColor = Color.rgb(255, 255, 255);

    /**
     * every x Coordinate width
     */
    private float xScale = 0;

    /**
     * every bar's margin,the value = xScale * 0.1
     */
    private float xSpace = 0;
    /**
     * 柱状图宽  (xScale-(multipleDataEntries.size()+1)*xSpace)/multipleDataEntries.size()
     */
    private float barWidth = 0;

    /**
     * every y Coordinate height
     */
    private float yScale = 0;
    /**
     * x offset of every drawing ,default value is margin.left, touch move to left or right,this value will change
     */
    private float startX = margin.getLeft();

    /**
     * y Coordinate's max value
     */
    private float maxYValue = 0;

    /**
     * canvas's width
     */
    private float maxWidth = 0;

    /**
     * y offset of every drawing
     */
    private float yOffset;

    private void init()
    {
        ChartUtil.init(context);
        paint_Grid = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_Grid.setColor(Color.WHITE);

        paint_XCoordinate = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_XCoordinate.setColor(Color.WHITE);
        paint_XCoordinate.setTextSize(ChartUtil.dip2px(context,xCoordinateSize));

        paint_XAxis = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_XAxis.setColor(Color.WHITE);

        paint_YCoordinate = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_YCoordinate.setColor(Color.WHITE);
        paint_YCoordinate.setTextSize(ChartUtil.dip2px(context,yCoordinateSize));

        paint_YAxis = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_YAxis.setColor(Color.WHITE);

        paint_Bg = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_Bg.setColor(BackgroundColor);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawColor(BackgroundColor);
        if (XLables == null || YLables == null)
        {
            return;
        }
        if (xAxisLength <= 0 || yAxisLength <= 0)
        {
            return;
        }
        // draw description
        float left = 0;
        float right = 0;
        float top = 0;
        float bottom = 0;
        for (int i = 0; i < multipleDataEntries.size(); i++)
        {
            MultipleDataEntry entry = multipleDataEntries.get(i);
            for (int j = 0; j < entry.getEntries().size(); j++)
            {
                DataEntry dataEntry = entry.getEntries().get(j);

                left = margin.getLeft() + ChartUtil.dip2px(context,10) + ChartUtil.dip2px(context,60 * j);
                right = left + +ChartUtil.dip2px(context,16);

                top = margin.getTop() - ChartUtil.dip2px(context,16) + i * ChartUtil.dip2px(context,12);
                bottom = margin.getTop() - ChartUtil.dip2px(context,8) + i * ChartUtil.dip2px(context,12);

                canvas.drawRect(left, top, right, bottom, dataEntry.getRectPaint());
                canvas.drawText(dataEntry.getLabel(), left + ChartUtil.dip2px(context,20), bottom, paint_XCoordinate);
            }
        }

        yOffset = (multipleDataEntries.size()) * ChartUtil.dip2px(context,12);
        float xCoordinateYValue = margin.getTop() + yAxisLength + yOffset;
        // draw x Coordinate line
        canvas.drawLine(margin.getLeft(), xCoordinateYValue, margin.getLeft() + xAxisLength, xCoordinateYValue, paint_XAxis);

        //draw landscape grid line
        for (int i = 1; i < YLables.size(); i++)
        {
            float y = margin.getTop() + yAxisLength - i * yScale + yOffset;
            canvas.drawLine(margin.getLeft(), y, margin.getLeft() + xAxisLength, y, paint_Grid);
        }

        // draw x Coordinate split line
        float yStart = margin.getTop() + yAxisLength + yOffset;
        float yEnd = yStart + ChartUtil.dip2px(context,5);
        for (int i = 0; i <= XLables.size(); i++)
        {
            float xStart = startX + xScale * i;
            canvas.drawLine(xStart, yStart, xStart, yEnd, paint_XAxis);
        }

        // draw x Coordinate label text
        for (int i = 0; i < XLables.size(); i++)
        {
            float xStart = startX + xScale * i;
            String label=XLables.get(i);
            label=ChartUtil.SubString(label,xScale,paint_XCoordinate);
            int labelWidth =ChartUtil.CalculateTextWidth(label,paint_XCoordinate);
            canvas.drawText(label, xStart + (xScale-labelWidth)/2, yStart + ChartUtil.dip2px(context,xCoordinateSize + 2), paint_XCoordinate);

        }

        // draw bar chart
        // the every bar chart's width: barWidth = (xScale - (multipleDataEntries.size() + 1) * xSpace) / 2

        // first bar chart: left = xScale + xSpace
        // first bar chart: right = left + barWidth

        // second bar chart: left = right + xSpace
        // second bar chart: right = left + barWidth

        // third bar chart: left = right + xSpace
        // third bar chart: right = left + barWidth
        // .......
        //total x Coordinate count
        for (int z = 0; z < multipleDataEntries.get(0).getEntries().get(0).getDatas().size(); z++)
        {
            //bar chart count in every x Coordinate
            for (int i = 0; i < multipleDataEntries.size(); i++)
            {
                //every bar chart count start point
                left = startX + xScale * z + xSpace * (i + 1) + barWidth * i;
                right = left + barWidth;

                MultipleDataEntry multipleDataEntry = multipleDataEntries.get(i);
                //every bar chart has vertical multiple level
                for (int j = 0; j < multipleDataEntry.getEntries().size(); j++)
                {
                    DataEntry dataEntry = multipleDataEntry.getEntries().get(j);
                    if (j == 0)
                    {
                        bottom = margin.getTop() + yAxisLength;
                        top = margin.getTop() + yAxisLength - ((dataEntry.getDatas().get(z) * 1.0f) / maxYValue) * yAxisLength;
                        bottom = bottom+yOffset;
                        top = top+yOffset;
                    }
                    else
                    {
                        bottom = top;
                        top = bottom - ((dataEntry.getDatas().get(z) * 1.0f) / maxYValue) * yAxisLength;
                    }

                    if (right > margin.getLeft())
                    {
                        // draw bar
                        canvas.drawRect(left, top, right, bottom, dataEntry.getRectPaint());
                        if (dataEntry.getDatas().get(z) > 0)
                        {
                            String text=dataEntry.getLabel() + "：" + dataEntry.getDatas().get(z);
                            Rect rect = new Rect();
                            dataEntry.getTextPaint(context).getTextBounds(text,0,text.length(),rect);
                            int textWidth= rect.right-rect.left;
                            canvas.drawText(text, left+(barWidth-textWidth)/2, top + (bottom - top) / 2 + ChartUtil.dip2px(context,3.5f), dataEntry.getTextPaint(context));
                        }
                    }
                }
            }
        }

        // draw left foreground,in order touch move to left or right,the chart only display in canvas
        canvas.drawRect(0, 0, margin.getLeft(), yAxisLength + margin.getTop() + ChartUtil.dip2px(context,30) + yOffset, paint_Bg);

        // draw y Coordinate line
        canvas.drawLine(margin.getLeft(), margin.getTop() + yAxisLength + yOffset, margin.getLeft(), margin.getTop() + yOffset, paint_YAxis);

        // draw y Coordinate label text
        for (int i = 0; i < YLables.size(); i++)
        {
            float y = margin.getTop() + yAxisLength - i * yScale + yOffset;
            String lableName = YLables.get(i).getLableName();

            for (int j = 0; j < lableName.length(); j++)
            {
                canvas.drawText(String.valueOf(lableName.charAt(lableName.length() - 1 - j)), margin.getLeft() - ChartUtil.dip2px(context,8) - j * ChartUtil.dip2px(context,7), y + ChartUtil.dip2px(context,3), paint_YCoordinate);
            }
        }
    }

    public void setMargin(ChartMargin margin)
    {
        this.margin = margin;
        this.startX = margin.getLeft();
    }

    public void setData(ArrayList<String> xLables, ArrayList<MultipleDataEntry> multipleDataEntries)
    {
        if (xLables == null || xLables.size() == 0 || multipleDataEntries == null)
        {
            return;
        }
        this.XLables = xLables;
        this.multipleDataEntries = multipleDataEntries;

        maxYValue = getYMaxValue();
        if (maxYValue <= 10)
        {
            maxYValue = 10;
        }
        else
        {
            while (true)
            {
                if (maxYValue % 10 == 0)
                {
                    break;
                }
                else
                {
                    maxYValue++;
                }
            }
        }

        YLables = new ArrayList<>();
        float ys = maxYValue / 10;
        for (int i = 0; i <= 10; i++)
        {
            YLables.add(new XYEntry("" + i * (int) ys, i * (int) ys));
        }
        yAxisLength = (YLables.size() - 1) * yScale;
        xAxisLength = xLables.size() * xScale;
        if (xAxisLength < maxWidth)
        {
            xAxisLength = maxWidth;
        }
        this.barWidth = (xScale - (multipleDataEntries.size() + 1) * xSpace) / multipleDataEntries.size();
    }

    /**
     * calculate y Coordinate max value
     *
     */
    private int getYMaxValue()
    {
        int maxY = 0;
        for (MultipleDataEntry multipleDataEntry : multipleDataEntries)
        {
            int maxSum = multipleDataEntry.getMaxSum();
            if (maxY <= maxSum)
            {
                maxY = maxSum;
            }
        }
        return maxY;
    }

    public void setBackgroundColor(int color)
    {
        this.BackgroundColor = color;
        paint_Bg.setColor(color);
    }

    public void setXCoordinateColor(int color)
    {
        paint_XCoordinate.setColor(color);
    }

    public void setXAxisColor(int color)
    {
        paint_XAxis.setColor(color);
    }

    public void setYCoordinateColor(int color)
    {
        paint_YCoordinate.setColor(color);
    }

    public void setYAxisColor(int color)
    {
        paint_YAxis.setColor(color);
    }

    public void setGridColor(int color)
    {
        paint_Grid.setColor(color);
    }

    public void setxScale(float xScale)
    {
        this.xScale = xScale;
        this.xSpace = this.xScale * 0.1f;
    }

    float preX;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        getParent().requestDisallowInterceptTouchEvent(true);
        if (XLables != null)
        {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    preX = event.getRawX();
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    float currentX = event.getRawX();
                    float distance = currentX - preX;
                    if (distance > 0)
                    {
                        if (startX <= margin.getLeft())
                        {
                            startX = startX + distance;

                            if (startX > margin.getLeft())
                            {
                                startX = margin.getLeft();
                            }
                            postInvalidate();
                        }
                    }
                    else if (distance < 0)
                    {
                        if ((Math.abs(startX) + maxWidth) < xAxisLength+margin.getLeft())
                        {
                            startX = startX + distance;
                            if (startX < (maxWidth - xAxisLength))
                            {
                                startX = maxWidth - xAxisLength;
                            }
                        }
                        postInvalidate();
                    }
                    else
                    {
                    }
                    preX = currentX;
                    break;
                default:
                    break;
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void setyScale(float yScale)
    {
        this.yScale = yScale;
    }


    public void setXCoordinateSize(int xCoordinateSize)
    {
        this.xCoordinateSize = xCoordinateSize;
        paint_XCoordinate.setTextSize(ChartUtil.dip2px(context,xCoordinateSize));
    }

    public void setYCoordinateSize(int yCoordinateSize)
    {
        this.yCoordinateSize = yCoordinateSize;
        paint_YCoordinate.setTextSize(ChartUtil.dip2px(context,yCoordinateSize));
    }

    public void setMaxWidth(float maxWidth)
    {
        this.maxWidth = maxWidth;
    }
}
