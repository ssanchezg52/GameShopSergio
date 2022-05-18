package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Galery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long galeryId;
	private String image;
	@ManyToOne()
	@JoinColumn(name="game_id")
	private Game game;
	
	public Galery(String image, Game game) {
		super();
		this.image = image;
		this.game = game;
	}
	
	public Galery() {
		super();
	}

	public Long getGaleryId() {
		return galeryId;
	}
	public void setGaleryId(Long galeryId) {
		this.galeryId = galeryId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public int hashCode() {
		return Objects.hash(galeryId, game, image);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Galery other = (Galery) obj;
		return Objects.equals(galeryId, other.galeryId) && Objects.equals(game, other.game)
				&& Objects.equals(image, other.image);
	}

	@Override
	public String toString() {
		return "Galery [galeryId=" + galeryId + ", image=" + image + ", game=" + game + "]";
	}
	
}
