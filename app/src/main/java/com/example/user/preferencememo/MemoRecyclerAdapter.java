package com.example.user.preferencememo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 2017-04-22.
 */

public class MemoRecyclerAdapter extends RecyclerView.Adapter<MemoRecyclerAdapter.ViewHolder> {

    private List<Memo> mData;

    public MemoRecyclerAdapter(List<Memo> mData) {
        this.mData = mData;
        Log.e("###", "MemoRecyclerAdapter: " + mData);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Memo item = mData.get(position);

        if (item != null) {
            holder.titleText.setText(item.getTitle());
            holder.contentText.setText(item.getContent());
            // 싱글턴
//            SimpleDateFormat format = FormatUtils.getDateFormat();
            holder.dayText.setText(FormatUtils.getDateFormatString(item.getDay()));
            holder.percentProgress.setMax(100);
            holder.percentProgress.setProgress(item.getPercent());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText;
        TextView contentText;
        TextView dayText;

        ProgressBar percentProgress;

        public ViewHolder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.item_memo_title_txt);
            contentText = (TextView) itemView.findViewById(R.id.item_memo_content_txt);
            dayText = (TextView) itemView.findViewById(R.id.item_memo_day_txt);

            percentProgress = (ProgressBar) itemView.findViewById(R.id.item_memo_percent_progress);
        }
    }

}
