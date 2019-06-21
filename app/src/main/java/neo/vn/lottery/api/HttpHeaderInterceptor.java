package neo.vn.lottery.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //String token = getToken();
        Request request = chain.request().newBuilder()
                //.addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .build();
        return chain.proceed(request);
    }
}
