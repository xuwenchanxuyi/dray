package com.oracle.service;

/**
 * 消息服务
 * 
 * @author oracle
 *
 */
public interface AmqService {

	/**
	 * 队列发送消息
	 * @param queueName 队列名称
	 * @param message 消息内容
	 *            
	 */
	public void queueSendMessage(String queueName, String message);

	/**
	 * 队列接收消息
	 * @param queueName 队列名称
	 *            
	 */
	public void queueReceiveMessage(String queueName);
	
	/**
	 * topic发送消息
	 * @param queueName 队列名称
	 * @param message 消息内容
	 *            
	 */
	public void topicSendMessage(String queueName, String message);
	
	/**
	 * topic接收消息
	 * @param queueName 队列名称
	 *            
	 */
	public void topicReceiveMessage(String queueName);
}
