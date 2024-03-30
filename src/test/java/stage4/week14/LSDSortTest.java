package stage4.week14;

public class LSDSortTest {

    public static void main(String[] args) {
        String[] arr = {"BCA", "CAB", "ACB", "BAC", "ABC", "CBA"};
        LSDSort.sort(arr, 3);
        for (String s : arr) System.out.println(s);
    }
}
