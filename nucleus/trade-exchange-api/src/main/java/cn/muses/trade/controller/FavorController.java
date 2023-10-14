package cn.muses.trade.controller;

import cn.muses.trade.entity.FavorSymbol;
import cn.muses.trade.entity.transform.AuthMember;
import cn.muses.trade.service.FavorSymbolService;
import cn.muses.trade.service.LocaleMessageSourceService;
import cn.muses.trade.util.MessageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

import static cn.muses.trade.constant.SysConstant.SESSION_MEMBER;


@Slf4j
@RestController
@RequestMapping("/favor")
public class FavorController {
    @Autowired
    private FavorSymbolService favorSymbolService;

    @Autowired
    private LocaleMessageSourceService msService;

    /**
     * 添加自选
     * @param member
     * @param symbol
     * @return
     */
    @RequestMapping("add")
    public MessageResult addFavor(@SessionAttribute(SESSION_MEMBER) AuthMember member, String symbol){
        if(StringUtils.isEmpty(symbol)){
            return MessageResult.error(500,msService.getMessage("SYMBOL_CANNOT_BE_EMPTY"));
        }
        FavorSymbol favorSymbol = favorSymbolService.findByMemberIdAndSymbol(member.getId(),symbol);
        if(favorSymbol != null){
            return MessageResult.error(500,msService.getMessage("SYMBOL_ALREADY_FAVORED"));
        }
        FavorSymbol favor =  favorSymbolService.add(member.getId(),symbol);
        if(favor!= null){
            return MessageResult.success(msService.getMessage("EXAPI_SUCCESS"));
        }
        return MessageResult.error(msService.getMessage("EXAPI_ERROR"));
    }

    /**
     * 查询当前用户自选
     * @param member
     * @return
     */
    @RequestMapping("find")
    public List<FavorSymbol> findFavor(@SessionAttribute(SESSION_MEMBER) AuthMember member){
        return favorSymbolService.findByMemberId(member.getId());
    }

    /**
     * 删除自选
     * @param member
     * @param symbol
     * @return
     */
    @RequestMapping("delete")
    public MessageResult deleteFavor(@SessionAttribute(SESSION_MEMBER) AuthMember member,String symbol){
        if(StringUtils.isEmpty(symbol)){
            return MessageResult.error(msService.getMessage("SYMBOL_CANNOT_BE_EMPTY"));
        }
        FavorSymbol favorSymbol = favorSymbolService.findByMemberIdAndSymbol(member.getId(),symbol);
        if(favorSymbol == null){
            return MessageResult.error(msService.getMessage("FAVOR_NOT_EXISTS"));
        }
        favorSymbolService.delete(member.getId(),symbol);
        return MessageResult.success(msService.getMessage("EXAPI_SUCCESS"));
    }
}
