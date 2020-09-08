package com.tugbaolcer.zomato.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.tugbaolcer.zomato.Fragments.AdanaFragment;
import com.tugbaolcer.zomato.Fragments.AnkaraFragment;
import com.tugbaolcer.zomato.Fragments.CollectionFragment;
import com.tugbaolcer.zomato.Fragments.EskisehirFragment;
import com.tugbaolcer.zomato.Fragments.IzmirFragment;
import com.tugbaolcer.zomato.Fragments.KonyaFragment;
import com.tugbaolcer.zomato.Fragments.NearByFragment;
import com.tugbaolcer.zomato.Fragments.RestorantsFragment;
import com.tugbaolcer.zomato.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView navigation_view;
    private ScrollView scroll_view;
    private Fragment fragment;
    private ViewPager viewpager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        drawerLayout();
        tabLayout();
        fragment= new AdanaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,fragment).commit();
        toolbar.setTitle("Zomato");
        setSupportActionBar(toolbar);

    }

    private void initView(){
        toolbar=findViewById(R.id.toolbar);
        navigation_view=findViewById(R.id.navigation_view);
        drawer_layout=findViewById(R.id.drawer_layout);
        scroll_view=findViewById(R.id.scroll_view);
        tabLayout=findViewById(R.id.tabLayout);
        viewpager=findViewById(R.id.viewpager);
    }

    private void drawerLayout(){
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle
                (this,drawer_layout,toolbar,0,0);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
        View headerView = navigation_view.inflateHeaderView(R.layout.nav_header);
        navigation_view.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START);
        }else{
            Intent intent=new Intent (Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id=menuItem.getItemId();
        if(id==R.id.adana){
            fragment=new AnkaraFragment();
        }
        if(id==R.id.ankara){
            fragment=new AnkaraFragment();
        }
        if(id==R.id.eskisehir){
            fragment=new EskisehirFragment();
        }
        if(id==R.id.izmir){
            fragment=new IzmirFragment();
        }
        if(id==R.id.konya){
            fragment=new KonyaFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,fragment).commit();
        drawer_layout.closeDrawer(GravityCompat.START);

        return true ;
    }

    private void tabLayout() {
     MyViewPagerAdapter adapter=new MyViewPagerAdapter(getSupportFragmentManager());
     adapter.fragmentEkle(new CollectionFragment(), "Derlemeler");
     adapter.fragmentEkle(new NearByFragment(),"Yakın Restorantlar");
     adapter.fragmentEkle(new RestorantsFragment(), "Restorantlar");
     viewpager.setAdapter(adapter);
     tabLayout.setupWithViewPager(viewpager);
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment>fragmentList=new ArrayList<>();
        private final List<String>stringList=new ArrayList<>();
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringList.get(position);
        }
        public void fragmentEkle(Fragment fragment, String baslik){
            fragmentList.add(fragment);
            stringList.add(baslik);
        }

    }

}
