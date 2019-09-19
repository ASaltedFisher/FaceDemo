package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class DateUtils {
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    public static int getMondayPlus(int days) {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6 + days;
        } else {
            return 2 - dayOfWeek + days;
        }
    }

    /**
     * 获取当前周 周一日期
     *
     * @return
     */
    public static String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }
    public static String getCurrentMonday(int days) {
        int mondayPlus = getMondayPlus(days);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }
    /**
     * 获得该月第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获得该月最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    public static String getYearMonthOfToday() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        if(month < 10) {
            return String.valueOf(year) + "0" + String.valueOf(month);
        }
        return String.valueOf(year) + String.valueOf(month);
    }
    /**
     * 判断是否为节假日
     * @param date
     * @return
     */
    public static int holiday(String date) {
         String httpUrl = "http://api.goseek.cn/Tools/holiday";
         date = date.substring(0, 4) +  date.substring(5, 7) + date.substring(8, 10);
         BufferedReader reader = null;
         String result = null;
         StringBuffer sbf = new StringBuffer();
         httpUrl = httpUrl + "?date=" + date;
        Map<String , Object> map = null;
         try {
             URL url = new URL(httpUrl);
             HttpURLConnection connection = (HttpURLConnection) url
                     .openConnection();
             connection.setRequestMethod("GET");
             connection.connect();
             InputStream is = connection.getInputStream();
             reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             String strRead = null;
             while ((strRead = reader.readLine()) != null) {
                 sbf.append(strRead);
             }
             reader.close();
             result = sbf.toString();
             map  = JSON.parseObject(result);
         } catch (Exception e) {
             e.printStackTrace();
         }
        return Integer.parseInt(String.valueOf(map.get("data")));
    }
    public static void main(String[] args) {
        System.out.println(holiday("20181001"));
    }

}
