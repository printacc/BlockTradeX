package cn.muses.trade.service;

import cn.muses.trade.dao.FeedbackDao;
import cn.muses.trade.entity.Feedback;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年03月19日
 */
@Service
public class FeedbackService extends BaseService {
    @Autowired
    private FeedbackDao feedbackDao;

    public Feedback save(Feedback feedback){
        return feedbackDao.save(feedback);
    }
}
