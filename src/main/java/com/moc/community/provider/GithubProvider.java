package com.moc.community.provider;

import com.alibaba.fastjson.JSON;
import com.moc.community.dto.AccessTokenDto;
import com.moc.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try(Response response = client.newCall(request).execute()){
            String str = response.body().string();
            String token = str.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        /** try中的内容会出现 javax.net.ssl.SSLException: Received fatal alert: protocol_version
         *  未找到 解决方法  to doing
         *  捕获，硬编码跳过这个错误
         */
        } catch(IOException e) {
            // e.printStackTrace();
            String str = "GitHub获取Token失败，采用离线数据：access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer";
            String token = str.split("&")[0].split("=")[1];
            System.out.println(str);
            return token;
        }
        //return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            GithubUser githubUser = new GithubUser();
            githubUser.setId(1001L);
            githubUser.setName("MOC同学");
            githubUser.setBio("Code To Do");
            System.out.println("GitHub获取用户失败，采用离线数据：{Id:1001, Name:MOC同学, BIo:Code To Do}");
            return githubUser;
            // e.printStackTrace();
        }
        // return null;
    }


}
