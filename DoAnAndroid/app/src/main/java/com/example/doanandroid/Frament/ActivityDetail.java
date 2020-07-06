package com.example.doanandroid.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.doanandroid.R;
import com.squareup.picasso.Picasso;

public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    ImageView imgHinhDetail;
    TextView txtGiaDetail,txtTenDetail,txtMoTaDetail;
    TextView edtSoLuongDetail;
    Animation topAnim, bottomAnim;
    androidx.cardview.widget.CardView cardView;
    ImageButton btnCongDetail,btnTruDetail;
    int MASP = -1;
    int MALOAI = -1;
    String hinh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detail);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        AnhXa();
        LoadDataFromIntent();
//        getSupportActionBar().setTitle("Chi tiết sản phẩm");
        XuLyClick();

        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);




       cardView.setAnimation(bottomAnim);

    }

    private void XuLyClick() {
        btnCongDetail.setOnClickListener(this);
        btnTruDetail.setOnClickListener(this);

    }

    private void LoadDataFromIntent() {
        Intent intent = getIntent();
        txtTenDetail.setText(intent.getStringExtra("TENSP"));
        txtGiaDetail.setText((intent.getIntExtra("GIA",0))+"VNĐ");
        txtMoTaDetail.setText(intent.getStringExtra("MOTA"));
        MALOAI = intent.getIntExtra("MALOAI",-1);
        MASP = intent.getIntExtra("MASP",-1);
        hinh = intent.getStringExtra("HINH");
        Picasso.with(this)
                .load(hinh)
                .placeholder(R.drawable.ic_wait)
                .error(R.drawable.ic_offline)
                .into(imgHinhDetail);

    }

    private void AnhXa() {
        imgHinhDetail = findViewById(R.id.imageViewDetail);
        txtGiaDetail = findViewById(R.id.textViewGiaDetail);
        txtTenDetail = findViewById(R.id.textViewTenDetail);
        txtMoTaDetail = findViewById(R.id.textViewMoTaDetail);
        edtSoLuongDetail = findViewById(R.id.editTextSoLuongDetail);
        btnCongDetail = findViewById(R.id.imageButtonCongDetail);
        btnTruDetail = findViewById(R.id.imageButtonTruDetail);
        cardView = findViewById(R.id.cardView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageButtonCongDetail:
                int sl = Integer.parseInt(edtSoLuongDetail.getText().toString());
                sl = sl+1;
                edtSoLuongDetail.setText(sl+"");
                break;
            case R.id.imageButtonTruDetail:
                int sll = Integer.parseInt(edtSoLuongDetail.getText().toString());
                if(sll>0)
                    sll = sll-1;
                edtSoLuongDetail.setText(sll+"");
                break;
        }
    }
}
