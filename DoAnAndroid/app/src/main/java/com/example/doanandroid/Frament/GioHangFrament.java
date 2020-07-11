package com.example.doanandroid.Frament;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doanandroid.R;
import com.example.doanandroid.SanPham;
import com.example.doanandroid.WellCome;
import com.example.doanandroid.adapter.SPGioHangAdapter;

import java.util.ArrayList;

public class GioHangFrament extends Fragment {

    ListView lstDanhSachSanPham;
    Button btnThanhToan,btnTiepTucMuaHang;
    TextView txtTongTien,txtTrangThaiGio;
    ArrayList<SanPham> listSP ;
    SPGioHangAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_giohang,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa();
        listSP= new ArrayList<>();
        listSP = layDanhSachSanPhamTrongGioHang();
        if(listSP.isEmpty())
        {
            txtTrangThaiGio.setVisibility(View.INVISIBLE);
        }
        else
            txtTrangThaiGio.setVisibility(View.GONE);
        adapter = new SPGioHangAdapter(getContext(),R.layout.custom_dong_sanpham,listSP);
        lstDanhSachSanPham.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lstDanhSachSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(), "heelo ", Toast.LENGTH_SHORT).show();
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layDanhSachSanPhamTrongGioHang();
                Toast.makeText(getContext(), "Bạn chọn thanh toán", Toast.LENGTH_SHORT).show();
            }
        });

        btnTiepTucMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Bạn chọn tiếp tục mua hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void AnhXa() {
        lstDanhSachSanPham = getView().findViewById(R.id.listViewGioHang);
        btnThanhToan = getView().findViewById(R.id.buttonThanhToanGioHang);
        btnTiepTucMuaHang = getView().findViewById(R.id.buttonTiepTucMua);
        txtTongTien = getView().findViewById(R.id.textViewGiaTri);
        txtTrangThaiGio =  getView().findViewById(R.id.textViewTrangThaiGioHang);
    }

    public ArrayList<SanPham> layDanhSachSanPhamTrongGioHang(){


        Cursor SP = WellCome.data.GetData("SELECT * FROM GIOHANG");
        int tongtien = 0;
        while(SP.moveToNext()){
            listSP.add(new SanPham(SP.getInt(1),SP.getString(2),SP.getString(3),
                    SP.getString(4),SP.getInt(5),SP.getInt(6)));
            tongtien+=SP.getInt(5)*SP.getInt(6);
        }

        txtTongTien.setText(tongtien+" VNĐ");
        return listSP;
    }


}

