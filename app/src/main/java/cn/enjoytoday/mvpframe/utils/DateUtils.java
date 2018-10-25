package cn.enjoytoday.mvpframe.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 * @author hfcai
 */
public class DateUtils {

    /**
     * 格式化时间
     * @param mill 时间
     * @return
     */
    public static String getDateString(long mill){
        Date date = new Date(mill);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(date);
    }

    public static String getDateString(long mill,String formatString){
        Date date = new Date(mill);
        SimpleDateFormat format=new SimpleDateFormat(formatString, Locale.CHINA);
        return format.format(date);

    }

}
