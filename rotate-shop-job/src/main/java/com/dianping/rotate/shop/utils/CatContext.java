package com.dianping.rotate.shop.utils;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * 可分阶段打点的工具
 *
 * 非线程安全
 *
 * @author yihua.huang@dianping.com
 *         Date: 15-1-16
 *         Time: 下午6:01
 */
public class CatContext {

	private final Map<String, Transaction> transactions;
	private final String transactionName;
	private int stepCount;
	private final Stack<Transaction> transactionStack;

	private static ThreadLocal<Map<String, CatContext>> catContextMaps = new InheritableThreadLocal<Map<String, CatContext>>();

	public static CatContext transaction(String transactionName) {
		Map<String, CatContext> catContextMap = catContextMaps.get();
		if (catContextMap == null) {
			catContextMap = new HashMap<String, CatContext>();
			catContextMaps.set(catContextMap);
		}
		CatContext catContext = catContextMap.get(transactionName);
		if (catContext == null) {
			catContext = new CatContext(transactionName);
			catContextMap.put(transactionName, catContext);
		}
		return catContext;
	}

	private CatContext(String transactionName) {
		this.transactions = new HashMap<String, Transaction>();
		this.transactionName = transactionName;
		this.stepCount = 0;
		this.transactionStack = new Stack<Transaction>();
	}

	public String getTransactionName() {
		return transactionName;
	}

	private int incAndGetStepCount() {
		return ++this.stepCount;
	}

	public void startTransaction() {
		startTransactionWithStep(null);
	}

	public void startTransactionWithStep(String stepName) {
		newTransaction("ALL");
		if (stepName != null) {
			newTransaction(formatIntWithZero(incAndGetStepCount()) + "-" + stepName);
		}
	}

	public void startNewStep(String stepName) {
		if (!transactionStack.peek().getName().equals("ALL")) {
			transactionStack.pop().complete();
		}
		newTransaction(formatIntWithZero(incAndGetStepCount()) + "-" + stepName);
	}

	public void stopTransaction() {
		while (!transactionStack.isEmpty()) {
			transactionStack.pop().complete();
		}
		catContextMaps.remove();
	}

	private Transaction newTransaction( String step) {
		Transaction transaction = Cat.newTransaction(transactionName, step);
		transactions.put(transactionName + step, transaction);
		transactionStack.push(transaction);
		return transaction;
	}

	private String formatIntWithZero(int i){
		return String.format("%02d",i);
	}
}
