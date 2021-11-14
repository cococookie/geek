package com.geek.study.sql.entity;


public class OrderEntity {
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 商家ID
	 */
	private String vendorId;
	/**
	 * 门店ID
	 */
	private String storeId;
	/**
	 * 收货手机号码
	 */
	private String phone;
	/**
	 * 收货地址
	 */
	private String addr;
	/**
	 * 订单状态 0-待支付 1-捡货中 2-已发货 3-已收货 4-已取消
	 */
	private Integer status;
	/**
	 * 实付价格
	 */
	private Long price;
	/**
	 * 发货时间
	 */
	private java.util.Date sendTime;
	/**
	 * 收货时间
	 */
	private java.util.Date receiveTime;
	/**
	 * 修改人
	 */
	private String updateUser;
	/**
	 * 系统版本
	 */
	private Integer sysVersion;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 修改时间
	 */
	private java.util.Date updateTime;
	/**
	 * 删除状态
	 */
	private Integer yn;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getVendorId(){
		return vendorId;
	}
	public void setVendorId(String vendorId){
		this.vendorId = vendorId;
	}
	public String getStoreId(){
		return storeId;
	}
	public void setStoreId(String storeId){
		this.storeId = storeId;
	}
	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getAddr(){
		return addr;
	}
	public void setAddr(String addr){
		this.addr = addr;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Long getPrice(){
		return price;
	}
	public void setPrice(Long price){
		this.price = price;
	}
	public java.util.Date getSendTime(){
		return sendTime;
	}
	public void setSendTime(java.util.Date sendTime){
		this.sendTime = sendTime;
	}
	public java.util.Date getReceiveTime(){
		return receiveTime;
	}
	public void setReceiveTime(java.util.Date receiveTime){
		this.receiveTime = receiveTime;
	}
	public String getUpdateUser(){
		return updateUser;
	}
	public void setUpdateUser(String updateUser){
		this.updateUser = updateUser;
	}
	public Integer getSysVersion(){
		return sysVersion;
	}
	public void setSysVersion(Integer sysVersion){
		this.sysVersion = sysVersion;
	}
	public java.util.Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	public Integer getYn(){
		return yn;
	}
	public void setYn(Integer yn){
		this.yn = yn;
	}
}