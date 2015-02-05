package com.dianping.rotate.shop.service;

import com.dianping.rotate.shop.constants.MessageStatus;
import com.dianping.rotate.shop.dao.MessageQueueDAO;
import com.dianping.rotate.shop.json.MessageEntity;
import com.dianping.rotate.shop.model.MessageModel;
import com.dianping.rotate.shop.model.MessageProcessModel;
import com.dianping.rotate.shop.model.StatisticsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaza on 15/2/4.
 */
@Service
public class StatisticsService {
    @Autowired
    private MessageQueueDAO messageQueueDao;

    public StatisticsModel getProcessMessage(int source,int type,int status,int limit,int offset){
        List<MessageEntity> messages = messageQueueDao.getMessage(source,type,status,limit,offset);
        List<MessageModel> messageModels = new ArrayList<MessageModel>();
        for(MessageEntity message:messages){
            MessageModel messageModel = new MessageModel();
            messageModel.setId(message.getId());
            messageModel.setMsg(message.getMsg());
            messageModel.setRetry(message.getAttemptIndex());
            messageModels.add(messageModel);
        }
        return new StatisticsModel(messageModels);
    }

    public void deleteMessage(int id){
        messageQueueDao.deleteMessage(id);
    }

    public StatisticsModel getMessageStatistics(int type,int source){
        StatisticsModel statisticsModel = new StatisticsModel();
        MessageProcessModel messageProcessModel = getMessageProcessModel(type,source);
        List<MessageProcessModel> messageStatisticsList = new ArrayList<MessageProcessModel>();
        messageStatisticsList.add(messageProcessModel);
        statisticsModel.setMessageStatisticsList(messageStatisticsList);
        return statisticsModel;
    }

    public StatisticsModel getAllMessageStatistics(){
        StatisticsModel statisticsModel = new StatisticsModel();
        List<MessageProcessModel> messageStatisticsList = new ArrayList<MessageProcessModel>();
        for(int type=0;type<8;type++){
            int source =0;
            if(type==4) source = 1;
            MessageProcessModel messageProcessModel = getMessageProcessModel(type,source);
            messageStatisticsList.add(messageProcessModel);
        }
        MessageProcessModel messageProcessModel = getMessageProcessModel(5,1);
        messageStatisticsList.add(messageProcessModel);
        statisticsModel.setMessageStatisticsList(messageStatisticsList);
        return statisticsModel;
    }

    public StatisticsModel retryAllMessage(int type,int source){
        StatisticsModel statisticsModel = new StatisticsModel();
        messageQueueDao.updateMessageAttemptIndexByType(type,source,0);
        return statisticsModel;
    }

    public StatisticsModel retryMessage(int id){
        StatisticsModel statisticsModel = new StatisticsModel();
        messageQueueDao.updateMessageAttemptIndex(id,0);
        return statisticsModel;
    }

    private MessageProcessModel getMessageProcessModel(int type,int source){
        int successMessage = messageQueueDao.getMessageCount(source,type, MessageStatus.SUCCESS);
        int failMessage = messageQueueDao.getMessageCount(source,type, MessageStatus.FAIL);
        MessageProcessModel messageProcessModel = new MessageProcessModel();
        messageProcessModel.setMessageProcessSuccessTotal(successMessage);
        messageProcessModel.setMessageProcessFailTotal(failMessage);
        messageProcessModel.setMessageTotal(successMessage+failMessage);
        messageProcessModel.setMessageName(getMessageNameByType(type,source));
        messageProcessModel.setMessageSource(source);
        messageProcessModel.setMessageType(type);
        return messageProcessModel;
    }

    private String getMessageNameByType(int type,int source){
        switch (type){
            case 0:
                return "用户新增消息";
            case 1:
                return "商圈树消息";
            case 2:
                return "商户合并消息";
            case 3:
                return "商户误合并后恢复消息";
            case 4:
                return "系统批量新增POI消息";
            case 5:
                if(source == 0){
                    return "更新POI属性消息";
                }
                return "系统批量更新POI属性消息";
            case 6:
                return "分类树消息";
            case 7:
                return "商户状态变化消息";
            default:
                return "没有符合条件的消息";
        }
    }
}
