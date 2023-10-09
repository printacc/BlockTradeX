package cn.muses.trade.socket.client;

import cn.muses.trade.service.KlineRobotMarketService;
import cn.muses.trade.socket.ws.WebSocketHuobi;
import cn.muses.trade.util.WebSocketConnectionManage;

import java.net.URI;
import java.net.URISyntaxException;

public class WsClientHuobi {


    public WsClientHuobi() {}
    private KlineRobotMarketService marketService;

    public void run() {

        try {
            // 国内不被墙的地址/wss://api.huobi.pro/ws   ws://api.huobi.br.com:443/ws  wss://api.huobiasia.vip/ws
            URI uri = new URI("wss://api.huobi.pro/ws");
            WebSocketHuobi ws = new WebSocketHuobi(uri,  marketService);
            WebSocketConnectionManage.setWebSocket(ws);
            WebSocketConnectionManage.getClient().connect(ws);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void setContractMarketService(KlineRobotMarketService service) { this.marketService = service; }
}
