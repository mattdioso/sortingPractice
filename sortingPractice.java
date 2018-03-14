import java.time.Instant;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class sortingPractice {

	private int [] arr1 = new int[] {1, 6, 8, 4, 74, 15, 3, 90, 45, 9, 2, 46, 34};
	private int [] arr2 = new int[] {7, 9, 23, 65, 10, 14, 2, 5, 9, 87, 34, 64, 11, 43, 97};
	private int [] arr3 = new int[] {9, 6, 34, 86, 16, 13, 31, 69, 26, 88, 1, 7, 3, 4};

	public int [] getArray(int n) {
		if (n>3) {
			System.out.println("Not a valid option");
			return null;
		}
		if (n==1)
			return arr1;
		if (n==2)
			return arr2;
		if (n==3)
			return arr3;
		return null;
	}

	public void mergeSort(int [] array){
		int [] help = new int[array.length];
		mergeSort(array, help, 0, array.length-1);
	}

	public void mergeSort(int [] array, int [] help, int low, int high) {
		if (low < high) {
			int middle = (low+high)/2;
			mergeSort(array, help, low, middle);
			mergeSort(array, help, middle+1, high);
			merge(array, help, low, middle, high);
		}
	}

	public void merge(int [] array, int [] help, int low, int middle, int high) {
		for (int i =low; i<= high; i++)
			help[i]=array[i];
		int helpLeft = low;
		int helpRight= middle+1;
		int current = low;

		while(helpLeft <= middle && helpRight <= high) {
			if (help[helpLeft] <= help[helpRight]) {
				array[current] = help[helpLeft];
				helpLeft++;
			} else {
				array[current] = help[helpRight];
				helpRight++;
			}
			current++;
		}

		int remaining = middle - helpLeft;
		for (int i =0; i<=remaining; i++) {
			array[current+i] = help[helpLeft+i];
		}
	}

	public void quickSort(int [] array) {
		quickSort(array, 0, array.length-1);
	}

	public void quickSort(int [] array, int left, int right) {
		int index=partition(array, left, right);
		if (left<index-1) {
			quickSort(array, left, index-1);
		}
		if (right > index) {
			quickSort(array, index, right);
		}
	}

	public int partition(int [] array, int left, int right) {
		int pivot = array[(left+right)/2];
		while (left<=right) {
			while (array[left] < pivot)
				left++;
			while (array[right] > pivot)
				right--;
			if (left<=right) {
				swap(array, left, right);
				left++;
				right--;
			}
		}
		return left;
	}

	public void swap(int [] array, int left, int right) {
		int temp = array[left];
		array[left]=array[right];
		array[right]=temp;
	}

	public void selectionSort(int [] array) {
		for (int i =0; i<array.length; i++) {
			int lowestIndex = findMin(array, i, array.length);
			if (i==lowestIndex)
				continue;
			else
				swap(array, i, lowestIndex);
		}
	}

	public int findMin(int [] array, int low, int high) {
		int min =low;
		for (int i=low; i<high; i++) {
			if (array[i]<array[min])
				min = i;
		}
		return min;
	}

	public void bubbleSort(int [] array) {
		for (int i=0; i<array.length-1; i++) {
			for (int j=0; j<array.length-i-1; j++) {
				if (array[j] > array[j+1])
					swap(array, j, j+1);
				else
					continue;
			}
		}
	}

	public void printArray(int [] array) {
		for (int i : array) {
			System.out.print(i +" ");
		}
		System.out.println(" ");
	}

	public static void main(String[] args) {
		if (args.length > 2) 
			System.out.println("Too many arguments");
		if (args.length < 2)
			System.out.println("Not enough arguments");

		sortingPractice s = new sortingPractice();
		if (args[0].equals("--bubble") || args[0].equals("-b")) {
			s.printArray(s.getArray(Integer.parseInt(args[1])));
			Instant start = Instant.now();
			s.bubbleSort(s.getArray(Integer.parseInt(args[1])));
			Instant end = Instant.now();
			Duration timeDiff = Duration.between(start, end);
			s.printArray(s.getArray(Integer.parseInt(args[1])));
			System.out.println("Time it took to sort using Bubble Sort: " + timeDiff.toMillis() + "ms");
		}

		if (args[0].equals("--selection") || args[0].equals("-s")) {
			s.printArray(s.getArray(Integer.parseInt(args[1])));
			Instant start = Instant.now();
			s.selectionSort(s.getArray(Integer.parseInt(args[1])));
			Instant end = Instant.now();
			Duration timeDiff = Duration.between(start, end);
			s.printArray(s.getArray(Integer.parseInt(args[1])));
			System.out.println("Time it took to sort using Selection Sort: " + timeDiff.toMillis() + "ms");
		}

		if (args[0].equals("--merge") || args[0].equals("-m")) {
			s.printArray(s.getArray(Integer.parseInt(args[1])));
			Instant start = Instant.now();
			s.mergeSort(s.getArray(Integer.parseInt(args[1])));
			Instant end = Instant.now();
			Duration timeDiff = Duration.between(start, end);
			s.printArray(s.getArray(Integer.parseInt(args[1])));
			System.out.println("Time it took to sort using Merge Sort: " + timeDiff.toMillis() + "ms");
		}

		if (args[0].equals("--quick") || args[0].equals("-q")) {
			s.printArray(s.getArray(Integer.parseInt(args[1])));
			Instant start = Instant.now();
			s.quickSort(s.getArray(Integer.parseInt(args[1])));
			Instant end = Instant.now();
			Duration timeDiff = Duration.between(start, end);
			s.printArray(s.getArray(Integer.parseInt(args[1])));
			System.out.println("Time it took to sort using Quick Sort: " + timeDiff.toMillis() + "ms");
		}
	}
}