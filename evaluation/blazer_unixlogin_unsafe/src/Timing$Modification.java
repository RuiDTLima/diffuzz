import java.util.HashMap;
public class Timing$Modification {
    private static HashMap<String, String> map = new HashMap<String, String>();

    public static void resetMap(String username, String password) {
        map.clear();
        map.put(username, md5(password));
    }

    public static String md5(String s) {
        int lim = 100000000 * s.length();
        int k = 0;
        for (int i = 0; i < lim; i++) {
            if ((k % 3) == 0) {
                k--;
            } else {
                k++;
            }
        }
        return Integer.toString(k);
    }

    public static boolean login_unsafe(String u, String p) {
        boolean outcome = false;
        if (map.containsKey(u)) {
            if (map.get(u).equals(md5(p))) {
                outcome = true;
            }
        }
        return outcome;
    }

    public static boolean login_unsafe$Modification(String u, String p) {
        boolean $1 = false;
        boolean $4;
        boolean outcome = false;
        if (map.containsKey(u)) {
            if (map.get(u).equals(md5(p))) {
                outcome = true;
            } else {
                $1 = true;
            }
        } else {
            String $2 = md5(p);
            String $3 = md5(p);
            if ($2.equals($3)) {
                $1 = true;
            } else {
                $4 = true;
            }
        }
        return outcome;
    }

    public static boolean login_safe(String u, String p) {
        boolean outcome = false;
        if (map.containsKey(u)) {
            if (map.get(u).equals(md5(p))) {
                outcome = true;
            }
        } else {
            boolean unused;
            String md5_str = md5(p);
            String md5_str2 = new String(md5_str);
            if (md5_str.equals(md5_str2)) {
                unused = false;
            }
        }
        return outcome;
    }
}