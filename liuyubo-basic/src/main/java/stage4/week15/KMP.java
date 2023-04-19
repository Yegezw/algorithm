package stage4.week15;

/**
 * KMP 算法
 */
public class KMP {

    private KMP() {
    }

    public static int kmp(String s, String t) {
        if (t.length() == 0) return 0;
        if (s.length() < t.length()) return -1;

        int[] lps = getLPS(t);

        int si = 0; // sIndex
        int ti = 0; // tIndex
        while (si < s.length()) {
            if (s.charAt(si) == t.charAt(ti)) {
                si++;
                ti++;
                if (ti == t.length()) return si - t.length();
            }
            else if (ti > 0) ti = lps[ti - 1];
            else si++;
        }

        return -1;
    }

    private static int[] getLPS(String t) {
        // lps[i] = length(t[0 ... i] max border)
        int[] lps = new int[t.length()];

        for (int i = 1; i < lps.length; i++) {
            int a = lps[i - 1];
            while (a > 0 && t.charAt(a) != t.charAt(i)) a = lps[a - 1];
            if (t.charAt(a) == t.charAt(i)) lps[i] = a + 1;
        }

        return lps;
    }
}
