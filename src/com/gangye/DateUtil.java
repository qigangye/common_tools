package com.gangye;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname DateUtil
 * @Description 时间日期工具类
 * @Date 2020/4/3 16:02
 * @Created by gangye
 */
public class DateUtil {
    public static String REGEX = "((19|20)[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";
    public static String REGEX1 = "(\\d{4})-(\\d+)-(\\d+).*";

    //判断字符串是否是yyyy-MM-dd类型的
    public static boolean matchDateType(String timeStr){
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.matches()) {
            pattern = Pattern.compile(REGEX1);
            matcher = pattern.matcher(timeStr);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m-1, 1);
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }

    //获得传入日期的第二天的字符串
    public static String getNextDay(String dateStr){
        //先判断传入的字符串是否是yyyy-MM-dd类型的，且是否是正常日期
        if(matchDateType(dateStr)){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date ;
            try {
                date = formatter.parse(dateStr);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推一天,负数获得前一天
                date=calendar.getTime(); //获得传入时间的后一天
                return formatter.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
