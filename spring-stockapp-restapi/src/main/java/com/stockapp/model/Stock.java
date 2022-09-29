package com.stockapp.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Stock {

	@Id
	@GeneratedValue(generator = "stock_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "stock_gen", initialValue = 1, allocationSize = 1)
	private Integer stockId;

	//stock name should not be null or empty
	//stock name should have minimum 4 characters
	@NotEmpty // constrain
	@Size(min = 3,max=8,message = "stock name should have minimum 4 and max 8 characters")
	private String stockName;

	//gross profit
	@PositiveOrZero
//	@NotEmpty
	private double profit;
	
	
	//gross quantity 
//	@NotEmpty
//	@PositiveOrZero
	private int stockQuantity; // no of quantity

	// one stock is having one details
	@JoinColumn(name = "detail_id")
	@OneToOne(cascade = CascadeType.ALL)
	private Detail detail;

	// many stock can buy many buyers
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//to create a junction table
	@JoinTable(name = "stock_buyer", joinColumns = @JoinColumn(name = "stock_id"), inverseJoinColumns = @JoinColumn(name = "buyer_id"))
	private Set<Buyer> buyers;

	// one stock can have many options
	// like shortterm,longterm,weekterm,day
	@JoinColumn(name = "stock_id")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Set<Term> term;

	public Stock() {
		super();
	}

	// buyers added for many to many
	public Stock(String stockName, double profit, int stockQuantity, Detail detail, Set<Buyer> buyers, Set<Term> term) {
		super();
		this.stockName = stockName;
		this.profit = profit;
		this.stockQuantity = stockQuantity;
		this.detail = detail;
		this.buyers = buyers;
		this.term = term;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public Set<Buyer> getBuyers() {
		return buyers;
	}

	public void setBuyers(Set<Buyer> buyers) {
		this.buyers = buyers;
	}

	public Set<Term> getTerm() {
		return term;
	}

	public void setTerm(Set<Term> term) {
		this.term = term;
	}

	@Override
	public String toString() {
		return "Stock [stockName=" + stockName + ", profit=" + profit + ", stockQuantity=" + stockQuantity + ", detail="
				+ detail + ", term=" + term + "]";
	}

}
