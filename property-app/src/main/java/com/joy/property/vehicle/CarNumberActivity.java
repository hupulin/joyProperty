package com.joy.property.vehicle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.joy.property.R;
import com.joy.property.base.BaseActivity;
import com.joy.property.utils.KeyboardUtil;

/**
 * Created by usb on 2017/2/28.
 */
public class CarNumberActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private ImageView save;
    private EditText mContent;
    private TextView mTip;
    private KeyboardUtil keyboardUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_car_number);
        findById();
         initdata();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
           //     hideKeyboard();
                keyboardUtil.hideKeyboard();

                onBackPressed();
                break;
            case R.id.save:
                keyboardUtil.hideKeyboard();

              //  hideKeyboard();
                if(TextUtils.isEmpty(mContent.getText().toString())){
                        ToastShowLong(getThisContext(),"请输入车牌号码");
                }else
                    {//京津冀鲁晋-蒙辽黑沪苏-浙皖闽赣豫-鄂湘粤桂渝-川贵云藏陕-甘青琼新吉-宁
                        //使领
                    //    浙渝吉苏皖闽赣豫鄂湘粤桂琼川贵云藏陕甘青宁新;
//                   [京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$
                    String  regex= "[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼港澳台使领A-Z]{1}[A-Za-z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
                    if((mContent.getText().toString()).matches(regex)){
                         Intent intent=new Intent();
                         intent.putExtra("carNum", mContent.getText().toString());
                         setResult(20, intent);
                         Log.i("2221", mContent.getText().toString());
                         finish();
                     }   else{

                         ToastShowLong(getThisContext(),"请输入正确的车牌号");
                     }

                }
                    break;
        }
    }

    private void hideKeyboard() {

        InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
        }
    }
    private void findById() {
        back=(ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        save=(ImageView) findViewById(R.id.save);
        save.setOnClickListener(this);
        mContent= (EditText) findViewById(R.id.carNum);
        mTip=(TextView) findViewById(R.id.tip);
    }

    private void initdata() {
        keyboardUtil = new KeyboardUtil(this, mContent);

                mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyboardUtil.hideSoftInputMethod();
                keyboardUtil.showKeyboard();

                //   mTip.setText("还可以输入" + (200 - s.length()) + "字");
                mTip.setText(s.length() + "/10");
                if(s.length()==0){
                    keyboardUtil.changeKeyboard(false);
                }

                if (s.length() >=7) {
                    keyboardUtil.hideKeyboard();
                    //Toast.makeText(getThisContext(), "你只能输入200字哦", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {



                Log.i("2222", "onTouch: 左边");
                mContent.requestFocus();
                keyboardUtil.hideSoftInputMethod();

                keyboardUtil.showKeyboard();

                return false;
            }
        });
        String carNum =(String)getIntent().getSerializableExtra("mCarNum");
        mContent.setText(carNum);
    }

}
