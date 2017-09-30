package kr.ac.zipokun.handymuseca.Data;

/**
 * Created by Owner on 2017-07-15.
 */

public class MusicData implements DataInfo{
    private String[] uData = new String[5];

    public MusicData(){}
    public MusicData(String Mid, String mName, String mReleased,
                          String mProduce, String mGet){
        setData(0,Mid);
        setData(1,mName);
        setData(2,mReleased);
        setData(3,mProduce);
        setData(4,mGet);
    }

    @Override
    public String[] getData() {
        return uData;
    }
    @Override
    public String getData(int index) {
        return uData[index];
    }
    @Override
    public void setData(int index, String data) {
        uData[index] = data;
    }
    @Override
    public void setData(String[] uData) {
        this.uData = uData;
    }

    @Override
    public String getSendSQLString() {
        return null;
    }
}
