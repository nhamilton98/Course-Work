package main;

public class LoopsTester
{
    public static void main(String[] args)
    {
        forLoopOne();
        System.out.println();
        whileLoopOne();
        System.out.println();
        doWhileLoopOne();
        System.out.println();
        forLoopTwo();
        System.out.println();
        whileLoopTwo();
        System.out.println();
        doWhileLoopTwo();
        System.out.println();
    }

    // Print all [75, 101]
    private static void forLoopOne()
    {
        for (int i = 75; i <= 101; i++)
            System.out.println(i);
    }

    // Print even [50, 100]
    private static void whileLoopOne()
    {
        int x = 50;
        while (x <= 100)
        {
            System.out.println(x);
            x++;
        }
    }

    // Print all [1, 100] and squares where square is less than 5600
    private static void doWhileLoopOne()
    {
        int x = 1;
        do
        {
            System.out.println(x + ", " + Math.pow(x, 2));
            x++;
        } while (Math.pow(x, 2) < 5600 && x <= 100);
    }

    // Print all [1, 200] by threes where number is divisible by five
    private static void forLoopTwo()
    {
        for (int i = 1; i <= 200; i += 3)
        {
            if (i % 5 == 0)
                System.out.println(i);
        }
    }

    // Decrement [200, 1] and print lowest number evenly divisible by three and five
    private static void whileLoopTwo()
    {
        int x = 200, temp = -1;
        while (x > 0)
        {
            if (x % 3 == 0 && x % 5 == 0)
                temp = x;
            x--;
        }
        System.out.println(temp);
    }

    // Print all [100, 1] where digits sum to seven
    private static void doWhileLoopTwo()
    {
        int x = 100, sum, temp;
        do
        {
            sum = 0;
            temp = x;
            while (temp > 0)
            {
                sum += temp % 10;
                temp /= 10;
            }
            if (sum == 7)
                System.out.println(x);
            x--;
        } while (x > 0);
    }
}

