package com.il.sod.rest.dto.db;

import java.util.Date;
import java.util.List;

public class OrderDTO {
	private int idOrder;
	private String comments;
	private int idAddressDeliver;
	private int idAddressPickup;
	private double price;
	private int status;
	private ClientDTO client;
	private Integer orderType;
	private List<OrderTaskDTO> orderTasks;
	private List<OrderPickNDeliverDTO> orderPickNdelivers;
	
	private Date created;
	private Date updated;
	private int time;
	
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getIdAddressDeliver() {
		return idAddressDeliver;
	}
	public void setIdAddressDeliver(int idAddressDeliver) {
		this.idAddressDeliver = idAddressDeliver;
	}
	public int getIdAddressPickup() {
		return idAddressPickup;
	}
	public void setIdAddressPickup(int idAddressPickup) {
		this.idAddressPickup = idAddressPickup;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ClientDTO getClient() {
		return client;
	}
	public void setClient(ClientDTO client) {
		this.client = client;
	}
	public List<OrderTaskDTO> getOrderTasks() {
		return orderTasks;
	}
	public void setOrderTasks(List<OrderTaskDTO> orderTasks) {
		this.orderTasks = orderTasks;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public List<OrderPickNDeliverDTO> getOrderPickNdelivers() {
		return orderPickNdelivers;
	}
	public void setOrderPickNdelivers(List<OrderPickNDeliverDTO> orderPickNdelivers) {
		this.orderPickNdelivers = orderPickNdelivers;
	}

}
