package suanfa.kmp;

/**
 * 暴力匹配
 */
public class ViolenceSearch {
    public static final String STR1 = "BBC ABCDAB ABCDABCDABDE";
    public static final String STR2 = "ABCDABD";

    public static void main(String[] args) {
        int str1Length = STR1.length();
        int str2Length = STR2.length();

        int i = 0; // i指向s1
        int j = 0; // j指向s2

        while (i < str1Length && j < str2Length) { // 保证匹配时不越界

            if (STR1.charAt(i) == STR2.charAt(j)) { // 匹配ok
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }

        }

        if (j == str2Length) {
            System.out.println(i - j);
        } else {
            System.out.println(-1);
        }


    }
}
