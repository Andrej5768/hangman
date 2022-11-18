package com.andrewCorp.hangman;

public class Main {
    public static void main(String[] args) {
        HangmanGame hangmanGame = new HangmanGame("src/main/resources/source.txt");
        hangmanGame.start();
    }
}
