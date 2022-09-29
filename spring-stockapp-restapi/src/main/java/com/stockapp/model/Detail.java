package com.stockapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Detail {

	@Id
	@GeneratedValue(generator = "detail_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "detail_gen", initialValue = 1, allocationSize = 1)
	private Integer stockdetailId;

//	@NotEmpty
//	@Size(min = 3, max = 8, message = "type should be minimum of 3 characters")
	private String stockType; // NSE BSE

//	@PositiveOrZero
//	@NotEmpty
	private double stockCurrentPrice; // price

	public Detail() {
		super();
	}

	public Detail(String stockType, double stockCurrentPrice) {
		super();
		this.stockType = stockType;
		this.stockCurrentPrice = stockCurrentPrice;
	}

	public Integer getStockdetailId() {
		return stockdetailId;
	}

	public void setStockdetailId(Integer stockdetailId) {
		this.stockdetailId = stockdetailId;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public double getStockCurrentPrice() {
		return stockCurrentPrice;
	}

	public void setStockCurrentPrice(double stockCurrentPrice) {
		this.stockCurrentPrice = stockCurrentPrice;
	}

	@Override
	public String toString() {
		return "Detail [stockType=" + stockType + ", stockCurrentPrice=" + stockCurrentPrice + "]";
	}

}
