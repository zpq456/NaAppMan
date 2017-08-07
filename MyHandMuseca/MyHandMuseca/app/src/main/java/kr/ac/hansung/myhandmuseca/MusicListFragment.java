package kr.ac.hansung.myhandmuseca;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.hansung.myhandmuseca.Data.MusicData;
import kr.ac.hansung.myhandmuseca.DataBase.DBManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicListFragment extends Fragment
        implements MainActivity.onKeyBackPressedListener{
    private static final int REQUEST_NEW_GROUP = 1;
    private static final String REQUEST_SHOW_ADD_GROUP_DIALLOG = "showAddMusicDialogFragment";

    private MainActivity main;
    private ArrayList<MusicData> musicDatas = new ArrayList<>();
    private ListView listView;
    private MusicListViewAdapter musicListViewAdapter;
    private Button addMusicBtn;

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

        initDatas();

        listView = (ListView)v.findViewById(R.id.musicListView);
        musicListViewAdapter = new MusicListViewAdapter();
        if(musicDatas!=null) {
            for (int i = 0; i < musicDatas.size(); i++) {
                musicListViewAdapter.addItem(
                        musicDatas.get(i).getData(1), musicDatas.get(i).getData(2),
                        musicDatas.get(i).getData(3), musicDatas.get(i).getData(4));
            }
        }
        listView.setAdapter(musicListViewAdapter);

        addMusicBtn = (Button)v.findViewById(R.id.addMusicBtn);
        addMusicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddMusic();
            }
        });
        return v;
    }

    private void startAddMusic() {
        AddMusicDialogFragment dialog = new AddMusicDialogFragment();
        dialog.setTargetFragment(MusicListFragment.this, REQUEST_NEW_GROUP);
        dialog.show(getFragmentManager(), REQUEST_SHOW_ADD_GROUP_DIALLOG);
    }

    public void initDatas(){
        DBManager db = new DBManager();
        musicDatas = db.SelectAllMusicDB();
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


    public static class AddMusicDialogFragment extends DialogFragment {

        private Button addMusicBtn;
        private EditText nameEditText;
        private EditText releasedEditText;
        private EditText prouduceEditText;
        private EditText getEditText;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.dialog_add_music,null);
            builder.setView(v);
            builder.setTitle("새 악곡 추가");

            nameEditText = (EditText)v.findViewById(R.id.nameEditText);
            releasedEditText = (EditText)v.findViewById(R.id.releasedEditText);
            prouduceEditText = (EditText)v.findViewById(R.id.produceEditText);
            getEditText = (EditText)v.findViewById(R.id.getEditText);

            //그룹만들기 버튼
            addMusicBtn = (Button)v.findViewById(R.id.okAddMusicBtn);
            addMusicBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = nameEditText.getText().toString();
                    String released = releasedEditText.getText().toString();
                    String produce = prouduceEditText.getText().toString();
                    String get = getEditText.getText().toString();

                    DBManager dbManager = new DBManager();
                    dbManager.InsertMusicDB(new MusicData("",name,released,produce,get));

                    dismiss();
                    ((MainActivity)getActivity()).ChangeFragment(new MusicListFragment());
                }
            });
            return builder.create();
        }
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


