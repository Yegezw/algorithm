package practice4_string;

/**
 * <a href="https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/">剑指 Offer 58 - II - 左旋转字符串</a>
 */
class Solution5 {

    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
}