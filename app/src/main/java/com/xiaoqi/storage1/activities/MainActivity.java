package com.xiaoqi.storage1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xiaoqi.storage1.AppClient;
import com.xiaoqi.storage1.R;
import com.xiaoqi.storage1.utils.ConfigUtils;
import com.xiaoqi.storage1.utils.SpTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_et_pwd)
    EditText mainEtPwd;
    @BindView(R.id.main_et_pwd2)
    EditText mainEtPwd2;
    @BindView(R.id.main_btn_ok)
    Button mainBtnOk;
    @BindView(R.id.main_btn_clear)
    Button mainBtnClear;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();// 初始化数据
    }

    /**
     * 初始化数据
     */
    private void initData() {
        password = SpTools.getString(ConfigUtils.PASSWORD, "");

        if (!TextUtils.isEmpty(password)) {//如果密码不为空
            mainEtPwd2.setVisibility(View.GONE);//隐藏重复密码的输入框
        }

    }

    @OnClick({R.id.main_btn_clear, R.id.main_btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_ok:
                if (!TextUtils.isEmpty(password)) {//如果密码不为空
                    startFileEditorActivity();//验证密码，进行页面跳转
                } else {
                    savePassword();
                }
                break;
            case R.id.main_btn_clear:

                break;
        }
    }

    /**
     * 验证密码，进行页面跳转
     */
    private void startFileEditorActivity() {
        String pwd = mainEtPwd.getText().toString();
        if (pwd.equals(password)) {//密码与保存密码一致
            startActivity(new Intent(this, FileEditorActivity.class));
            finish();
        } else {//密码与保存密码不一致
            AppClient.showToast("Invalid Password");
        }
    }

    /**
     * 保存密码
     */
    private void savePassword() {
        String pwd1 = mainEtPwd.getText().toString();
        String pwd2 = mainEtPwd2.getText().toString();

        if (TextUtils.isEmpty(pwd1) || TextUtils.isEmpty(pwd2)) {
            AppClient.showToast("Password cannot be empty");
            return;
        }

        if (!pwd1.equals(pwd2)) {
            AppClient.showToast("Password Mismatch");
            return;
        }
        SpTools.putString(ConfigUtils.PASSWORD, pwd1);
        startActivity(new Intent(this, FileEditorActivity.class));
        finish();
    }
}
