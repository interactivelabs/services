package com.il.sod.db.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Service database table.
 *
 */
@Entity
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idService;

	private String description;

	private String name;

	private double price;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	private int time;

	//bi-directional many-to-one association to ServiceType
	@ManyToOne
	@JoinColumn(name="idServiceType")
	private ServiceType serviceType;

	//bi-directional many-to-one association to ServiceSpec
	@OneToMany(mappedBy="service", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<ServiceSpec> serviceSpecs;

	//bi-directional many-to-one association to ServiceTask
	@OneToMany(mappedBy="service", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<ServiceTask> serviceTasks;
	
	//bi-directional many-to-one association to Order
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idOrder")
	private Order order;

	public Service() {
	}

	public int getIdService() {
		return this.idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ServiceType getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public Set<ServiceSpec> getServiceSpecs() {
		return this.serviceSpecs;
	}

	public void setServiceSpecs(Set<ServiceSpec> serviceSpecs) {
		this.serviceSpecs = serviceSpecs;
	}

	public ServiceSpec addServiceSpec(ServiceSpec serviceSpec) {
		if (serviceSpecs == null){
			serviceSpecs = new HashSet<>();
		}
		getServiceSpecs().add(serviceSpec);
		serviceSpec.setService(this);

		return serviceSpec;
	}

	public ServiceSpec removeServiceSpec(ServiceSpec serviceSpec) {
		getServiceSpecs().remove(serviceSpec);
		serviceSpec.setService(null);

		return serviceSpec;
	}

	public Set<ServiceTask> getServiceTasks() {
		return this.serviceTasks;
	}

	public void setServiceTasks(Set<ServiceTask> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}

	public ServiceTask addServiceTask(ServiceTask serviceTask) {
		if (serviceTasks == null){
			serviceTasks = new HashSet<>();
		}
		getServiceTasks().add(serviceTask);
		serviceTask.setService(this);

		return serviceTask;
	}

	public ServiceTask removeServiceTask(ServiceTask serviceTask) {
		getServiceTasks().remove(serviceTask);
		serviceTask.setService(null);

		return serviceTask;
	}
	@Override
	public Integer getId() {
		return this.idService;
	}

	@Override
	public Service setId(Integer id) {
		this.idService = id;
		return this;
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
	
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}