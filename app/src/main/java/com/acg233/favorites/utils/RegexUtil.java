package com.acg233.favorites.utils;

import java.util.regex.Pattern;

import static me.lty.basemvplibrary.utils.Check.isEmpty;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/2/6 下午3:53</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class RegexUtil {

    // 邮箱匹配规则
    private static final Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\" +
            ".\\w+([-.]\\w+)*");

    // qq邮箱匹配规则
    private static final Pattern qq = Pattern.compile("[1-9][0-9]{4,10}");
    // qq邮箱匹配规则
    private static final Pattern qqEmail = Pattern.compile("[1-9][0-9]{4,10}@qq.com");
    // qq邮箱匹配规则
    private static final Pattern password = Pattern.compile("^[a-zA-Z\\d]{8,14}$");

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        return isEmpty(email) ? false : emailer.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的QQ电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isQQEmailValid(String email) {
        return isEmpty(email) ? false : qqEmail.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的QQ电子邮件地址
     *
     * @param qqNum
     * @return
     */
    public static boolean isQQNumValid(String qqNum) {
        return isEmpty(qqNum) ? false : qq.matcher(qqNum).matches();
    }

    /**
     * 判断是不是一个合法的密码（包含大小写英文字母和数字，长度为8-14位）
     *
     * @param pwd
     * @return
     */
    public static boolean isPasswordValid(String pwd) {
        return isEmpty(pwd) ? false : password.matcher(pwd).matches();
    }
}
