import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;

public class test {
    @Test
    public void main() throws IOException, InterruptedException {
        String res = request.post("http://139.9.221.111/api/user/login",
                "{\"phone\":\"13558622779\",\"password\":\"2d3383fa392936ad7847c50a0bb4a58e\"}",
                "{\"authorization\":\"13558622779\",\"phone\":\"13558622779\",\"password\":\"2d3383fa392936ad7847c50a0bb4a58e\"}");
        JSONObject jsonObject = JSONObject.parseObject(res);
        System.out.println(jsonObject.get("result"));
        Object result = jsonObject.get("result");
        String token = JSONObject.parseObject(result.toString()).get("token").toString();
        System.out.println(token);
        Thread.sleep(2000);
        String res2 = request.post("http://139.9.221.111/api/role/list",
                "{\"note\": \"\",\"pageNo\": 1,\"pageSize\": 10,\"roleName\": \"\"}",
                "{\"authorization\":\""+token+"\"}");
        JSONObject jsonObject1 = JSONObject.parseObject(res2);
        System.out.println(jsonObject1);
    }
}
