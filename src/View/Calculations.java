package View;

import java.util.Arrays;

public class Calculations
{
	public static boolean useArraysBinarySearch(int[] arr, int targetValue)
	{	
		int a =  Arrays.binarySearch(arr, targetValue);
		if(a > 0)
			return true;
		else
			return false;
	}
}
