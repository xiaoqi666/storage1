package com.xiaoqi.storage1.activities;

import android.opengl.ETC1;
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
 * sp保存与加载
 */
public class FileEditorActivity extends AppCompatActivity {

    @BindView(R.id.file_et_content)
    EditText fileEtContent;
    @BindView(R.id.file_btn_save)
    Button fileBtnSave;
    @BindView(R.id.file_btn_load)
    Button fileBtnLoad;
    @BindView(R.id.file_btn_clear)
    Button fileBtnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_editor);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.file_btn_clear, R.id.file_btn_load, R.id.file_btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.file_btn_clear://clear按钮
                clearContent();
                break;
            case R.id.file_btn_load://load 按钮
                loadContent();
                break;
            case R.id.file_btn_save://save按钮
                saveContent();
                break;
        }
    }

    /**
     * 加载内容
     */
    private void loadContent() {
        String content = SpTools.getString(ConfigUtils.STRING_CONTENT, "");
        if (TextUtils.isEmpty(content)) {
            AppClient.showToast("Fail to load file.");
            return;
        }
        fileEtContent.setText(content);
        AppClient.showToast("Load successfully.");
    }

    /**
     * 保存内容
     */
    private void saveContent() {
        String content = fileEtContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            AppClient.showToast("Content is Empty.");
            return;
        }
        SpTools.putString(ConfigUtils.STRING_CONTENT, content);
        AppClient.showToast("Save successfully.");
        fileEtContent.setText("");
    }

    /**
     * 清楚内容
     */
    private void clearContent() {
        fileEtContent.setText("");
    }
}
