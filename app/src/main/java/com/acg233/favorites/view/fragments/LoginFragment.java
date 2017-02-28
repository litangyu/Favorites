package com.acg233.favorites.view.fragments;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.acg233.favorites.R;
import com.acg233.favorites.api.RetrofitManager;
import com.acg233.favorites.api.type.Login;
import com.acg233.favorites.api.type.User;
import com.acg233.favorites.contract.LoginContract;
import com.acg233.favorites.presenter.LoginPresenterImpl;
import com.acg233.favorites.tool.ErrorHandler;
import com.acg233.favorites.tool.RegexUtil;
import com.acg233.favorites.view.activities.HomeActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.lty.basemvplibrary.tool.Check;
import me.lty.basemvplibrary.tool.DataKeeper;
import me.lty.basemvplibrary.ui.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/2/24 下午2:17</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {

    private static final String TAG = LoginFragment.class.getSimpleName();
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    @BindView(R.id.email)
    protected AutoCompleteTextView mEmailView;
    @BindView(R.id.password)
    protected EditText mPasswordView;
    @BindView(R.id.email_sign_in_button)
    protected Button mEmailSignInButton;
    private View mLoginFormView;

    private LoginPresenterImpl mPresenter;

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = (LoginPresenterImpl) presenter;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.content_login;
    }

    @Override
    protected void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initView(View view) {
        // Set up the login form.
        mLoginFormView = view.findViewById(R.id.login_form);
    }

    @Override
    protected void initData() {
        mPresenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void setListener() {
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    @OnClick(R.id.pass)
    public void pass2Home() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (getActivity().checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!Check.isEmpty(password) && !RegexUtil.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (Check.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!RegexUtil.isQQNumValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_qq_email));
            focusView = mEmailView;
            cancel = true;
        } else if (!RegexUtil.isQQEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_qq_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            final Login login = new Login(email, password);
            RetrofitManager.getInstance().getFavoritesService()
                    .login(login)
                    .doOnNext(new Action1<User>() {
                        @Override
                        public void call(User user) {
                            RetrofitManager.setAuth(user.getAuth());
                            saveLogin(login);

                            DataKeeper keeper = new DataKeeper(getActivity(), "app");
                            keeper.put("uid", user.getUsername());
                            keeper.put("isLogin", true);

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("user", user);
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<User>() {
                        @Override
                        public void call(User auth) {

                        }
                    }, ErrorHandler.displayErrorAction(getActivity()));
        }
    }

    private void saveLogin(Login login) {
//        String encrypted = BlackBoxes.encrypt(context, login.password);
//        Login encryptedLogin = new Login(login.username, encrypted);
//        Stores.login(context).save(encryptedLogin);
    }
}
