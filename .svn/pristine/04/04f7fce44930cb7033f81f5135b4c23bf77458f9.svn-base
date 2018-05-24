package com.xiakee.view;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiakee.dao.sku.SkuGoodsNoDao;
import com.xiakee.ecdao.order.EcGoodsDao;
import com.xiakee.service.sku.SkuCrawlerService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-application.xml" })
public class ControllerAppTest {

	@Autowired
	private SkuGoodsNoDao skuGoodsNoDao;
	@Autowired
	private EcGoodsDao ecGoodsDao;

	@Test
	public void test() {
		skuGoodsNoDao.selectByMallId();
		ecGoodsDao.selectIdByAll();
	}
}
