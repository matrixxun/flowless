package com.zhuinden.flow_alpha_master_detail.paths;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.zhuinden.flow_alpha_master_detail.IsMaster;
import com.zhuinden.flow_alpha_master_detail.R;

import flow.ClassKey;
import flow.preset.FlowAnimation;
import flow.preset.LayoutPath;

/**
 * Created by Zhuinden on 2016.04.16..
 */
@AutoValue
public abstract class FourthMasterKey implements LayoutPath, IsMaster {
    public static FourthMasterKey create() {
        return new AutoValue_FourthMasterKey(R.layout.path_fourth_master, FlowAnimation.SEGUE);
    }
}
