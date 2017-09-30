package kr.ac.zipokun.handymuseca;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MusicFragment extends Fragment
        implements MainActivity.onKeyBackPressedListener{
    private static MusicFragment uniqueInstance;
    private MainActivity main;

    MusicFragment() {}

    public static synchronized MusicFragment getInstance() {
        if(uniqueInstance == null){uniqueInstance = new MusicFragment();}
        return uniqueInstance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = ((MainActivity) getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_music, container, false);

        return v;
    }

    //백키 관련 코드
    @Override
    public void onBack() {//프래그먼트의 백키
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.changeFragment("mainFragment");
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).setOnKeyBackPressedListener(this);
    }

}
