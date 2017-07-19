package kr.ac.hansung.myhandmuseca;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.hansung.myhandmuseca.Data.MusicData;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicListFragment extends Fragment
        implements MainActivity.onKeyBackPressedListener{
    private MainActivity main;
    private ArrayList<MusicData> musicDatas = new ArrayList<>();
    private ListView listView;
    private MusicListViewAdapter musicListViewAdapter;

    public MusicListFragment() {
        // Required empty public constructor
    }

    public MusicListFragment newInstance() {
        MusicListFragment fragment = new MusicListFragment();
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
        View v = inflater.inflate(R.layout.fragment_music_list, container, false);

        //더미 데이터
        musicDatas.add(new MusicData("하이","20170504","헤헤","호호호호호"));
        musicDatas.add(new MusicData("하이","20170504","헤헤","호호호호호"));
        musicDatas.add(new MusicData("하이","20170504","헤헤","호호호호호"));

        listView = (ListView)v.findViewById(R.id.musicListView);
        musicListViewAdapter = new MusicListViewAdapter();
        if(musicDatas!=null) {
            for (int i = 0; i < musicDatas.size(); i++) {
                musicListViewAdapter.addItem(
                        musicDatas.get(i).getData(0), musicDatas.get(i).getData(1),
                        musicDatas.get(i).getData(2), musicDatas.get(i).getData(3));
            }
        }
        listView.setAdapter(musicListViewAdapter);
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

class MusicListViewItem{
    private String mNameText;
    private String mReleasedText;
    private String mProduceText;
    private String mGetText;

    public String getmNameText() {return mNameText;}
    public void setmNameText(String mNameText) {this.mNameText = mNameText;}
    public String getmReleasedText() {return mReleasedText;}
    public void setmReleasedText(String mReleasedText) {this.mReleasedText = mReleasedText;}
    public String getmProduceText() {return mProduceText;}
    public void setmProduceText(String mProduceText) {this.mProduceText = mProduceText;}
    public String getmGetText() {return mGetText;}
    public void setmGetText(String mGetText) {this.mGetText = mGetText;}
}


class MusicListViewAdapter extends BaseAdapter{
    private  ArrayList<MusicListViewItem>items = new ArrayList<>();

    public void addItem(String name,String released,String produce, String get){
        MusicListViewItem item = new MusicListViewItem();
        item.setmNameText(name);
        item.setmReleasedText(released);
        item.setmProduceText(produce);
        item.setmGetText(get);
        items.add(item);
    }

    @Override
    public int getCount() { return items.size();}
    @Override
    public Object getItem(int position) {return items.get(position);}
    @Override
    public long getItemId(int position) {return 0;}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_music_list, parent, false);
        }
        TextView mNameTextView = (TextView)convertView.findViewById(R.id.mName);
        TextView mReleasedTextView = (TextView)convertView.findViewById(R.id.mReleased);
        TextView mProduceTextView = (TextView)convertView.findViewById(R.id.mProduce);
        TextView mGetTextView = (TextView)convertView.findViewById(R.id.mGet);

        //리스트에 뿌려줄 아이템 받아오기
        MusicListViewItem item = (MusicListViewItem) getItem(position);
        mNameTextView.setText(item.getmNameText());
        mReleasedTextView.setText(item.getmReleasedText());
        mProduceTextView.setText(item.getmProduceText());
        mGetTextView.setText(item.getmGetText());

        return convertView;
    }
}
