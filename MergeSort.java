import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {

    static void merge(int[] arr, int s , int e, int mid){
        int leftArrLen = mid-s+1;
        int rightArrLen = e-mid;
        int[] leftArr = new int[leftArrLen];
        int[] rightArr = new int[rightArrLen];
        int k = s;
        for(int i=0;i<leftArrLen;i++){
            leftArr[i] = arr[k];
            k++;
        }
        k = mid+1;
        for(int i=0;i<rightArrLen;i++){
            rightArr[i] = arr[k];
            k++;
        }
        int i = 0;
        int j = 0;
        k = s;
        while(i<leftArrLen && j<rightArrLen){
            if(leftArr[i]>rightArr[j]){
                arr[k] = rightArr[j];
                j++;
                k++;
            }else{
                arr[k] = leftArr[i];
                i++;
                k++;
            }
        }
        while(i<leftArrLen){
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while(j<rightArrLen){
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    static void mergesort(int[] arr, int s, int e){
        if(s>=e){
            return;
        }
        int mid = (s+e)/2;
        mergesort(arr, s, mid);
        mergesort(arr,mid+1,e);
        merge(arr,s,e,mid);
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
        mergesort(arr,s,e);
        long endTime = System.nanoTime();
        System.out.print("Array after sorting: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("Execution time: "+(endTime - startTime)+ " nanoseconds");
        sc.close();
    }
}
