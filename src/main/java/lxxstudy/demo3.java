package lxxstudy;

import java.util.Arrays;

public class demo3 {
    public static void main(String[] args) {
        int[] arr={7,9,3,24,1,2,-1,23,-3,4,-76,-8};
        int left=0;
        int right=arr.length-1;
        orderby(arr,left,right);
        System.out.println(Arrays.toString(arr));
    }


    public static void orderby(int[] arr,int left1,int right1){
        int sign=arr[(left1+right1)/2];
        int tmp = 0;
        int left=left1;
        int right=right1;
        while (left<right){
            while (arr[left]<sign){
                left+=1;
            }
            while (arr[right]>sign){
                right-=1;
            }
            if(left>=right){
                break;
            };
            tmp=arr[right];
            arr[right]=arr[left];
            arr[left]=tmp;
        }
        if(left == right){
            left+=1;
            right-=1;
        };

        if(right>left1){
            orderby(arr,left1,right);
        };
        if(left<right1){
            orderby(arr,left,right1);
        };
    }
}



