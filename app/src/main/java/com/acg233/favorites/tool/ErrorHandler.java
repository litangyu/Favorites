package com.acg233.favorites.tool;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.acg233.favorites.R;
import com.acg233.favorites.api.tool.Errors;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.exceptions.CompositeException;
import rx.functions.Action1;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/2/15 下午3:05</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class ErrorHandler {
    private static final String TAG = ErrorHandler.class.getSimpleName();


    public static void displayError(Context context, String message) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public static void displayError(Context context, Throwable throwable) {
        if (context == null) {
            Log.e(TAG, "[300] " + "Context is null");
            return;
        }

        String errorMessage = null;
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            errorMessage = Errors.errorResponse(httpException).error;
        } else if (throwable instanceof UnknownHostException) {
            errorMessage = context.getString(R.string.tip_network_is_not_available);
        }
        if (errorMessage != null) {
            displayError(context, errorMessage);
        } else {
            Log.e(TAG, "[301]", throwable);
            displayError(context, Errors.errorMessage(throwable));
        }
    }


    public static Action1<Throwable> displayErrorAction(final Context context) {
        return new Action1<Throwable>() {
            @Override public void call(Throwable throwable) {
                displayError(context, throwable);
            }
        };
    }
}
