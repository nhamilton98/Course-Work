package main;

import java.util.Scanner;

public class ConversionTester
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        String name = getName(in);
        double height = getHeight(in);
        double weight = getWeight(in);

        System.out.println("\nNAME: " + name);
        System.out.println("HEIGHT: " + height * 2.54 + " cm");
        System.out.println("WEIGHT: " + weight * .453592 + " kg");

        in.close();
    }

    private static String getName(final Scanner in)
    {
        if (in == null)
            throw new IllegalArgumentException("Invalid Scanner.");

        System.out.println("NAME: ");
        return in.nextLine();
    }

    private static double getHeight(final Scanner in)
    {
        if (in == null)
            throw new IllegalArgumentException("Invalid Scanner.");

        System.out.println("HEIGHT IN INCHES: ");
        return Double.parseDouble(in.nextLine());
    }

    private static double getWeight(final Scanner in)
    {
        if (in == null)
            throw new IllegalArgumentException("Invalid Scanner.");

        System.out.println("WEIGHT IN POUNDS: ");
        return Double.parseDouble(in.nextLine());
    }
}