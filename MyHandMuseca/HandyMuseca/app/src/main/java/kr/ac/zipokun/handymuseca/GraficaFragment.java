package kr.ac.zipokun.handymuseca;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class GraficaFragment extends Fragment
    implements MainActivity.onKeyBackPressedListener{
    private static GraficaFragment uniqueInstance;
    private MainActivity main;

    GraficaFragment() {}

    public static synchronized GraficaFragment getInstance() {
        if(uniqueInstance == null){uniqueInstance = new GraficaFragment();}
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
        View v = inflater.inflate(R.layout.fragment_grafica, container, false);

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
