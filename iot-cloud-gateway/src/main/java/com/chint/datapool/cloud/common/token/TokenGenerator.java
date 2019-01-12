package com.chint.datapool.cloud.common.token;

import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName:  TokenGenerator   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wusheng
 * @date:   2019年1月12日 上午9:09:52   
 *
 */
@Component
public interface TokenGenerator {

    public String generate(String... strings);

}
