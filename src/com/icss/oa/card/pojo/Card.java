package com.icss.oa.card.pojo;

public class Card {
    private Integer cardId;

    private String cardName;

    private String cardSex;

    private String cardPhone;

    private String cardCareer;

    private String cardAddress;

    private String cardEmail;

    private String cardIntro;

    private Team team;

	public Card() {
		super();
	}

	public Card(Integer cardId, String cardName, String cardSex, String cardPhone, String cardCareer,
			String cardAddress, String cardEmail, String cardIntro, Team team) {
		super();
		this.cardId = cardId;
		this.cardName = cardName;
		this.cardSex = cardSex;
		this.cardPhone = cardPhone;
		this.cardCareer = cardCareer;
		this.cardAddress = cardAddress;
		this.cardEmail = cardEmail;
		this.cardIntro = cardIntro;
		this.team = team;
	}

	public Card(String cardName, String cardSex, String cardPhone, String cardCareer, String cardAddress,
			String cardEmail, String cardIntro, Team team) {
		super();
		this.cardName = cardName;
		this.cardSex = cardSex;
		this.cardPhone = cardPhone;
		this.cardCareer = cardCareer;
		this.cardAddress = cardAddress;
		this.cardEmail = cardEmail;
		this.cardIntro = cardIntro;
		this.team = team;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardSex() {
		return cardSex;
	}

	public void setCardSex(String cardSex) {
		this.cardSex = cardSex;
	}

	public String getCardPhone() {
		return cardPhone;
	}

	public void setCardPhone(String cardPhone) {
		this.cardPhone = cardPhone;
	}

	public String getCardCareer() {
		return cardCareer;
	}

	public void setCardCareer(String cardCareer) {
		this.cardCareer = cardCareer;
	}

	public String getCardAddress() {
		return cardAddress;
	}

	public void setCardAddress(String cardAddress) {
		this.cardAddress = cardAddress;
	}

	public String getCardEmail() {
		return cardEmail;
	}

	public void setCardEmail(String cardEmail) {
		this.cardEmail = cardEmail;
	}

	public String getCardIntro() {
		return cardIntro;
	}

	public void setCardIntro(String cardIntro) {
		this.cardIntro = cardIntro;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", cardName=" + cardName + ", cardSex=" + cardSex + ", cardPhone=" + cardPhone
				+ ", cardCareer=" + cardCareer + ", cardAddress=" + cardAddress + ", cardEmail=" + cardEmail
				+ ", cardIntro=" + cardIntro + ", team=" + team + "]";
	}

}