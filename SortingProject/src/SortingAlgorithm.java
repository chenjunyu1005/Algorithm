import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SortingAlgorithm {
	static int InsertionCount = 0;
	static int Mergecount = 0;
	static int Heapcount = 0;
	static int Quickcount = 0;
	private static int length;

	public static void Insertionsort(int arr[]) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				InsertionCount++;
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;

		}
	}


	static void merge(int arr[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		int L[] = new int[n1];
		int R[] = new int[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];
		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			Mergecount++;
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	static void Mergesort(int arr[], int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;

			Mergesort(arr, l, m);
			Mergesort(arr, m + 1, r);

			merge(arr, l, m, r);
		}
	}

	public static void HeapSort(int arr[]) {
		Buildmaxheap(arr);
		for (int i = length; i > 0; i--) {
			Swap(arr, 0, i);
			length = length - 1;
			Max_Heapify(arr, 0);
		}

	}

	public static void Max_Heapify(int arr[], int i) {
		int left = 2 * i;
		int right = 2 * i + 1;
		int max;
		if (left <= length && arr[left] > arr[i]) {
			max = left;
		} else {
			max = i;
		}
		if (right <= length && arr[right] > arr[max]) {
			max = right;
		}
		if (max != i) {
			Swap(arr, i, max);
			Heapcount++;
			Max_Heapify(arr, max);
		}
	}

	public static void Buildmaxheap(int arr[]) {
		length = arr.length - 1;
		for (int i = (int) Math.floor(length / 2); i >= 0; i--) {
			Max_Heapify(arr, i);
		}
	}

	public static void QuickSort(int arr[]) {
		Quickcount = 0;
		QUICKSORT(arr, 0, arr.length - 1);
	}

	public static void QUICKSORT(int arr[], int low, int high) {
		if (low < high) {
			int q = PARTITION(arr, low, high);
			QUICKSORT(arr, low, q - 1);
			QUICKSORT(arr, q + 1, high);
		}
	}

	public static int PARTITION(int arr[], int low, int high) {
		int x = arr[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			Quickcount++;
			if (arr[j] <= x) {
				i++;
				Swap(arr, i, j);
			}
		}
		Swap(arr, i + 1, high);
		return i + 1;
	}

	public static void Swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String args[]) throws FileNotFoundException {
		String filename = args[0];
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> lines = new ArrayList<String>();

		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		String[] arr = lines.toArray(new String[0]);
		int[] array = new int[arr.length - 1];
		for (int i = 0; i < array.length; i++) {
			try {
				array[i] = Integer.parseInt(arr[i]);
			} catch (NumberFormatException nfe) {
				// nfe.printStackTrace();
			}
		}
	     sc.close();
		int[] array1 = Arrays.copyOf(array, array.length);
		int[] array2 = Arrays.copyOf(array, array.length);
		int[] array3 = Arrays.copyOf(array, array.length);
		int[] array4 = Arrays.copyOf(array, array.length);
		Insertionsort(array1);
		System.out.println("InsertionSort");

		print(array1);
		System.out.println("Insertion count: " + InsertionCount);
		System.out.println("\t" + "\t");
		System.out.println("MergeSort");
		Mergesort(array2, 0, array.length - 1);
		print(array2);
		System.out.println("Merge count :" + Mergecount);
		System.out.println("\t" + "\t");
		System.out.println("HeapSort");
		HeapSort(array3);
		print(array3);
		System.out.println("Heap count :" + Heapcount);
		System.out.println("\t" + "\t");

		System.out.println("QuickSort");
		QuickSort(array4);
		print(array4);
		System.out.println("Quick count :" + Quickcount);
	}


	private static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}
