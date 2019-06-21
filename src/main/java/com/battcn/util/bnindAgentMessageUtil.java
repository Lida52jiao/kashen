package com.battcn.util;

import com.battcn.constant.InstitutionIdNumber;

import java.util.HashMap;
import java.util.Map;

public class bnindAgentMessageUtil {

    public static String openAgent(String phone){
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("institutionId", InstitutionIdNumber.AGENT);
		map.put("appId", "0000");
		map.put("phone", phone);
		map.put("type", "14");
		String url = "http://47.104.25.59/templet/Tongzhi/sends.shtml";
		String result=new HttpClientUtils().doPost(url,map);
		return result;
    }

}
