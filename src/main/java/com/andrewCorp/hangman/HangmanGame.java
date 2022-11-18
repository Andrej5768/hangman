package com.andrewCorp.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HangmanGame {

    private char[] result;
    private char[] splitWord;
    private int wrongAnswer = 0;
    private Set<Character> usedLetter;
    private final ArrayList<String> WORD_LIST;

    public HangmanGame(String fileName) {
        WORD_LIST = new ArrayList<>();
        fillWordListFromFile(fileName);
    }

    public void start() {
        String word = getRandomWorldFromList(WORD_LIST);
        splitWord = word.toCharArray();
        result = fillResultArray();
        usedLetter = new HashSet<>();
        wrongAnswer = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите 'старт' что бы начать игру");
            System.out.println("Или 'конец' что бы завершить");
            String message = scanner.next();
            if (message.equalsIgnoreCase("старт")) {
                System.out.println("Игра началась");
                System.out.print("Слово:");
                System.out.println(result);
                game(scanner);
                break;
            } else if (message.equalsIgnoreCase("конец")) {
                scanner.close();
                System.out.println("Игра окончена");
                break;
            } else {
                System.out.println("Неправильный ввод. Повторите");
            }
        }
    }

    private void game(Scanner scanner) {
        while (true) {
            checkLetterInWord(scanner.next().charAt(0));

            if (Arrays.equals(splitWord, result)) {
                System.out.println("Поздравляю, Вы выиграли");
                break;
            } else {
                System.out.println(result);
                printImage(wrongAnswer);
                System.out.println("Количество ошибок: " + wrongAnswer);
            }

            if (wrongAnswer >= 6) {
                System.out.println("Игра окончена, Вы проиграли");
                break;
            }
        }
        System.out.println("Начать сначала? да/нет");
        if (scanner.next().equals("да")) {
            start();
        }
    }

    private void checkLetterInWord(char letter) {
        if (!usedLetter.contains(letter)) {
            int correctLetter = 0;
            usedLetter.add(letter);
            for (int i = 0; i < splitWord.length; i++) {
                if (splitWord[i] == letter) {
                    result[i] = letter;
                    correctLetter++;
                }
            }
            if (correctLetter == 0) {
                wrongAnswer++;
                System.out.println("Такой буквы нет");
            } else {
                System.out.println("Такая буква есть");
            }
        } else {
            System.out.print("Вы уже использовали эти буквы");
            System.out.println(usedLetter);
        }
    }

    private char[] fillResultArray() {
        result = splitWord.clone();
        for (int i = 0; i < splitWord.length; i++) {
            result[i] = '_';
        }
        return result;
    }

    private void fillWordListFromFile(String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader br = new BufferedReader(fileReader);
            while (br.ready()) {
                this.WORD_LIST.add(br.readLine());
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    private String getRandomWorldFromList(ArrayList<String> list) {
        int flag = (int) (Math.random() * (list.size()));
        return list.get(flag);
    }

    private void printImage(int wrongAnswer) {
        ArrayList<String> list = new ArrayList<>();
        list.add("""
                   +---+
                   |   |
                       |
                       |
                       |
                       |
                =========""".indent(1));
        list.add("""
                   +---+
                   |   |
                   O   |
                       |
                       |
                       |
                =========""".indent(1));
        list.add("""
                   +---+
                   |   |
                   O   |
                   |   |
                       |
                       |
                =========""".indent(1));
        list.add("""
                   +---+
                   |   |
                   O   |
                  /|   |
                       |
                       |
                =========""".indent(1));
        list.add("""
                   +---+
                   |   |
                   O   |
                  /|\\  |
                       |
                       |
                =========""".indent(1));
        list.add("""
                   +---+
                   |   |
                   O   |
                  /|\\  |
                  /    |
                       |
                =========""".indent(1));
        list.add("""
                   +---+
                   |   |
                   O   |
                  /|\\  |
                  / \\  |
                       |
                =========""".indent(1));

        System.out.println(list.get(wrongAnswer));
    }
}
