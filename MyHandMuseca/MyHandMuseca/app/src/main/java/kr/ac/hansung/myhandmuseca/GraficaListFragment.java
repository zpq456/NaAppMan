package kr.ac.hansung.myhandmuseca;


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
public class GraficaListFragment extends Fragment
        implements MainActivity.onKeyBackPressedListener{
    private MainActivity main;

    public GraficaListFragment() {
        // Required empty public constructor
    }
    public GraficaListFragment newInstance() {
        GraficaListFragment fragment = new GraficaListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = ((MainActivity)getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grafica_list, container, false);
        return v;
    }

    //백키관련 코드
    @Override
    public void onBack() {
        Fragment fragment = new MainFragment().newInstance();
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.ChangeFragment(fragment);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).setOnKeyBackPressedListener(this);
    }
}
