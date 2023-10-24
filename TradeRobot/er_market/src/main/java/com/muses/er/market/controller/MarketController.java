package com.muses.er.market.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.muses.er.market.engine.MarketEngine;
import com.muses.er.market.engine.MarketEngineFactory;
import com.muses.er.market.entity.CoinThumb;
import com.muses.er.market.utils.MessageResult;

@RestController
public class MarketController {

	@Autowired
	private MarketEngineFactory marketEngineFactory;
	
	/**
	 * 获取交易对行情
	 * @param pair
	 * @return
	 */
	@RequestMapping("thumb/{pair}")
    public MessageResult findThumb(@PathVariable String pair){
		CoinThumb thumb = marketEngineFactory.getThumbByPair(pair);
		if(thumb != null) {
			MessageResult mr = new MessageResult(0,"success");
			mr.setData(thumb);
			return mr;
		}else {
			MessageResult mr = new MessageResult(500,"未发现交易对行情");
			return mr;
		}
    }
	
	/**
	 * 获取所有行情引擎状态
	 * @return
	 */
	@RequestMapping("status")
	public MessageResult status(){
		List<JSONObject> engineStatus = marketEngineFactory.engineStatus();
		MessageResult mr = new MessageResult(0,"success");
		mr.setData(engineStatus);
		return mr;
	}
	@RequestMapping("/adfe")
	public String getAdfe(String url){
		return doGet(url);
	}

	public static String doGet(String httpUrl){
		//链接
		HttpURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		StringBuffer result = new StringBuffer();
		try {
			//创建连接
			URL url = new URL(httpUrl);
			connection = (HttpURLConnection) url.openConnection();
			//设置请求方式
			connection.setRequestMethod("GET");
			//设置连接超时时间
			connection.setReadTimeout(15000);
			//开始连接
			connection.connect();
			//获取响应数据
			if (connection.getResponseCode() == 200) {
				//获取返回的数据
				is = connection.getInputStream();
				if (null != is) {
					br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
					String temp = null;
					while (null != (temp = br.readLine())) {
						result.append(temp);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//关闭远程连接
			connection.disconnect();
		}
		return result.toString();
	}

	@RequestMapping("abcd")
	public String afaf(){
		return "不带/的测试";
	}
	
	/**
	 * 更新行情获取URL
	 * @param market
	 * @param url
	 * @return
	 */
	@RequestMapping("updateUrl")
	public MessageResult updateEngineUrl(String market, String url) {
		if(marketEngineFactory.containsEngine(market)) {
			MarketEngine engine = marketEngineFactory.getEngine(market);
			engine.updateEngineUrl(url);
			MessageResult mr = new MessageResult(0, "更新成功（最新行情URL："+url);
			return mr;
		}else {
			MessageResult mr = new MessageResult(500, "行情获取引擎不存在");
			return mr;
		}
	}
	
	/**
	 * 增加别名映射
	 * @param market
	 * @param name
	 * @param alias
	 * @return
	 */
	@RequestMapping("addMaping")
	public MessageResult addMapping(String market, String name, String alias) {
		if(marketEngineFactory.containsEngine(market)) {
			MarketEngine engine = marketEngineFactory.getEngine(market);
			engine.addAliasMapping(name, alias);
			MessageResult mr = new MessageResult(0, "新增/更新别名映射 > " + name + " - " + alias);
			return mr;
		}else {
			MessageResult mr = new MessageResult(500, "行情获取引擎不存在");
			return mr;
		}
	}
	
	/**
	 * 移除映射
	 * @param market
	 * @param name
	 * @return
	 */
	@RequestMapping("removeMaping")
	public MessageResult addMapping(String market, String name) {
		if(marketEngineFactory.containsEngine(market)) {
			MarketEngine engine = marketEngineFactory.getEngine(market);
			engine.removeAliasMapp(name);
			MessageResult mr = new MessageResult(0, "新增/更新别名映射 > " + name);
			return mr;
		}else {
			MessageResult mr = new MessageResult(500, "行情获取引擎不存在");
			return mr;
		}
	}
}
