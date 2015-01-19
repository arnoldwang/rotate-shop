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

	public static final String STEP_ALL = "ALL";
	private final String transactionName;
	private int stepCount;
	private Transaction parentTransaction;
	private final Stack<Transaction> childTransactionStack;

	private static ThreadLocal<Map<String, CatContext>> catContextMaps = new ThreadLocal<Map<String, CatContext>>();

	/**
	 * 新建打点
	 * @param transactionName
	 * @return
	 */
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
		this.transactionName = transactionName;
		this.stepCount = 0;
		this.childTransactionStack = new Stack<Transaction>();
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
		newParentTransaction();
		if (stepName != null) {
			newChildTransaction(formatIntWithZero(incAndGetStepCount()) + "-" + stepName);
		}
	}

	public void startNewStep(String stepName) {
		if (!childTransactionStack.isEmpty()){
			completeTransaction(childTransactionStack.pop());
		}
		newChildTransaction(formatIntWithZero(incAndGetStepCount()) + "-" + stepName);
	}

	public void stopTransaction() {
		completeTransaction(parentTransaction);
		while (!childTransactionStack.isEmpty()) {
			completeTransaction(childTransactionStack.pop());
		}
		catContextMaps.remove();
	}

	private void completeTransaction(Transaction transaction) {
		if (transaction != null) {
			transaction.setStatus(Transaction.SUCCESS);
			transaction.complete();
		}
	}

	private Transaction newParentTransaction() {
		Transaction transaction = Cat.newTransaction(transactionName, STEP_ALL);
		parentTransaction = transaction;
		return transaction;
	}

	private Transaction newChildTransaction(String step) {
		Transaction transaction = Cat.newTransaction(transactionName, step);
		childTransactionStack.push(transaction);
		return transaction;
	}

	private String formatIntWithZero(int i){
		return String.format("%02d",i);
	}
}
