package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import utils.ArrayUtils;
import utils.StatsUtils;

public class StatsTester
{
    private static int[] list = new int[0];

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(System.in);

        int choice;
        do
        {
            printMenu();
            choice = Integer.parseInt(in.nextLine());

            int num;
            File file;
            switch(choice)
            {
                case 1:
                    System.out.println("VALUE:");
                    num = Integer.parseInt(in.nextLine());
                    list = ArrayUtils.addElement(list, num);
                    break;
                case 2:
                    System.out.println("INDEX:");
                    num = Integer.parseInt(in.nextLine());
                    list = ArrayUtils.removeElement(list, num);
                    break;
                case 3:
                    ArrayUtils.printArray(list);
                    break;
                case 4:
                    System.out.println("MEAN");
                    System.out.println("----");
                    System.out.println(StatsUtils.computeMean(list));
                    break;
                case 5:
                    System.out.println("MEDIAN");
                    System.out.println("------");
                    System.out.println(StatsUtils.computeMedian(list));
                    break;
                case 6:
                    System.out.println("MIDPOINT");
                    System.out.println("--------");
                    System.out.println(StatsUtils.computeMidpoint(list));
                    break;
                case 7:
                    System.out.println("STANDARD DEVIATION");
                    System.out.println("------------------");
                    System.out.println(StatsUtils.computeStdDev(list));
                    break;
                case 8:
                    System.out.println("FILENAME:");
                    file = new File(in.nextLine());
                    if (file.canRead())
                    {
                        readFromFile(file);
                        System.out.println("File read successful.");
                    }
                    else
                        System.out.println("Invalid file.");
                    break;
                case 9:
                    System.out.println("FILENAME:");
                    file = new File(in.nextLine());
                    if (file.canWrite())
                    {
                        writeToFile(file);
                        System.out.println("File write successful.");
                    }
                    else
                        System.out.println("Invalid file.");
                    break;
                case 0:
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            System.out.println();
        } while (choice != 0);

        in.close();
    }

    private static void printMenu()
    {
        System.out.println("STATISTICS MENU");
        System.out.println("------------------------------");
        System.out.println("1.) ADD VALUE");
        System.out.println("2.) DELETE VALUE");
        System.out.println("3.) DISPLAY LIST");
        System.out.println("4.) COMPUTE MEAN");
        System.out.println("5.) COMPUTE MEDIAN");
        System.out.println("6.) COMPUTE MIDPOINT");
        System.out.println("7.) COMPUTE STANDARD DEVIATION");
        System.out.println("8.) SAVE TO FILE");
        System.out.println("9.) READ FROM FILE");
        System.out.println("\n0.) QUIT");
        System.out.println("------------------------------");
        System.out.println("CHOICE:");
    }

    private static void readFromFile(File file) throws IOException
    {
        if (file == null)
            throw new IllegalArgumentException("Invalid file.");

        list = new int[0];
        Scanner in = new Scanner(file);
        while (in.hasNext())
            ArrayUtils.addElement(list, Integer.parseInt(in.nextLine()));

        in.close();
    }

    private static void writeToFile(File file) throws IOException
    {
        if (file == null)
            throw new IllegalArgumentException("Invalid file.");

        PrintStream write = new PrintStream(file);
        for (int i = 0; i < list.length; i++)
            write.println(list[i]);

        write.close();
    }
}

