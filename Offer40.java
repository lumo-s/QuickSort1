/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.offer40;

/**
 *
 * @author 86139
 */
import java.util.Arrays;
import java.util.Random;

public class Offer40 {

    public int[] getLeastNumbers(int[] arr, int k) {
        if(k==0) return new int[0];
            Random rnd=new Random();
        selectK(arr,0,arr.length-1,k-1,rnd);
        return Arrays.copyOf(arr,k);
    }
    private int selectK(int[] arr,int l,int r,int k,Random rnd){
         int p=partition(arr,l,r,rnd);
         if(k==p)return arr[p];
         if(k<p) return selectK(arr,l,p-1,k,rnd);
         return selectK(arr,p+1,r,k,rnd);  
     }
     private int partition(int[]arr,int l,int r,Random rnd){
      //生成[l.r]之间的随机索引
        int p=l+rnd.nextInt(r-l+1);
        swap(arr,l,p);

        //arr[l+1..j]<=v;arr[j+i...i]>=v
        int i=l+1,j=r;
        while(true){
            
            while(i<=j&&arr[i]<arr[l])
                i++;
            while(j>=i&&arr[j]>arr[l])
                j--;
            if(i>=j) break;
            swap(arr,i,j);
            i++;
            j--;    
        } 
        swap(arr,l,j);
        return j;
     }
     
      private static void swap(int [] arr,int i,int j){
        
        int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }
}
