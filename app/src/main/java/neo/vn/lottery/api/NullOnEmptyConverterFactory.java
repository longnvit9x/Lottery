package neo.vn.lottery.api;


import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;


import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class NullOnEmptyConverterFactory extends Converter.Factory {
    public NullOnEmptyConverterFactory() {
    }

    public static Converter.Factory create() {
        return new NullOnEmptyConverterFactory();
    }

    @Override
    public @Nullable
    Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return (Converter<ResponseBody, Object>) body -> {
            if (body.contentLength() == 0) {
                return null;
            }
            return delegate.convert(body);
        };
    }
}