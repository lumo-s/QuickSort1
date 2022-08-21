/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.leetcode75;

/**
 *
 * @author 86139
 */
public class Leetcode75 {

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
}
