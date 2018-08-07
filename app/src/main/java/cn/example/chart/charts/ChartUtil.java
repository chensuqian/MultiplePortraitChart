package cn.example.chart.charts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import java.lang.reflect.Field;

/**
 * tools class
 */
public class ChartUtil
{
    private static Resources resources;

    public static void init(Context context)
    {
        resources = context.getResources();
    }



    public static String SubString(String label, float maxWidth, Paint paint)
    {
        Rect rect = new Rect();
        paint.getTextBounds(label, 0, label.length(), rect);
        int labelWidth = rect.right - rect.left;
        if (labelWidth < maxWidth)
        {
            return label;
        }
        while (labelWidth > maxWidth)
        {
            label = label.substring(0, label.length() - 1);
            paint.getTextBounds(label, 0, label.length(), rect);
            labelWidth = rect.right - rect.left;
            if (labelWidth < maxWidth)
            {
                if (label.length() > 3)
                {
                    label = label.substring(0, label.length() - 3) + "..";
                    paint.getTextBounds(label, 0, label.length(), rect);
                    labelWidth = rect.right - rect.left;
                }
            }
        }
        return label;
    }

    public static int CalculateTextWidth(String label, Paint paint)
    {
        Rect rect = new Rect();
        paint.getTextBounds(label, 0, label.length(), rect);
        return rect.right - rect.left;
    }

    /**
     * get top status bar height
     *
     * @param context
     * @return
     */
    public static int getTopHeight(Context context)
    {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try
        {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return sbar;
    }

    /**
     * dip2px
     *
     * @param paramContext
     * @param paramFloat
     * @return
     */
    public static int dip2px(Context paramContext, float paramFloat)
    {
        return (int) (0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
    }

    /**
     * px2dp
     *
     * @param context
     * @param px
     * @return
     */
    public static int px2dp(Context context, float px)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * px2sp
     *
     * @return
     */
    public static int px2sp(Context context, float px)
    {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scale + 0.5f);
    }

}
