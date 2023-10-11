/**
 * Copyright (c) 2016-2017  All Rights Reserved.
 * 
 * <p>FileName: HawkSessionContext.java</p>
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
import org.apache.shiro.session.mgt.SessionContext;

/**
 * <p>Title: HawkSessionContext</p>
 * <p>Description: </p>
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年7月25日
 */
public interface HawkSessionContext extends SessionContext, RequestPairSource {
	 RequestPacket getHawkRequest();
	 void setHawkRequest(RequestPacket request);
	 ResponsePacket getHawkResponse();
	 void setHawkResponse(ResponsePacket response);
}
