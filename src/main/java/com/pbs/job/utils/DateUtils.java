package com.pbs.job.utils;

public class DateUtils {

    public static final String JAVA_DATETIME_FORMATTER_24 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间
     * @return
     */
    public static long getDateTime(){
        return System.currentTimeMillis();
    }

}
