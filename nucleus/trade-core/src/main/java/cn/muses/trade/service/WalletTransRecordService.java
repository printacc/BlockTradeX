package cn.muses.trade.service;

import cn.muses.trade.dao.WalletTransRecordDao;
import cn.muses.trade.entity.WalletTransRecord;
import cn.muses.trade.service.Base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WalletTransRecordService extends BaseService {

    @Autowired
    private WalletTransRecordDao walletTransRecordDao;

    /**
     * 保存划转记录
     * @param record
     * @return
     */
    public WalletTransRecord save(WalletTransRecord record) {
        return walletTransRecordDao.saveAndFlush(record);
    }

}
