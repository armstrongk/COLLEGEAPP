package com.example.armstrong.college;

/**
 * Created by armstrong on 4/3/2017.
 */
public class DataObject {
    private String mText1;
    private String mText2;
    int photoId;


    DataObject (String text1,int photoId){
        mText1 = text1;
        this.photoId = photoId;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

}