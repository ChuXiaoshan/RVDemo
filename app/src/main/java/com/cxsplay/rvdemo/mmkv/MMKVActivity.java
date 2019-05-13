package com.cxsplay.rvdemo.mmkv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.cxsplay.rvdemo.R;
import com.tencent.mmkv.MMKV;

public class MMKVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmkv);
        String rootDir = MMKV.initialize(this);

        LogUtils.d("mmkv root: " + rootDir);
        init();
    }

    private void init() {
        initMMKV();
        initSP();
    }

    private void initMMKV() {
        Long start = System.currentTimeMillis();
        LogUtils.d("-----M--start---------->" + start);
        MMKV kv = MMKV.mmkvWithID("rvdemo");
        kv.encode("string", new StrMaker().longStr);
        String str = kv.decodeString("string");
        Long end = System.currentTimeMillis();
        LogUtils.d("-----M--end---------->" + end);
        LogUtils.d("-----M---time------->" + (end - start));
        LogUtils.d("-----M---str------->" + str);
    }

    private void initSP() {
        Long start = System.currentTimeMillis();
        LogUtils.d("----S---start---------->" + start);
        SharedPreferences sp = getSharedPreferences("rvdemo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("string", new StrMaker().longStr);
        editor.commit();
        String str = sp.getString("string", "");
        Long end = System.currentTimeMillis();
        LogUtils.d("----S---end---------->" + end);
        LogUtils.d("-----S---time------->" + (end - start));
        LogUtils.d("-----S---str------->" + str);
    }

    public static void toAlipayAppWeb(String url) {
//        String content = "alipayqr://platformapi/startapp?saId=10000007&qrcode=" + url;
//        String content = "alipays://platformapi/startapp?appId=20000067&url=http%3A%2F%2F%2F5.vyicoo.com";
//        String content = "alipays://platformapi/startapp?appId=66666674";
//        String content = "alipays://platformapi/startapp?appId=20000056";
//        String content = "weixin://";
//        String content = "alipayqr://platformapi/startapp?saId=10000007&qrcode=https://qr.alipay.com/fkx00680w9drjx4h54fso15?t=1548819801392";
        String content = "weixin://wap/pay?appid%3Dwx2421b1c4370ec43b%26noncestr%3D3e84679af4efab5f32ee9ea01b2ec290%26package%3DWAP%26prepayid%3Dwx20160504154919fdacd7bc0d0127918780%26timestamp%3D1462348159%26sign%3DC40DC4BB970049D6830BA567189B463B";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(content));
        ActivityUtils.startActivity(intent);
    }
}
