package cn.muses.trade.model;

import cn.muses.trade.model.screen.AccountScreen;
import lombok.Data;

@Data
public class MemberPromotionScreen extends AccountScreen{

    private int minPromotionNum = -1;

    private int maxPromotionNum = -1;
}
