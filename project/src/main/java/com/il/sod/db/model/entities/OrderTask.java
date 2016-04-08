package com.il.sod.db.model.entities;

import java.util.Date;
import java.util.Set;

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
 * The persistent class for the OrderTask database table.
 *
 */
@Entity
@NamedQuery(name="OrderTask.findAll", query="SELECT o FROM OrderTask o")
public class OrderTask implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrderTask;

	private String comments;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	//bi-directional many-to-one association to AssetTaskOrder
	@OneToMany(mappedBy="orderTask", fetch=FetchType.EAGER)
	private Set<AssetTaskOrder> assetTaskOrders;

	//bi-directional many-to-one association to EmployeeTaskOrder
	@OneToMany(mappedBy="orderTask", fetch=FetchType.EAGER)
	private Set<EmployeeTaskOrder> employeeTaskOrders;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="idTask")
	private Task task;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idOrder")
	private Order order;

	public OrderTask() {
	}

	public int getIdOrderTask() {
		return this.idOrderTask;
	}

	public void setIdOrderTask(int idOrderTask) {
		this.idOrderTask = idOrderTask;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Set<AssetTaskOrder> getAssetTaskOrders() {
		return this.assetTaskOrders;
	}

	public void setAssetTaskOrders(Set<AssetTaskOrder> assetTaskOrders) {
		this.assetTaskOrders = assetTaskOrders;
	}

	public AssetTaskOrder addAssetTaskOrder(AssetTaskOrder assetTaskOrder) {
		getAssetTaskOrders().add(assetTaskOrder);
		assetTaskOrder.setOrderTask(this);

		return assetTaskOrder;
	}

	public AssetTaskOrder removeAssetTaskOrder(AssetTaskOrder assetTaskOrder) {
		getAssetTaskOrders().remove(assetTaskOrder);
		assetTaskOrder.setOrderTask(null);

		return assetTaskOrder;
	}

	public Set<EmployeeTaskOrder> getEmployeeTaskOrders() {
		return this.employeeTaskOrders;
	}

	public void setEmployeeTaskOrders(Set<EmployeeTaskOrder> employeeTaskOrders) {
		this.employeeTaskOrders = employeeTaskOrders;
	}

	public EmployeeTaskOrder addEmployeeTaskOrder(EmployeeTaskOrder employeeTaskOrder) {
		getEmployeeTaskOrders().add(employeeTaskOrder);
		employeeTaskOrder.setOrderTask(this);

		return employeeTaskOrder;
	}

	public EmployeeTaskOrder removeEmployeeTaskOrder(EmployeeTaskOrder employeeTaskOrder) {
		getEmployeeTaskOrders().remove(employeeTaskOrder);
		employeeTaskOrder.setOrderTask(null);

		return employeeTaskOrder;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public Integer getId() {
		return this.idOrderTask;
	}

	@Override
	public OrderTask setId(Integer id) {
		this.idOrderTask = id;
		return this;
	}
}