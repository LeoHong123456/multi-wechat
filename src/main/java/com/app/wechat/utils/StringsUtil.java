package com.app.wechat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
public class StringsUtil {
    static Random random = new Random();
    static char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static long startVaue = 0;

    public static String randomString(int digit) {
        StringBuilder build = new StringBuilder(digit);
        for (int i = 0; i < digit; i++) {
            build.append(str[random.nextInt(26)]);
        }
        return build.toString();
    }

    public static synchronized String getBillno(int siteId, String username, double money) {
        StringBuffer billno = new StringBuffer();
        billno.append("no");
        billno.append(MD5Util.getMD5(siteId + username + money + System.currentTimeMillis()), 8, 24);
        return billno.toString();
    }

    public static synchronized String reportId() {
        SimpleDateFormat oFormat;
        startVaue++;
        startVaue = startVaue % 1000;
        java.text.DecimalFormat format = new java.text.DecimalFormat("000");
        String sStartVaue = format.format(startVaue);
        Date oToday = new Date();
        oFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String sDate = oFormat.format(oToday);
        String id = sDate + sStartVaue;
        return id;
    }

    public static synchronized String getFileDirId() {
        Date oToday = new Date();
        SimpleDateFormat oFormat = new SimpleDateFormat("yyyyMMdd");
        return oFormat.format(oToday);
    }

    public static synchronized String incrementId() {
        Date oToday = new Date();
        SimpleDateFormat oFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return oFormat.format(oToday);
    }
}