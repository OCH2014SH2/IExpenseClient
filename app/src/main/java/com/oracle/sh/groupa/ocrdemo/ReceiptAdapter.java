package com.oracle.sh.groupa.ocrdemo;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalReceiptInfo;

import java.util.List;

/**
 * Created by 麟 on 2015/1/11.
 */
public class ReceiptAdapter extends ArrayAdapter<LocalReceiptInfo> {
    public ReceiptAdapter(Context context, int resource, List<LocalReceiptInfo> objects) {
        super(context, resource, objects);
    }
}
