package cn.muses.trade.model.screen;

import cn.muses.trade.ability.ScreenAbility;
import cn.muses.trade.entity.QSign;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Description:
 * @date 2019/5/315:53
 */
@Data
public class SignScreen implements ScreenAbility {

    private String unit;

    @Override
    public ArrayList<BooleanExpression> getBooleanExpressions() {
        ArrayList<BooleanExpression> booleanExpressions = new ArrayList<>();
        if (StringUtils.isNotBlank(unit)) {
            booleanExpressions.add(QSign.sign.coin.unit.eq(unit));
        }
        return booleanExpressions;
    }
}
