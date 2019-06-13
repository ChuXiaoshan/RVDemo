package com.cxsplay.appkotlin.ui;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bruce on 2018/6/15.
 */

public class MerchantInfo {
    //这里三个值请填写自己真实的值
    //应用的签名私钥
    public final static String appKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCwKPKMR5H2hBMf5rAzAH+GlJcQV5E8Z120oKbxbjedYgaxkg4to12pVs5NumoDv/NWbAXnUM9OwAlCsMUg4YEc6QWLf7vg9mUi1MWR4JSbZqsnbo6ZlBpW6uauvCU5K/bBsKimIR57uSBzP6dZQFrJQjImUPVGJgBhtIJeJ7jo/CfOLBn5Q+JsGtvWk4ndYs+G2jkSks+Xwrn8zuuJzy/5meXQddBYe3C+1oJDtiYA6Dn41PaXnLLJ1hYtFeizMxZMZmB0JzXkNO+kfA94kAaG69y/xF+XaVmrd2QaevBXqw/TnyysP8jmyUs3YxA3gIK60IGLJx4oLoir9eDZnaGNAgMBAAECggEATXVj2VzrCJtgkWltVq/EY1fY6N2oRggpgvAVGBnfqHte+BuB0n2ABKLfyfvK++UeSYTsP58f2L/GEqYJuACQNjccMg+zfCK7q8olKZS3o3FMluUdHNirkEZvsquWLoJvWRGlxo7PiZWIeESoAC20/cP2qzRz9Slmh4/Itz/+JatnyQy00mGsz6CliucaWqXbHhP7bqVjscD/8oHFb90bf2QQ9sGk76ARL5r4R9tiMjrK91m0XyBu5kaYRnkvd5oXbQQYPca68LACIGLHO9fS3KrZaphMhC/WPHTReiUn0w5PggwvGYnfgiJot0N+NlJIYtVJ/Mo3ZDND//rAtUuf3QKBgQDYBhVjAbT4BsA5tkRMThCE/x2jGNNcb1VDz81PNYUArLt+T/r7/ivEiUOoqYfnL1/TNtkmKr3zOAeOULvfg6PGR1gr6DNXofTNYw6sfvfmfhKN9dPDkjmp4Wmnpe35Pw5Dop8/wlk7c4fulVHdAP93lfSSAxF9wppqYvaZwmf3vwKBgQDQwlq6opvCeFoiYiY9e2uTeXNsq4CWDTX32IsIQokRoiNLN8nVoS9WgLXKV3nxRIgdiZfbnHz5hSkzgETE2svGQFBMG3WSalfGXBH36aOB+O6TEIgnkbIc0xQ2ggxUjyDEno447LL9z3xuN3q3PAHuF+slZHWRfIBeQQxrXLZZswKBgHeLLkyixi23Nqr5RWRgkt22fAUkmNp/NdUX6srZmCeebwdgZUkMB9Z0C4ALiQyTd9jfcl2fq4wrxmqoHL99DqWMF6v8jGUDWABMv/MPNEpWHBh5IF4KzmRHPHus5DtY/KDLHotfFphCv8d01F+TzMu1rSkcD5LjnSYga8z8+yHrAoGAYTUvNFsFtp9+MSaM8fInnKtgIQkXPkf2imtuCjGZS/m4/4yB0pVUyq3yeL8VDFDCJSbt7JhI5ZOpJM8ktpm/a5iFC3t52uzj5Hz47P0BY0M8LIT1MZ+5VYzAi8Uw4qsnMgc26LDnhCJ6qYPyA6LQr0K5/1ZxHIbh64HUu6WXT9sCgYAHUcjoP2MaX9cfQgW5CCWdJErJ59pqooeVR1eqIa+Frq43A+KYK/s27PDTcJEv2myM+D8zoZVbMnZX2KNXrsRSWmtFPtJbNJGeH8RVeX7ZQt6Tt9ZgVLPX9FEbWihilKCf84RyTWt1R5zRJdyQCGFZDx64v+teVPe0HJSgwpProg==";
    //商户id
    public final static String partnerId = "2088421326544474";
    //应用的appId
    public final static String appId = "2019021463221582";

    /**
     * mock数据，真实商户请填写真实信息.
     */
    public static Map mockInfo() {
        Map merchantInfo = new HashMap();
        //以下信息请根据真实情况填写
        //商户id
        merchantInfo.put("partnerId", partnerId);
        merchantInfo.put("merchantId", partnerId);
        //开放平台注册的appid
        merchantInfo.put("appId", appId);
        //机具编号，便于关联商家管理的机具
//        merchantInfo.put("deviceNum", "TEST_ZOLOZ_TEST");
        merchantInfo.put("deviceNum", "123456");
        //真实店铺号
        merchantInfo.put("storeCode", "TEST");
        //口碑店铺号
        merchantInfo.put("alipayStoreCode", "TEST");
        //品牌，传入拼音或者英文，标示该商家
        merchantInfo.put("brandCode", "TEST");

        merchantInfo.put("areaCode", "TEST");
        merchantInfo.put("geo", "0.000000,0.000000");
        merchantInfo.put("wifiMac", "TEST");
        merchantInfo.put("wifiName", "TEST");
        merchantInfo.put("deviceMac", "TEST");

        return merchantInfo;
    }
}
