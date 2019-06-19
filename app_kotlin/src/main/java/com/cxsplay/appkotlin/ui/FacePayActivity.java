package com.cxsplay.appkotlin.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.zoloz.smile2pay.service.Zoloz;
import com.alipay.zoloz.smile2pay.service.ZolozCallback;
import com.cxsplay.appkotlin.R;
import com.cxsplay.appkotlin.common.base.BaseActivity;

import java.util.Map;

import static com.cxsplay.appkotlin.ui.MerchantInfo.mockInfo;

public class FacePayActivity extends BaseActivity {
    private static final String TAG = "smiletopay";


    public static final String KEY_INIT_RESP_NAME = "zim.init.resp";
    private Zoloz zoloz;

    // 值为"1000"调用成功
    // 值为"1003"用户选择退出
    // 值为"1004"超时
    // 值为"1005"用户选用其他支付方式
    static final String CODE_SUCCESS = "1000";
    static final String CODE_EXIT = "1003";
    static final String CODE_TIMEOUT = "1004";
    static final String CODE_OTHER_PAY = "1005";

    static final String TXT_EXIT = "已退出刷脸支付";
    static final String TXT_TIMEOUT = "操作超时";
    static final String TXT_OTHER_PAY = "已退出刷脸支付";
    static final String TXT_OTHER = "抱歉未支付成功，请重新支付";

    //刷脸支付相关
    static final String SMILEPAY_CODE_SUCCESS = "10000";
    static final String SMILEPAY_SUBCODE_LIMIT = "ACQ.PRODUCT_AMOUNT_LIMIT_ERROR";
    static final String SMILEPAY_SUBCODE_BALANCE_NOT_ENOUGH = "ACQ.BUYER_BALANCE_NOT_ENOUGH";
    static final String SMILEPAY_SUBCODE_BANKCARD_BALANCE_NOT_ENOUGH = "ACQ.BUYER_BANKCARD_BALANCE_NOT_ENOUGH";

    static final String SMILEPAY_TXT_LIMIT = "刷脸支付超出限额，请选用其他支付方式";
    static final String SMILEPAY_TXT_EBALANCE_NOT_ENOUGH = "账户余额不足，支付失败";
    static final String SMILEPAY_TXT_BANKCARD_BALANCE_NOT_ENOUGH = "账户余额不足，支付失败";
    static final String SMILEPAY_TXT_FAIL = "抱歉未支付成功，请重新支付";
    static final String SMILEPAY_TXT_SUCCESS = "刷脸支付成功";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_pay);

        findViewById(R.id.face_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });
    }

    /**
     * 发起刷脸支付请求.
     *
     * @param txt toast文案
     */
    void promptText(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getInfo() {
        zoloz.zolozGetMetaInfo(mockInfo(), new ZolozCallback() {
            @Override
            public void response(Map smileToPayResponse) {
                if (smileToPayResponse == null) {
                    Log.e(TAG, "response is null");
                    promptText(TXT_OTHER);
                    return;
                }

                String code = (String) smileToPayResponse.get("code");
                String metaInfo = (String) smileToPayResponse.get("metainfo");

                //获取metainfo成功
                if (CODE_SUCCESS.equalsIgnoreCase(code) && metaInfo != null) {
                    Log.i(TAG, "metanfo is:" + metaInfo);

                    //正式接入请上传metaInfo到服务端，不要忘记UrlEncode，使用服务端使用的sdk从服务端访问openapi获取zimId和zimInitClientData；
//                    AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
//                    AlipayClient alipayClient = new DefaultAlipayClient("https://pay.dev.vyicoo.com/test/index2",
//                            appId,
//                            appKey,
//                            "json",
//                            "utf-8",
//                            null,
//                            "RSA2");
//                    ZolozAuthenticationCustomerSmilepayInitializeRequest request
//                            = new ZolozAuthenticationCustomerSmilepayInitializeRequest();
//                    request.setBizContent(metaInfo);
//
//                    //起一个异步线程发起网络请求
//                    alipayClient.execute(request,
//                            new AlipayCallBack() {
//                                @Override
//                                public AlipayResponse onResponse(AlipayResponse response) {
//                                    if (response != null && SMILEPAY_CODE_SUCCESS.equals(response.getCode())) {
//                                        try {
//
//                                            ZolozAuthenticationCustomerSmilepayInitializeResponse zolozResponse
//                                                    = (ZolozAuthenticationCustomerSmilepayInitializeResponse) response;
//
//                                            String result = zolozResponse.getResult();
//                                            JSONObject resultJson = JSON.parseObject(result);
//                                            String zimId = resultJson.getString("zimId");
//                                            String zimInitClientData = resultJson.getString("zimInitClientData");
//                                            //人脸调用
//                                            smile(zimId, zimInitClientData);
//                                        } catch (Exception e) {
//                                            promptText(TXT_OTHER);
//                                        }
//                                    } else {
//                                        promptText(TXT_OTHER);
//                                    }
//                                    return null;
//                                }
//                            });
                } else {
                    promptText(TXT_OTHER);
                }
            }
        });
    }
}
