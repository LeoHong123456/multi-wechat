package com.app.wechat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/*
 * 工具类
 */
public class StringsUtil {
    static Random random = new Random();
    static char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
    private static long startVaue = 0;

    public static String randomString(int digit) {
        StringBuilder build = new StringBuilder(digit);
        for (int i = 0; i < digit; i++) {
            build.append(str[random.nextInt(26)]);
        }
        return build.toString();
    }

    /**
     * @param siteId
     * @param username
     * @param money
     * @return
     */
    public static synchronized String getBillno(int siteId, String username, double money) {
        StringBuffer billno = new StringBuffer();
        billno.append("no");
        billno.append(MD5Util.getMD5(siteId + username + money + System.currentTimeMillis()), 8, 24);
        return billno.toString();
    }

    /**
     * 转账单号
     *
     * @return
     */
    public static synchronized String getTransBillno() {
        SimpleDateFormat oFormat;
        startVaue++;
        startVaue = startVaue % 1000;
        java.text.DecimalFormat format = new java.text.DecimalFormat("000");
        String sStartVaue = format.format(startVaue);
        Date oToday = new Date();
        oFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String sDate = oFormat.format(oToday);
        String id = sDate + sStartVaue;
        String fix = id.substring(0, 16);
        String after = id.substring(17);
        String billno = fix + after;
        return billno;
    }


    /**
     * 获取唯一随机数
     *
     * @return
     */
    public static synchronized String getRandomNum() {
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

    /**
     * <b>获取指定区间随机数</b>
     *
     * @return
     * @category 2018-09-03
     */
    @SuppressWarnings("unused")
    public static synchronized String getappointRandomNum(int Min, int Max) {
        String appointRandomNum = "0";
        try {
            Random rand = new Random();
            int randNumber = Min + (int) (Math.random() * ((Max - Min) + 1));
            appointRandomNum = String.valueOf(randNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointRandomNum;
    }


    /**
     * 线程随机休眠 @Title: threadSleep @param @param time 参数 @return void 返回类型 @throws
     */
    public static void threadSleep(String time) {
        String[] split = time.split("-");
        int first = Integer.parseInt(split[0]);
        int second = Integer.parseInt(split[1]);
        try {
            Thread.sleep((int) first + (int) (Math.random() * (second - first)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成个人信息缓存哈希键值 @Title: getSessionKey @param @param company @param @param
     * username @param @return 参数 @return String 返回类型 @throws
     */
    public static String getSessionKey(Integer company, String username) {
        return "dssession_" + company + "_" + username;
    }

    /**
     * 第三方平台余额缓存KEY
     *
     * @param company
     * @param username
     * @param platform
     * @return
     */
    public static String getPlatFromBalanceKey(Integer company, String username, String currency, String platform) {
        return "balance_" + company + "_" + username + "_" + currency + "_" + platform;
    }

    /**
     * 生成会员积分抽奖哈希键值
     *
     * @param company
     * @param username
     * @return
     */
    public static String getShakeLockKey(Integer company, String username) {
        return "shakeLock_" + company + "_" + username;
    }

    /**
     * 签到KEY
     *
     * @param company
     * @param username
     * @return
     */
    public static String getSignInLockKey(Integer company, String username) {
        return "signIn_" + company + "_" + username;
    }

    /**
     * 外接平台转账KEY
     *
     * @return
     */
    public static String getTransLockKey() {
        return "transfer_business_lock";
    }

    /**
     * 内部平台转账KEY
     *
     * @return
     */
    public static String getInternalTransLockKey() {
        return "internal_trans_lock";
    }

    /**
     * 抢红包KEY
     *
     * @param company
     * @param username
     * @return
     */
    public static String getRedPacketLockKey(Integer company, String username) {
        return "redPacket_" + company + "_" + username;
    }
}