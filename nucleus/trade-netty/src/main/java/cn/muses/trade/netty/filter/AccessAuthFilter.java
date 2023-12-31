/*
 * Copyright (c) 2016-2017  All Rights Reserved.
 * 
 * <p>FileName: AccessAuthFilter.java</p>
 * 
 * Description: 
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年7月24日
 * @version 1.0
 * History:
 * v1.0.0, , 2020年7月24日, Create
 */
package cn.muses.trade.netty.filter;

import cn.muses.trade.core.annotation.HawkFilter;
import cn.muses.trade.core.common.constant.NettyCommands;
import cn.muses.trade.core.common.constant.NettyResponseCode;
import cn.muses.trade.core.configuration.NettyProperties;
import cn.muses.trade.core.entity.RequestPacket;
import cn.muses.trade.core.entity.ResponsePacket;
import cn.muses.trade.core.exception.NettyException;
import cn.muses.trade.core.filter.FilterChain;
import cn.muses.trade.core.filter.HFilter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Title: AccessAuthFilter</p>
 * <p>Description: </p>
 * 访问鉴权过滤器，判断sessionid是否存在，如果存在，判断是否已经登录，否则都给客户端返回登录指令
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Date 2020年7月24日
 */
//@HawkFilter(order = 2,ignoreCmds={NettyCommands.CONNECT,NettyCommands.LOGIN})
@HawkFilter(order = 2)
public class AccessAuthFilter extends HFilter {

	@Autowired
	private NettyProperties nettyProperties;
	@Override
	public void init() throws NettyException {
		
	}

	@Override
	public void doFilter(RequestPacket request, ResponsePacket response, ChannelHandlerContext ctx, FilterChain chain)
			throws  NettyException {
		Subject subject = SecurityUtils.getSubject();
		if(nettyProperties.getDirectAccessFlag()!=1
				||nettyProperties.getDirectAccessCommand()==null//启用直接访问
				||!nettyProperties.getDirectAccessCommand().contains(String.valueOf(request.getCmd()))){//允许访问的指令中包含请求的指令
			if(request.getCmd()!= NettyCommands.LOGIN
					&& subject.getPrincipal()==null){//未登录，提示登录
				Session session = subject.getSession();
				response.setSequenceId((long)session.getId());//返回序列id给客户端
				throw new NettyException(buildExceptionMsg(NettyResponseCode.NOLOGIN_ERROR.getResponseCode(),
						NettyResponseCode.NOLOGIN_ERROR.getResponseMessage()));
			}
			if(request.getCmd()==NettyCommands.LOGIN){//针对登录请求，单独处理一次sessionId返回
				subject.getPrincipal();//执行这一步才能获取新session
				Session session = subject.getSession();
				request.setSequenceId((long)session.getId());
				response.setSequenceId((long)session.getId());//返回序列id给客户端
			}
			chain.doFilter(request, response, ctx);
		}

		
	}
	@Override
	public void destroy() {
		
	}


}
