/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapplication;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Ellis
 */
public class JavaPractice {
    static ArrayList<Integer> findDuplicate(int[] x){
        HashSet<Integer> y = new HashSet<Integer>();
        ArrayList<Integer> z=new ArrayList<Integer>();
        
        for(int i = 0;i<x.length;i++){
            if(y.contains(x[i])){
                z.add(x[i]);
            }else{
                y.add(x[i]);
            }
        }
        return z;
    }
    
    static int[] removeDuplicate(int[] x){
        HashSet<Integer> y = new HashSet<Integer>();
        StringBuilder s;
        String d;
        HashSet<Character> chars = new HashSet<Character>();
        for(int i = 0;i<x.length;i++){
            if(y.contains(x[i])){
                for(int j = i;j<(x.length-1);j++){
                    x[j]=x[j+1];
                }
                x[x.length-1]=0;
            }else{
                y.add(x[i]);
            }
        }
        return x;
    }
    
    public int lengthOfLongestSubstring(String s) {
        StringBuilder x = new StringBuilder(s);
        int max;
        max = 0;
        if(s.length()==1){
            return 1;
        }
        
        for(int i = 0;i<(s.length()-1);i++){
            for(int j = i+1;j<s.length()+1;j++){
                if((j-i)>max && checkChars(s,i,j)){
                    max=(j-i);
                }
            }
        }
        return max;
    }
    
    public static int selectBirdType(int[] arr){
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        HashSet<Integer> nums = new HashSet<Integer>();
        for(int i=0;i<arr.length;i++){
            if(nums.contains(arr[i])){
                int j = map.get(arr[i]);
                map.put(arr[i], j+1);
            } else{
                nums.add(arr[i]);
                map.put(arr[i], 1);
            }
        }
        HashSet<Integer> maxKey=new HashSet<Integer>();
        int max=0;
        for(Integer x: nums){
            if(map.get(x)>max){
                max=map.get(x);
                maxKey.clear();
                maxKey.add(x);
            }else if(map.get(x)==max){
                maxKey.add(x);
            }
        }
        int min=10000;
        for(Integer x: maxKey){
            if(x<min){
                min=x;
            }
        }
        return min;
    }
    
    public static int minPages(int n, int p){
        int turns=0;
        if(p==1||p==n){
            return 0;
        }
        if(p<(n/2)){
            int i=2;
            int j=3;
            turns++;
            while(i<(n/2)){
                if(p==i||p==j){
                    break;
                }else{
                    turns++;
                    i+=2;
                    j+=2;
                }
            }
        }else{
            if((n%2)==1){
                int i=n-1;
                int j=n;
                while(i>(n/2)){
                    if(p==i||p==j){
                        break;
                    } else{
                        turns++;
                        i-=2;
                        j-=2;
                    }
                }
            }else{
                int j=n-1;
                int i=n-2;
                turns++;
                while(i>(n/2)){
                    if(p==i||p==j){
                        break;
                    } else{
                        turns++;
                        i-=2;
                        j-=2;
                    }
                }
            }
        }
        return turns;
    }
    
    public static int findMaxCombo(int[] A, int[] B, int money){
        int max=0;
        for(int i =0;i<A.length;i++){
            for(int j =0;j<B.length;j++){
                if((A[i]+B[j]>max)&&(A[i]+B[j]<money)){
                    max=A[i]+B[j];
                }
            }
        }
        return max;
    }
    
    public static String catAndMouse(int A, int B, int M){
        if(Math.abs(A-M)>Math.abs(B-M)){
            return "Cat B  will get the Mouse";
        }else{
            return "Cat A  will get the Mouse";
        }
    }
            
    public boolean checkChars(String l, int i,int j){
        Set<Character> x = new HashSet<Character>();
        for(int k = i; k<j;k++){
            Character c = l.charAt(k);
            if(x.contains(c)){
                return false;
            }
            x.add(c);
        }
        return true;
    }
    
    public static int[][] magicSquare(int[][] a){
        int sum;
        for(int i = 0;i<3;i++){
            sum=a[i][0]+a[i][1]+a[i][2];
            if(sum!=15){
                
            }
        }
    }
    
    public static int findValleys(char[] hike, int start){
        int val=0;
        int sw;
        if(start<0){
            val++;
            sw=0;
        }else{
            sw=1;
        }
        for(int i=0;i<hike.length;i++){
            if(hike[i]=='U'){
                start++;
                if(start==0){
                    sw=1;
                }
            }else{
                start--;
                if(start<0&&sw==1){
                    val++;
                }
            }
        }
        return val;
    }
    
    public static int findCandles(int[] a){
        int max=0;
        int numCand=0;
        for(int i=0;i<a.length;i++){
            if(a[i]>max){
                numCand=1;
                max=a[i];
            }else if(a[i]==max){
                numCand++;
            }
        }
        return numCand;
    }
    
    static int[] findMinMax(int[] x){
        int[] y = new int[2];
        y[0]=x[0];
        y[1]=x[0];
        for(int i=0;i<x.length;i++){
            int min=x[i];
            int max=x[i];
            if(min<y[0]){
                y[0]=min;
            }
            if(max>y[1]){
                y[1]=max;
            }
        }
        return y;
    }
    
    static int[] reverseArray(int[] x){
        for(int i=0;i<x.length;i++){
            x[i]=x[x.length-1-i];
        }
        return x;
    }
    
    static HashMap<Integer,Integer> findSum(int[] x, int val){
        HashMap<Integer,Integer> vals = new HashMap();
        for(int i = 0;i<x.length;i++){
            for(int j = 0;j<x.length;i++){
                if((x[i]+x[j])==val){
                    vals.putIfAbsent(x[i],x[j]);
                }
            }
        }
        return vals;
    }
    
    static int findMissing(int[] x){
        for(int i=1;i<100;i++){
            if(x[i]!=i){
                return i;
            }
        }
        return 0;
    }
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if((nums[i]+nums[j])==target){
                    return new int[] {i,j};
                }
            }
        }
        return new int[] {0,0};
    }
    
     public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
    
    public Node<Integer> sumOfLink(Node<Integer> l1, Node<Integer> l2){
        Node<Integer> x = new Node<Integer>(0);
        x.setData(0);
        Node<Integer> curr,p,q;
        int carry = 0;
        curr=x;
        p=l1;
        q=l2;
        while(p != null || q != null){
            int sum = p.getData()+q.getData()+carry;
            curr.setData(sum%10);
            carry=sum/10;
            curr.setNext(new Node<Integer>(0));
            curr=curr.getNext();
            p=p.getNext();
            q=q.getNext();
        }
        return x;
    }
    public static void main(String[] args){
        int[] A = {4,4,1,3};
        System.out.println(findCandles(A));
    }
}
