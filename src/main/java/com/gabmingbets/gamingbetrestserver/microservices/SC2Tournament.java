package com.gabmingbets.gamingbetrestserver.microservices;

public class SC2Tournament {

	private String link;
	private int id;
	private String name;

	public SC2Tournament(String link, int id, String name) {
		super();
		this.link = link;
		this.id = id;
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
