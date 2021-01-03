// Student Name: Vikram Thangavel
// This lab was was done individually
// URL : https://www.youtube.com/watch?v=N97ECNd2JME

package com.example.capsapp;

import java.util.List;
import java.util.Map;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

public class Game {
    private CountryDB db;
    private String answer;

    public Game(){

        this.db = new CountryDB();
    }

    public String qa(){

        String question;
        List<String> capitals = db.getCapitals();

        int n = capitals.size();
        int index = (int) (n * Math.random());

        String c = capitals.get(index);
        Map<String, Country> data = db.getData();
        Country ref = data.get(c);

        String countryName = ref.getName();
        String capitalName = ref.getCapital();

        if(Math.random() < 0.5){
            question = "What is the capital of " + countryName + "?";
            this.answer = capitalName;
        }

        else {
            question = capitalName + " is the capital of what country?";
            this.answer = countryName;
        }

        System.out.println(ref);
        return question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.qa());
    }
}