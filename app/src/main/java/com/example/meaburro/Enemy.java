package com.example.meaburro;

import java.io.Serializable;

public class Enemy implements Serializable {

    String name;
    String race;
    private int health;
    private int atack;
    private int defense;
    private int givedExp;
    private int givedGold;
    private int img;

    public Enemy(String name, String race, int health, int atack, int defense, int img, int exp, int gold) {
        this.name = name;
        this.race = race;
        this.health = health;
        this.atack = atack;
        this.defense = defense;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAtack() {
        return atack;
    }

    public void setAtack(int atack) {
        this.atack = atack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getGivedExp() {
        return givedExp;
    }

    public void setGivedExp(int givedExp) {
        this.givedExp = givedExp;
    }

    public int getGivedGold() {
        return givedGold;
    }

    public void setGivedGold(int givedGold) {
        this.givedGold = givedGold;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void quitarVidaEnemigo(int damage)
    {
        this.health -= damage;
    }
}
