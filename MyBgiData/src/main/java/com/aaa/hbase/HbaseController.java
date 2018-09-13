package com.aaa.hbase;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HbaseController {
	@Autowired
	private HbaseTemplate hbaseTemplate;

	@RequestMapping("/hbaseTest")
	public void hbaseTest() {
		System.out.println("fsafdsfsf");
		// hbaseTemplate.delete("tbl_chenjian", "0001", "id");//删除一行
		// 查询一个字段的值
		Object object = hbaseTemplate.get("tbl_chenjian", "0001", "id", new RowMapper<String>() {
			public String mapRow(Result result, int rowNum) throws Exception {
				return Bytes.toString(result.value());
			}
		});
		System.out.println(object);
	}
}
