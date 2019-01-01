package methods;

import java.util.Random;

public class Methods
{
    public static double circleArea(final double radius) { return Math.PI * Math.pow(radius, 2); }

    public static int smallest(final int num1, final int num2, final int num3)
    {
        if (num1 < num2 && num1 < num3)
            return num1;
        else if (num2 < num1 && num2 < num3)
            return num2;
        else
            return num3;
    }

    public static boolean isPositive(final int num) { return num >= 0; }

    public static String stringReverse(final String str)
    {
        StringBuilder build = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i++)
            build.append(str.charAt(i));
        return build.toString();
    }

    public static int AddEmUp(final int start, final int end, final int incr)
    {
    	int sum = 0;
        for (int i = start; i < end; i += incr)
            sum += i;
        return sum;
    }

    public static void loopNums(final int bound1, final int bound2)
    {
        int start, end;
        if (bound1 < bound2)
        {
            start = bound1;
            end = bound2;
        }
        else
        {
            start = bound2;
            end = bound1;
        }

        while (start <= end)
        {
            System.out.println(start);
            start++;
        }
    }

    public static String evenChar(final String str)
    {
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < str.length(); i += 2)
            build.append(str.charAt(i));
        return build.toString();
    }

    public static int[] arrayFill(final int[] array, final int bound)
    {
        Random rand = new Random(bound);
        for (int i = 0; i < array.length; i++)
            array[i] = rand.nextInt();
        return array;
    }

    public static void squareOfAsterisks(final int side)
    {
        for (int i = 0; i < side; i++)
        {
            for (int j = 0; j < side; j++)
                System.out.print("*");
            System.out.println();
        }
    }
}

