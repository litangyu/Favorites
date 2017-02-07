package me.lty.basemvplibrary.base.AppRuntimeException;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2016/10/28 下午3:02</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class LayoutResIDException extends RuntimeException {
    public LayoutResIDException() {
        //在绑定布局文件之前，请检查initContentView返回的资源ID是否正确
        super("Please return a valid layout resource ID");
    }
}
