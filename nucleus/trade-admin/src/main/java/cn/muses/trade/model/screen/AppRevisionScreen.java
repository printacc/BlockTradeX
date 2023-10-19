package cn.muses.trade.model.screen;

import cn.muses.trade.ability.ScreenAbility;
import cn.muses.trade.constant.Platform;
import cn.muses.trade.entity.QAppRevision;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Title: ${file_name}
 * @Description:
 * @date 2019/4/2417:20
 */
@Data
public class AppRevisionScreen implements ScreenAbility {

    private String version;

    private Platform platform;

    @Override
    public ArrayList<BooleanExpression> getBooleanExpressions() {
        ArrayList<BooleanExpression> booleanExpressions = new ArrayList<>();
        if (StringUtils.isNotBlank(version)) {
            booleanExpressions.add(QAppRevision.appRevision.version.eq(version));
        }
        if (platform != null) {
            booleanExpressions.add(QAppRevision.appRevision.platform.eq(platform));
        }
        return booleanExpressions;
    }

}
