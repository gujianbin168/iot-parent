package com.chint.datapool.cloud.common.token;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;

/**
 * 
 * @ClassName:  Md5TokenGenerator   
 * @Description:token加密  
 * @author: wusheng
 * @date:   2019年1月12日 上午9:11:20   
 *
 */
@Component
public class Md5TokenGenerator implements TokenGenerator {

    @Override
    public String generate(String... strings) {
        long   timestamp = System.currentTimeMillis();
        String tokenMeta = "";
        for (String s : strings) {
            tokenMeta = tokenMeta + s;
        }
        tokenMeta = tokenMeta + timestamp;
        String token = DigestUtils.md5DigestAsHex(tokenMeta.getBytes());
        return token;
    }
}
