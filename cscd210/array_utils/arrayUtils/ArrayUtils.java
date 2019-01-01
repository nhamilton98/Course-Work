package arrayUtils;

public class ArrayUtils
{
    public ArrayUtils() {}

    public static int[] addElement(final int[] array, final int num)
    {
        if (array == null)
            throw new IllegalArgumentException("Invalid array.");

        int[] ret = new int[array.length];
        for (int i = 0; i < array.length; i++)
            ret[i] = array[i];
        ret[ret.length - 1] = num;
        return ret;
    }

    public static int[] cloneArray(final int[] array)
    {
        if (array == null)
            throw new IllegalArgumentException("Invalid array.");

        int[] ret = new int[array.length];
        for (int i = 0; i < array.length; i++)
            ret[i] = array[i];
        return ret;
    }

    public static void printArray(final int[] array)
    {
        if (array == null)
            throw new IllegalArgumentException("Invalid array.");

        System.out.println("[");
        for (int i = 0; i < array.length; i++)
        {
            if (i == array.length - 1)
                System.out.println(array[i]);
            else
                System.out.println(array[i] + ", ");
        }
        System.out.println("]");
    }

    public static int[] removeElement(final int[] array)
    {
        if (array == null || array.length < 1)
            throw new IllegalArgumentException("Invalid array.");

        int[] ret = new int[array.length - 1];
        for (int i = 0; i < ret.length; i++)
            ret[i] = array[i];
        return ret;
    }

    public static int[] removeElement(final int[] array, final int index)
    {
        if (array.length < 1)
            throw new IllegalArgumentException("Invalid array.");
        if (index < 0 || index >= array.length)
            throw new IllegalArgumentException("Invalid index.");

        int[] ret = new int[array.length - 1];
        int cur = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (i != index)
            {
                ret[cur] = array[i];
                cur++;
            }
        }
        return ret;
    }

    public static int searchArray(final int[] array, final int element)
    {
        for (int i = 0; i < array.length; i++)
            if (array[i] == element)
                return i;
        return -1;
    }

    public static void sortArray(final int[] array)
    {
        int min, temp;
        for (int i = 0; i < array.length; i++)
        {
            min = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[min])
                    min = j;

            temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }
}