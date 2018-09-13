package com.aaa.hbase;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
//加载hbase所需的配置文件
@ConfigurationProperties(prefix = "hbase")
public class HBaseProperties {
	private Map<String, String> config;
    
    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }
}
