package com.battcn.constant;

public class Constaint {
	/**
	 * 成功
	 */
	public static final String SUCCESS = "0000";
	/**
	 * 错误
	 */
	public static final String SERVER_ERROR = "0001";
	/**
	 * 商户未认证
	 */
	public static final String NOT_AUTHENTICATION = "0002";
	/**
	 * 没有此商户
	 */
	public static final String NONE_MERCHAT = "0003";
	/**
	 * 无此代理商
	 */
	public static final String NONE_AGENT = "0004";
	/**
	 * 登录超时
	 */
	public static final String INVALID= "0005";
	/**
	 * 激活码不足
	 */
	public static final String NOT_CODE = "0006";
	/**
	 * 初始化错误
	 */
	public static final String FAIL_INIT = "0007";
	/**
	 * 芝麻认证失败了
	 */
	public static final String FAIL_ZHIMA = "0008";
	/**
	 * 查询失败了
	 */
	public static final String FAIL_FOUND = "0009";
	/**
	 * 激活失败
	 */
	public static final String FAIL = "0010";
	/**
	 * 上传失败
	 */
	public static final String FAIL_UPLOAD = "0011";
	/**
	 * 此商户不是会员
	 */
	public static final String NOT_MEMBER = "0012";
	
	/**
	 * 参数异常
	 */
	public static final String INVILIDPARAM = "0013";
	
	/**
	 * 没有上级推荐人
	 */
	public static final String NONE_ONEMERID = "0014";
	/**
	 * 激活码已用
	 */
	public static final String USED = "0015";
	/**
	 * 没有激活码记录
	 */
	public static final String NONE_RECORD = "0016";
	/**
	 * 无结算底价
	 */
	public static final String NONE_FLOOR = "0017";
	/**
	 * 卡已存在
	 */
	public static final String CARDS = "0019";
	/**
	 * 卡不存在
	 */
	public static final String NOTNE_CARDS = "0021";
	/**
	 * 卡在用
	 */
	public static final String NOT_DEL = "0022";
	
	public static final String  HOST= "http://47.104.110.70/yj-api/plan/findPlan";
	
	public static final String  DuanXin= "http://47.104.25.59/templet/DuanXin/getDuanXin.shtml";
	
	public static final String  JiGuang= "http://47.104.25.59/templet/JiGuang/selectJiGuang.shtml";
	
	public static final String  RenZhen= "http://47.104.25.59/templet/RenZhen/getRenZhen.shtml";
	
	public static final String  F= "http://47.104.4.155/yj-api/withdraw/withdrawFail";
	
	public static final String  BuDan = "http://47.104.4.155/yj-api/pay/anewRepayment";
	
	public static final String APPKEY = "83429a5c40bf17d664db88b4";

	public static final String Secret = "f410c6ac944c3a6efa1ecf70";
	public static final String BASE64ENCODE = "OTk0ODViYTUxMDkxMjFiYjAxMzk1YzMyOjZmMmIwYTBiNWEzYjcyMTEwNzE0YjE1YQ==";
	public static final String PUSHURL = "https://api.jpush.cn/v3/push";
	public static final String DEVICE = "https://api.jpush.cn/v3/devices/";

	public static final String AGENTID = "2000000403";

	// 接口密钥

	public static final String SECRETID = "abcd123456";

	// 初始化ascClient需要的几个参数

	public static final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）

	public static final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）

	// 替换成你的AK

	public static final String accessKeyId = "LTAI8iG0n3PN3xhH";// 你的accessKeyId,参考本文档步骤2

	public static final String accessKeySecret = "PRMGZsz2Lg1pSwymPg03uk7L1K9OyE";// 你的accessKeySecret，参考本文档步骤2
	
	public static final String AGENT = "T00000009";

	public static final String  BIND= "http://47.104.25.59:1183/sendMessage/selectLists";

	//通道顺序控制

	public static final String AisleSet = "http://47.104.161.254:1003/common/aisle/find";

	public static final String AisleSetEdit = "http://47.104.161.254:1003/common/aisle/update";

	//信用卡网申配置

    public static final String WsSet= "http://47.104.198.186:1010/ws/updateConfig";

    public static final String WsList = "http://47.104.198.186:1010/ws/findConfig";

    public static final String WsFind = "http://47.104.198.186:1010/ws/find";

}
