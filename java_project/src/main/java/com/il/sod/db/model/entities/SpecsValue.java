package com.il.sod.db.model.entities;

import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;


/**
 * The persistent class for the SpecsValues database table.
 */
@Entity
@Table(name = "SpecsValues")
@NamedQuery(name = "SpecsValue.findAll", query = "SELECT s FROM SpecsValue s")
public class SpecsValue implements IEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idSpecsValues;

  private int idSupplyType;

  private int type; // 1 = value; 2 = product

  private String value;

  private int prefered;

  //bi-directional many-to-one association to Spec
  @ManyToOne
  @JoinColumn(name = "idSpecs")
  private Spec spec;

  private double serviceIncrement;

  private double specPrice;

  private int costType; // 0 = increment; 1 = specPrice

  public SpecsValue() {
  }

  public int getIdSpecsValues() {
    return this.idSpecsValues;
  }

  public void setIdSpecsValues(int idSpecsValues) {
    this.idSpecsValues = idSpecsValues;
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Spec getSpec() {
    return this.spec;
  }

  public void setSpec(Spec spec) {
    this.spec = spec;
  }

  @Override
  public Integer getId() {
    return this.idSpecsValues;
  }

  @Override
  public SpecsValue setId(Integer id) {
    this.idSpecsValues = id;
    return this;
  }

  public int getIdSupplyType() {
    return idSupplyType;
  }

  public void setIdSupplyType(int idSupplyType) {
    this.idSupplyType = idSupplyType;
  }

  public double getServiceIncrement() {
    return serviceIncrement;
  }

  public void setServiceIncrement(double serviceIncrement) {
    this.serviceIncrement = serviceIncrement;
  }

  public int getPrefered() {
    return prefered;
  }

  public void setPrefered(int prefered) {
    this.prefered = prefered;
  }

  public double getSpecPrice() {
    return specPrice;
  }

  public void setSpecPrice(double specPrice) {
    this.specPrice = specPrice;
  }

  public int getCostType() {
    return costType;
  }

  public void setCostType(int costType) {
    this.costType = costType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SpecsValue that = (SpecsValue) o;
    return idSpecsValues == that.idSpecsValues &&
            idSupplyType == that.idSupplyType &&
            type == that.type &&
            prefered == that.prefered &&
            Double.compare(that.serviceIncrement, serviceIncrement) == 0 &&
            Double.compare(that.specPrice, specPrice) == 0 &&
            costType == that.costType &&
            Objects.equal(value, that.value) &&
            Objects.equal(spec, that.spec);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(idSpecsValues, idSupplyType, type, value, prefered, spec, serviceIncrement, specPrice, costType);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
            .append("idSpecsValues", idSpecsValues)
            .append("idSupplyType", idSupplyType)
            .append("type", type)
            .append("value", value)
            .append("prefered", prefered)
            .append("serviceIncrement", serviceIncrement)
            .append("specPrice", specPrice)
            .append("costType", costType)
            .toString();
  }
}