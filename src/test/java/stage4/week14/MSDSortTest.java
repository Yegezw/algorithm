package stage4.week14;

@SuppressWarnings("all")
public class MSDSortTest {

    public static void main(String[] args) {
        String[] arr = {"BCA", "CBAA", "AC", "BADFE", "ABC", "CBA"};
        MSDSort.sort(arr);
        for (String s : arr) System.out.println(s);
    }
}
