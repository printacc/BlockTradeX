package cn.muses.trade.service;

import cn.muses.trade.dao.MemberApiKeyDao;
import cn.muses.trade.entity.MemberApiKey;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: MemberApiKeyService
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @create: 2019/05/07 10:40
 */
@Service
public class MemberApiKeyService extends BaseService<MemberApiKey> {

    @Autowired
    private MemberApiKeyDao apiKeyDao ;

    public MemberApiKey findMemberApiKeyByApiKey(String apiKey) {
        return apiKeyDao.findMemberApiKeyByApiKey(apiKey);
    }

    public MemberApiKey findByMemberIdAndId(Long memberId,Long id){
        return apiKeyDao.findMemberApiKeyByMemberIdAndId(memberId,id);
    }

    public MemberApiKey save(MemberApiKey memberApiKey){
        return apiKeyDao.save(memberApiKey);
    }


    public void del(Long id){
        apiKeyDao.del(id);
    }

    public List<MemberApiKey> findAllByMemberId(Long memberId){
        return apiKeyDao.findAllByMemberId(memberId);
    }
}
