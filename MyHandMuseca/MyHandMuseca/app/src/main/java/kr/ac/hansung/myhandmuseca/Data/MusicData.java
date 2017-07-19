package kr.ac.hansung.myhandmuseca.Data;

/**
 * Created by Owner on 2017-07-15.
 */

public class MusicData implements DataInfo{
    private String[] uData = new String[4];

    public MusicData(){}
    public MusicData(String mName, String mReleased,
                          String mProduce, String mGet){
        setData(0,mName);
        setData(1,mReleased);
        setData(2,mProduce);
        setData(3,mGet);
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
