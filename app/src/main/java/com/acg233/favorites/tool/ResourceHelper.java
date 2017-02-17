package com.acg233.favorites.tool;

import android.widget.ImageView;

import com.acg233.favorites.R;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/2/17 下午3:07</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class ResourceHelper {

    public static void switchLevel(ImageView imageView, int level) {
        switch (level) {
            case 0:
                imageView.setImageResource(R.mipmap.ic_lv0);
                break;
            case 1:
                imageView.setImageResource(R.mipmap.ic_lv1);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.ic_lv2);
                break;
            case 3:
                imageView.setImageResource(R.mipmap.ic_lv3);
                break;
            case 4:
                imageView.setImageResource(R.mipmap.ic_lv4);
                break;
            case 5:
                imageView.setImageResource(R.mipmap.ic_lv5);
                break;
            case 6:
                imageView.setImageResource(R.mipmap.ic_lv6);
                break;
            case 7:
                imageView.setImageResource(R.mipmap.ic_lv7);
                break;
            case 8:
                imageView.setImageResource(R.mipmap.ic_lv8);
                break;
            case 9:
                imageView.setImageResource(R.mipmap.ic_lv9);
                break;
        }
    }
}
