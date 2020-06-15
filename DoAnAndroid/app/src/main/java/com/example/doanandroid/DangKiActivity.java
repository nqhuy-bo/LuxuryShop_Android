package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DangKiActivity extends AppCompatActivity {
    EditText edtHoTenDangKi,edtSoDienThoaiDangKi,edtMatKhauDangKi;
    Button btnDangKi,btnQuayVe;
    String hoTen,soDienThoai,matKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);

        AnhXa();
        final String url = "https://mylifemrrobot.000webhostapp.com/insert.php";

        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoTen = edtHoTenDangKi.getText().toString();
                soDienThoai = edtSoDienThoaiDangKi.getText().toString();
                matKhau = edtMatKhauDangKi.getText().toString();
                if(hoTen.isEmpty()){
                    edtHoTenDangKi.setError("Không được để trống");
                    return;
                }
                if(soDienThoai.isEmpty())
                {
                    edtSoDienThoaiDangKi.setError("Không được để trống");
                    return;
                }
                if(matKhau.isEmpty())
                {
                    edtMatKhauDangKi.setError("Không được để trống");
                    return;
                }
                DangKiThanhVien(url);




            }
        });
    }

    private void DangKiThanhVien(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Success")){

                            Toast.makeText(DangKiActivity.this,"Đăng kí Tài khoản thành công",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(DangKiActivity.this,"Số điện thoại này đã được đăng kí",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangKiActivity.this,"Xẩy ra lỗi",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("hoten",hoTen.trim());
                params.put("sodienthoai",soDienThoai.trim());
                params.put("matkhau",matKhau.trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        edtHoTenDangKi = findViewById(R.id.editTextHoTenDangKi);
        edtSoDienThoaiDangKi = findViewById(R.id.editTextSoDienThoaiDangKi);
        edtMatKhauDangKi = findViewById(R.id.editTextMatKhauDangKi);
        btnDangKi = findViewById(R.id.buttonDangKiDangKi);
        btnQuayVe = findViewById(R.id.buttonCancelDangKi);
    }

}
