/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.quicksort1;

/**
 *
 * @author 86139
 */

import java.util.Arrays;
import java.util.Random;

public class QuickSort1 {
    
    private QuickSort1(){}
    
    public static <E extends Comparable<E>>void sort(E[] arr){
        sort(arr,0,arr.length-1);     
    }
    
    private static <E extends Comparable<E>>void sort(E[] arr,int l,int r){
        
        if(l>=r) return;
        
        int p=partition(arr,l,r);
        sort(arr,l,p-1);
        sort(arr,p+1,r);
    }
   
    
    public static <E extends Comparable<E>>void sort2(E[] arr){
        Random rnd=new Random();
        sort2(arr,0,arr.length-1,rnd);     
    }
    
    private static <E extends Comparable<E>>void sort2(E[] arr,int l,int r,Random rnd){
        
        if(l>=r) return;
        
        int p=partition2(arr,l,r,rnd);
        sort2(arr,l,p-1,rnd);
        sort2(arr,p+1,r,rnd);
    }
  
    private static <E extends Comparable<E>>int partition(E[] arr,int l,int r){
        
        //生成[l.r]之间的随机索引
        int p=l+(new Random()).nextInt(r-l+1);
        swap(arr,l,p);

        //arr[l+1..j]<v;arr[j+i...i]>v
        int j=l;
        for(int i=l+1;i<=r;i++)
            if(arr[i].compareTo(arr[l])<0){
                j++;
                swap(arr,i,j);
            }
        swap(arr,l,j);
        return j;
    }
    
    private static <E extends Comparable<E>>int partition2(E[] arr,int l,int r,Random rnd){
        
        //生成[l.r]之间的随机索引
        int p=l+rnd.nextInt(r-l+1);
        swap(arr,l,p);

        //arr[l+1..j]<v;arr[j+i...i]>v
        int j=l;
        for(int i=l+1;i<=r;i++)
            if(arr[i].compareTo(arr[l])<0){
                j++;
                swap(arr,i,j);
            }
        swap(arr,l,j);
        return j;
    }
    
    private static<E>void swap(E[] arr,int i,int j){
        
        E t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }
    
    
    public static void main(String[] args) {
        
        int n=1000000;
        
        Integer[]arr=ArrayGenerator.generateRandomArray(n,n);
        Integer[]arr2=Arrays.copyOf(arr,arr.length);
        Integer[]arr3=Arrays.copyOf(arr,arr.length);
        
        SortingHelper.sortTest("MergeSort",arr);
        SortingHelper.sortTest("QuickSort",arr2);
        SortingHelper.sortTest("QuickSort2",arr3);
        
     
    }
}
