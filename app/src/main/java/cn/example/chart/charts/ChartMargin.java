package cn.example.chart.charts;

/**
 * the MultiplePortraitBarChartView's margin value
 */
public class ChartMargin
{
    public ChartMargin()
    {

    }

    public ChartMargin(float l, float t, float r, float b)
    {
        this.left = l;
        this.top = t;
        this.right = r;
        this.bottom = b;
    }

    public ChartMargin(float m)
    {
        this.left = this.top = this.right = this.bottom = m;
    }

    private float left = 50;
    private float top = 50;
    private float right = 50;
    private float bottom = 50;

    public float getLeft()
    {
        return left;
    }

    public void setLeft(float left)
    {
        this.left = left;
    }

    public float getTop()
    {
        return top;
    }

    public void setTop(float top)
    {
        this.top = top;
    }

    public float getRight()
    {
        return right;
    }

    public void setRight(float right)
    {
        this.right = right;
    }

    public float getBottom()
    {
        return bottom;
    }

    public void setBottom(float bottom)
    {
        this.bottom = bottom;
    }
}
