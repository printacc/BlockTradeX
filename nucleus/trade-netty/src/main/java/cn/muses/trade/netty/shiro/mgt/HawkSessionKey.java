/**
 * Copyright (c) 2016-2017  All Rights Reserved.
 * 
 * <p>FileName: HawkSessionKey.java</p>
 * 
 * Description: 
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年7月25日
 * @version 1.0
 * History:
 * v1.0.0, , 2020年7月25日, Create
 */
package cn.muses.trade.netty.shiro.mgt;

import cn.muses.trade.core.entity.RequestPacket;
import cn.muses.trade.core.entity.ResponsePacket;
import cn.muses.trade.netty.shiro.util.RequestPairSource;
import org.apache.shiro.session.mgt.DefaultSessionKey;

import java.io.Serializable;

/**
 * <p>Title: HawkSessionKey</p>
 * <p>Description: </p>
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年7月25日
 */
@SuppressWarnings("serial")
public class HawkSessionKey extends DefaultSessionKey implements RequestPairSource {
	private final RequestPacket requestPacket;
    private final ResponsePacket responsePacket;

    public HawkSessionKey(RequestPacket request, ResponsePacket response) {
        if (request == null) {
            throw new NullPointerException("request argument cannot be null.");
        }
        if (response == null) {
            throw new NullPointerException("response argument cannot be null.");
        }
        this.requestPacket = request;
        this.responsePacket = response;
    }

    public HawkSessionKey(Serializable sessionId, RequestPacket request, ResponsePacket response) {
        this(request, response);
        setSessionId(sessionId);
    }
    @Override
	public RequestPacket getHawkRequest() {
		return requestPacket;
	}
    @Override
	public ResponsePacket getHawkResponse() {
		return responsePacket;
	}

	

}
