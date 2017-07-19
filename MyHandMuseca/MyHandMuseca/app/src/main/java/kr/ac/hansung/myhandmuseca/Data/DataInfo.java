package kr.ac.hansung.myhandmuseca.Data;

/**
 * Created by Owner on 2017-07-15.
 */

public interface DataInfo {
    public String[] getData();
    public String getData(int index);
    public void setData(int index,String data);
    public void setData(String[] uData);

    public String getSendSQLString();
}
