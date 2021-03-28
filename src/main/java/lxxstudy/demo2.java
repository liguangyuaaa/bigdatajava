package lxxstudy;
import java.util.Arrays;

public class demo2{
    public static void main(String[] args) {
        int[] arr={7,9,3,24,1,2,-1,23,3,4};

        int tmp=0;
        for(int j=0; j<arr.length; j++){
            for(int i=0; i<arr.length-1;i++){
                if(arr[j]<arr[i]){
                    tmp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}