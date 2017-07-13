package com.adamzfc.architecturecomponentsdemo.data.api;

import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 * Created by adamzfc on 2017/7/10.
 */

public class LogInterceptor implements Interceptor {
    private static final String TAG = "test_";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //对请求进行操作
        Request.Builder requestBuilder = request.newBuilder()
                .header("jheader", "tkheadeyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290IiwiY3JlYXRlZCI6MTQ5OTY5MTM2MDc5NiwiZXhwIjoxNTAwMjk2MTYwfQ.a4MW9yVMk7tb_02Ck4BLhSReizEqb2y1ijka787H9Zn8XY3M_3jUof6rTaDXIeqKzstb7AVl5YN8phAC3l7ZSg");
        //添加请求头
//        Request.Builder requestBuilder = request.newBuilder().header("APIKEY", "XXXXX");
        Request newRequest = requestBuilder.build();

        //打印请求日志
        Log.v(TAG, "request: " + newRequest.toString());
        //请求
        if (newRequest.method().equals("POST")) {
            String req = bodyToString(newRequest.body());
            Log.i(TAG, "request body: " + req);
        }
        //long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(newRequest);
        //回调
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        //打印回调日志
        Log.i(TAG, "response body: " + content);
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }

    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            copy.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
    /**
     * 请求参数加密
     * @param body
     * @return sign
     */
//    public FormBody.Builder getMd5Body(FormBody.Builder body) {
//        List<Map.Entry<String, Object>> orderMap;//排序后的列表
//        FormBody.Builder temFormBody = new FormBody.Builder();//最终Body
//        FormBody tem=body.build();
//        if (tem==null||tem.size()<=0){
//            return temFormBody.addEncoded("sign",MD5.MD5Encoder("", "UTF-8").toLowerCase(Locale.US));
//        }
//        Map<String,Object> sendData=new HashMap<>();
//        String temKey="";
//        //包装
//        for (int i = 0;i<tem.size();i++){
//            sendData.put(tem.encodedName(i),tem.encodedValue(i));
//        }
//
//        //String temKey2="";
//        if (sendData!=null && sendData.size()>0) {
//            // 带序列的map
//            orderMap = new ArrayList<>(sendData.entrySet());
//            // 排序
//            Collections.sort(orderMap,new Comparator<Map.Entry<String, Object>>(){
//                @Override
//                public int compare(Map.Entry<String, Object> lhs,
//                                   Map.Entry<String, Object> rhs) {
//                    return (lhs.getKey().toLowerCase(Locale.US)).toString().compareTo(
//                            rhs.getKey().toLowerCase(Locale.US));
//                }
//            });
//            for (int i = 0; i < orderMap.size(); i++) {
//                temKey += orderMap.get(i).toString() + "&";
//                temFormBody.addEncoded(orderMap.get(i).getKey(),orderMap.get(i).getValue().toString());
//                //temKey2 += (orderMap.get(i).getKey() + "=" + getUrlEncode(orderMap.get(i).getValue()+"") + "&");
//            }
//            temFormBody.addEncoded("sign",MD5.MD5Encoder((temKey+Constants.MD5KEY), "UTF-8").toLowerCase(Locale.US));
//            //temKey = temKey.substring(0, temKey.length() - 1);
//            return temFormBody;
//        }
//        return body;
//    }

    /**
     * URL编码转义
     *
     * @param strUrl
     * @return
     */
    public static String getUrlEncode(String strUrl) {
        try {
            return URLEncoder.encode(strUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return strUrl.replace("+", "%2b").replace(" ", "%20").replace("=", "%3d").replace("&", "%26");
    }
}
