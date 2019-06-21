package neo.vn.lottery.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import neo.vn.lottery.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiRequest {

    private static ApiRequest mInstance;
    private static ApiRequest mNoAuthorityInstance;
    private Retrofit mRetrofit;
    private APIService mService;
    private String TAG = ApiRequest.class.getName();
    private String mTokenAccess = null;

    public static ApiRequest getInstance(int type) {
        if (type == Config.NO_AUTHORITY) {
            if (mNoAuthorityInstance == null)
                mNoAuthorityInstance = new ApiRequest(0);
            return mNoAuthorityInstance;
        } else {
            if (mInstance == null)
                mInstance = new ApiRequest(1);
            return mInstance;
        }
    }

    class NullHostNameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            Log.i("RestUtilImpl", "Approving certificate for " + hostname);
            return true;
        }

    }

    public ApiRequest(int type) {
        // Install the all-trusting trust manager
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        httpClient.writeTimeout(20, TimeUnit.SECONDS);
        httpClient.addInterceptor(new ApiExceptionInterceptor());
        httpClient.addInterceptor(new HttpHeaderInterceptor());
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        OkHttpClient.Builder httpNoAuthorityClient = new OkHttpClient().newBuilder();
        httpNoAuthorityClient.connectTimeout(1, TimeUnit.MINUTES);
        httpNoAuthorityClient.readTimeout(30, TimeUnit.SECONDS);
        httpNoAuthorityClient.writeTimeout(20, TimeUnit.SECONDS);
        httpNoAuthorityClient.addInterceptor(new ApiExceptionInterceptor());
        httpNoAuthorityClient.connectTimeout(60, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
            httpNoAuthorityClient.addInterceptor(logging);
        }
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();
            httpClient.hostnameVerifier(new NullHostNameVerifier());
            httpNoAuthorityClient.hostnameVerifier(new NullHostNameVerifier());
            httpClient.sslSocketFactory(sslSocketFactory);
            httpNoAuthorityClient.sslSocketFactory(sslSocketFactory);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ").serializeNulls().create();
        if (type == Config.AUTHORITY) { //Return api login
            mRetrofit = new Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl(Config.BASE_SERVER)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        } else if (type == 0) { //Return api Not login
            mRetrofit = new Retrofit.Builder()
                    .client(httpNoAuthorityClient.build())
                    .baseUrl(Config.BASE_SERVER)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        mService = mRetrofit.create(APIService.class);
    }

}
