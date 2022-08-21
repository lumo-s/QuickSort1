# QuickSort1
1.快速排序法的原理
int p=partition(arr,l,r)  标定点
QuickSort(arr,l,r)
对arr的[l,r]部分排序

伪代码：
QuickSort(arr,l,r){

   if(l>=r) return;//求解最基本问题

   int p=partition(arr,l,r);

   //对arr[l,p-1]进行排序
   QuickSort(arr,l,p-1);

   //对arr[p+1,r]进行排序
   QuickSort(arr,p+1,r);
}

2.最基础的partition


3.第一版快速排序

import java.util.Arrays;

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
    
    private static <E extends Comparable<E>>int partition(E[] arr,int l,int r){
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
        
        SortingHelper.sortTest("MergeSort",arr);
        SortingHelper.sortTest("QuickSort",arr2);
    }
}

4.使用插入排序优化快速排序

  //使用插入排序优化快速排序
    
    public static <E extends Comparable<E>>void sort2(E[] arr){
        sort2(arr,0,arr.length-1);     
    }
    
    private static <E extends Comparable<E>>void sort2(E[] arr,int l,int r){
        
        if(r-l<=15){
            InsertionSort.sort2(arr,l,r);
            return;
        }
        
        int p=partition(arr,l,r);
        sort(arr,l,p-1);
        sort(arr,p+1,r);
    }

5.第一版快速排序的问题
6.为快速排序添加随机化
目标：生成一个[l,r]区间的随机值
生成一个[0,r-l]区间的随机值
l+[0,r-l]区间的随机值->[l,r]区间的随机值


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

