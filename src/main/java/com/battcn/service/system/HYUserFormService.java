package com.battcn.service.system;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.HYUserForm;
import com.battcn.entity.HYUserFormExample;
import com.battcn.entity.HYUserIncomCount;
import com.battcn.entity.HYUserWithdrawCount;
import com.battcn.mapper.HYUserFormMapper;

@Service
public class HYUserFormService {
	
//	@Autowired
//	private HYUserFormMapper hyUserFormMapper;
	@Autowired
	private HYInstitutionFormService hyInstitutionFormService;
	
//	public HYUserIncomCount getUserCount(String searchstartTime,String searchendTime) throws ParseException{
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
//		SimpleDateFormat sdfo = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date d=sdfo.parse(calendar.getTime()+"");
//		long longStartDate = d.getTime();//当前月份一号0点的毫秒值（不选时间默认值）
//		Date endTime = new Date();
//		long longEndDate = endTime.getTime();//当前月份今日当前时间的毫秒值（不选时间默认值）
//		//传入时间
//		long startSeconds = 0;
//		long finishSeconds = 0;
//		if(!StringUtils.isBlank(searchstartTime)){
//			 startSeconds = sdf.parse(searchstartTime+" 00:00:00").getTime();//毫秒
//		}
//		if(!StringUtils.isBlank(searchendTime)){
//			finishSeconds = sdf.parse(searchendTime+" 23:59:59").getTime();//毫秒
//		}
//		HYUserFormExample example = new HYUserFormExample();
//		HYUserFormExample.Criteria cri = new HYUserFormExample().createCriteria();
//		if(!StringUtils.isBlank(searchstartTime) ){//如果开始搜索时间为不为空，转换
//			cri.andRegdateGreaterThanOrEqualTo(String.valueOf(startSeconds));
//		} else if (StringUtils.isBlank(searchstartTime) && StringUtils.isBlank(searchendTime)){//如果开始、结束都为空给开始默认值
//			cri.andRegdateGreaterThanOrEqualTo(String.valueOf(longStartDate));
//		}
//		if(!StringUtils.isBlank(searchendTime)){
//			cri.andRegdateLessThanOrEqualTo(String.valueOf(finishSeconds));
//		} else if(StringUtils.isBlank(searchstartTime) && StringUtils.isBlank(searchendTime)){//如果开始、结束都为空给结束默认值
//			cri.andRegdateLessThanOrEqualTo(String.valueOf(longEndDate));
//		}
//		example.or(cri);
//		List<HYUserForm> one = hyUserFormMapper.selectByExample(example);
//		List<BigDecimal> interfaceList = new ArrayList<BigDecimal>();//接口查询出平台收益
//		if(StringUtils.isBlank(searchstartTime) && StringUtils.isBlank(searchendTime)){//如果开始、结束时间都是空
//			List<BigDecimal> listTimek = hyInstitutionFormService.hyIncomCount(String.valueOf(longStartDate), String.valueOf(longEndDate));
//			interfaceList.addAll(listTimek);
//		} else {
//			List<BigDecimal> listTimeNotK = hyInstitutionFormService.hyIncomCount(String.valueOf(startSeconds), String.valueOf(finishSeconds));
//			interfaceList.addAll(listTimeNotK);
//		}
//		Integer regCount = one.size();//这个时间段注册的人数
//		Integer notReal = 0;//未实名用户
//		Integer realNameCount = 0; //实名用户
//		Integer experienceCount = 0;//小咖用户
//		Integer lifeCount = 0;//大咖用户
//		Integer agentCount = 0;//代理
//		Integer yunYingCount = 0;//运营商
//		for(HYUserForm form:one){
//			if(StringUtils.isBlank(form.getMername())){
//				notReal++;
//			} else {
//				realNameCount++;
//			}
//			if(form.getMertype().contains("1")){
//				experienceCount++;
//			} else {
//				lifeCount++;
//			}
//			if(form.getAgentstatus().equals("1")){
//				agentCount++;
//			}
//			if(form.getAgentstatus().equals("2")){
//				yunYingCount++;
//			}
//		}
//		List<Integer> intList = new ArrayList<Integer>();
//		intList.add(regCount);
//		intList.add(notReal);
//		intList.add(realNameCount);
//		intList.add(experienceCount);
//		intList.add(lifeCount);
//		intList.add(agentCount);
//		intList.add(yunYingCount);
//	//	Integer [] intCount = {regCount,notReal,realNameCount,experienceCount,lifeCount,agentCount};//存放各统计数据
//	//	String strCount= Arrays.toString(intCount);
//		HYUserIncomCount all = new HYUserIncomCount();
//		all.setIntList(intList);
//		all.setBigList(interfaceList);
//		return all;
//	}
//
//	public HYUserWithdrawCount getUserFenRunTiXCount(String searchstartTime,String searchendTime) throws ParseException{
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
//		SimpleDateFormat sdfo = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date d=sdfo.parse(calendar.getTime()+"");
//		long longStartDate = d.getTime();//当前月份一号0点的毫秒值（不选时间默认值）
//		Date endTime = new Date();
//		long longEndDate = endTime.getTime();//当前月份今日当前时间的毫秒值（不选时间默认值）
//		//传入时间
//		long startSeconds = 0;
//		long finishSeconds = 0;
//		if(!StringUtils.isBlank(searchstartTime)){
//			 startSeconds = sdf.parse(searchstartTime+" 00:00:00").getTime();//毫秒
//		}
//		if(!StringUtils.isBlank(searchendTime)){
//			finishSeconds = sdf.parse(searchendTime+" 23:59:59").getTime();//毫秒
//		}
//		if(StringUtils.isBlank(searchstartTime) && StringUtils.isBlank(searchendTime)){//如果开始、结束时间都是空
//			HYUserWithdrawCount count = hyInstitutionFormService.hyUserCount(String.valueOf(longStartDate), String.valueOf(longEndDate));
//			return count;
//		}
//		HYUserWithdrawCount count = hyInstitutionFormService.hyUserCount(String.valueOf(startSeconds), String.valueOf(finishSeconds));
//		return count;
//	}
}
