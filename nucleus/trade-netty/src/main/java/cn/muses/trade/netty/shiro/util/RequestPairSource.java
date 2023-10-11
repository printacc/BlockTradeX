/**
 * Copyright (c) 2016-2017  All Rights Reserved.
 * 
 * <p>FileName: RequestPairSource.java</p>
 * 
 * Description: 
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年7月25日
 * @version 1.0
 * History:
 * v1.0.0, , 2020年7月25日, Create
 */
package cn.muses.trade.netty.shiro.util;


import cn.muses.trade.core.entity.RequestPacket;
import cn.muses.trade.core.entity.ResponsePacket;

/**
 * <p>Title: RequestPairSource</p>
 * <p>Description: </p>
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年7月25日
 */
public interface RequestPairSource {

	RequestPacket getHawkRequest();

	ResponsePacket getHawkResponse();
}
