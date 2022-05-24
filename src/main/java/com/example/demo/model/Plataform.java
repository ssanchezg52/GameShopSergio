package com.example.demo.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Plataform {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long plataformId;
	private String name;
	private String urlIcon;
	@JsonIgnore
	@ManyToMany(mappedBy = "plataforms")
	private List<Game> game;

	public Plataform(String name, String urlIcon, List<Game> game) {
		super();
		this.name = name;
		this.urlIcon = urlIcon;
		this.game = game;
	}

	public Plataform() {
		super();
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

	public String getUrlIcon() {
		return urlIcon;
	}

	public void setUrlIcon(String urlIcon) {
		this.urlIcon = urlIcon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(game, name, plataformId, urlIcon);
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
				&& Objects.equals(plataformId, other.plataformId) && Objects.equals(urlIcon, other.urlIcon);
	}

	@Override
	public String toString() {
		return "Plataform [plataformId=" + plataformId + ", name=" + name + ", urlIcon=" + urlIcon + ", game=" + game
				+ "]";
	}

}
