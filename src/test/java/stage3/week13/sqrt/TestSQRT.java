package stage3.week13.sqrt;

@SuppressWarnings("all")
public class TestSQRT
{

    private static Integer[]                  nums    = {-2, 0, 3, -5, 2, -1};
    private static SQRTDecomposition<Integer> sumSQRT = new SQRTDecomposition<>(nums, Integer::sum);
    private static SQRTDecomposition<Integer> maxSQRT = new SQRTDecomposition<>(nums, Math::max);
    private static SQRTDecomposition<Integer> minSQRT = new SQRTDecomposition<>(nums, Math::min);

    public static void testSum()
    {
        System.out.println(sumSQRT.query(0, 2)); // 1

        sumSQRT.update(1, 2);

        System.out.println(sumSQRT.query(0, 2)); // 3
        System.out.println(sumSQRT.query(3, 5)); // -4
        System.out.println(sumSQRT.query(0, 5)); // -1
    }

    public static void testMax()
    {
        System.out.println(maxSQRT.query(0, 2)); // 3

        maxSQRT.update(1, 5);

        System.out.println(maxSQRT.query(0, 2)); // 5
        System.out.println(maxSQRT.query(3, 5)); // 2
        System.out.println(maxSQRT.query(0, 5)); // 3
    }

    public static void testMin()
    {
        System.out.println(minSQRT.query(0, 2)); // -2

        minSQRT.update(1, -9);

        System.out.println(minSQRT.query(0, 2)); // -9
        System.out.println(minSQRT.query(3, 5)); // -5
        System.out.println(minSQRT.query(0, 5)); // -9
    }

    public static void main(String[] args)
    {
        testSum();

        testMax();

        testMin();
    }
}
