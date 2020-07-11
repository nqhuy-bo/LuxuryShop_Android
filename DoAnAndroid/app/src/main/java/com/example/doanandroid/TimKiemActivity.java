package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.doanandroid.Frament.ActivityDetail;
import com.example.doanandroid.Retrofit.ApiInterface;
import com.example.doanandroid.Retrofit.Retrofitclient;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclyViewSanPham;
    ArrayList<SanPham> arrayList;
    ListView listView;
    TagGroup tagGroup;
    ArrayList<String> tag;
    String text="";
    ApiInterface apInterface;
    final ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
    CustomSanPhamAdapter customSanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        AnhXa();
        apInterface = Retrofitclient.getClient().create(ApiInterface.class);
        tag=new ArrayList<>();
        tag.add("Quần jean");
        tagGroup.setTags(tag);
        sanPhamArrayList.add(new SanPham(1,1,100000,"Quần short","Quần short nam thời trang, lựa chọn hàng đầu cho những tín đồ thời trang mùa hè",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjrupOhagxSFeFnLtSSDU-ZXqiiNFO7DxTPQ&usqp=CAU"));

        recyclyViewSanPham.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclyViewSanPham.setLayoutManager(layoutManager);
        customSanPhamAdapter = new CustomSanPhamAdapter(sanPhamArrayList,getApplicationContext());
        customSanPhamAdapter.notifyDataSetChanged();
        recyclyViewSanPham.setAdapter(customSanPhamAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tag.add(query);
                tagGroup.setTags(tag);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                timKiemSanPham(newText);
                return false;
            }
        });

    }

    private void timKiemSanPham(String text)
    {
        Call<List<SanPham>> sanpham = apInterface.searchSanPham(text);

        sanpham.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhamArrayList.clear();
                for(int i=0;i<response.body().size();i++)
                {
                    sanPhamArrayList.add(new SanPham(response.body().get(i).getMASANPHAM(),
                            response.body().get(i).getMALOAI(),response.body().get(i).getGIA(),
                            response.body().get(i).getTENSANPHAM(),response.body().get(i).getMOTASANPHAM(),
                            response.body().get(i).getHINHANH()));
                    Log.d("BBB",response.body().get(i).getMASANPHAM()+"");
                    customSanPhamAdapter.notifyDataSetChanged();
                }



                customSanPhamAdapter.setOnItemClickListenner(new CustomSanPhamAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        SanPham sp ;
                        Intent intent = new Intent(TimKiemActivity.this, ActivityDetail.class);
                        sp= sanPhamArrayList.get(position);
                        intent.putExtra("MASP",sp.getMASANPHAM());
                        intent.putExtra("TENSP",sp.getTENSANPHAM());
                        intent.putExtra("GIA",sp.getGIA());
                        intent.putExtra("HINH",sp.getHINHANH());
                        intent.putExtra("MALOAI",sp.getMALOAI());
                        intent.putExtra("MOTA",sp.getMOTASANPHAM());
                        startActivity(intent);
                        Toast.makeText(TimKiemActivity.this, ""+sp.getTENSANPHAM(), Toast.LENGTH_SHORT).show();
                    }
                }
                );

            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(TimKiemActivity.this, "Lỗi"+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("SSS",t.toString());
            }
        });
    }

    private void AnhXa() {
        recyclyViewSanPham = findViewById(R.id.recycleViewSanPham);
        tagGroup  = findViewById(R.id.tag_group);
        searchView = findViewById(R.id.search_vew);

    }
}