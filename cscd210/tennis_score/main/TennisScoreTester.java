package main;

import java.util.Scanner;

public class TennisScoreTester
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("YOUR POINTS: ");
        int myPoints = Integer.parseInt(in.nextLine());
        System.out.println("OPPONENT'S POINTS: ");
        int oppPoints = Integer.parseInt(in.nextLine());

        calcScore(myPoints, oppPoints);

        in.close();
    }

    private static void calcScore(final int points1, final int points2)
    {
        if (points1 < 0 || points2 < 0)
            throw new IllegalArgumentException("Invalid score(s).");

        StringBuilder str = new StringBuilder();
        str.append("\nSCORE: ");

        if (points1 > 3 || points2 > 3)
        {
            if (points1 == points2)
                str.append("Deuce");
            else if (points1 - points2 == 1)
                str.append("Advantage In");
            else if (points2 - points1 == 1)
                str.append("Advantage Out");
            else if (points1 - points2 > 1)
                str.append("You Win");
            else
                str.append("You Lose");
        }
        else
        {
            String score1 = calcScore(points1);
            String score2 = calcScore(points2);

            str.append(score1 + " - " + score2);
        }

        System.out.println(str);
    }

    private static String calcScore(final int points)
    {
        switch (points)
        {
            case 0:
                return "Love";
            case 1:
                return "15";
            case 2:
                return "30";
            case 3:
                return "40";
            default:
                throw new IllegalArgumentException("Invalid score.");
        }
    }
}