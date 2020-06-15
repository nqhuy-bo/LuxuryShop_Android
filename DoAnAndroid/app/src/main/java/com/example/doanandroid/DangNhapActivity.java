package com.example.doanandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtSoDienThoaiWelcome,edtMatKhauWelcome;
    TextView txtTitleWelcome;
    Button btnDangNhapWelcome,btnDangKiWelcome;
    LoginButton btnFBWelcome;
    CallbackManager callbackManager;
    FragmentManager fragmentManager;
    String username = "";
    ImageView imgShowPass;
    int status = 1; //lưu trạng thái của imageButton showpass

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_dangnhap);
        fragmentManager = getSupportFragmentManager();
        AnhXa();
        LoadShow();
        btnFBWelcome.setReadPermissions(Arrays.asList("public_profile","email"));
        setLogin_FB();
        SetSuKienClick();
        TrangThaiNhapMatKhau();


    }

    private void TrangThaiNhapMatKhau() {
        if(status==1){
            edtMatKhauWelcome.setTransformationMethod(new PasswordTransformationMethod());
            imgShowPass.setImageResource(R.drawable.ic_showtext);
            status=0;
        }
        else
        {
            edtMatKhauWelcome.setTransformationMethod(null);
            imgShowPass.setImageResource(R.drawable.ic_hidetext);
            status=1;
        }
    }

    private void SetSuKienClick() {
        btnDangKiWelcome.setOnClickListener(this);
        btnDangNhapWelcome.setOnClickListener(this);
        imgShowPass.setOnClickListener(this);
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
                            edtSoDienThoaiWelcome.setText(object.getString("name"));
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
        txtTitleWelcome.startAnimation(AnimationUtils.loadAnimation(txtTitleWelcome.getContext(),R.anim.anim_scale));
        btnDangNhapWelcome.startAnimation(AnimationUtils.loadAnimation(btnDangNhapWelcome.getContext(),R.anim.anim_scale));
        btnFBWelcome.startAnimation(AnimationUtils.loadAnimation(btnFBWelcome.getContext(),R.anim.anim_scale));

        //btnGGWelcome.startAnimation(AnimationUtils.loadAnimation(btnGGWelcome.getContext(),R.anim.anim_scale));

    }

    private void AnhXa() {
        edtSoDienThoaiWelcome = findViewById(R.id.editTextSoDienThoaiWelcome);
        txtTitleWelcome = findViewById(R.id.textViewTitle);
        edtMatKhauWelcome = findViewById(R.id.editTextMatKhauWelcome);
        btnDangNhapWelcome = findViewById(R.id.buttonDangNhapWelcome);
        btnFBWelcome =(LoginButton) findViewById(R.id.buttonFaceBookWelcome);
        btnDangKiWelcome = findViewById(R.id.buttonDangKiWelcome);
        imgShowPass = findViewById(R.id.imageViewShowPassDangNhap);
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
                Intent intent = new Intent(DangNhapActivity.this,DangKiActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonDangNhapWelcome:
                DangNhap("https://mylifemrrobot.000webhostapp.com/dangnhap.php");
                startActivity(new Intent(DangNhapActivity.this,TrangChuActivity.class));
                break;
            case R.id.imageViewShowPassDangNhap:
                TrangThaiNhapMatKhau();
                break;
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
                    params.put("SODIENTHOAI",edtSoDienThoaiWelcome.getText().toString());
                    params.put("MATKHAU",edtMatKhauWelcome.getText().toString());
                    return params;
                }
            };
            requestQueue.add(jsonArrayRequest);

    }
}
