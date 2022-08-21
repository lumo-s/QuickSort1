# QuickSort1
1.快速排序法的原理
快速排序算法是一个随机算法，它的原理是选定一个标定点，将标定点放在它该在的位置，即标定点之前的元素都小于标定点，标定点之后的元素都大于标定点。快速排序也运用了递归的思想，快速排序算法的关键点是如何将标定点放在正确的位置，portition的原理是这样的:iarr[l+1..j]<v,arr[j+1..r]>v,  if arr[i]>arr[l] i++;  if(arr[i]<arr[l],j++,swap(i,j),i++。快速排序算法的时间复杂度是O(nlogn)，最坏复杂度为O(n^2),概率非常低。

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

2.单路快速排序

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

3.使用插入排序优化快速排序

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

4.为快速排序添加随机化
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
5.快速排序算法还有问题，如果数组中每个元素值都相等，那么时间复杂度特别低，算法退化

双路排序算法

public static <E extends Comparable<E>>void sort3(E[] arr){
        Random rnd=new Random();
        sort3(arr,0,arr.length-1,rnd);     
    }
    
    private static <E extends Comparable<E>>void sort3(E[] arr,int l,int r,Random rnd){
        
        if(l>=r) return;
        
        int p=partition3(arr,l,r,rnd);
        sort3(arr,l,p-1,rnd);
        sort3(arr,p+1,r,rnd);
    }
  
    private static <E extends Comparable<E>>int partition3(E[] arr,int l,int r,Random rnd){
        
        //生成[l.r]之间的随机索引
        int p=l+rnd.nextInt(r-l+1);
        swap(arr,l,p);

        //arr[l+1..j]<v;arr[j+i...i]>=v
        int i=l+1,j=r;
        while(true){
            
            while(i<=j&&arr[i].compareTo(arr[l])<0)
                i++;
            while(j>=i&&arr[j].compareTo(arr[l])>0)
                j--;
            if(i>=j) break;
            swap(arr,i,j);
            i++;
            j--;    
        } 
        swap(arr,l,j);
        return j;
    }
    
6.三路快速排序

优点：不需要对大量等于标定点的元素重复操作

 public static <E extends Comparable<E>>void sort3ways(E[] arr){
        Random rnd=new Random();
        sort3ways(arr,0,arr.length-1,rnd);     
    }
    
    private static <E extends Comparable<E>>void sort3ways(E[] arr,int l,int r,Random rnd){
        
        if(l>=r) return;
        
        //生成[l,r]之间的随机索引
        int p=l+rnd.nextInt(r-l+1);
        swap(arr,l,p);
        
        //arr[l+1,lt]<v,arr[lt+1,i-1]==v,arr[gt,r]>v
        int lt=l,i=l+1,gt=r+1;
        while(i<gt){
            
            if(arr[i].compareTo(arr[l])<0){
                lt++;
                swap(arr,i,lt);
                i++;
            }
            else if(arr[i].compareTo(arr[l])>0){
                gt--;
                swap(arr,i,gt);
            }
            else{//arr[i]==arr[l]
                i++;
            }   
        }
        swap(arr,l,lt);
        //arr[l,lt-1]<v,arr[lt,gt-1]==v,arr[gt,r]>v
        
        sort3ways(arr,l,lt-1,rnd);
        sort3ways(arr,gt,r,rnd);       
    }
     private static<E>void swap(E[] arr,int i,int j){
        
        E t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }

7.leetcode75
给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

必须在不使用库的sort函数的情况下解决这个问题。

 public void sortColors(int[] nums) {

        //nums[0..zero]==0,nums[zero+1,i]==1,nums[two,n-1]==2
        int zero=-1,i=0,two=nums.length;
        while(i<two){
            if(nums[i]==0){
                zero++;
                swap(nums,zero,i);
                i++;
            }
            else if(nums[i]==2){
                two--;
                swap(nums,i,two);
            }
            else{//nums[i]==0
                 i++;
            }
        }
    }

    private void swap(int[] nums,int i,int j){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }

8.第K个最大元素 leetcode215

import java.util.Random;
public class Leetcode215 {
    
     public int findKthLargest(int[] nums, int k){
         Random rnd=new Random();
         return selectK(nums,0,nums.length-1,nums.length-k,rnd);
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


9.剑指offer40,最小的k个数

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

10.设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。

import java.util.Arrays;
import java.util.Random;
public class MS1714 {

    public int[] smallestK(int[] arr, int k) {
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
