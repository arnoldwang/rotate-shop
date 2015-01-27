/**
*  阿波罗门店相关表
*  2014-12-29
**/


DROP TABLE IF EXISTS `ApolloShop`;
CREATE TABLE `ApolloShop`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `ShopID` int(11) NOT NULL COMMENT '门店ID',
  `ShopStatus` tinyint(4) NOT NULL DEFAULT 5 COMMENT '门店状态：0，只在手机端现实；1，已关；2，暂停营业；3，非active；4，尚未营业；5，正常；10，积分商户；',
  `ShopGroupID` int(11) NOT NULL COMMENT '轮转门店组ID',
  `CityID` int(11) NOT NULL COMMENT '城市ID',
  `District` int(11) NOT NULL DEFAULT 0 COMMENT '前台区域（行政区）标识',
  `ShopType` int(11) NOT NULL COMMENT '频道：美食、休闲、娱乐等',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_SHOP_ID` (`ShopID`),
  KEY `IX_SHOP_GROUP_ID` (`ShopGroupID`),
  KEY `IX_SHOP_CITY_ID` (`CityID`),
  KEY `IX_SHOP_DISTRICT` (`District`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '落地的POI门店信息表';


DROP TABLE IF EXISTS `Region`;
CREATE TABLE `Region`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `RegionID` int(11) NOT NULL COMMENT '区域ID',
  `RegionName` varchar(100) COMMENT '区域名',
  `CityID` int(11) NOT NULL COMMENT '城市ID',
  `RegionType` int(11) COMMENT '区域类型',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_REGION_ID` (`RegionID`),
  KEY `IX_REGION_CITY_ID` (`RegionID`,`CityID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '区域表';


/** 数据不需要同步到表里
DROP TABLE IF EXISTS `RegionTree`;
CREATE TABLE `RegionTree`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `RegionID` int(11) NOT NULL COMMENT '区域ID',
  `ParentID` int(11) NOT NULL COMMENT '父区域ID',
  `IsMain` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否主区域：0，否；1，是；',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_REGION_ID` (`RegionID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '区域树表';

DROP TABLE IF EXISTS `RegionExpand`;
CREATE TABLE `RegionExpand`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `RegionID` int(11) NOT NULL COMMENT '区域ID',
  `SubRegionID` int(11) NOT NULL COMMENT '子区域ID',
  `CityID` int(11) NOT NULL DEFAULT 0 COMMENT '城市ID',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_REGION_ID` (`RegionID`),
  KEY `IX_REGION_CITY_ID` (`RegionID`,`CityID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '区域树拉平表';
**/

DROP TABLE IF EXISTS `ShopRegion`;
CREATE TABLE `ShopRegion`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `ShopID` int(11) NOT NULL COMMENT '门店ID',
  `RegionID` int(11) NOT NULL COMMENT '区域ID',
  `IsMain` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否主区域：0，否;1，是;',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_SHOP_ID` (`ShopID`),
  KEY `IX_REGION_ID` (`RegionID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '门店和区域树的关系表';


DROP TABLE IF EXISTS `Category`;
CREATE TABLE `Category`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `CategoryID` int(11) NOT NULL COMMENT '分类ID',
  `CategoryName` varchar(100) COMMENT '分类名',
  `CityID` int(11) NOT NULL COMMENT '城市ID',
  `CategoryType` int(11) COMMENT '分类类型',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_CATEGORY_ID` (`CategoryID`),
  KEY `IX_CATEGORY_CITY_ID` (`CategoryID`,`CityID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '分类表';


/** 数据不需要同步到表里
DROP TABLE IF EXISTS `CategoryTree`;
CREATE TABLE `CategoryTree`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `CategoryID` int(11) NOT NULL COMMENT '分类ID',
  `CityID` int(11) NOT NULL COMMENT '城市ID',
  `ParentID` int(11) COMMENT '父分类ID',
  `IsMain` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否主区域：0，否;1，是;',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_CATEGORY_ID` (`CategoryID`),
  KEY `IX_CATEGORY_CITY_ID` (`CategoryID`,`CityID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '分类树表';


DROP TABLE IF EXISTS `CategoryExpand`;
CREATE TABLE `CategoryExpand`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `CategoryID` int(11) NOT NULL COMMENT '分类ID',
  `CityID` int(11) NOT NULL COMMENT '城市ID',
  `SubCategoryID` int(11) NOT NULL COMMENT '子分类ID',
  `NextCategoryID` int(11) NOT NULL DEFAULT 0 COMMENT '直接子分类ID',
  `IsMain` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否主区域：0，否;1，是;',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_CATEGORY_ID` (`CategoryID`),
  KEY `IX_CATEGORY_CITY_ID` (`CategoryID`,`CityID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '分类树拉平表';
**/


DROP TABLE IF EXISTS `ShopCategory`;
CREATE TABLE `ShopCategory`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `ShopID` int(11) NOT NULL COMMENT '门店ID',
  `CategoryID` int(11) NOT NULL COMMENT '分类ID',
  `IsMain` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否主分类：0，否;1，是;',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_SHOP_ID` (`ShopID`),
  KEY `IX_CATEGORY_ID` (`CategoryID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '门店和分类的关系表';


DROP TABLE IF EXISTS `ApolloShopExtend`;
CREATE TABLE `ApolloShopExtend`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `ShopID` int(11) NOT NULL COMMENT '门店ID',
  `Type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '客户类型：0，普通客户；1，大客户；',
  `BizID` int(11) NOT NULL COMMENT 'BizID',
  `Rating` varchar(10) NOT NULL DEFAULT '' COMMENT '客户分级：K,A,B,各个BU可以定义自己的客户分级',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_SHOP_ID` (`ShopID`),
  KEY `IX_SHOP_BIZ_ID` (`ShopID`,`BizID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '门店扩展信息表';


DROP TABLE IF EXISTS `ApolloShopBusinessStatus`;
CREATE TABLE `ApolloShopBusinessStatus`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `ShopID` int(11) NOT NULL COMMENT '门店ID',
  `CooperationStatus` tinyint(4) NOT NULL DEFAULT 1 COMMENT '合作状态：0，已下线；1，在线；',
  `OfflineDate` datetime COMMENT '下线时间',
  `BusinessType` tinyint(4) NOT NULL DEFAULT 0 COMMENT '业务类型：0，团购；1，推广；2，会员卡；4，储值卡；5，预定；6，外卖；7，闪惠；',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_SHOP_ID` (`ShopID`),
  KEY `IX_SHOP_BUSINESSTYPE_ID` (`ShopID`,`BusinessType`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '门店各业务的合作信息表';


DROP TABLE IF EXISTS `RotateGroup`;
CREATE TABLE `RotateGroup`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `BizID` int(11) NOT NULL COMMENT 'BizID',
  `Type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '类型：0，单店；1，连锁店',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；2，关闭；3，暂停营业；4，尚未营业；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_BIZ_ID` (`BizID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '轮转门店组信息表';


DROP TABLE IF EXISTS `RotateGroupShop`;
CREATE TABLE `RotateGroupShop`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `RotateGroupID` int(11) NOT NULL COMMENT '轮转门店组ID',
  `ShopID` int(11) NOT NULL COMMENT '门店ID',
  `ShopGroupID` int(11) NOT NULL COMMENT '门店组ID',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；2，关闭；3，暂停营业；4，尚未营业；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_ROTATEGROUP_ID` (`RotateGroupID`),
  KEY `IX_SHOP_ID` (`ShopID`),
  KEY `IX_SHOPGROUP_ID` (`ShopGroupID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '轮转门店组和门店的关系表';


DROP TABLE IF EXISTS `Biz`;
CREATE TABLE `Biz`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `BizID` int(11) NOT NULL COMMENT 'BizID',
  `Name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_BizID_ID` (`BizID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '业务聚合体信息表';


DROP TABLE IF EXISTS `BuBiz`;
CREATE TABLE `BuBiz`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `BuID` int(11) NOT NULL COMMENT 'BuID',
  `BizID` int(11) NOT NULL COMMENT 'BizID',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，正常；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '业务聚合体和BU的关系表';


DROP TABLE IF EXISTS `MessageQueue`;
CREATE TABLE `MessageQueue`(
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `Msg` varchar(1000) COMMENT 'Swallow消息体',
  `SwallowID` varchar(32) NOT NULL COMMENT 'Swallow消息ID',
  `AttemptIndex` int(11) NOT NULL DEFAULT 0 COMMENT '重试的次数，次数越大，后面再次重试的时间间隔越长',
  `Source` tinyint(4) NOT NULL DEFAULT 0 COMMENT '消息来源：0，用户行为；1，系统行为；',
  `Type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '消息类型：0，用户增量添加；1，商圈树；2，商户合并；3，商户误合并后恢复；4，系统批量新增POI；5，更新POI属性；6，分类树；',
  `Status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0，删除；1，新增；2，正在处理；3，处理完成；',
  `CreatedTime` datetime NOT NULL COMMENT '记录添加时间',
  `LastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`ID`),
  KEY `IX_SOURCE_TYPE_ATTEMPTINDEX_ID` (`Source`,`Type`,`AttemptIndex`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'POI消息的缓存队列表';

