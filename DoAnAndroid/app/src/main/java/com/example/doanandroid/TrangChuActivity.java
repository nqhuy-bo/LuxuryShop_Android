package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.doanandroid.Frament.FragmentSlider;
import com.example.doanandroid.Frament.GioHangFrament;
import com.example.doanandroid.Frament.QuanJeanFragment;
import com.example.doanandroid.Frament.TaiKhoanFragment;
import com.example.doanandroid.Frament.TrangChuFragment;
import com.example.doanandroid.adapter.SliderPagerAdapter;
import com.example.doanandroid.decoration.BannerSlider;
import com.example.doanandroid.decoration.SliderIndicator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity {

    RecyclerView mList1,mList2;
    List<App> appList;

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private BannerSlider bannerSlider;
    private LinearLayout mLinearLayout;
    MeowBottomNavigation meo;
    LinearLayout benner;
    RelativeLayout search;


//    BottomNavigationView navView;
//    FrameLayout frameLayout;
//    TabLayout tabLayout;
//    ViewPager viewPager;

    private final static int ID_HOME=1;
    private final static int ID_INTERFACE=2;
    private final static int ID_SHOPPING=3;
    private final static int ID_USER=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        AnhXa();
        setupSlider();


        meo=findViewById(R.id.navigation);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        meo.add(new MeowBottomNavigation.Model(2,R.drawable.ic_interface));
        meo.add(new MeowBottomNavigation.Model(3,R.drawable.ic_shopping_cart));
        meo.add(new MeowBottomNavigation.Model(4,R.drawable.ic_user));

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrangChuFragment()).commit();

        meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"Clicked item"+item.getId(),Toast.LENGTH_SHORT).show();
            }
        });
        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment select_fragment=null;
                switch (item.getId()){
                    case ID_HOME:
                        benner.setVisibility(View.VISIBLE);
                        search.setVisibility(View.VISIBLE);
                        select_fragment=new TrangChuFragment();
                        break;
                    case ID_INTERFACE:

                        benner.setVisibility(View.GONE);
                        search.setVisibility(View.GONE);
                        select_fragment=new QuanJeanFragment();

                        break;
                    case ID_SHOPPING:
                        select_fragment=new GioHangFrament();
                        benner.setVisibility(View.GONE);
                        break;
                    case ID_USER:
                        select_fragment=new TaiKhoanFragment();
                        benner.setVisibility(View.GONE);
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,select_fragment).commit();
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

//        LinearLayoutManager manager2 = new LinearLayoutManager(this);
//        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mList2.setLayoutManager(manager2);

        CustomAdaptor adaptor1 = new CustomAdaptor(this,appList);
        mList1.setAdapter(adaptor1);

//        CustomAdaptor adaptor2 = new CustomAdaptor(this,appList);
//        mList2.setAdapter(adaptor2);
    }


//    @Override
//    public void onBackPressed() {
//        finish();
//        return;
//    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction =
        getSupportFragmentManager().beginTransaction();
   //     transaction.replace(R.id.viewPagerTrangChu, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void AnhXa() {
        bannerSlider = findViewById(R.id.sliderView);
        mLinearLayout = findViewById(R.id.pagesContainer);
        benner = (LinearLayout) findViewById(R.id.llBanner);
        search = (RelativeLayout) findViewById(R.id.relative);
        mList1 = findViewById(R.id.list1);

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




}
