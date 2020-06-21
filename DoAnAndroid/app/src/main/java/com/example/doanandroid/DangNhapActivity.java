package com.example.doanandroid;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.os.Handler;
import 	android.util.Pair;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    //com.google.android.material.textfield.TextInputEditText edtSoDienThoaiWelcome;
   // com.google.android.material.textfield.TextInputEditText edtMatKhauWelcome;
    TextInputLayout edtSoDienThoaiWelcome,edtMatKhauWelcome;
   // EditText edtSoDienThoaiWelcome,edtMatKhauWelcome;
    TextView txtLuxury,txtSingIn,btnDangKiWelcome;

    LoginButton btnFBWelcome;
    FrameLayout btnDangNhapWelcome;
    CallbackManager callbackManager;
    FragmentManager fragmentManager;
    String username = "";
    TextView signInText;
    ProgressBar progressBar;
    RelativeLayout relativeLayout;
    ImageView logo;
    int status = 1; //lưu trạng thái của imageButton showpass

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activiti_login);
        fragmentManager = getSupportFragmentManager();
        AnhXa();
        LoadShow();
        btnFBWelcome.setReadPermissions(Arrays.asList("public_profile","email"));
        setLogin_FB();
        SetSuKienClick();
     //   TrangThaiNhapMatKhau();


    }

//    private void TrangThaiNhapMatKhau() {
//        if(status==1){
//            edtMatKhauWelcome.setTransformationMethod(new PasswordTransformationMethod());
//            imgShowPass.setImageResource(R.drawable.ic_showtext);
//            status=0;
//        }
//        else
//        {
//            edtMatKhauWelcome.setTransformationMethod(null);
//            imgShowPass.setImageResource(R.drawable.ic_hidetext);
//            status=1;
//        }
//    }

    private void SetSuKienClick() {
        btnDangKiWelcome.setOnClickListener(this);
        btnDangNhapWelcome.setOnClickListener(this);

    }

    private void setLogin_FB() {
        btnFBWelcome.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                result();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    //Lấy thông tin tài khoản fb
    private void result() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("JSON",response.getJSONObject().toString());
                        try {
                            username = object.getString("name");
                            edtSoDienThoaiWelcome.getEditText().setText(object.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields","id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();

    }

    private void LoadShow() {

        edtSoDienThoaiWelcome.startAnimation(AnimationUtils.loadAnimation(edtSoDienThoaiWelcome.getContext(),R.anim.anim_scale));
        edtMatKhauWelcome.startAnimation(AnimationUtils.loadAnimation(edtMatKhauWelcome.getContext(),R.anim.anim_scale));
      //  txtTitleWelcome.startAnimation(AnimationUtils.loadAnimation(txtTitleWelcome.getContext(),R.anim.anim_scale));
        btnDangNhapWelcome.startAnimation(AnimationUtils.loadAnimation(btnDangNhapWelcome.getContext(),R.anim.anim_scale));
        btnFBWelcome.startAnimation(AnimationUtils.loadAnimation(btnFBWelcome.getContext(),R.anim.anim_scale));

        //btnGGWelcome.startAnimation(AnimationUtils.loadAnimation(btnGGWelcome.getContext(),R.anim.anim_scale));

    }

    private void AnhXa() {
        edtSoDienThoaiWelcome = findViewById(R.id.editTextSoDienThoaiWelcome1);
    //   txtTitleWelcome = findViewById(R.id.textViewTitle);
        edtMatKhauWelcome = findViewById(R.id.editTextMatKhauWelcome1);
        btnDangNhapWelcome = findViewById(R.id.buttonDangNhapWelcome);
        btnFBWelcome =(LoginButton) findViewById(R.id.buttonFaceBookWelcome);
        btnDangKiWelcome = findViewById(R.id.buttonDangKiWelcome);
     //   imgShowPass = findViewById(R.id.imageViewShowPassDangNhap);
        logo=findViewById(R.id.logo_image);
        txtLuxury=findViewById(R.id.text_luxury);
        txtSingIn=findViewById(R.id.text_singIn);
        signInText = findViewById(R.id.signInText);
        progressBar = findViewById(R.id.progressBar);
        relativeLayout=findViewById(R.id.retive);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDangKiWelcome:
                Intent intent = new Intent(DangNhapActivity.this, DangKiActivity.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(logo, "logo_image");
                pairs[1] = new Pair<View, String>(txtLuxury, "logo_text");
                pairs[2] = new Pair<View, String>(txtSingIn, "logo_textLogin");
                pairs[3] = new Pair<View, String>(edtSoDienThoaiWelcome, "phone_tran");
                pairs[4] = new Pair<View, String>(edtMatKhauWelcome, "password_tran");
                pairs[5] = new Pair<View, String>(btnDangNhapWelcome, "login_tran");
                pairs[6] = new Pair<View, String>(btnDangKiWelcome, "dangKii_tran");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DangNhapActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                }
                break;
            case R.id.buttonDangNhapWelcome:

                if(  !validatePassword() | !validatePhoneNo()  ){
                    return;
                }
                animateButtonWith();
                fadeDutTextAndSetProgressDiaDig();
                nextAction();
                DangNhap("https://mylifemrrobot.000webhostapp.com/dangnhap.php");
              //  startActivity(new Intent(DangNhapActivity.this,TrangChuActivity.class));
                break;
//            case R.id.imageViewShowPassDangNhap:
//                TrangThaiNhapMatKhau();
//                break;
        }
    }

    private void DangNhap(String url) {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //SANPHAMArrayList = new ArrayList<>();
                            //Duyệt từng JSON trog mảng
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject object = response.getJSONObject(i);


                                    String hoten = object.getString("HOTEN");
                                    Log.d("AAA", hoten + " Tên");

                                    String sodienthoai = object.getString("SODIENTHOAI");
                                    Log.d("AAA", sodienthoai + " SĐT");
                                    String matkhau = object.getString("MATKHAU");
                                    Log.d("AAA", matkhau + " hinh ta");

                                    //Toast.makeText(MainActivity.this, hoTen + "\n" + namsSinh + "\n" + diaChi, Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("AAA",error.toString());
                            Toast.makeText(DangNhapActivity.this, error.toString()+"đÂY LÀ LỖI", Toast.LENGTH_SHORT).show();
                                                }
                    }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String,String> params = new HashMap<>();
                    params.put("SODIENTHOAI",edtSoDienThoaiWelcome.getEditText().getText().toString());
                    params.put("MATKHAU",edtMatKhauWelcome.getEditText().getText().toString());
                    return params;
                }
            };
            requestQueue.add(jsonArrayRequest);




    }
    private Boolean validatePhoneNo() {
        String val = edtSoDienThoaiWelcome.getEditText().getText().toString();
        String passwordVal = "^0\\d{9}$";


        if (val.isEmpty()) {
            edtSoDienThoaiWelcome.setError("Không được để trống số điện thoại");
            return false;
        }
        if(!val.matches(passwordVal))
        {
            edtSoDienThoaiWelcome.setError("Số điện thoại không hợp lệ");
            return false;
        }
        else {
            edtSoDienThoaiWelcome.setError(null);
            edtSoDienThoaiWelcome.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = edtMatKhauWelcome.getEditText().getText().toString();


        if (val.isEmpty()) {
            edtMatKhauWelcome.setError("Mật Khẩu Không được để trống");
            return false;
        }
        else {
            edtMatKhauWelcome.setError(null);
            edtMatKhauWelcome.setErrorEnabled(false);
            return true;
        }
    }



    private void animateButtonWith()
    {
        ValueAnimator anim = ValueAnimator.ofInt(btnDangNhapWelcome.getMeasuredWidth(),getFinaWith());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = btnDangNhapWelcome.getLayoutParams();
                layoutParams.width=value;
                btnDangNhapWelcome.requestLayout();
            }
        });
        anim.setDuration(250);
        anim.start();
    }
    private void fadeDutTextAndSetProgressDiaDig()
    {
        signInText.animate().alpha(0f).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showProgressDialog();
            }
        }).start();
    }
    private void showProgressDialog()
    {
        progressBar.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.INVISIBLE);
        btnDangKiWelcome.setVisibility(View.INVISIBLE);
        //btnCancelRegister.setVisibility(View.INVISIBLE);
    }
    private void nextAction()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                fadeOutProgressDigLog();
                delayStartNextActivity();
            }
        },2000)  ;
    }




    public void fadeOutProgressDigLog()
    {
        progressBar.animate().alpha(0f).setDuration(250).start();
    }
    private void delayStartNextActivity()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                overridePendingTransition(R.anim.zoom,R.anim.star_animation);
                startActivity(intent);
                finish();

            }
        },2000);
    }
    public int getFinaWith()
    {
        return (int) getResources().getDimension(R.dimen.get_width);
    }
}
