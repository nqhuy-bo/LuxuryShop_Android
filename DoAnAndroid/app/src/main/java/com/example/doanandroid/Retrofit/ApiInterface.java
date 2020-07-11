package com.example.doanandroid.Retrofit;

import com.example.doanandroid.KhachHang;
import com.example.doanandroid.SanPham;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("dangnhap.php")
    Call<List<KhachHang>> loginUser(@Field("SODIENTHOAI") String SODIENTHOAI
            , @Field("MATKHAU") String MATKHAU);


    @FormUrlEncoded
    @POST
    Call<SanPham> getSanPham(@Field("MASANPHAM") String MASANPHAM);

    @FormUrlEncoded
    @POST("timkiem.php")
    Call<List<SanPham>> searchSanPham(@Field("TEXT") String TENSANPHAM);


    @GET("getdata.php")
    Call<List<SanPham>> getAllSanPham();
}
