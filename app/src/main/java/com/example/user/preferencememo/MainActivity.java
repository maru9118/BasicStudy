package com.example.user.preferencememo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Memo> mData = new ArrayList<>();

    static int RESULT_CODE = 1000;
    private MemoRecyclerAdapter mAdapter;

    private int persentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_memo_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        findViewById(R.id.fab_add_memo_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                RESULT_CODE = 1000;
                startActivityForResult(intent, RESULT_CODE);
            }

        });

        mAdapter = new MemoRecyclerAdapter(mData);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        // 앱이 다시 시작됐을때 저장 된 데이터 Memo 데이터에 다시 입력
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        int count = pref.getInt("count", 0);

        // 카운터 만큼 Model 재생성
        for (int i = 0; i < count; i++) {
            mData.add(new Memo(pref.getString("title" + i, ""),
                    pref.getString("content" + i, ""),
                    pref.getLong("day" + i, Long.parseLong("3")),
                    pref.getInt("percent" + i, Integer.parseInt("0"))));

            // 퍼센트 유지
            persentCount = mData.get(i).getPercent();
        }
    }

    @Override
    protected void onStop() {
        // 앱이 완전 종료됐을때 데이터 저장.
        savePrefence();
        super.onStop();
    }

    // EditActivity에서 Intent로 넘어오는 값
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        long nowData = System.currentTimeMillis();

        if (mData.size() != 0) {
            persentCount++;
        }

        mData.add(new Memo(data.getStringExtra("title"),
                data.getStringExtra("content"),
                nowData,
                persentCount
        ));

        // 어뎁터 갱신
        mAdapter.notifyDataSetChanged();
    }

    private void savePrefence() {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();

        // Intent 동시에 저장.
        for (int i = 0; i < mData.size(); i++) {
            editor.putString("title" + i, mData.get(i).getTitle());
            editor.putString("content" + i, mData.get(i).getContent());
            editor.putLong("day" + i, mData.get(i).getDay());
            editor.putInt("percent" + i, mData.get(i).getPercent());
            editor.apply();
        }

        // 사이즈를 모르니 count만큼 for 문 돌려서 Memo모델 Data 얻기
        editor.putInt("count", mData.size());
        editor.apply();
    }
}
