package com.joy.property;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import com.joy.property.base.BaseActivity;
import com.joyhome.nacity.app.util.CustomDialog;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by usb on 2017/5/4.
 */

public class AboutNewActivity extends BaseActivity implements OnClickListener {
    private TextView versionName;
    private String versionCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_new);
        findById();
        initDatas();
    }

    private void initDatas() {
        versionCode=getVersionName();
        versionName.setText("V "+versionCode);
    }

    private void findById() {
        versionName= (TextView)findViewById(R.id.version_name);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.phone).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.phone:
                mobileShowDialog("4001037979");
                break;
            default:
                break;
        }
    }

    private void mobileShowDialog(String phoneNumber) {
        final CustomDialog dialog = new CustomDialog(getThisContext(), R.layout.dialog_contact_nacity, R.style.myDialogTheme);

        Button btnAdd = (Button) dialog.findViewById(R.id.confirm);
        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
            dialog.dismiss();

        });
        btnCancel.setOnClickListener(v1 -> {
            dialog.dismiss();
        });
        dialog.findViewById(R.id.parent).setOnClickListener(v -> dialog.dismiss());
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }
    public String getVersionName() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
