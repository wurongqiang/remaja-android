package org.gerejajkt.remaja.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.gerejajkt.remaja.BuildConfig;
import org.gerejajkt.remaja.custom.RxErrorHandlingCallAdapterFactory;
import org.gerejajkt.remaja.data.api.ApiService;
import org.gerejajkt.remaja.data.dao.UserDao;
import org.gerejajkt.remaja.model.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huteri on 4/25/17.
 */

@Module
public class RetrofitModule {

    private final String baseUrl;

    public RetrofitModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(UserDao userDao) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(buildHeaderInterceptor(userDao));

        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    private Interceptor buildHeaderInterceptor(UserDao userDao) {
        return chain -> {
            Request request = chain.request();
            Request.Builder builderRequest = request.newBuilder();
            builderRequest.addHeader("Content-Type", "application/json");
            builderRequest.addHeader("Accept", "application/json");
            builderRequest.addHeader("API-KEY", "17d32457220C136fA34bA83964d493Be");
            builderRequest.addHeader("PLATFORM", "Android");

            User user = userDao.getUser();

            if(user != null) {
                builderRequest.addHeader("Saint-Access-Token", user.getToken());
            }

            request = builderRequest.build();
            return chain.proceed(request);
        };
    }
}
