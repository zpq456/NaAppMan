package kr.ac.hansung.myhandmuseca;

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
    private Button musicBtn, graficaBtn;
    private MainActivity main;

    public MainFragment() {
        // Required empty public constructor
    }

    public MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
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
                Fragment fragment = new MusicListFragment().newInstance();
                main.ChangeFragment(fragment);
                Toast.makeText(getActivity(), "musicList", Toast.LENGTH_SHORT).show();
            }
        });
        graficaBtn = (Button) v.findViewById(R.id.graficaListBtn);
        graficaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new GraficaListFragment().newInstance();
                main.ChangeFragment(fragment);
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