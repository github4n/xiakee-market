CREATE DATABASE `xiakee` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON xiakee.* TO boge@"%" IDENTIFIED BY 'xiakee_boge@798#168' WITH GRANT OPTION;
FLUSH PRIVILEGES;

DROP TABLE urm_rrole;
DROP TABLE urm_resource;
DROP TABLE urm_urole;
DROP TABLE urm_roles;
DROP TABLE urm_users;




/* Create Tables */

CREATE TABLE urm_resource
(
	resid BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	type VARCHAR(20),
	content VARCHAR(100),
	priority TINYINT(2),
	descn VARCHAR(200),
	PRIMARY KEY (resid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限管理资源表';


CREATE TABLE urm_roles
(
	roleid BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	descn VARCHAR(200),
	PRIMARY KEY (roleid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限管理角色表';


CREATE TABLE urm_rrole
(
	resid BIGINT NOT NULL,
	roleid BIGINT NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限管理角色资源关联表';


CREATE TABLE urm_urole
(
	roleid BIGINT NOT NULL,
	userid BIGINT NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限管理角色用户关联表';


CREATE TABLE urm_users
(
	userid BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	password VARCHAR(20),
	status TINYINT(1) DEFAULT 1,
	email VARCHAR(50),
	depart VARCHAR(20),
	address VARCHAR(100),
	contact VARCHAR(20),
	PRIMARY KEY (userid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限管理用户表';



/* Create Foreign Keys */

ALTER TABLE urm_rrole
	ADD FOREIGN KEY (resid)
	REFERENCES urm_resource (resid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE urm_rrole
	ADD FOREIGN KEY (roleid)
	REFERENCES urm_roles (roleid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE urm_urole
	ADD FOREIGN KEY (roleid)
	REFERENCES urm_roles (roleid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE urm_urole
	ADD FOREIGN KEY (userid)
	REFERENCES urm_users (userid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


INSERT INTO urm_users(name,password,email,depart,address,contact) VALUES("boge","guxinghanshe","boge@xiakee.com","遐客行研发中心","北京市朝阳区酒仙桥路4号友谊大厦5层","负责遐客行公司的技术组建与发展");

CREATE TABLE `xiakee_userinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '微信用户信息主键ID',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `identity_number` varchar(20) DEFAULT NULL COMMENT '购物用户身份证号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='遐客行微信用户信息表';

CREATE TABLE `xiakee_wxopenid` (
  `openid` varchar(50) NOT NULL COMMENT '公众号用户openID',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `random` varchar(6) NOT NULL COMMENT '随机码',
  `verify` tinyint(1) DEFAULT 0 COMMENT '是否已通过验证',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公众号用户手机号关联表';

CREATE TABLE `xiakee_yzorders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '有赞微商城订单ID',
  `orderid` varchar(50) NOT NULL COMMENT '订单ID',
  `price` varchar(10) DEFAULT NULL COMMENT '商品价格',
  `payment` varchar(10) DEFAULT NULL COMMENT '买家实付货款',
  `status` varchar(50) DEFAULT NULL COMMENT '订单状态',
  `name` varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  `province` varchar(20) DEFAULT NULL COMMENT '收货人省份',
  `city` varchar(20) DEFAULT NULL COMMENT '收货人城市',
  `district` varchar(20) DEFAULT NULL COMMENT '收货人地区',
  `address` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `zipCode` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `mobile` varchar(15) DEFAULT NULL COMMENT '联系手机',
  `created` varchar(20) DEFAULT NULL COMMENT '订单创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '订单修改时间',
  `pay_time` varchar(20) DEFAULT NULL COMMENT '订单交付时间',
  `title` varchar(200) DEFAULT NULL COMMENT '宝贝标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='有赞微商城订单表';

CREATE TABLE `xiakee_yzorderinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '单类商品订单id',
  `item_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '订单明细ID',
  `orderid` varchar(50) NOT NULL COMMENT '订单ID',
  `title` varchar(200) DEFAULT NULL COMMENT '宝贝标题',
  `sku_properties_name` varchar(200) DEFAULT NULL COMMENT '商品规格',
  `num` int DEFAULT 0 COMMENT '商品数量',
  `price` varchar(10) DEFAULT NULL COMMENT '单个商品价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='有赞微商城订单详情';

CREATE TABLE `xiakee_logistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物流ID',
  `orderid` varchar(50) NOT NULL COMMENT '订单ID',
  `content` varchar(500) DEFAULT NULL COMMENT '物流内容',
  `node` int DEFAULT 0 COMMENT '物流内容类型，分国外，百威，国内等',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='订单物流信息表';

CREATE TABLE `xiakee_abroadOrder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '国外包裹id',
  `url` varchar(200) NOT NULL COMMENT '采购网址',
  `outOrderId` varchar(50) DEFAULT NULL COMMENT '国外订单号',
  `currency` varchar(50) DEFAULT '0' COMMENT '货币类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='国外包裹物流信息表';

CREATE TABLE `xiakee_boxno` (
  `infoId` bigint(20)  DEFAULT NULL COMMENT '具体物品信息id',
  `abroadId` bigint(20) DEFAULT NULL COMMENT  '国外包裹id',
  `expressno` varchar(50) DEFAULT NULL COMMENT '国外包裹号',
  `declared` TINYINT(1) DEFAULT 0 COMMENT '该国外包裹已申报，0默认尚未申报，方便人工审核是否已申报',
  `boxno` varchar(50) DEFAULT NULL COMMENT '百威提供的boxno，在包裹出仓时才有',
  `status` varchar(50) DEFAULT NULL COMMENT '根据boxno获取的最后一次状态，由百威提供',
  `price` varchar(10) DEFAULT NULL COMMENT '采购金额',
  `sum` int DEFAULT 0 COMMENT '数量',
  `trend` TINYINT(1) DEFAULT 0 COMMENT '价格趋势，-1为降价；0为平本，1为涨价',
  `modified` datetime DEFAULT NULL COMMENT '海外物流信息更改时间',
  `created` datetime DEFAULT NULL COMMENT '海外订单生成时间',
  PRIMARY KEY (`infoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='百威分箱信息表';

CREATE TABLE `xiakee_logistComp` (
  `infoId` bigint(20)  DEFAULT NULL COMMENT '具体物品信息id',
  `logistComp` TINYINT(1) DEFAULT 0 COMMENT '国内物流公司,0默认不设置，1代表申通，2代表中通。。。',
  `expressno` varchar(50) DEFAULT NULL COMMENT '国内包裹号',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`infoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国内打印信息表';

CREATE TABLE `smsc_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '短信发送ID',
  `mobile` varchar(200) DEFAULT NULL COMMENT '发送手机号码，多个使用逗号分开',
  `content` varchar(300) DEFAULT NULL COMMENT '短信发送内容',
  `types` TINYINT(1) DEFAULT 0 COMMENT '短信类型',
  `status` int DEFAULT 0 COMMENT '短信发送状态',
  `counts` TINYINT(1) DEFAULT 0 COMMENT '短信发送条数',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信发送记录表';

CREATE TABLE `smsc_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '短信发送结果ID',
  `smscId` bigint(20) NOT NULL COMMENT '短信发送ID',
  `mobile` varchar(15) DEFAULT NULL COMMENT '发送手机号码',
  `mid` varchar(30) DEFAULT NULL COMMENT '短信发送批次号',
  `msg` varchar(150) DEFAULT NULL COMMENT '短信发送错误反馈内容',
  `status` varchar(10) DEFAULT NULL COMMENT '短信发送接收状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信发送结果表';

CREATE TABLE `sku_manage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'SKU信息ID',
  `productId` bigint(20) DEFAULT 0 COMMENT '商城对应产品编码，在商城url链接中product码',
  `enName` varchar(300) DEFAULT NULL COMMENT 'SKU英文名称，即商品英文名称',
  `zhName` varchar(300) DEFAULT NULL COMMENT 'SKU中文名称，即商品中文名称',
  `skuCode` varchar(20) DEFAULT NULL COMMENT 'SKU全站唯一编码，使用62进制针对id进行编码',
  `brand` bigint(10) DEFAULT 0 COMMENT '品牌ID，关联商城品牌',
  `classify` bigint(10) DEFAULT 0 COMMENT '分类ID，关联商城分类',
  `types` bigint(10) DEFAULT 0 COMMENT '类型ID，关联商城类型',
  `mainUrl` varchar(300) DEFAULT NULL COMMENT '默认URL',
  `pattern` varchar(10) DEFAULT NULL COMMENT 'SKU运营模式，自营或代购',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SKU管理表';

CREATE TABLE `sku_urls` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'SKU关联url_ID',
  `skuCode` varchar(20) DEFAULT NULL COMMENT 'SKU全站唯一编码，使用62进制针对id进行编码',
  `url` varchar(300) DEFAULT NULL COMMENT '默认URL',
  `defUrl` TINYINT(1) DEFAULT 0 COMMENT '是否默认的url，1为是，0为否，默认为0，即是不是SKU主要指定的url',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SKU海外选品url表';

CREATE TABLE `sku_catalog` (
  `cat_id` bigint(20) NOT NULL COMMENT '商品目录ID，即商品类型',
  `cat_name` varchar(20) DEFAULT NULL COMMENT '类型名称，即上装，下装等',
  `catCode` varchar(4) DEFAULT NULL COMMENT '目录名称代码，两位简称',
  `parent_id` bigint(20) DEFAULT 0  COMMENT '上级目录ID',
  `type_id` bigint(20) DEFAULT 0   COMMENT '关联的分类ID',
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SKU所属商品目录信息表';

CREATE TABLE `sku_type` (
  `type_id` bigint(20) NOT NULL COMMENT '商品类型ID，由商城系统生成',
  `name` varchar(20) DEFAULT NULL COMMENT '类型名称',
  `typeCode` varchar(4) DEFAULT NULL COMMENT '类型名称代码，两位简称',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SKU所属商品类型信息表';

CREATE TABLE `sku_brand` (
  `id` bigint(20) NOT NULL COMMENT '品牌ID',
  `name` varchar(20) DEFAULT NULL COMMENT '品牌名称',
  `brandCode` varchar(4) DEFAULT NULL COMMENT '品牌名称代码，两位简称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SKU所属商品品牌信息表';

CREATE TABLE `sku_goodsno` (
  `goodsno` varchar(50) NOT NULL DEFAULT '',
  `crawler_goods_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`goodsno`,`crawler_goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城货号与抓取货品ID对应表';

CREATE TABLE `ecos_orders` (
  `order_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '订单号',
  `total_amount` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '商品默认货币总值',
  `final_amount` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '订单货币总值, 包含支付价格,税等',
  `pay_status` enum('0','1','2','3','4','5') NOT NULL DEFAULT '0' COMMENT '付款状态',
  `ship_status` enum('0','1','2','3','4') NOT NULL DEFAULT '0' COMMENT '发货状态',
  `createtime` int(10) unsigned DEFAULT NULL COMMENT '下单时间',
  `last_modified` int(10) unsigned DEFAULT NULL COMMENT '最后更新时间',
  `payment` varchar(100) DEFAULT NULL COMMENT '支付方式',
  `member_id` mediumint(8) unsigned DEFAULT NULL COMMENT '会员用户名',
  `status` enum('active','dead','finish') NOT NULL DEFAULT 'active' COMMENT '订单状态',
  `ship_area` varchar(255) DEFAULT NULL COMMENT '收货地区',
  `ship_name` varchar(50) DEFAULT NULL COMMENT '收货人',
  `weight` decimal(20,3) DEFAULT NULL COMMENT '订单总重量',
  `ship_addr` text COMMENT '收货地址',
  `ship_zip` varchar(20) DEFAULT NULL COMMENT '收货人邮编',
  `ship_tel` varchar(50) DEFAULT NULL COMMENT '收货电话',
  `ship_email` varchar(200) DEFAULT NULL COMMENT '收货人email',
  `ship_mobile` varchar(50) DEFAULT NULL COMMENT '收货人手机',
  `cost_item` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '订单商品总价格',
  `discount` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '订单减免',
  `pmt_goods` decimal(20,3) DEFAULT NULL COMMENT '商品促销优惠',
  `pmt_order` decimal(20,3) DEFAULT NULL COMMENT '订单促销优惠',
  `payed` decimal(20,3) DEFAULT '0.000' COMMENT '订单支付金额',
  `memo` longtext COMMENT '订单附言',
  `cost_freight` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '配送费用',
  `order_refer` varchar(20) NOT NULL DEFAULT 'local' COMMENT '订单来源',
  `source` enum('pc','wap','weixin') DEFAULT 'pc' COMMENT '平台来源',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城订单表';

CREATE TABLE `ecos_orderitems` (
  `item_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '订单明细ID',
  `order_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '订单ID',
  `obj_id` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '订单明细对应的商品对象ID, 对应到sdb_b2c_order_objects表',
  `product_id` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '货品ID',
  `goods_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品ID',
  `type_id` mediumint(8) unsigned DEFAULT NULL COMMENT '商品类型ID',
  `bn` varchar(40) DEFAULT NULL COMMENT '明细商品的品牌名',
  `name` varchar(200) DEFAULT NULL COMMENT '明细商品的名称',
  `cost` decimal(20,3) DEFAULT NULL COMMENT '明细商品的成本',
  `amount` decimal(20,3) DEFAULT NULL COMMENT '明细商品总额',
  `price` decimal(20,3) NOT NULL DEFAULT '0.000' COMMENT '明细商品的销售价(购入价)',
  `nums` float NOT NULL DEFAULT '1' COMMENT '明细商品购买数量',
  `addon` longtext COMMENT '明细商品的规格属性',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城订单明细表';

CREATE TABLE `sys_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统操作ID',
  `userid` bigint(20) NOT NULL COMMENT '操作人员ID',
  `url` varchar(300) DEFAULT NULL COMMENT '操作URL',
  `created` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统操作日志表';

CREATE TABLE `purch_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '采购备注ID',
  `infoId` bigint(20) NOT NULL COMMENT '采购货品id',
  `userid` bigint(20) NOT NULL COMMENT '操作人员ID',
  `name` varchar(20) DEFAULT NULL COMMENT '操作人员姓名',
  `content` varchar(500) DEFAULT NULL COMMENT '备注内容',
  `created` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购备注信息表';

CREATE TABLE `analy_order_referrer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单追踪埋点ID',
  `userid` bigint(20) NOT NULL COMMENT '申请人ID',
  `title` varchar(100) DEFAULT NULL COMMENT '追踪埋点名称',
  `promotion` varchar(50) DEFAULT NULL COMMENT '推广人姓名',
  `code` varchar(200) DEFAULT NULL COMMENT '埋点码',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注内容',
  `created` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单追踪埋点信息表';

CREATE TABLE `analy_order_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '推广链接ID',
  `userid` bigint(20) NOT NULL COMMENT '申请人ID',
  `url` varchar(300) DEFAULT NULL COMMENT '推广链接',
  `title` varchar(100) DEFAULT NULL COMMENT '推广名称',
  `created` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广链接信息表';


CREATE TABLE `logist_transport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物流转运信息id',
  `expressno` varchar(50) DEFAULT NULL COMMENT '海外物流快递号',
  `orderid` varchar(50) NOT NULL COMMENT '订单ID',
  `transportno` varchar(50) DEFAULT NULL COMMENT '转运公司包裹号',
  `transfer_id` TINYINT(1) DEFAULT 0 COMMENT '转运公司ID，1、百威；2、递优国际',
  `packageno` TINYINT(1) DEFAULT 0 COMMENT '分包id，默认0为不分包',
  `target` varchar(50) DEFAULT NULL COMMENT '物流节点标识,OK为结束符',
  `modify` datetime DEFAULT NULL COMMENT '最后修改的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流转运信息表';
	

ALTER TABLE xiakee_yzorderinfo ADD item_id  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '订单明细ID';
ALTER TABLE xiakee_boxno ADD modified datetime DEFAULT NULL COMMENT '海外物流信息更改时间';
ALTER TABLE xiakee_boxno ADD created datetime DEFAULT NULL COMMENT '海外订单生成时间';
ALTER TABLE xiakee_boxno ADD transfer_id  TINYINT(1) DEFAULT 0 COMMENT '转运公司ID，1、百威；2、递优国际';
ALTER TABLE `sku_manage` ADD COLUMN `userId`  bigint(20) NULL COMMENT '添加用户ID' AFTER `created`;
ALTER TABLE `sku_goods`  ADD COLUMN `price_increase`  int(10) NULL DEFAULT 0 COMMENT '价格涨幅' AFTER `image`;
ALTER TABLE `sku_manage` ADD COLUMN `isImport`  int(1) NULL DEFAULT 0 COMMENT '是否导入商城 0-否 1-是' AFTER `userId`;
ALTER TABLE `sku_manage` ADD COLUMN `grossId`  int(5) NULL COMMENT '毛利类型ID' AFTER `userId`;
ALTER TABLE `sku_manage` ADD COLUMN `keyword`  varchar(100) NULL DEFAULT '' COMMENT '关键词，多个以“|”隔开' AFTER `grossId`;
ALTER TABLE `xiakee_yzorderinfo` ADD COLUMN `remark`  varchar(200) NULL DEFAULT '' COMMENT '采购人员针对该商品进行备注处理';
ALTER TABLE `sku_manage` ADD COLUMN `priceLockTime`  datetime NULL COMMENT '价格锁定时间' AFTER `keyword`;
ALTER TABLE `sku_goods` ADD COLUMN `total_store`  int(10) NULL DEFAULT 0 COMMENT '总库存' AFTER `price_increase`;
ALTER TABLE `sys_logs` MODIFY COLUMN `url`  varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作URL' AFTER `userid`;
