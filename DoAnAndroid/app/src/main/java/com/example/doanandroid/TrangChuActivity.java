package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.doanandroid.Frament.ActivityDetail;
import com.example.doanandroid.Frament.FragmentSlider;
import com.example.doanandroid.Frament.GioHangFrament;
import com.example.doanandroid.Frament.QuanJeanFragment;
import com.example.doanandroid.Frament.TaiKhoanFragment;
import com.example.doanandroid.Retrofit.ApiInterface;
import com.example.doanandroid.Retrofit.Retrofitclient;
import com.example.doanandroid.adapter.SliderPagerAdapter;
import com.example.doanandroid.decoration.BannerSlider;
import com.example.doanandroid.decoration.SliderIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChuActivity extends AppCompatActivity {

    RecyclerView mList1,mList2;
    List<App> appList;
    ImageView imgTimKiem;

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    CustomSanPhamAdapter customSanPhamAdapter;

    private BannerSlider bannerSlider;
    private LinearLayout mLinearLayout;
    MeowBottomNavigation meo;
    LinearLayout benner;
    RelativeLayout search;
    private final static int ID_HOME=1;
    private final static int ID_INTERFACE=2;
    private final static int ID_SHOPPING=3;
    private final static int ID_USER=4;
    ArrayList<SanPham> arrayList ;

    ApiInterface apInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        AnhXa();
        setupSlider();
        apInterface = Retrofitclient.getClient().create(ApiInterface.class);
        arrayList= new ArrayList<>();
        customSanPhamAdapter = new CustomSanPhamAdapter(arrayList,getApplicationContext());
        getData("https://mylifemrrobot.000webhostapp.com/getdata.php");

        mList2.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        mList2.setLayoutManager(layoutManager);

//        arrayList.add(new SanPham(1,1,100000,"Quần short","Quần short nam thời trang, lựa chọn hàng đầu cho những tín đồ thời trang mùa hè",
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjrupOhagxSFeFnLtSSDU-ZXqiiNFO7DxTPQ&usqp=CAU"));
        mList2.setAdapter(customSanPhamAdapter);
        customSanPhamAdapter.notifyDataSetChanged();
        customSanPhamAdapter.setOnItemClickListenner(new CustomSanPhamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SanPham sp ;
                Intent intent = new Intent(TrangChuActivity.this,ActivityDetail.class);
                sp= arrayList.get(position);
                intent.putExtra("MASP",sp.getMASANPHAM());
                intent.putExtra("TENSP",sp.getTENSANPHAM());
                intent.putExtra("GIA",sp.getGIA());
                intent.putExtra("HINH",sp.getHINHANH());
                intent.putExtra("MALOAI",sp.getMALOAI());
                intent.putExtra("MOTA",sp.getMOTASANPHAM());
                startActivity(intent);
                Toast.makeText(TrangChuActivity.this, ""+sp.getTENSANPHAM(), Toast.LENGTH_SHORT).show();
            }
        });


        meo=findViewById(R.id.navigation);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        meo.add(new MeowBottomNavigation.Model(2,R.drawable.ic_interface));
        meo.add(new MeowBottomNavigation.Model(3,R.drawable.ic_shopping_cart));
        meo.add(new MeowBottomNavigation.Model(4,R.drawable.ic_user));

      //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrangChuFragment()).commit();

        meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
               // Toast.makeText(getApplicationContext(),"Clicked item"+item.getId(),Toast.LENGTH_SHORT).show();
            }
        });
        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment select_fragment = null;
                if(ID_HOME == item.getId()){
                    finish();
                    startActivity(getIntent());
                }
                else {
                    switch (item.getId()) {
                        case ID_INTERFACE:

                            benner.setVisibility(View.GONE);
                            search.setVisibility(View.GONE);
                            mList1.setVisibility(View.GONE);
                            mList2.setVisibility(View.GONE);
                            select_fragment = new QuanJeanFragment();
                            break;
                        case ID_SHOPPING:

                            benner.setVisibility(View.GONE);
                            search.setVisibility(View.GONE);
                            mList1.setVisibility(View.GONE);
                            mList2.setVisibility(View.GONE);
                            select_fragment = new GioHangFrament();
                            break;
                        case ID_USER:

                            benner.setVisibility(View.GONE);
                            search.setVisibility(View.GONE);
                            mList1.setVisibility(View.GONE);
                            mList2.setVisibility(View.GONE);
                            select_fragment = new TaiKhoanFragment();
                            break;

                        default:
                            throw new IllegalStateException("Unexpected value: " + item.getId());
                    }
                    //     getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,select_fragment).commit();

                    FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayoutContent, select_fragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }

            }
        });

        imgTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrangChuActivity.this, "Tìm kiếm", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TrangChuActivity.this,TimKiemActivity.class));
                finish();
            }
        });

        meo.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
        appList = new ArrayList<>();
        appList.add(new App(R.drawable.icon1,"Danh mục"));
        appList.add(new App(R.drawable.icon2,"Tài khoản"));
        appList.add(new App(R.drawable.icon3,"Voucher"));
        appList.add(new App(R.drawable.icon4,"Giỏ hàng"));
        appList.add(new App(R.drawable.icon5,"Tìm Kiếm"));
        appList.add(new App(R.drawable.icon1,"Whatsapp"));
        appList.add(new App(R.drawable.icon1,"Danh mục"));
        appList.add(new App(R.drawable.icon2,"Tài khoản"));
        appList.add(new App(R.drawable.icon3,"Voucher"));

        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList1.setLayoutManager(manager1);
        CustomAdaptor adaptor1 = new CustomAdaptor(this,appList);
        mList1.setAdapter(adaptor1);

        adaptor1.setOnItemClickListenner(new CustomAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position)
                {
                    case 0:
                        Toast.makeText(TrangChuActivity.this, "Fragment Danh mục", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(TrangChuActivity.this, "Fragment Tài khoản", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(TrangChuActivity.this, "Fragment Voucher", Toast.LENGTH_SHORT).show();

                        break;
                    case 3:
                        benner.setVisibility(View.GONE);
                        search.setVisibility(View.GONE);
                        mList1.setVisibility(View.GONE);
                        mList2.setVisibility(View.GONE);
                        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayoutContent, new GioHangFrament());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;

                    default:
                        Toast.makeText(TrangChuActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void AnhXa() {
        bannerSlider = findViewById(R.id.sliderView);
        mLinearLayout = findViewById(R.id.pagesContainer);
        benner =  findViewById(R.id.llBanner);
        search =  findViewById(R.id.relative);
        mList1 = findViewById(R.id.list1);
       mList2 = findViewById(R.id.list2);
       imgTimKiem = findViewById(R.id.imageViewTimKiem);

    }
    private void setupSlider() {
        bannerSlider.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        //link image
        fragments.add(FragmentSlider.newInstance("https://i1.wp.com/snkrvn.com/wp-content/uploads/2019/05/Banner.jpg?fit=1920%2C1080&ssl=1"));
        fragments.add(FragmentSlider.newInstance("https://dathangnhanh.net.vn/uploads/news/2018_02/banner-top-trang-chu-1-slide-19.jpg"));
        fragments.add(FragmentSlider.newInstance("https://slimages.macysassets.com/is/image/McomMedia/media/091718_MENS_STYLE_HUB_SUPPORTING_ASSETS_03_1325175.jpg?wid=1200"));
        fragments.add(FragmentSlider.newInstance("https://shopify-ecommerce-university.s3.amazonaws.com/production/images/success_story/cover_image_large/699/mens_fashion_shopify_success_stories_cover.jpg"));

        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        bannerSlider.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this, mLinearLayout, bannerSlider, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }


    @Override
    public void onBackPressed() {
        finish();
        //startActivity(getIntent());
        super.onBackPressed();
    }

    private void getData(String url) {

        Call<List<SanPham>> sanpham = apInterface.getAllSanPham();

        sanpham.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                arrayList.clear();
                for(int i=0;i<response.body().size();i++)
                {
                    arrayList.add(new SanPham(response.body().get(i).getMASANPHAM(),
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
                        Intent intent = new Intent(TrangChuActivity.this, ActivityDetail.class);
                        sp= arrayList.get(position);
                        intent.putExtra("MASP",sp.getMASANPHAM());
                        intent.putExtra("TENSP",sp.getTENSANPHAM());
                        intent.putExtra("GIA",sp.getGIA());
                        intent.putExtra("HINH",sp.getHINHANH());
                        intent.putExtra("MALOAI",sp.getMALOAI());
                        intent.putExtra("MOTA",sp.getMOTASANPHAM());
                        startActivity(intent);
                        Toast.makeText(TrangChuActivity.this, ""+sp.getTENSANPHAM(), Toast.LENGTH_SHORT).show();
                    }

                                                             }

                );

            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(TrangChuActivity.this, "Lỗi"+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("SSS",t.toString());
            }
        });
    }
}
