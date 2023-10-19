package cn.muses.trade.model.update;

import cn.muses.trade.ability.UpdateAbility;
import cn.muses.trade.constant.Platform;
import cn.muses.trade.entity.AppRevision;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Title: ${file_name}
 * @Description:
 * @date 2019/4/2416:48
 */
@Data
public class AppRevisionUpdate implements UpdateAbility<AppRevision> {

    private String remark;

    private String version;

    private String downloadUrl;

    private Platform platform;

    //转化
    @Override
    public AppRevision transformation(AppRevision appRevision) {
        if (StringUtils.isNotBlank(remark)) {
            appRevision.setRemark(remark);
        }
        if (StringUtils.isNotBlank(version)) {
            appRevision.setVersion(version);
        }
        if (StringUtils.isNotBlank(downloadUrl)) {
            appRevision.setDownloadUrl(downloadUrl);
        }
        if (platform != null) {
            appRevision.setPlatform(platform);
        }
        return appRevision;
    }


}
