
package cn.muses.trade.netty.push;

import com.google.protobuf.MessageLite;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;

import java.util.Map;
import java.util.Set;

/**
 * <p>Title: HawkPushServiceApi</p>
 * <p>Description: </p>
 * 消息推送接口
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年8月8日
 */
public interface HawkPushServiceApi {
    /**
     *
     * <p>Title: pushMsg</p>
     * <p>Description: </p>
     * 推送文本消息到客户端
     */
    void pushMsg(Set<Channel> channels, short cmd, String msg);

    /**
     *
     * <p>Title: pushMsg</p>
     * <p>Description: </p>
     * 推送字节消息到客户端
     */
    void pushMsg(Set<Channel> channels, short cmd, byte[] msg);

    /**
     *
     * <p>Title: pushMsg</p>
     * <p>Description: </p>
     * 推送protobuffer消息到客户端
     */
    void pushMsg(Set<Channel> channels, short cmd, MessageLite msg);

    /**
     *
     * <p>Title: pushMsg</p>
     * <p>Description: </p>
     * 推送文本消息到客户端
     */
    Map<String,ChannelPromise> syncPushMsg(Set<Channel> channels, short cmd, String msg);

    /**
     *
     * <p>Title: pushMsg</p>
     * <p>Description: </p>
     * 推送字节消息到客户端
     */
    Map<String,ChannelPromise> syncPushMsg(Set<Channel> channels, short cmd, byte[] msg);

    /**
     *
     * <p>Title: pushMsg</p>
     * <p>Description: </p>
     * 推送protobuffer消息到客户端
     */
    Map<String,ChannelPromise> syncPushMsg(Set<Channel> channels, short cmd, MessageLite msg);
}
