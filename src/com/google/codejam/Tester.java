package com.google.codejam;

import java.util.function.Predicate;

public class Tester {

    static Predicate<Integer> greaterThan5 = x -> x > 5;

    public static void main(String[] args) throws Exception {
        for (int i=0; i<10; i++) {
            System.out.println(i + " " + greaterThan5.test(i));
        }
//        JFrame frame = new JFrame("hehe");
//        JButton button = new JButton("Click me");
//        button.addActionListener(event -> System.out.println(name));
//        frame.add(button);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);

        check((int x) -> x > 5);


    }

//    static boolean check(Predicate<Integer> predicate) {
//        return true;
//    }

    static boolean check(IntPred suck) {
        return true;
    }

    static boolean check(IntPred1 suck) {
        return true;
    }

    interface IntPred {
        boolean test(int val);
    }

    interface IntPred1 {
        boolean jb(Integer val);
    }

}
