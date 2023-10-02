package cn.muses.trade.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
public enum AdminModule {
    CMS("CMS"),
    COMMON("COMMON"),
    EXCHANGE("EXCHANGE"),
    FINANCE("FINANCE"),
    MEMBER("MEMBER"),
    OTC("OTC"),
    SYSTEM("SYSTEM"),
    PROMOTION("PROMOTION"),
    INDEX("INDEX"),
	ACTIVITY("ACTIVITY"),
	CTC("CTC"),
	REDENVELOPE("REDENVELOPE"),
    CONTRACTOPTION("CONTRACTOPTION");
	
    @Setter
    private String title;
}