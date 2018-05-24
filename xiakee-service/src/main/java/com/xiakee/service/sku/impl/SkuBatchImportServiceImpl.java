package com.xiakee.service.sku.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xiakee.domain.sku.SkuManagerBean;
import com.xiakee.domain.smsc.EmailContentBean;
import com.xiakee.service.AutoExecuteTasker;
import com.xiakee.service.sku.SkuBatchImportService;
import com.xiakee.service.sku.SkuCrawlerService;
import com.xiakee.service.sku.SkuImportShopService;
import com.xiakee.service.sku.SkuManagerService;
import com.xiakee.service.sku.SkuUrlsService;
import com.xiakee.service.smsc.AccountEmailService;
import com.xiakee.service.smsc.EmailAutoSendTask;

@Service
public class SkuBatchImportServiceImpl implements SkuBatchImportService {
	private static Logger log = Logger.getLogger(SkuBatchImportServiceImpl.class);
	private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
	// 线程工厂
	private static ThreadFactory threadFactory = new ThreadFactory() {
		private final AtomicInteger integer = new AtomicInteger();

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "myThreadPool thread:" + integer.getAndIncrement());
		}
	};
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(20, 20, 10000, TimeUnit.SECONDS, workQueue, threadFactory);
	@Autowired
	private SkuManagerService skuManagerService;
	@Autowired
	private SkuUrlsService skuUrlsService;
	@Autowired
	private SkuCrawlerService skuCrawlerService;
	@Autowired
	private SkuImportShopService skuImportShopService;
	@Autowired
	private AccountEmailService emailService;

	@Override
	public void batchImport(List<SkuManagerBean> beanList, Long userId) {
		for (SkuManagerBean skuManagerBean : beanList) {
			threadPool.execute(new ReadThread(skuManagerBean));
		}
	}

	@Override
	public int getSurplusCount(Long userId) {
		return threadPool.getQueue().size();
	}

	private void sendEmail(String content) {
		if (StringUtils.isNotEmpty(content)) {
			EmailContentBean email = new EmailContentBean();
			// email.setTo("moniorder@xiakee.com");
			email.setTo("huangzhaoshui@xiakee.com");
			email.setSubject("小遐提醒：批量抓取SKU有错误...");
			EmailAutoSendTask emailTask = new EmailAutoSendTask();
			emailTask.setEmailService(emailService);
			emailTask.setTaskBean(email);
			email.setHtmlText(content);
			AutoExecuteTasker.addAutoExecuteTasker(emailTask);
		}
	}

	class ReadThread implements Runnable {
		private SkuManagerBean bean;

		public ReadThread(SkuManagerBean bean) {
			this.bean = bean;
		}

		@Override
		public void run() {
			if (bean != null) {
				String rawlerUrls = bean.getMainUrl();
				String[] urls = null;
				if (bean.getUrls() != null && bean.getUrls().size() > 0) {
					urls = (String[]) bean.getUrls().toArray();
					if (urls != null && urls.length > 0) {
						for (String url : urls) {
							if (StringUtils.isNotBlank(url)) {
								rawlerUrls += ";" + url;
							}
						}
					}
				}
				String results = skuCrawlerService.getSkuCodeByUrls(bean.getMainUrl());
				@SuppressWarnings("rawtypes")
				Map skuCodeMap = JSON.parseObject(results, Map.class);
				if (skuCodeMap != null && skuCodeMap.size() > 0) {
					String skuCode = String.valueOf(skuCodeMap.get(bean.getMainUrl()));
					SkuManagerBean managerBean = skuManagerService.findSkuManagerBeanBySkuCode(skuCode);
					if (managerBean != null) {
						return;
					}
				}
				try {
					skuManagerService.addSkuManagerBean(bean, urls);
					String result = skuCrawlerService.crawlerSku(bean.getSkuCode(), rawlerUrls, bean.getTypes() + "", bean.getClassify() + "", 0);
					if ("0".equals(result)) {
						sendEmail("数据抓取错误，错误URL=" + rawlerUrls);
					} else {
						skuManagerService.comb(bean.getSkuCode());
						skuImportShopService.importData(bean);
					}
				} catch (Exception e) {
					log.error("批量错误， e = {}", e);
					sendEmail("数据添加SKU库错误，错误URL=" + rawlerUrls);
				}
			}
		}
	}
}
