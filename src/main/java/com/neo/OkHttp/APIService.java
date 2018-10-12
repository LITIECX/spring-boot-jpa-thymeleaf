package com.neo.OkHttp;


import java.util.List;

public class APIService {
    public static final String INDEX = "http://10.10.10.220/eams/localLogin.action";
    public static final String CAPTCAH = "https://cas.xaau.edu.cn/lyuapServer/captcha.jsp";
    public static final String LOGIN = "http://10.10.10.220/eams/localLogin.action";
    public static final String TABLE = "http://10.10.10.220/eams/courseTableForStd!courseTable.action";

    String data;
    List<String> cookies;

    public void index() {

        IRequest request = new MyRequest(INDEX);
        IHttpClient mHttpClient = new MyOkHttpClient();
        mHttpClient.get(request, new OnResultListener<MyResponse>() {
            @Override
            public void onResult(MyResponse result) {
                data = result.getData();
                cookies = result.getHeader().values("Set-Cookie");
                System.out.println(data);
                System.out.println(result.getHeader().toString());
                System.out.println(result.getCode());

            }

            @Override
            public void onError(Exception error) {

            }
        });
    }

    public void login(String username, String password) {
        SHA1 sha1 = new SHA1();
        String data = "2a1cb6f7-c180-421b-80a1-9799eb88dd3c-" + password;
        IRequest request = new MyRequest(INDEX);
        request.setHeader("Cookie", "JSESSIONID=314E9B57535F676EFFFF110ED0971015; GSESSIONID=314E9B57535F676EFFFF110ED0971015");
        request.setBody("username", username);
        request.setBody("password", "ebd912f7958b3aaf3e46064a6c4bf45463a74626");
        request.setBody("encodedPassword", "");
        request.setBody("session_locale", "zh_CN");

        IHttpClient mHttpClient = new MyOkHttpClient();
        mHttpClient.post(request, new OnResultListener<MyResponse>() {
            @Override
            public void onResult(MyResponse result) {

                System.out.println(result.getData());
            }

            @Override
            public void onError(Exception error) {

            }
        });

    }

    public void Table() {
        IRequest request = new MyRequest(TABLE);
        request.setHeader("Cookie", "semester.id=11; JSESSIONID=4F61B385ACA6F990EDEF4A00295781F7; GSESSIONID=4F61B385ACA6F990EDEF4A00295781F7");
        IHttpClient mHttpClient = new MyOkHttpClient();
        mHttpClient.get(request, new OnResultListener<MyResponse>() {
            @Override
            public void onResult(MyResponse result) {
                String data = result.toString();
                System.out.println(data);
                System.out.println(result.getData());
            }

            @Override
            public void onError(Exception error) {

            }
        });

    }
//    public void initCode() {
//        Request request = new Request.Builder().url("").build();
//        OkHttpClient mOkHttpClient=new OkHttpClient();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
////                showToast("验证码加载失败");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                byte[] byte_image = response.body().bytes();
//                //通过handler更新UI
//                Message message = new Message();
//                message.obj = byte_image;
//                message.what = 1;
////                handler.sendMessage(message);
//                //获取session的操作，session放在cookie头，且取出后含有“；”，取出后为下面的 s （也就是jsesseionid）
//                Headers headers = response.headers();
////                Log.d("info_headers", "header " + headers);
//                List<String> cookies = headers.values("Set-Cookie");
//                String session = cookies.get(0);
////                Log.d("info_cookies", "onResponse-size: " + cookies);
//                String sessionID = session.substring(0, session.indexOf(";"));
////                eduBean.setSessionID(sessionID);
////                Log.i("info_s", "session is  :" + sessionID);
//            }
//        });
//    }


}
