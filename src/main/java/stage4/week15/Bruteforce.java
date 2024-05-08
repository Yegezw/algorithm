package stage4.week15;

/**
 * 子串匹配
 */
public class Bruteforce
{

    private Bruteforce()
    {
    }

    /**
     * 暴力搜索 O(|s| * |t|)
     */
    public static int bruteforce(String s, String t)
    {
        if (s.length() < t.length()) return -1;

        // s[i ... i + t.length - 1] == t ?
        for (int i = 0; i + t.length() - 1 < s.length(); i++)
        {
            int j;
            for (j = 0; j < t.length(); j++)
            {
                if (s.charAt(i + j) != t.charAt(j)) break;
            }
            if (j == t.length()) return i;
        }

        return -1;
    }
}
