package cn.muses.trade.model.create;

import cn.muses.trade.ability.CreateAbility;
import cn.muses.trade.entity.DataDictionary;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Title: ${file_name}
 * @Description:
 * @date 2019/4/1214:24
 */
@Data
public class DataDictionaryCreate implements CreateAbility<DataDictionary> {
    @NotBlank
    private String bond;
    @NotBlank
    private String value;
    private String comment;

    @Override
    public DataDictionary transformation() {
        return new DataDictionary(bond, value, comment);
    }
}
