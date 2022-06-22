package com.geekbrains.lesson4.hw;

public class Triangle {
    public static double calcAreaTriangle(double a, double b, double c) throws Exception {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new Exception("Стороны треугольника должны быть положительными!!!");
        }

        double p = (a + b + c) / 2;

        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
