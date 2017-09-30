package kr.ac.zipokun.handymuseca;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainFragment extends Fragment
        implements MainActivity.onKeyBackPressedListener {
    private static MainFragment uniqueInstance;

    private MainActivity main;
    private Button musicBtn,graficaBtn;

    MainFragment() {}

    public static synchronized MainFragment getInstance() {
        if(uniqueInstance == null){uniqueInstance = new MainFragment();}
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
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        musicBtn = (Button) v.findViewById(R.id.musicListBtn);
        musicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.changeFragment("musicFragment");
                Toast.makeText(getActivity(), "musicList", Toast.LENGTH_SHORT).show();
            }
        });
        graficaBtn = (Button) v.findViewById(R.id.graficaListBtn);
        graficaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.changeFragment("graficaFragment");
                Toast.makeText(getActivity(), "graficaList", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    //백키 관련 코드
    @Override
    public void onBack() {//프래그먼트의 백키
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.onBackPressed();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).setOnKeyBackPressedListener(this);
    }
}