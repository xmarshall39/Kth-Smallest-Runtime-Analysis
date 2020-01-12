/**
 * 
 */
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

/**
 * @author Xavier Marshall
 *
 */
public class KthSmallest<T extends Comparable<T>> {

	/**
	 * We'll make 3 arrays of variable length and randomized integers (Maybe do it one more time for randomized strings)
	 * Then, each will be copied over to temporary arrays that will be processed by
	 * KthSmallest functions for each sort type 
	 * 
	 */
	private Sorting<T> _sorter;
	private RestorativePriorityQueue<T> _RPQ;
	
	KthSmallest(){
		_sorter = new Sorting<T>();
	}
	
	
	//Naive Sort Functions. These will sort the entire array and return the nth smallest
	//More efficient variations will locate the value through exclusively partial sorts
	private T mergeKthSmallestNaive(T[] arr, int k) {
		if(k > arr.length || k <= 0) 
			throw new ArrayIndexOutOfBoundsException("k value is invalid");
		_sorter.mergeSort(arr);
		return arr[k-1];
		
	}
	
	private T heapKthSmallestNaive(T[] arr, int k) {
		if(k > arr.length || k <= 0) 
			throw new ArrayIndexOutOfBoundsException("k value is invalid");
		
		_RPQ = new RestorativePriorityQueue<T>(arr);
		System.out.println("Length: " + _RPQ.getLength());
		for(int i = 0; i < k - 1; ++i) {
			_RPQ.pop();
		}
		
		return _RPQ.pop();
	}
	
	
	private static void randomIntArr(Integer[] arr) {
		Random rand = new Random();
		for(int i = 0; i < arr.length; ++i) {
			arr[i] = rand.nextInt(500);
		}
	}
	
	private static<T> void printArr(T[] arr) {
		System.out.print("[");
		for(int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + (i == arr.length-1?"":", "));
			if (i == 30) {
				System.out.print("...");
				break;
			}
		}
		System.out.println("]");
	}
	
	
	public static void main(String[] args) {
		//User input for array parameters
		Scanner scanner = new Scanner(System.in);
		int len1 = 0, len2 = 0, len3 = 0;
		int k1 = 1, k2 = 1, k3 = 1;
		int i = 0;
		while (i < 3) {
			System.out.println("Enter an integer for array #" + (i+1));
			if(scanner.hasNextInt()) {
				switch (i){
					case 0:
						len1 = scanner.nextInt();
						break;
					case 1:
						len2 = scanner.nextInt();
						break;
					case 2:
						len3 = scanner.nextInt();
						break;
					default:
						System.out.println("Ummm...");
				}
			}
			
			System.out.println("Now Enter a k value:");
			if(scanner.hasNextInt()) {
				switch (i){
					case 0:
						k1 = scanner.nextInt();
						++i; break;
					case 1:
						k2 = scanner.nextInt();
						++i; break;
					case 2:
						k3 = scanner.nextInt();
						++i; break;
					default:
						System.out.println("Ummm...");
				}
			}

		}
		
		scanner.close();
		
		//Random Array Generation
		KthSmallest<Integer> Kth = new KthSmallest<Integer>();
		Integer[] arr1 = new Integer[len1]; Integer[] arr2 = new Integer[len2]; Integer[] arr3 = new Integer[len3];
		randomIntArr(arr1); randomIntArr(arr2); randomIntArr(arr3);
		System.out.println("Here are the generated arrays:");
		printArr(arr1); printArr(arr2); printArr(arr3);
		
		Integer[] temp = Arrays.copyOf(arr1, arr1.length);
		long start = System.nanoTime();
		
		//---------------------------------------------------------------------------------------------------------

		System.out.println("-----NAIVE HEAP-----");
		System.out.print("Kth Smallest: " + Kth.heapKthSmallestNaive(temp, k1));
		System.out.println(" | Time: " + (System.nanoTime() - start));
		
		temp = Arrays.copyOf(arr1, arr1.length);
		start = System.nanoTime();
		
		System.out.println("-----NAIVE MERGE-----");
		System.out.print("Kth Smallest: " + Kth.mergeKthSmallestNaive(temp, k1));
		System.out.println(" | Time: " + (System.nanoTime() - start));
		

		
		
	
	}
	
}
