package lgystudy.LeetCode.Interview;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ac"
 * 输出："a"
 */
public class Top005_0405_longestPalindrome {
    public static void main(String[] args) {
        String s = "abccdbe";
        String ss = new Top005_0405_longestPalindrome().longestPalindrome(s);
        System.out.println(ss);

    }
    public String longestPalindrome(String s) {
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length; i++){
            if(c[i] == c[c.length - i -1]){


                return null;

            }
        }
        System.out.println(c);
        return null;
    }
    public int getIndex(int i){

        return 0;
    }
}
