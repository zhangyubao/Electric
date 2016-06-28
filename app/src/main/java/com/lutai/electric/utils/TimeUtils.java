package com.lutai.electric.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangYB on 2016/6/25.
 */
public class TimeUtils {
    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String formatDateTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String newTime = dateFormat.format(time);
        long value = System.currentTimeMillis() - time;
        if (value < 1000 * 60) {
            return "刚刚";
        } else if (1000 * 60 < value && value < 1000 * 60 * 60 * 60) {
            return CommonUtil.formatTime(Long.valueOf(time));
        } else {
            if ("".equals(time)) {
                return "";
            }
            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = null;
            try {
                date = format.parse(newTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar current = Calendar.getInstance();

            Calendar today = Calendar.getInstance();    //今天

            today.set(Calendar.YEAR, current.get(Calendar.YEAR));
            today.set(Calendar.MONTH, current.get(Calendar.MONTH));
            today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
            //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);

            Calendar yesterday = Calendar.getInstance();    //昨天

            yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
            yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
            yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
            yesterday.set(Calendar.HOUR_OF_DAY, 0);
            yesterday.set(Calendar.MINUTE, 0);
            yesterday.set(Calendar.SECOND, 0);

            Calendar lastday = Calendar.getInstance();    //前天

            lastday.set(Calendar.YEAR, current.get(Calendar.YEAR));
            lastday.set(Calendar.MONTH, current.get(Calendar.MONTH));
            lastday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 2);
            lastday.set(Calendar.HOUR_OF_DAY, 0);
            lastday.set(Calendar.MINUTE, 0);
            lastday.set(Calendar.SECOND, 0);

            current.setTime(date);

            if (current.after(today)) {
                return "今天 " + newTime.split(" ")[1];
            } else if (current.before(today) && current.after(yesterday)) {
                return "昨天 " + newTime.split(" ")[1];
            } else if (current.before(yesterday) && current.after(lastday)) {
                return "前天 " + newTime.split(" ")[1];
            } else {
                int index = newTime.indexOf("-") + 1;
                return newTime.substring(index, newTime.length());
            }
        }
    }

}
