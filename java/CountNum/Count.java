package CountNum;

public class Count {
    
    public static int rep(int[] arr,int begin,int end,int k){

        int i = first(arr,begin,end,k);

        if (i==-1) {
            return i;
        }

        int j = last(arr, i, end, k);

        return j-i+1;
    }

    private static int first(int[] arr,int begin,int end,int k){

        int mid = (begin+end)/2;

        if (arr[mid] == k) {
            if (arr[mid-1] < k || mid == 0) {
                return mid;
            }
        }else{
            if (arr[mid] < k) {
                first(arr, mid+1, end, k);
            }else{
                first(arr, begin, mid-1, k);
            }
        }
        return -1;
    }

    private static int last(int[] arr,int begin,int end,int k){

        int mid = (begin+end)/2;

        if (arr[mid] == k) {
            if (arr[mid+1] > k || mid == end) {
                return mid;
            }
        }else{
            if (arr[mid] < k) {
                last(arr, mid+1, end, k);
            }else{
                last(arr, begin, mid-1, k);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        
        int[] arr = {1,1,2,2,2,2,2};

        int res = rep(arr, 0, arr.length-1, 2);

        System.out.println(res);
    }
}
