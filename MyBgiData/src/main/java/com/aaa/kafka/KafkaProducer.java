package com.aaa.kafka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.alibaba.fastjson.JSON;

@Component
@EnableScheduling
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;//kafka模版对象
    
    /**
     * 读取测试文件数据，返回到list中
     * */
    public List<String>  sendMessage() {
    	List<String> myList = new ArrayList<String>();
    	File file = new File("C:\\f1.txt");//测试数据保存在c盘
    	BufferedReader br = null ;
    	String message = null;
			 try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			 try {
				 while((message=br.readLine())!=null) {
					 myList.add(message);
				 }
			} catch (IOException e) {
				e.printStackTrace();
			}
    	
    	return myList;
    }

    /**
     * 定时任务，每一秒执行一次
     */
    @Scheduled(cron = "00/30 * * * * ?")
    public void send(){
        //String message = UUID.randomUUID().toString();
    	Date date= new Date();
    	//模拟发送json日志
    	List<String> sendMessage = sendMessage();
    	for(String message:sendMessage) {
    		System.out.println("hahaha:"+message);
    		//发送到input topic
    		ListenableFuture future = kafkaTemplate.send("input", message);
    		//添加回调处理，两个参数（SuccessCallback<? super T> var1, FailureCallback var2），成功（successCallback），失败（FailureCallback）
    		future.addCallback(o->System.out.println("send-消息发送成功：" + message),throwable->System.out.println("消息发送失败：" + message));
    		
    	}
   
    }
    

}
