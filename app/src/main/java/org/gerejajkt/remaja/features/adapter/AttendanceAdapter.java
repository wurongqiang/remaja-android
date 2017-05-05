package org.gerejajkt.remaja.features.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gerejajkt.remaja.R;
import org.gerejajkt.remaja.domain.viewparam.AttendanceViewParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huteri on 5/2/17.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<AttendanceViewParam> list = new ArrayList<>();
    private Context context;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");

    public AttendanceAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AttendanceViewHolder(LayoutInflater.from(context).inflate(R.layout.item_attendance, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).onBindViewHolder(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<AttendanceViewParam> data) {
        list.clear();
        list.addAll(data);

        notifyDataSetChanged();
    }

    public abstract class BaseViewHolder extends RecyclerView.ViewHolder {


        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public abstract void onBindViewHolder(int position);
    }

    public class AttendanceViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_meeting_name)
        TextView tvMeetingName;

        @BindView(R.id.tv_date)
        TextView tvDate;

        public AttendanceViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindViewHolder(int position) {
            AttendanceViewParam data = list.get(position);

            tvMeetingName.setText(data.getMeetingName());
            tvDate.setText(simpleDateFormat.format(data.getMeetingDate()));
        }
    }

}
