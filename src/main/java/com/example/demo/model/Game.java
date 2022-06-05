package com.example.demo.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gameId;
	private String name;
	private String developer;
	private String gender;
	private Integer minimum_age;
	private Date releaseDate;
	@Column(columnDefinition = "TEXT")
	private String description;
	private String trailer;
	private String coverPage;
	@ManyToMany()
	@JoinTable(name = "game_plataform", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "plataform_id"))
	private List<Plataform> plataforms;

	public Game() {
		super();
	}

	public Game(String name, String developer, String gender, Integer minimum_age, Date releaseDate, String description,
			String trailer, String coverPage, List<Plataform> plataforms) {
		super();
		this.name = name;
		this.developer = developer;
		this.gender = gender;
		this.minimum_age = minimum_age;
		this.releaseDate = releaseDate;
		this.description = description;
		this.trailer = trailer;
		this.coverPage = coverPage;
		this.plataforms = plataforms;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getMinimum_age() {
		return minimum_age;
	}

	public void setMinimum_age(Integer minimum_age) {
		this.minimum_age = minimum_age;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getCoverPage() {
		return coverPage;
	}

	public void setCoverPage(String coverPage) {
		this.coverPage = coverPage;
	}

	public List<Plataform> getPlataform() {
		return plataforms;
	}

	public void setPlataform(ArrayList<Plataform> plataform) {
		this.plataforms = plataform;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coverPage, description, developer, gameId, gender, minimum_age, name, plataforms,
				releaseDate, trailer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Objects.equals(coverPage, other.coverPage) && Objects.equals(description, other.description)
				&& Objects.equals(developer, other.developer) && Objects.equals(gameId, other.gameId)
				&& Objects.equals(gender, other.gender) && Objects.equals(minimum_age, other.minimum_age)
				&& Objects.equals(name, other.name) && Objects.equals(plataforms, other.plataforms)
				&& Objects.equals(releaseDate, other.releaseDate) && Objects.equals(trailer, other.trailer);
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", name=" + name + ", developer=" + developer + ", gender=" + gender
				+ ", minimum_age=" + minimum_age + ", releaseDate=" + releaseDate + ", description=" + description
				+ ", trailer=" + trailer + ", coverPage=" + coverPage + ", plataforms=" + plataforms + "]";
	}

	public boolean thisPlataformExists(String plataformName) {
		List<Plataform> filter = this.plataforms.stream().filter(plataform -> plataform.getName().equals(plataformName)).collect(Collectors.toList());
		return filter.isEmpty();
	}

}
