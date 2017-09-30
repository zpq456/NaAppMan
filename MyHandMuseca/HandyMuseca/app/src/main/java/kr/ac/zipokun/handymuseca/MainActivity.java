package kr.ac.zipokun.handymuseca;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //Fragment
    private FragmentManager fragmentManager;
    private static final MainFragment mainFragment = new MainFragment();
    private static final MusicFragment musicFragment = new MusicFragment();
    private static final GraficaFragment graficaFragment = new GraficaFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Make Fragments
        mainFragment.getInstance();
        musicFragment.getInstance();
        graficaFragment.getInstance();

        //Fragment Manager
        fragmentManager = getSupportFragmentManager();
        changeFragment("mainFragment");
    }


    //프래그먼트 교체 코드
    public void changeFragment(String changefragment){
        switch (changefragment){
            case "mainFragment":
                fragmentManager.beginTransaction().replace(R.id.container, mainFragment).commit();
                break;
            case "musicFragment":
                fragmentManager.beginTransaction().replace(R.id.container, musicFragment).commit();
                break;
            case "graficaFragment":
                fragmentManager.beginTransaction().replace(R.id.container, graficaFragment).commit();
                break;
        }
    }

    //백키 관련 코드
    @Override
    public void onBackPressed() {
        if (mOnKeyBackPressedListener != null) { //백키 리스너 있을 경우
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
