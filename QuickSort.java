import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {

    static int partition(int[] arr,int s, int e){
        int partition = arr[s];
        int count = 0;
        for(int i=s+1;i<=e;i++) {
            if (partition >= arr[i]) {
                count++;
            }
        }
        int partitionIndex = s + count;
        arr[s] = arr[partitionIndex];
        arr[partitionIndex] = partition;
        int i = s;
        int j = e;
        while(i<partitionIndex && j>partitionIndex){
            while(arr[i]<=arr[partitionIndex]){
                i++;
            }
            while(arr[j]>arr[partitionIndex]){
                j--;
            }
            if(i<partitionIndex && j>partitionIndex){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        return partitionIndex;
    }

    static void quicksort(int[] arr,int s,int e){
        if(s>=e){
            return;
        }
        int partitionIndex = partition(arr,s,e);
        quicksort(arr,s,partitionIndex-1);
        quicksort(arr,partitionIndex+1,e);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of an Array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<arr.length;i++){
            System.out.print("Enter element " +(i+1)+": ");
            arr[i] = sc.nextInt();
        }
        System.out.print("Array before sorting: ");
        System.out.println(Arrays.toString(arr));
        int s = 0;
        int e = arr.length-1;
        long startTime = System.nanoTime();
        quicksort(arr,s,e);
        long endTime = System.nanoTime();
        System.out.print("Array after sorting: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("Execution time: "+(endTime - startTime)+ " nanoseconds");
        sc.close();
    }
}