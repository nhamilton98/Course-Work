// Nathan Hamilton
// CSCD 320-01
// HW1

public class Tester
{
	public static void main(String[] args)
	{
		int res;
		int[] A = {1, 3, 5, 7, 9, 14, 16, 19};
		
		res = quickSearch(A, 8);
		if (res == -1)
			System.out.println("Performing quickSearch(A, 8) returns -1, meaning there is no such value within A that is greater than or equal to 8.");
		else
			System.out.println("Performing quickSearch(A, 8) returns " + res + ", the index of " + A[res] + ".");
		
		res = quickSearch(A, 19);
		if (res == -1)
			System.out.println("Performing quickSearch(A, 19) returns -1, meaning there is no such value within A that is greater than or equal to 19.");
		else
			System.out.println("Performing quickSearch(A, 19) returns " + res + ", the index of " + A[res] + ".");
		
		res = quickSearch(A, 20);
		if (res == -1)
			System.out.println("Performing quickSearch(A, 20) returns -1, meaning there is no such value within A that is greater than or equal to 20.");
		else
			System.out.println("Performing quickSearch(A, 20) returns " + res + ", the index of " + A[res] + ".");
		
		res = quickSearch(A, 6);
		if (res == -1)
			System.out.println("Performing quickSearch(A, 6) returns -1, meaning there is no such value within A that is greater than or equal to 6.");
		else
			System.out.println("Performing quickSearch(A, 6) returns " + res + ", the index of " + A[res] + ".");
		
		res = quickSearch(A, -1);
		if (res == -1)
			System.out.println("Performing quickSearch(A, -1) returns -1, meaning there is no such value within A that is greater than or equal to -1.");
		else
			System.out.println("Performing quickSearch(A, -1) returns " + res + ", the index of " + A[res] + ".");		
	}
	
	public static int quickSearch(final int[] array, final int value)
	{
		int low = 0, high = array.length - 1;
		
		if (value < array[low])
			return low;
		
		if (value > array[high])
			return -1;
		
		while (low <= high)
		{
			int mid = low + (high - 1) / 2;
			
			if (array[mid] == value)
				return mid;
			
			else if (array [mid] < value && array[mid + 1] > value)
				return mid + 1;
			
			else if (array[mid] > value && array[mid - 1] < value)
				return mid;
			
			else if (array[mid] < value)
				low = mid + 1;
			
			else
				high = mid - 1;
		}
		
		return -1;
	}
}