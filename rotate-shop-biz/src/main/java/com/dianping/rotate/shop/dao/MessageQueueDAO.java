package com.dianping.rotate.shop.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.json.MessageStatisticsEntity;

import java.util.List;

/**
 * Created by zaza on 15/1/4.
 */
public interface MessageQueueDAO extends GenericDao {

    @DAOAction(action = DAOActionType.INSERT)
    int addToMessageQueue(@DAOParam("message") MessageEntity message);

    @DAOAction(action = DAOActionType.UPDATE)
    void deleteMessage(@DAOParam("id") int id);

    @DAOAction(action = DAOActionType.DELETE)
    void deleteMessagePhysically(@DAOParam("id") int id);

    @DAOAction(action = DAOActionType.DELETE)
    void deleteMessagePhysicallyBySwallowID(@DAOParam("swallowId") String swallowId);

    @DAOAction(action = DAOActionType.LOAD)
    MessageEntity getMessageByID(@DAOParam("id") int id);

    @DAOAction(action = DAOActionType.LOAD)
    MessageEntity getMessageBySwallowID(@DAOParam("swallowId") String swallowId);


	@DAOAction(action = DAOActionType.QUERY)
	List<MessageEntity> getUnprocessedMessage(@DAOParam("source") int source,
			@DAOParam("type") int type,
			@DAOParam("maxAttemptIndex") int maxAttemptIndex,
			@DAOParam("limit") int limit);

    @DAOAction(action = DAOActionType.UPDATE)
    void updateMessageAttemptIndex(@DAOParam("id") int id,
                                   @DAOParam("attemptIndex") int attemptIndex);

    @DAOAction(action = DAOActionType.UPDATE)
    void updateMessageAttemptIndexByType(@DAOParam("type") int type,
                                         @DAOParam("source") int source,
                                         @DAOParam("attemptIndex") int attemptIndex);

    @DAOAction(action = DAOActionType.INSERT)
    void addMessageQueueToHistory();

    @DAOAction(action = DAOActionType.DELETE)
    int clearMessageQueue();

    @DAOAction(action = DAOActionType.QUERY)
    List<MessageEntity> getMessage(@DAOParam("source") int source,
                                   @DAOParam("type") int type,
                                   @DAOParam("status") int status,
                                   @DAOParam("limit") int limit,
                                   @DAOParam("offset") int offset);

    @DAOAction(action = DAOActionType.LOAD)
    int getMessageCount(@DAOParam("source") int source,
                        @DAOParam("type") int type,
                        @DAOParam("status") int status);

    @DAOAction(action = DAOActionType.QUERY)
    List<MessageStatisticsEntity> getMessageStatistics(@DAOParam("source") int source,
                                                       @DAOParam("type") int type);
}
