package org.example;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println("Демонстрация работы калькулятора:");
        System.out.println("6 + 4 = " + calculator.add(6, 4));
        System.out.println("9 - 5 = " + calculator.subtract(9, 5));
        System.out.println("2 * 3 = " + calculator.multiply(2, 3));
        System.out.println("8 / 2 = " + calculator.divide(8, 2));

        try {
            System.out.println("Попытка деления на ноль:");
            System.out.println("10 / 0 = " + calculator.divide(10, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}