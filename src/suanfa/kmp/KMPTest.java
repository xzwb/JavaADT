package suanfa.kmp;


/**
 * kmp是一个解决，模式串在文本串是否出现过，如果出现过，最早出现的位置的经典算法
 *
 * KMP算法就利用之前判断过的信息,通过next数组,保存模式串中最长公共子序列的长度,
 * 每次回溯时通过next数组找到前面匹配过的位置,省去了大量的计算时间
 *
 * 案例:
 * str1 = "BBC ABCDAB ABCDABCDABDE"
 * str2 = "ABCDABD"
 * 判断str1是否含有str2如果存在,就返回第一次出现的位置,如果没有,返回-1
 *
 * 1. 首先,用str1的第一个字符和str2的第一个字符去比较,不符合关键词向后移动一位
 * 2. 重复第一步，还是不符合再向后移动
 * 3. 一直重复直到str1与str2的第一个字符符合为止
 * 4. 接着比较字符串和搜索词的下一个字符,直到遇到str1有一个字符和str2不符合;
 * 5. 这时候想到的是继续遍历str1的下一个字符重复第一步(其实这是很不明智的,因为此时BCD已经比较过了,
 *    没有必要在做重复的工作,一个基本事实是当空格与D不匹配时,你其实知道前面6个字符是ABCDAB,
 *    KMP算法的思想是不要把“搜索位置”移动回已经比较过的位置，继续把他向后移动)
 * 6. 问题？我怎么知道A和后面的BCD是不想同的呢;
 * 7. 可以计算出一个部分匹配表
 *         A  B  C  D  A  B  D
 *         0  0  0  0  1  2  0
 *    如果是A部分匹配值就是0,如果是AB部分匹配值是0,如果是ABC部分匹配值是0以此类推....
 * 8. 已知空格与D不匹配时,前面6个字符"ABCDAB"是匹配的,查表可知,最后一个匹配字符B对应的部分匹配值是2
 *    因此按照下面的公式算出向后移动的位数:
 *          移动位数 = 已匹配字符串 - 对应的部分匹配值
 * 9. 介绍部分匹配表怎么产生
 *  什么是前缀?
 *  什么是后缀?
 *  字符串: "bread"
 *  前缀: "b", "br", "bre", "brea"
 *  后缀: "read", "ead", "ad", "d"
 *  部分匹配值就是前缀和后缀的最长共有元素的长度;
 *  "a"的前缀和后缀都为空集,共有元素为0;
 *  "ab"的前缀为"a"后缀为"b"
 *  "abc"的前缀是["a", "ab"]后缀是["bc", "c"]
 *  ...
 *
 */
public class KMPTest {
    public static final String STR1 = "BBC ABCDAB ABCDABCDABDE";
    public static final String STR2 = "ABCDABD";

    public static void main(String[] args) {
        int[] next = kmpNext(STR2);
        int result = kmpSearch(STR1, STR2, next);
        System.out.println(result);
    }

    /**
     * 获取到一个字符串的部分匹配值表
     */
    public static int[] kmpNext(String dest) {
        // 创建一个next数组，保存部分匹配值表
        int[] next = new int[dest.length()];
        // 如果字符串长度为1,部分匹配值就是0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当dest.charAt(i) != dest.charAt(j)时,我们需要从next[j-1]获取新的j
            // 知道我们发现有dest.charAt(i) == dest.charAt(j)成立才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j-1];
            }
            // 当dest.charAt(i) == dest.charAt(j)条件满足时,部分匹配值加一
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     *
     * @param str1 原字符串
     * @param str2 子串
     * @param next 部分匹配值表
     * @return -1就是没有匹配到,否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        // 遍历str1
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 处理str1.charAt(i) != str2.charAt(j)情况
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j-1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                // 找到了
                return i - j + 1;
            }
        }
        return -1;
    }
}
