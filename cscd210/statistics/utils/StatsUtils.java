package utils;

public class StatsUtils
{
    public static double computeMean(int[] array)
    {
        if (array.length < 1)
            throw new IllegalArgumentException("Invalid array.");

        double sum = 0;
        for (int i: array)
            sum += i;
        return sum / (double) array.length;
    }

    public static double computeMedian(int[] array)
    {
        if (array.length < 1)
            throw new IllegalArgumentException("Invalid array.");

        int[] temp = ArrayUtils.cloneArray(array);
        ArrayUtils.sortArray(temp);

        if (temp.length % 2 == 0)
            return ((double) temp[temp.length / 2] + (double) temp[temp.length / 2 + 1]) / 2.0;
        else
            return temp[temp.length / 2];
    }

    public static double computeMidpoint(int[] array)
    {
        if (array.length < 1)
            throw new IllegalArgumentException("Invalid array.");
        if (array.length == 1)
            return array[0];

        int[] sort = ArrayUtils.cloneArray(array);
        ArrayUtils.sortArray(sort);
        int[] temp = new int[2];
        temp[0] = sort[0];
        temp[1] = sort[sort.length - 1];

        return computeMean(temp);
    }

    public static double computeStdDev(int[] array)
    {
        double mean = computeMean(array);

        double[] temp = new double[array.length];
        for (int i = 0; i < array.length; i++)
            temp[i] = Math.pow(array[i] - mean, 2);

        double sum = 0;
        for (int i = 0; i < temp.length; i++)
            sum += temp[i];

        return Math.sqrt(sum / array.length - 1);
    }
}