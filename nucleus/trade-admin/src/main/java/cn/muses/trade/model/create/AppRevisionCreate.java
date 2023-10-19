package cn.muses.trade.model.create;

import cn.muses.trade.ability.CreateAbility;
import cn.muses.trade.constant.Platform;
import cn.muses.trade.entity.AppRevision;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Title: ${file_name}
 * @Description:
 * @date 2019/4/2416:33
 */
@Data
public class AppRevisionCreate implements CreateAbility<AppRevision> {

    private String remark;

    @NotBlank
    private String version;

    private String downloadUrl;

    @NotNull
    private Platform platform;

    //转化
    @Override
    public AppRevision transformation() {
        AppRevision appRevision = new AppRevision();
        appRevision.setRemark(this.remark);
        appRevision.setVersion(this.version);
        appRevision.setDownloadUrl(this.downloadUrl);
        appRevision.setPlatform(this.platform);
        return appRevision;
    }
}
