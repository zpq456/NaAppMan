package kr.ac.hansung.myhandmuseca;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프래그먼트 매니저 부분
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.container);
        if(fragment==null){
            fragment = new MainFragment().newInstance();
            fm.beginTransaction().replace(R.id.container,fragment).commit();
        }

        //네비게이션 드로어 부분
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_main) {
            fragment = new MainFragment().newInstance();
        } else if (id == R.id.nav_music) {
            fragment = new MusicListFragment().newInstance();
        } else if (id == R.id.nav_grafica) {
            fragment = new GraficaListFragment().newInstance();
        }

        if(fragment != null)
            ChangeFragment(fragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //프래그먼트 이동시키기
    public void ChangeFragment(int id) {
        Fragment changeFragment = null;
        switch (id) {
            case 0://R.id.calendars:
                //calDatas MonthCalendarFragment 로 전달함.
                changeFragment = new MainFragment().newInstance();
                break;
        }
        if (changeFragment != null) {
            fragment = changeFragment;
            fm.beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

    public void ChangeFragment(Fragment changeFragment) {
        if (changeFragment != null) {
            fragment = changeFragment;
            fm.beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

    //백키 관련 코드
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else  if (mOnKeyBackPressedListener != null) { //백키 리스너 있을 경우
            mOnKeyBackPressedListener.onBack();
        } else {
            this.finishAffinity();
            super.onBackPressed();
        }
    }

    public interface onKeyBackPressedListener {
        public void onBack();
    }

    private onKeyBackPressedListener mOnKeyBackPressedListener;

    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener) {
        mOnKeyBackPressedListener = listener;
    }


}
