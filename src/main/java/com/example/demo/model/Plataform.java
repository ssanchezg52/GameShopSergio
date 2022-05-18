package com.example.demo.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Plataform {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long plataformId;
	private String name;
	@ManyToMany(mappedBy = "plataforms")
	private List<Game> game;
	public Plataform(String name, List<Game> game) {
		super();
		this.name = name;
		this.game = game;
	}
	public Plataform() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getPlataformId() {
		return plataformId;
	}
	public void setPlataformId(Long plataformId) {
		this.plataformId = plataformId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Game> getGame() {
		return game;
	}
	public void setGame(List<Game> game) {
		this.game = game;
	}
	@Override
	public int hashCode() {
		return Objects.hash(game, name, plataformId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plataform other = (Plataform) obj;
		return Objects.equals(game, other.game) && Objects.equals(name, other.name)
				&& Objects.equals(plataformId, other.plataformId);
	}
	@Override
	public String toString() {
		return "Plataform [plataformId=" + plataformId + ", name=" + name + ", game=" + game + "]";
	}
	
}
