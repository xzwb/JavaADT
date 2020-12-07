package mainshi;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class MaxSonString {
    public int maxString(String string) {
        int left = 0;
        int right = 0;
        int maxLength = -1;
        Set<Character> set = new HashSet<>();
        int size = string.length();
        while (right < size) {
            char c = string.charAt(right++);
            // 存在相同字符，移动左指针，直到滑动窗口中不含有该字符
            while (set.contains(c)) {
                set.remove(string.charAt(left++));
            }
            set.add(c);
            maxLength = Math.max(maxLength, right-left);
        }
        return maxLength;
    }
}
