package com.example.demo.model;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Edition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long editionId;
	private String name;
	private Float price;
	@ManyToOne()
	@JoinColumn(name="game_id")
	private Game game;
	public Edition(String name, Float price, Game game) {
		super();
		this.name = name;
		this.price = price;
		this.game = game;
	}
	public Edition() {
		super();
	}
	public Long getEditionId() {
		return editionId;
	}
	public void setEditionId(Long editionId) {
		this.editionId = editionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public ArrayList<Plataform> getPlataformArray(){
		return new ArrayList<>(this.game.getPlataform());
	}
	public void setPlataformArray(ArrayList<Plataform> plataformArray){
		this.game.setPlataform(plataformArray);
	}
	@Override
	public int hashCode() {
		return Objects.hash(editionId, game, name, price);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edition other = (Edition) obj;
		return Objects.equals(editionId, other.editionId) && Objects.equals(game, other.game)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price);
	}
	@Override
	public String toString() {
		return "Edition [editionId=" + editionId + ", name=" + name + ", price=" + price + ", game=" + game + "]";
	}
	
}
