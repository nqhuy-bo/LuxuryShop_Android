package com.example.doanandroid.Retrofit;

import com.example.doanandroid.KhachHang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("dangnhap.php")
    Call<List<KhachHang>> loginUser(@Field("SODIENTHOAI") String SODIENTHOAI
            , @Field("MATKHAU") String MATKHAU);

    //Có năm Annotation được tích hợp sẵn: @GET, @POST, @PUT, @DELETE và @HEAD
}
