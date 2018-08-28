package com.boyue.tlh.inputview.keyboard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.boyue.tlh.inputview.R;

import java.util.ArrayList;
import java.util.Map;

import static com.boyue.tlh.inputview.keyboard.VirtualKeyboardView.KEY;


/**
 * Created by Tianluhua on 2018\8\8 0008.
 */
public class KeyBoardAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Map<String, String>> valueList;

    public KeyBoardAdapter(Context mContext, ArrayList<Map<String, String>> valueList) {
        this.mContext = mContext;
        this.valueList = valueList;
    }

    @Override
    public int getCount() {
        return valueList.size();
    }

    @Override
    public Object getItem(int position) {
        return valueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_grid_virtual_keyboard, null);
            viewHolder = new ViewHolder();
            viewHolder.btnKey = convertView.findViewById(R.id.btn_keys);
            viewHolder.imgDelete = convertView.findViewById(R.id.imgDelete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position == 9) {
            //这个按键暂时没有功能
            viewHolder.imgDelete.setVisibility(View.INVISIBLE);
            viewHolder.btnKey.setVisibility(View.INVISIBLE);
            viewHolder.btnKey.setText(valueList.get(position).get(KEY));
        } else if (position == 11) {
            viewHolder.imgDelete.setVisibility(View.VISIBLE);
            viewHolder.btnKey.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.imgDelete.setVisibility(View.INVISIBLE);
            viewHolder.btnKey.setVisibility(View.VISIBLE);
            viewHolder.btnKey.setText(valueList.get(position).get(KEY));
        }
        return convertView;
    }

    /**
     * 存放控件
     */
    public final class ViewHolder {
        public TextView btnKey;
        public RelativeLayout imgDelete;
    }
}
