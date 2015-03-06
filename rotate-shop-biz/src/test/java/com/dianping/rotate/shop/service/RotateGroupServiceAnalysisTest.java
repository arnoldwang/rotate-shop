package com.dianping.rotate.shop.service;

import com.dianping.rotate.shop.AbstractTest;
import com.dianping.rotate.shop.api.RotateGroupService;
import com.dianping.rotate.shop.api.RotateGroupShopService;
import com.dianping.rotate.shop.dao.RotateGroupDAO;
import com.dianping.rotate.shop.dto.RotateGroupShopDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zaza on 15/3/4.
 */
public class RotateGroupServiceAnalysisTest extends AbstractTest {
    @Autowired
    @Qualifier("RotateGroupService")
    RotateGroupService rotateGroupService;
    @Autowired
    @Qualifier("RotateGroupShopService")
    RotateGroupShopService rotateGroupShopService;

    @Autowired
    RotateGroupDAO rotateGroupDAO;

    @Test
    public void getRotateGroupWithCooperationStatusTest(){
        int count =0;
        for(int rotateGroupId=1;;rotateGroupId++){
            if(rotateGroupDAO.getRotateGroup(rotateGroupId)!=null){
                rotateGroupService.getRotateGroupWithCooperationStatus(rotateGroupId);
                count++;
            }
            if(count>10000){
                return;
            }
        }
    }

    @Test
    public void getRotateGroup_List_Test(){
        List<Integer> rgIds = new ArrayList<Integer>();
        for(int rgId=10000;rgId<10101;rgId++){
            rgIds.add(rgId);
        }
        for(int i=0;i<9901;i++){
            rotateGroupService.getRotateGroup(rgIds);
        }
    }

    @Test
    public void getRotateGroup_Int_Int_Test(){
        for(int shopId=3202243;shopId<3212243;shopId++){
            rotateGroupService.getRotateGroup(101,shopId);
        }
    }

    @Test
    public void getRotateGroup_Int_Test(){
        int count =0;
        for(int rotateGroupId=1;;rotateGroupId++){
            if(rotateGroupDAO.getRotateGroup(rotateGroupId)!=null){
                rotateGroupService.getRotateGroup(rotateGroupId);
                count++;
            }
            if(count>10000){
                return;
            }
        }
    }

    @Test
    public void getRotateGroupWithCustomerStatusTest(){
        int count =0;
        for(int rotateGroupId=1;;rotateGroupId++){
            if(rotateGroupDAO.getRotateGroup(rotateGroupId)!=null){
                rotateGroupService.getRotateGroupWithCustomerStatus(rotateGroupId);
                count++;
            }
            if(count>10000){
                return;
            }
        }
    }

    @Test
    public void getRotateGroupShop_Int_Test(){
        int count =0;
        for(int rotateGroupId=1;;rotateGroupId++){
            if(rotateGroupDAO.getRotateGroup(rotateGroupId)!=null){
                rotateGroupShopService.getRotateGroupShop(rotateGroupId);
                count++;
            }
            if(count>10000){
                return;
            }
        }
    }

    @Test
    public void getRotateGroupShop_List_Test(){
        List<Integer> rotateGroupIds = new ArrayList<Integer>();
        rotateGroupIds.add(95620503);
        rotateGroupIds.add(95466392);
        Map<Integer, List<RotateGroupShopDTO>> id2RotateGroupShops =  rotateGroupShopService.getRotateGroupShop(rotateGroupIds);
        System.out.println(id2RotateGroupShops.toString());
    }

}
