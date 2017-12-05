package com.joy.property.visit.view;

import java.io.IOException;
import java.util.Vector;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Util.SpUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.module.visitor.VerifyCardParam;
import com.jinyi.ihome.module.visitor.VerifyCardResultTo;
import com.joy.common.api.ApiClient;
import com.joy.common.api.HttpCallback;
import com.joy.common.api.VisitorApi;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.ChangeParkUtil;
import com.joy.property.visit.ExpressActivity;
import com.joy.property.visit.ReceiveExpressActivity;
import com.joy.property.visit.VerifyResultActivity;
import com.joy.property.visit.camera.CameraManager;
import com.joy.property.visit.decoding.CaptureActivityHandler;
import com.joy.property.visit.decoding.InactivityTimer;

import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * 开启相机扫描二维码页面
 * @author
 */
public class CaptureActivity extends BaseActivity implements Callback ,OnClickListener{

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
    private TextView mTitle;
    private TextView manualInput;
    private TextView openLight;
    private TextView message;
//    private ImageView changePark;
    private boolean firstChange;
    private boolean open;
    private String cardNumber;
    private String type;
//	private Button cancelScanButton;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		hasSurface = false;
        type=getIntent().getStringExtra("type");
        inactivityTimer = new InactivityTimer(this);
        findById();

    }

    private void findById() {
        mTitle = (TextView) findViewById(R.id.title);
//        changePark = (ImageView) findViewById(R.id.changePark);
        openLight = (TextView) findViewById(R.id.open_light);
		manualInput = (TextView) findViewById(R.id.manual_input);
		message = (TextView) findViewById(R.id.message_tv);
        openLight.setOnClickListener(this);
//        changePark.setOnClickListener(this);
//		if (com.joy.property.utils.SpUtil.getString(getThisContext(), "limitPark")!=null&& com.joy.property.utils.SpUtil.getString(getThisContext(),"limitPark").contains("A008"))
//		{
//			changePark.setVisibility(View.VISIBLE);
//		}

//            changePark.setVisibility(View.VISIBLE);
			if("0".equals(type)){
				mTitle.setText("二维码");

			}else if("1".equals(type)){
				mTitle.setText("快递录入");
			}else{
				mTitle.setText("领取快递");
			}

		if("0".equals(type)){
//			changePark.setVisibility(View.VISIBLE);
			manualInput.setVisibility(View.GONE);
			message.setText("将访客证二维码放入框内\n       即可验证对方身份");
		}else if("1".equals(type)){
//			changePark.setVisibility(View.GONE);
			manualInput.setVisibility(View.VISIBLE);
			message.setText("将快递面单条形码放入框内\n       即可得到快递单号");
		}else{
//			changePark.setVisibility(View.GONE);
			manualInput.setVisibility(View.GONE);
			message.setText("将快递面单条形码放入框内\n       即可得到快递单号");
		}
		manualInput.setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;

		//退出扫描试图
//		cancelScanButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				CaptureActivity.this.finish();
//			}
//		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	/**
	 * 处理扫描结果
	 * @param result
	 * @param barcode
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		 cardNumber = result.getText();
		if("0".equals(type)){
			//FIXME
			if ("".equals(cardNumber)) {
//			Toast.makeText(CaptureActivity.this, "Scan failed!", Toast.LENGTH_SHORT).show();
			}else {
				VerifyCardParam param = new VerifyCardParam();
				VisitorApi api = ApiClient.create(VisitorApi.class);
				param.setCardData(result.getText());
				System.out.println(result);
				param.setUserSid(mUserHelper.getSid());

				api.verifyCard(param,new HttpCallback<MessageTo<VerifyCardResultTo>>(getThisContext()) {
					@Override
					public void success(MessageTo<VerifyCardResultTo> msg, Response response) {
						if (msg.getSuccess() ==0) {
							Intent intent = new Intent(getThisContext(),VerifyResultActivity.class);
							intent.putExtra("mode",msg.getData());
							intent.putExtra("carNo",cardNumber);
							startActivity(intent);

							finish();

						}else {
							Toast.makeText(getThisContext(), msg.getMessage(), Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void failure(RetrofitError error) {
						super.failure(error);
					}
				});

			}
		}else if("1".equals(type)){
			Intent intent = new Intent(getThisContext(),ExpressActivity.class);
			intent.putExtra("codeNo",cardNumber);
			startActivity(intent);
		}else if("2".equals(type)){
			Intent intent = new Intent(getThisContext(),ReceiveExpressActivity.class);
			intent.putExtra("codeNo",cardNumber);
			setResult(RESULT_OK, intent);
			finish();

		}

	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.changePark:
//                changePark();
//                break;
            case R.id.open_light:
                openLight();
                break;
			case R.id.manual_input:
				Intent intent = new Intent(getThisContext(),ExpressActivity.class);
				startActivity(intent);

                break;
        }
    }
//    private void changePark() {
//        if (!firstChange){
//            ChangeParkUtil.changeToPark(getThisContext(), mUserHelper);
//            changePark.setBackgroundResource(R.drawable.selector_home);
//            firstChange=true;
//				if("0".equals(type)){
//				mTitle.setText("二维码(园区)");
//			}else {
//				mTitle.setText("快递录入(园区)");
//			}
//
//        }else {
//            ChangeParkUtil.changeToHome(getThisContext(), mUserHelper);
//            changePark.setBackgroundResource(R.drawable.selector_park);
//            firstChange=false;
//			if("0".equals(type)){
//				mTitle.setText("二维码(住宅)");
//			}else {
//				mTitle.setText("快递录入(住宅)");
//			}
//
//        }
//    }
    private void openLight() {
		if (CameraManager.get().camera == null) {
			Log.i("2222" ,"openLight: meiyoucamare");
			return;
		}
		if (!open) {
//            final PackageManager pm = getPackageManager();
//            final FeatureInfo[] features = pm.getSystemAvailableFeatures();
//            for (final FeatureInfo f : features) {
//            if (PackageManager.FEATURE_CAMERA_FLASH.equals(f.name)) { // 判断设备是否支持闪光灯
            Camera.Parameters mParameters = CameraManager.get().camera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
			CameraManager.get().camera.setParameters(mParameters);
            openLight.setText("点击关闭照明");
            open = true;
//            }
//            }
        } else {
            Camera.Parameters mParameters = CameraManager.get().camera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			CameraManager.get().camera.setParameters(mParameters);
            openLight.setText("点击开启照明");

            open = false;
        }
    }
}