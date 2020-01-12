import java.util.Arrays;


public class Sorting<T extends Comparable<T>> {

	/*
	 * Performs an in-place merge of an array by breaking it into sub arrays
	 * arr1 -> [Start:Midpoint] inclusive
	 * arr2 -> [Midpoint + 1:End] inclusive
	 */
	
	private void swap(T[] arr, int v1, int v2) {
		try {
			T temp = arr[v2];
			arr[v2] = arr[v1];
			arr[v1] = temp;
		} 
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid swap indecies");
		}

	}
	
	//Performs a merge of subarry sections of an array. Helper to mergeSort()
	private void mergeHelper(T[] arr, int start, int midpoint, int end) {
		//Create temp arrays
		//Copy sub arrays into temps
		
		T[] lowerHalf = Arrays.copyOfRange(arr, start, midpoint+1);
		T[] upperHalf = Arrays.copyOfRange(arr, midpoint+1, end+1);

		
		//Merge sub arrays back into the original
		int combinedLen = lowerHalf.length + upperHalf.length;
		int itr1 = 0; int itr2 = 0; int mainItr = start;
		
		for(int i = 0; i < combinedLen; ++i, ++mainItr) {
			if(itr1 == lowerHalf.length) {
				arr[mainItr] = upperHalf[itr2];
				itr2++;
			}
			
			else if(itr2 == upperHalf.length) {
				arr[mainItr] = lowerHalf[itr1];
				itr1++;
			}
			
			else if(lowerHalf[itr1].compareTo(upperHalf[itr2]) < 0) {
				arr[mainItr] = lowerHalf[itr1];
				itr1++;
				
			}
			
			else {
				arr[mainItr] = upperHalf[itr2];
				itr2++;
			}
		}
		
		
	}
	
	
	private void mergeSort(T[] arr, int low, int high) {
		if(low >= high) return;
		int mid = (low + high) / 2;
		
		mergeSort(arr, low, mid);
		mergeSort(arr, mid+1, high);
		
		mergeHelper(arr, low, mid, high);
	}
	
	//Merge two sorted arrays into a single array
	public void merge(T[] destArr, T[] arr1, T[] arr2){
		if(destArr.length < arr1.length + arr2.length) 
			throw new ArrayIndexOutOfBoundsException("Destination Array is smaller than combined sources");
		
		int combinedLen = arr1.length + arr2.length;
		int itr1 = 0; int itr2 = 0;
		
		for (int i = 0; i < combinedLen; ++i) {
			
			if(itr1 == arr1.length) {
				destArr[i] = arr2[itr2];
				itr2++;
			}
			
			else if(itr2 == arr2.length) {
				destArr[i] = arr1[itr1];
				itr1++;
			}
			
			else if(arr1[itr1].compareTo(arr2[itr2]) < 0) {
				destArr[i] = arr1[itr1];
				itr1++;
			}
			
			else {
				destArr[i] = arr2[itr2];
				itr2++;
			}
		}
		
	}
	
	public void mergeSort(T[] arr) {
		
		mergeSort(arr, 0, arr.length-1);
	}
	
	public void printArray(T[] arr) {
		System.out.print("[");
		for(int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + (i==arr.length-1? "" : ", "));
		}
		System.out.println("]");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] a1 = new Integer[]{14, 7, 3, 12, 17, 3, 44, 8, 9, 11, 6, 2};
		Integer[] a2 = new Integer[]{2, 9, 3, 1, 2, 33, 56, 21, 0, 5};
		Sorting<Integer> sorter = new Sorting<Integer>();
		
		sorter.swap(a1, 0, 99);
		sorter.mergeSort(a1); sorter.mergeSort(a2);
		

		Integer[] a3 = new Integer[a1.length + a2.length];
		sorter.merge(a3, a1, a2);
		
		sorter.printArray(a1);
		sorter.printArray(a2);
		sorter.printArray(a3);
		
	}

}
