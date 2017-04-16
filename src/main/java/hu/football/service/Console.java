package hu.football.service;

import java.util.Scanner;

/**
 * @author Peter_Fazekas on 2017.04.16..
 */
public class Console {

    public int readInt() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public String read() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
