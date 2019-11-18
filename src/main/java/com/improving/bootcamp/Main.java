package com.improving.bootcamp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players sir/madam?");
        int numOfPlayers =  scanner.nextInt();
        scanner.nextLine();

        Game game = new Game(numOfPlayers);
        game.play();
    }
}
