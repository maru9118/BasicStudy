package com.example.user.preferencememo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.user.preferencememo.MainActivity.RESULT_CODE;

public class EditActivity extends AppCompatActivity {

    private static final String TAG = EditActivity.class.getSimpleName();
    private EditText mTitleEdit;
    private EditText mContentEdit;
    private TextView txt;

    private String contentText;

    private final String TITLE_CODE = "title";
    private final String CONTENT_CODE = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
    }

    @Override
    protected void onResume() {
        // 기존 엑티비티가 다시 화면에 표시될때 복원하기 위해서
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        mTitleEdit.setText(pref.getString("title", ""));
        mContentEdit.setText(pref.getString("content", ""));

        super.onResume();
    }

    @Override
    protected void onPause() {
        // 액티비티 실행이 멈춘 후 저장하기 위해서
        savePreference();
        super.onPause();
    }

    private void init() {
        mTitleEdit = (EditText) findViewById(R.id.title_edit);
        mContentEdit = (EditText) findViewById(R.id.content_edit);

        txt = (TextView) findViewById(R.id.txt);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 100) {
                    Toast.makeText(EditActivity.this, "글자 입력 수 제한!", Toast.LENGTH_SHORT).show();

                    contentText = mContentEdit.getText().toString();
                    txt.setText(contentText.substring(0, 100));

                    contentText = contentText.substring(0, 100);
                } else {
                    txt.setText(mContentEdit.getText());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        mContentEdit.addTextChangedListener(watcher);

    }

    // back키 누르면 저장
    @Override
    public void onBackPressed() {

        String titleText = mTitleEdit.getText().toString();

        /** Intent로 MainActivity에 입력받은 값 이동
         *{@link MainActivity#onActivityResult(int, int, Intent)}
         * */
        Intent intent = new Intent();
        intent.putExtra("title", titleText);
        intent.putExtra("content", contentText);

        setResult(RESULT_CODE, intent);
        finish();

        Log.d(TAG, "onBackPressed: intent로 옴");
    }

    private void savePreference() {

        // SharePreferences로 Home키로 나왔다 와도 유지
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TITLE_CODE, mTitleEdit.getText().toString());
        editor.putString(CONTENT_CODE, mContentEdit.getText().toString());
        editor.apply();
    }
}
