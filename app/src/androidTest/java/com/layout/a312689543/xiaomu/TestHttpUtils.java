package com.layout.a312689543.xiaomu;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.util.Log;

import gongjubao.Httputils;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class TestHttpUtils extends AndroidTestCase{


    public void testsendinfo(){
        String res= Httputils.doGet("给我讲个笑话");
        Log.e("TAG", res);
        res= Httputils.doGet("给我讲个黄色笑话");
        Log.e("TAG", res);
        res= Httputils.doGet("我帅不帅");
        Log.e("TAG", res);
        res= Httputils.doGet("有男朋友么");
        Log.e("TAG", res);

    }

}