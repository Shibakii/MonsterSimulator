package com.example.meaburro;

import java.io.Serializable;

public class Character implements Serializable {

    String name;
    String race;
    private int health;
    private int maxHealth;
    private int atack;
    private int defense;
    private int exp;
    private int maxExp;
    private int gold;
    private int level;
    private int img;

    public Character (String race, int health, int atack, int defense, int img)
    {
        this.name = name;
        this.race = race;
        this.health = health;
        this.maxHealth = health;
        this.atack = atack;
        this.defense = defense;
        this.img = img;
        this.exp = 0;
        this.maxExp = 100;
        this.gold = 0;
        this.level = 1;
    }

    public void TryLevelUp ()
    {
        if(exp >= maxExp)
        {
            this.level += 1;
            this.maxExp = (int) (maxExp * 1.2f);
            this.health = maxHealth;
            this.exp = 0;

            int upgradeStats = randomNumber();

            if(upgradeStats == 1)
            {
                 // 1 makes an Upgrade to the Atack
                atack += atack + (level * atack / 3);
            }else if(upgradeStats == 2)
            {
                // 2 makes an Upgrade to the Health
                health += health + (level * health / 3);
            }else{
                // 3 makes an Upgrade to the Defense
                defense += 1;
            }
        }
    }

    private int randomNumber()
    {
        return (int) (Math.random() * 3 + 1);
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

    public void quitarVida(int health) {
        this.health -= health;
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp += exp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp += maxExp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold += gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level += level;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
