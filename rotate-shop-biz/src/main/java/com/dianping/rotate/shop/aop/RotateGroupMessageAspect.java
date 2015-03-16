package com.dianping.rotate.shop.aop;

import com.dianping.avatar.dao.DAOMethod;
import com.dianping.rotate.shop.json.RotateGroupEntity;
import com.dianping.rotate.shop.producer.RotateGroupMessageProducer;
import com.dianping.rotate.shop.utils.JsonUtil;
import com.google.common.collect.Lists;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaza on 15/3/11.
 */
@Aspect
@Component
public class RotateGroupMessageAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("rotateGroupMessageProducer")
    RotateGroupMessageProducer producer;

    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeInsert(*))&&args(daoMethod)")
    public void doAfterInsert(DAOMethod daoMethod){
        if(daoMethod.getName().equals("addToRotateGroup")){
            try{
                List<Integer> newValues = Lists.newArrayList();
                newValues.add((Integer)daoMethod.getParams().get("id"));
                producer.send(getMessage("insert",new ArrayList<Integer>(),newValues));
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
    }


    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeUpdate(*))&&args(daoMethod)")
    public void doAfterUpdate(DAOMethod daoMethod){
        if(daoMethod.getName().equals("restoreRotateGroup")){
            try{
                List<Integer> newValues = Lists.newArrayList();
                newValues.add((Integer)daoMethod.getParams().get("id"));
                producer.send(getMessage("update",new ArrayList<Integer>(),newValues));
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
        if(daoMethod.getName().equals("updateRotateGroup")){
            try{
                List<Integer> newValues = Lists.newArrayList();
                newValues.add(((RotateGroupEntity)daoMethod.getParams().get("rotateGroup")).getId());
                producer.send(getMessage("update",new ArrayList<Integer>(),newValues));
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
        if(daoMethod.getName().equals("updateRotateGroupTypeByID")){
            try{
                List<Integer> newValues = Lists.newArrayList();
                newValues.add((Integer)daoMethod.getParams().get("id"));
                producer.send(getMessage("update",new ArrayList<Integer>(),newValues));
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
    }


    @AfterReturning(value = "execution(* com.dianping.avatar.dao.ibatis.IBatisGenericDaoImpl.executeUpdate(*))&&args(daoMethod)")
    public void doAfterDelete(DAOMethod daoMethod){
        if(daoMethod.getName().equals("deleteRotateGroup")){
            try{
                List<Integer> oldValues = Lists.newArrayList();
                oldValues.add((Integer)daoMethod.getParams().get("id"));
                producer.send(getMessage("delete",oldValues,new ArrayList<Integer>()));
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
        if(daoMethod.getName().equals("deleteRotateGroupBatch")){
            try{
                List<Integer> oldValues = Lists.newArrayList();
                oldValues.addAll((List<Integer>)daoMethod.getParams().get("rotateGroupIDList"));
                producer.send(getMessage("delete",oldValues,new ArrayList<Integer>()));
            }catch(Exception ex){
                logger.error(ex.getMessage());
            }
        }
    }

    private String getMessage(String type,List<Integer> oldIds,List<Integer> newIds) throws IOException{
        Message msg = new Message();
        msg.setType(type);
        List<Value> oldValues = new ArrayList<Value>();
        for(Integer id:oldIds){
            oldValues.add(new Value(id));
        }
        List<Value> newValues = new ArrayList<Value>();
        for(Integer id:newIds){
            newValues.add(new Value(id));
        }
        msg.setNewValues(newValues);
        msg.setOldValues(oldValues);
        return JsonUtil.toStr(msg);
    }

    class Message{
        String type;
        List<Value> oldValues;
        List<Value> newValues;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Value> getOldValues() {
            return oldValues;
        }

        public void setOldValues(List<Value> oldValues) {
            this.oldValues = oldValues;
        }

        public List<Value> getNewValues() {
            return newValues;
        }

        public void setNewValues(List<Value> newValues) {
            this.newValues = newValues;
        }
    }

    class Value{
        Integer rotateGroupId;

        Value(Integer rotateGroupId){
            this.rotateGroupId = rotateGroupId;
        }

        public Integer getRotateGroupId() {
            return rotateGroupId;
        }

        public void setRotateGroupId(Integer rotateGroupId) {
            this.rotateGroupId = rotateGroupId;
        }
    }


}
