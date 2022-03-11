import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import java.io.IOException;
import java.util.Map;

public class request {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String post(String url, String data, String ... headers) throws IOException {
        RequestBody body = RequestBody.create(JSON, data);
        if (!headers.equals("") && headers != null){
            Headers.Builder headers_builder =  new Headers.Builder();
            JSONObject o = JSONObject.parseObject(headers[0]);
            for (Map.Entry<String, Object> entry:o.entrySet()){
//                System.out.println(entry.getKey()+"==============="+entry.getValue());
                headers_builder.add(entry.getKey(),entry.getValue().toString());
            }
            Headers header = headers_builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .headers(header)
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
            else {
                throw new IOException("Unexpected code " + response);
            }
//            System.out.println(o.getString("authorization"));
//            JSONObject jsonObject = JSONObject.parseObject(headers.toString());
//            for(String key: jsonObject.keySet()){//通过keySet()方法得到key的值，然后获取value
//                headers_builder.add(key, Objects.requireNonNull(jsonObject.get(key)));
//                System.out.println(key+"\n"+jsonObject.get(key));
//            }
//            Headers build = headers_builder.build();
//            System.out.println(headers.toString());
        }
        return null;
    }
}
