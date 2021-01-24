import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
@author wang bingqi
final time complexity is O(nlog n + nlog m + n + m)
 */
public class ListUnion {
    static void swap(List<Integer> arr ,int a ,int b){
        int c = arr.get(a);
        arr.set(a,arr.get(b));
        arr.set(b,c);
    }
    static int partition(List<Integer> arr, int l ,int r){
        int v = arr.get(l);
        int j = l;
        // v [v+1...j]<v [j+1...i]>v
        for (int i = l+1;i<=r;i++){
            if (arr.get(i).compareTo(v)<=0){
                swap(arr,i,j+1);
                j++;
            }
        }
        swap(arr,l,j);
        return j;
    }
    static void sort(List<Integer> arr, int l ,int r){
        if (l>=r) return;
        int p = partition(arr,l,r);
        sort(arr,l,p-1);
        sort(arr,p+1,r);
    }
    static void quickSort(List<Integer> arr, int len){
        sort(arr,0,len-1);
    }
    static void addRes(List<Integer> res, List<Integer> a , List<Integer> b){
        int i = 0, j = 0 , num;
        if (a.get(0).compareTo(b.get(0))>=0) num = b.get(0);
        else num = a.get(0);
        res.add(num);
        while (i<a.size() || j<b.size()){
            if(i==a.size()){
                if(b.get(j).compareTo(num)==0) j++;
                else {
                    num=b.get(j++);
                    res.add(num);
                }
            }
            else if(j==b.size()){
                if(a.get(i).compareTo(num)==0) i++;
                else {
                    num=a.get(i++);
                    res.add(num);
                }
            }
            else {
                if (a.get(i).compareTo(b.get(j))<0){
                    if(a.get(i).compareTo(num)==0) i++;
                    else {
                        num=a.get(i++);
                        res.add(num);
                    }
                }
                else if (a.get(i).compareTo(b.get(j))>0){
                    if(b.get(j).compareTo(num)==0) j++;
                    else {
                        num=b.get(j++);
                        res.add(num);
                    }
                }
                else if (a.get(i).compareTo(b.get(j))==0){
                    if(b.get(j).compareTo(num)==0) {
                        i++;
                        j++;
                    }
                    else {
                        i++;
                        num=b.get(j++);
                        res.add(num);
                    }
                }
            }
        }
    }
    //O(nlog n + nlog m + n + m)
    static List<Integer> union(List<Integer> a, List<Integer> b){
        int n = a.size(), m = b.size();
        List res = new ArrayList(n+m);
        //sort list a and b firstly(using quick sort here because it sort in place and generally good)
        quickSort(a,n);//O(log n)
        quickSort(b,m);//O(log m)
        //union two lists when merge them in order
        addRes(res,a,b); //O(n+m)
        return res;

    }
    public static void main(String[] args){
//        union([2,1,3],[2,3,4]) == [1,2,3,4]
            System.out.println(union(Arrays.asList(2,1,3,5,7,5,3,4), Arrays.asList(2,3,4,5,7,9,3,5)));

    }
}
