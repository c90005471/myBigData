package com.aaa.mr;

/**
 *  * 用来描述一个特定的作业
 * 比如，该作业使用哪个类作为逻辑处理中的map，哪个作为reduce
 * 还可以指定该作业要处理的数据所在的路径
 * 还可以指定改作业输出的结果放到哪个路径
 * @Author: 陈建
 * @Date: 2018/8/20 0020 10:49
 * @Version 1.0
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WCRunner {

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		// 也可以在代码中对conf中的配置信息进行手动设置，会覆盖掉配置文件中的读取的值
		conf.set("fs.defaultFS", "hdfs://master:9000/");
		Job wcjob = Job.getInstance(conf);

		// 设置整个job所用的那些类在哪个jar包
		wcjob.setJarByClass(WCRunner.class);

		// 本job使用的mapper和reducer的类
		wcjob.setMapperClass(WCMapper.class);
		wcjob.setReducerClass(WCReducer.class);

		// 指定reduce的输出数据kv类型
		wcjob.setOutputKeyClass(Text.class);
		wcjob.setOutputValueClass(LongWritable.class);

		// 指定mapper的输出数据kv类型
		wcjob.setMapOutputKeyClass(Text.class);
		wcjob.setMapOutputValueClass(LongWritable.class);

		// 指定要处理的输入数据存放路径
		FileInputFormat.setInputPaths(wcjob, new Path("/tmp/wordcount/input/"));

		// 指定处理结果的输出数据存放路径
		FileOutputFormat.setOutputPath(wcjob, new Path("/tmp/wordcount/output"));

		// 将job提交给集群运行
		wcjob.waitForCompletion(true);

	}
}
