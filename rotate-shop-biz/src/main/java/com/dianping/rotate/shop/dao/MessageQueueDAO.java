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
    MessageEntity getMessageById(@DAOParam("id") int id);


    @DAOAction(action = DAOActionType.QUERY)
    List<MessageEntity> getMessage(@DAOParam("source") int source,
                                   @DAOParam("type") int type,
                                   @DAOParam("limit") int limit);

}
