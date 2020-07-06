import java.io.Serializable;

public class Credential {

    public static boolean stringEquals_safe(String s1, String s2) {
        boolean result = true;
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 != l2)
            result = false;
        int n = (l1 < l2) ? l1 : l2;
        for (int i = 0; i < n; i++)
            result &= s1.charAt(i) == s2.charAt(i);
        return result;
    }

    public static boolean stringEquals_original(String s1, String s2) {
        boolean $1 = false;
        if (s1 == s2) {
            $1 = true;
        }
        int n = s1.length();
        if (n == s2.length()) {
            char[] v1 = s1.toCharArray();
            char[] v2 = s2.toCharArray();
            int i = 0;
            $1 = true;
            while ((n--) != 0) {
                if ((i < v1.length) && (i < v2.length) && v1[i] != v2[i]) {
                    $1 = false;
                }
                i++;
            } 
            return $1;
        }
        return $1;
    }

}