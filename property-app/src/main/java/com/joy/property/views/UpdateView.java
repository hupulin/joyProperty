package com.joy.property.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joy.property.MainActivity;
import com.joy.property.R;
import com.joy.property.utils.UpdateConfig;
import com.joyhome.nacity.app.util.CustomDialog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Admin on 2014-12-25
 */

@SuppressLint("HandlerLeak")
public class UpdateView {

    private static final String TAG = "UpdateView";

    private Handler handler = new Handler();
    public ProgressDialog pBar; // 更新进度条
    public ProgressBar mProgressBar;
    public TextView mDialogText;
    public Context mContext;
    public MainActivity mActivity;
    private String downloadUrl = "";
    private CustomDialog mDialog; //更新进度弹框
    private int newVerCode = 0; // 新版本号
    private int forceUpdate = 0; // 是否强更
    private String appSize ; // 版本大小
    private String newVerName = "";// 新版本名称
    private boolean bShow = true;

    public UpdateView(Context context) {
        this.mContext = context;
        bShow = true;
    }

    public UpdateView(Context context, boolean isshow) {
        this.mContext = context;
        this.bShow = isshow;
    }
    public UpdateView(Context context, boolean isshow, MainActivity activity) {
        this.mContext = context;
        this.bShow = isshow;
        this.mActivity = activity;
    }


    /**
     * 开始更新
     */
    public void Start() {
        if (getServerVerCode()) {
            int vercode = UpdateConfig.getVerCode(mContext);
            if (newVerCode > vercode) {
                Message newMsg = new Message();
                newMsg.arg1 = 1;
                dialogHhandler.sendMessage(newMsg);
            } else {

                if (bShow) //判断是否显示不更新对话框
                    {
                    Message noMsg = new Message();
                    noMsg.arg1 = 2;
                    dialogHhandler.sendMessage(noMsg);
                }

            }
        }
    }

    Handler dialogHhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub

            switch (msg.arg1) {
                case 1:
                    doNewVersionUpdateNew();
                    break;
                case 2:
                    notNewVersionShow();
                    break;
                case 3:
                    mDialogText.setText("当前进度："+msg.what+"%");
                    break;
            }
            super.handleMessage(msg);
        }

    };

    /**
     * 获取服务器版本号
     *
     * @return
     */
    private boolean getServerVerCode() {
        try {
            String verjson = getContent("http://139.129.166.169/AppUpdate/gKey/android-update.html");
            JSONObject object = new JSONObject(verjson);

            // JSONArray array = new JSONArray(verjson);
            if (object != null) {
                //JSONObject obj = array.getJSONObject(0);
                try {
                    newVerCode = Integer.parseInt(object.getString("verCode"));
                    newVerName = object.getString("updateDesc");
                    downloadUrl = object.getString("downloadUrl");
                    forceUpdate = object.getInt("forceUpdate");
                    appSize = object.getString("appSize");
                } catch (Exception e) {
                     newVerCode = -1;
                    newVerName = "";
                    return false;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 不包含新版本
     */
    private void notNewVersionShow() {
        int verCode = UpdateConfig.getVerCode(mContext);
        String verName = UpdateConfig.getVerName(mContext);
        StringBuffer sb = new StringBuffer();
        sb.append("当前版本号:");
        sb.append(verName);
//        sb.append(" Code:");
//        sb.append(verCode);
        sb.append("\n已是最新版,无需更新!");
        Dialog dialog = new AlertDialog.Builder(mContext).setTitle("软件更新").setMessage(sb.toString())// 设置内容
                .setPositiveButton("确定",// 设置确定按钮
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }

    /**
     * 更新APK文件
     */
    void updateApk() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                "joyProperty.apk")), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
        mActivity.finish();
    }


    /**
     * 下载新版本的程序
     */
    private DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };
    private void doNewVersionUpdateNew() {

        final CustomDialog dialog = new CustomDialog(mContext, R.layout.dialog_updata, R.style.myDialogTheme);
        TextView btnAdd = (TextView) dialog.findViewById(R.id.confirm);
        TextView btnCancel = (TextView) dialog.findViewById(R.id.cancel);
        TextView dialogText = (TextView) dialog.findViewById(R.id.dialogText);
        if(0==forceUpdate){
            btnCancel.setText("确定");
        }else{
            btnCancel.setText("退出");

        }
//      dialogText.setText("检测到系统更新 "+"大小："+appSize+"MB");
        dialogText.setText(newVerName);
        btnAdd.setOnClickListener(v -> {
             mDialog = new CustomDialog(mContext, R.layout.dialog_download, R.style.myDialogTheme);
            mDialog.setOnKeyListener(keylistener);
            mDialog.setCancelable(false);
             mProgressBar = (ProgressBar) mDialog.findViewById(R.id.progressbar_two);
            mDialogText = (TextView) mDialog.findViewById(R.id.dialogText);
            String newPath = downloadUrl;
            downloadApkFile(newPath);
        dialog.dismiss();

    });
        btnCancel.setOnClickListener(v1 -> {
            if(0==forceUpdate){
                dialog.dismiss();

            }else{
                mActivity.finish();
                dialog.dismiss();
            }

    });
        dialog.findViewById(R.id.parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(1==forceUpdate){
                    Log.i("222", "onClick: 1");
                    return;
                }else{
                    Log.i("222", "onClick: 2");

                    dialog.dismiss();
                }
            }
        });
        dialog.setOnKeyListener(keylistener);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
//    更新弹出框
    private void doNewVersionUpdate() {
        int verCode = UpdateConfig.getVerCode(mContext);
        String verName = UpdateConfig.getVerName(mContext);
        StringBuffer sb = new StringBuffer();
        sb.append("当前版本号:Ver");
        sb.append(verName);
        //sb.append(" Code:");
        //sb.append(verCode);
        sb.append("\n发现新版本:");
        //sb.append(newVerName);
        //sb.append(" Code:");
        sb.append(newVerName);
        sb.append("\n是否更新?");
        Dialog dialog = new AlertDialog.Builder(mContext).setTitle("软件更新").setMessage(sb.toString())

                // 设置内容
                .setPositiveButton("更新",// 设置确定按钮
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pBar = new ProgressDialog(mContext);
                                pBar.setTitle("正在下载软件");
                                pBar.setMessage("请稍候...");
                                pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                String newPath = downloadUrl;
                                downloadApkFile(newPath);
                            }

                        }).setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 点击"取消"按钮之后退出程序

                    }
                }).create();// 创建
        // 显示对话框
        dialog.show();
    }

    /**
     * 下载文件
     *
     * @param url
     */
    @SuppressWarnings("deprecation")
    private void downloadApkFile(final String url) {
        mDialog.show();
        new Thread() {

            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();
//                    InputStream is = entity.getContent();
                    CountingInputStream is = new CountingInputStream(
                            entity.getContent(), new CountingInputStream.ProgressListener() {
                        @Override
                        public void transferred(long transferedBytes) {
                            Log.i("FileDownLoadAsyncTask", "总字节数：" + length
                                    + " 已下载字节数：" + transferedBytes);
                        //    publishProgress((int) (100 * transferedBytes / length));
                            Message newMsg = new Message();
                            newMsg.arg1 = 3;
                            int x=(int)(100 * transferedBytes / length);

                            newMsg.what=x;
                            dialogHhandler.sendMessage(newMsg);
                            mProgressBar.setMax((int)length);
                            mProgressBar.setProgress((int)transferedBytes);

                        }
                    });
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {

                        File file = new File(Environment.getExternalStorageDirectory(), "joyProperty.apk");
                        fileOutputStream = new FileOutputStream(file);

                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    downloadSuccess();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();

    }

    /**
     * 下载成功
     */
    private void downloadSuccess() {
        handler.post(new Runnable() {
            public void run() {
                mDialog.dismiss();

                updateApk();
            }
        });

    }

    /**
     * 获取网址内容
     *
     * @param url
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static String getContent(String url) throws Exception {
        StringBuilder sb = new StringBuilder();

        HttpClient client = new DefaultHttpClient();
        HttpParams httpParams = client.getParams();
        //设置网络超时参数
        HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
        HttpConnectionParams.setSoTimeout(httpParams, 5000);
        HttpResponse response = client.execute(new HttpGet(url));
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"), 8192);

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            reader.close();
        }
        return sb.toString();
    }
}
