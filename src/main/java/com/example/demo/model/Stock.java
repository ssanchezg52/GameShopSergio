package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stockId;
	private Long amount;
	@ManyToOne()
	@JoinColumn(name = "id_edition")
	private Edition edition;
	public Stock(Long amount, Edition edition) {
		super();
		this.amount = amount;
		this.edition = edition;
	}
	public Stock() {
	}
	public Long getId_stock() {
		return stockId;
	}
	public void setId_stock(Long id_stock) {
		this.stockId = id_stock;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Edition getEdition() {
		return edition;
	}
	public void setEdition(Edition edition) {
		this.edition = edition;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, edition, stockId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(edition, other.edition)
				&& Objects.equals(stockId, other.stockId);
	}
	@Override
	public String toString() {
		return "Stock [id_stock=" + stockId + ", amount=" + amount + ", edition=" + edition + "]";
	}
	
}
