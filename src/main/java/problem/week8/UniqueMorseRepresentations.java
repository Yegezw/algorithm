package problem.week8;

import java.util.Set;
import java.util.TreeSet;

/**
 * <a href="https://leetcode-cn.com/problems/unique-morse-code-words/">804 - 唯一摩尔斯密码词</a>
 */
public class UniqueMorseRepresentations
{

    /**
     * <p>a ~ z: 97 ~ 122
     * <p>codes[word.charAt(i) - 'a']
     * <p>codes[word.charAt(i) - 97]
     */
    public static int uniqueMorseRepresentations(String[] words)
    {
        String[]    codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> set   = new TreeSet<>();

        StringBuilder sb = new StringBuilder();
        for (String word : words)
        {
            for (int i = 0; i < word.length(); i++)
            {
                sb.append(codes[word.charAt(i) - 'a']);
            }
            set.add(sb.toString());
            sb.delete(0, sb.length());
        }

        return set.size();
    }

    public static void main(String[] args)
    {
        String[] words = {"gin", "zen", "gig", "msg"};
        int      count = uniqueMorseRepresentations(words);
        System.out.println(count);
    }
}
