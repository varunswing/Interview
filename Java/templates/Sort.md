public static void sort(int[] arr)
{
    //because Arrays.sort() uses quicksort which is dumb
    //Collections.sort() uses merge sort
    ArrayList<Integer> ls = new ArrayList<Integer>();
    for(int x: arr)
        ls.add(x);
    Collections.sort(ls);
    for(int i=0; i < arr.length; i++)
        arr[i] = ls.get(i);
}