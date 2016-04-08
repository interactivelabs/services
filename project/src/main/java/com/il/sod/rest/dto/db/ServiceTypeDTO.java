package com.il.sod.rest.dto.db;

import java.util.Set;

public class ServiceTypeDTO {
	private int idServiceType;
	private String description;
	private String name;
	private double price;
	private int time;
	private Set<Integer> services;
	private Set<ServiceTypeSpecDTO> serviceTypeSpecs;
	private Set<Integer> serviceTypeTasks;
	private Set<Integer> orderTypes;
	
	public int getIdServiceType() {
		return idServiceType;
	}
	public void setIdServiceType(int idServiceType) {
		this.idServiceType = idServiceType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Set<Integer> getServiceTypeTasks() {
		return serviceTypeTasks;
	}
	public void setServiceTypeTasks(Set<Integer> serviceTypeTasks) {
		this.serviceTypeTasks = serviceTypeTasks;
	}
	public Set<Integer> getServices() {
		return services;
	}
	public void setServices(Set<Integer> services) {
		this.services = services;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Set<Integer> getOrderTypes() {
		return orderTypes;
	}
	public void setOrderTypes(Set<Integer> orderTypes) {
		this.orderTypes = orderTypes;
	}
	public Set<ServiceTypeSpecDTO> getServiceTypeSpecs() {
		return serviceTypeSpecs;
	}
	public void setServiceTypeSpecs(Set<ServiceTypeSpecDTO> serviceTypeSpecs) {
		this.serviceTypeSpecs = serviceTypeSpecs;
	}
}