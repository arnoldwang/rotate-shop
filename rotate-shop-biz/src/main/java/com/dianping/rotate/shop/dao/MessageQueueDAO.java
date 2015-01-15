package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.entity.MessageEntity;

import java.util.List;

/**
 * Created by zaza on 15/1/4.
 */
public interface MessageQueueDAO extends GenericDao {

    @DAOAction(action = DAOActionType.INSERT)
    void addToMessageQueue(@DAOParam("message") MessageEntity message);

    @DAOAction(action = DAOActionType.DELETE)
    void deleteMessage(@DAOParam("id") int id);

    @DAOAction(action = DAOActionType.DELETE)
    void deleteMessagePhysically(@DAOParam("id") int id);

    @DAOAction(action = DAOActionType.LOAD)
    MessageEntity getMessageByID(@DAOParam("id") int id);


	@DAOAction(action = DAOActionType.QUERY)
	List<MessageEntity> getUnproccessedMessage(@DAOParam("source") int source,
			@DAOParam("type") int type,
			@DAOParam("maxAttemptIndex") int maxAttemptIndex,
			@DAOParam("limit") int limit);

    @DAOAction(action = DAOActionType.UPDATE)
    void updateMessageAttemptIndex(@DAOParam("id") int id,
                                   @DAOParam("attemptIndex") int attemptIndex);
}
