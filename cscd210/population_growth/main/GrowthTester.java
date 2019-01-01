package main;

import java.util.Random;
import java.util.Scanner;

public class GrowthTester
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("TOTAL LIFECYCLES:");
        int cycles = Integer.parseInt(in.nextLine());

        int[] pop = new int[cycles];
        int cur = 10;
        Random rand = new Random();

        for (int i = 0; i < cycles; i++)
        {
            if (cur > 0)
            {
                cur = nextLifecycle(rand, cur);
                pop[i] = cur;
            }
        }

        printResults(pop);
        
        in.close();
    }

    private static int nextLifecycle(final Random rand, final int cur)
    {
        if (rand == null)
            throw new IllegalArgumentException("Invalid Random.");
        if (cur < 0)
            throw new IllegalArgumentException("Invalid population.");

        int ret = cur;
        int roll = rand.nextInt(6) + 1;
        switch(roll)
        {
            case 1:
                ret++;
                System.out.println("1 Birth.");
                break;
            case 2:
                ret += 2;
                System.out.println("Birth of twins.");
                break;
            case 3:
                System.out.println("No change.");
                break;
            case 4:
                System.out.println("No change.");
                break;
            case 5:
                ret--;
                System.out.println("1 death.");
                break;
            case 6:
                int died = rand.nextInt(6) + 1;
                if (died > ret)
                    died = ret;
                ret -= died;
                System.out.println("Plague struck. " + died + " death(s).");
                break;
        }
        return ret;
    }

    private static void printResults(final int[] population)
    {
        for (int i = 0; i < population.length; i++)
            if (population[i] > 0)
                System.out.println((i + 1) + " - " + population[i]);
    }
}