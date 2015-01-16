package com.oracle.sh.groupa.ocrdemo;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalReceiptInfo;

import java.util.List;

/**
 * Created by 麟 on 2015/1/11.
 */
public class ReceiptAdapter extends ArrayAdapter<LocalReceiptInfo> {

    private int resourceId;

    public ReceiptAdapter(Context context, int resource, List<LocalReceiptInfo> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LocalReceiptInfo localReceiptInfo = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);

        ImageView imageView = (ImageView) view.findViewById(R.id.receipt_pic);
        TextView textViewTitle = (TextView) view.findViewById(R.id.receipt_title);
        TextView textViewPrice = (TextView) view.findViewById(R.id.receipt_price);
        TextView textViewTime = (TextView) view.findViewById(R.id.receipt_date);

        imageView.setImageBitmap(BitmapFactory.decodeFile(localReceiptInfo.getPicPath()));
        textViewTitle.setText(localReceiptInfo.getTitle());
        textViewPrice.setText(String.valueOf(localReceiptInfo.getPrice()));
        textViewTime.setText(localReceiptInfo.getDateTime());

        return view;
    }
}
