import java.util.ArrayList;

public class LengthVsSize {
    public static void main(String[] args) {
        int arr[] = new int[10];
        arr[0] = 10;
        arr[1] = 12;
        System.out.println(arr.length);// 10

        String str = "Length for array, length() for string and size() for arrayList.";
        System.out.println(str.length());// 63

        ArrayList<String> list = new ArrayList<String>();
        list.add("ankit");
        list.add("nippun");
        System.out.println(list.size());// 2
    }
}