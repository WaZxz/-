public class Test {
    public static void main(String[] args) {
//      int[] data = SortHelper. generateNearlySortedArray(10000000,10);
        int[] data = SortHelper.generateArray(1000000,100000,10000000);
//        int[] data = {1,3,5,7,32,4,61,23,156};
////    bubbleSortLowBetter(data);
//      insertSort(data);
//      binaryInsertSort(data);
//        shellSort(data);
//        shellSort(data);
//        SortHelper.printArray(data);
        quickSort(SortHelper.copyArray(data));
        quickSort(data);
    }



    public static void bubbleSortLowBetter(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        } else {
            //控制冒泡排序的次数
            //一次冒泡只能确保一个元素到达最终位置
            for (int i = 0; i < n; i++) {
                boolean flag = false;
                for (int j = 0; j < n - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        flag = true;
                        //交换两个相邻元素
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
                if(!flag){
                    System.out.println("当前遍历到第" + i +"数据集已经有序");
                    break;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("冒泡排序算法总耗时为" + (end - start));
    }
    public static void insertSort(int[] array){
        long start = System.currentTimeMillis();
        int n = array.length;
        if(n <= 1){
            return;
        }else{
            //将待排序数组看为两个空间
            for(int i = 1;i < n;i++){
                //待排序集合的第一个元素
                int value = array[i];
                //已排序集合的最后一个元素
                int j = i -1;
                //找到要插入的位置
                for(;j >= 0;j--){
                    if(array[j] > value){
                        array[j + 1] = array[j];
                    }else{
                        break;
                    }
                }
                //找到要插入的位置
                array[j+1] = value;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("插入排序算法总耗时为" + (end - start) + "毫秒");
    }
    public static void binaryInsertSort(int[] array){
        long start = System.currentTimeMillis();
        int n = array.length;
        int low,mid,high,j,i,temp= 0;
        if(n <= 1){
            return;
        }else {
            for (i = 1; i < n; i++) {
                //已排序集合的第一个元素
                low = 0;
                //已排序集合的最后一个元素
                high = i - 1;
                //待排序空间的第一个元素
                temp = array[i];
                //寻找待插入位置
                while (low <= high) {
                    //寻找中间位置
                    mid = (low + high) / 2;
                    if (array[mid] < temp){
                        low = mid + 1;
                    }else{
                        high = mid - 1;
                    }
                }
                //要插入的位置在high+1处，搬移元素
                for(j = i -1 ;j > high;j--){
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("折半插入排序耗时："+(end - start)+"毫秒");
    }
    public static void shellSort(int[] array){
        long start = System.currentTimeMillis();
        int n = array.length;
        if(n <= 1){
            return;
        }else{
            int step = n / 2;
            while(step >= 1){
                for(int i = step;i < n;i++){
                    int value = array[i];
                    int j = i - step;
                    for(;j>=0; j-= step){
                        if(array[j] > value){
                            array[j + step] = array[j];
                        }else{
                            break;
                        }
                    }
                    array[j + step] = value;
                }
                step = step/2;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("希尔排序耗时:"+(end-start)+"毫秒");
    }
    public static void selectionSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if(n <= 1){
            return;
        }else{
            //从未排序区间中选出最小值
            for(int i = 0;i < n - 1; i++){
                int minIndex = i;
                for(int j = i + 1;j < n;j++){
                    if(array[j] < array[minIndex]){
                        minIndex = j;
                    }
                }
                //此时minIndex对应的是当前未排序数组中最小的值
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
    public static void quickSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }
        quickSortInternal(array,0,n-1);
        long end = System.currentTimeMillis();
        System.out.println("快速排序总耗时为:"+(end-start)+"毫秒");
    }

    private static void quickSortInternal(int[] array,int l,int r) {
        if(l >= r){
            return;
        }
        int q = partition2(array, l, r);
        quickSortInternal(array, l, q - 1);
        quickSortInternal(array,q + 1,r);
    }

    private static int partition2(int[] array,int l,int r) {
        int randomIndex = (int)((Math.random() * (r - l +1)) + l);
        swap(array,l,randomIndex);
        int v = array[l];
        // array[l + 1 —— i - 1] < v
        int i = l + 1;
        // array[j + 1 —— r] > v
        int j = r;
        while(true){
            //从前到后扫描
            while(i <= r && array[i] < v) i++;
            //从前向后扫描
            while(j >= l+1 && array[j] > v)j--;
            if(i > j){
                break;
            }
            swap(array, l, r);
            i++;
            j--;
        }
        swap(array, l, r);
        return j;
    }

    private static void swap(int[] array,int indexA,int indexB){
        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

}


