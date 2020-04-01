public class User2 {
    public static boolean passwordsEqual_safe(String a, String b) {
        boolean equal = true;
        boolean shmequal = true;
        int aLen = a.length();
        int bLen = b.length();
        if (aLen != bLen) {
            equal = false;
        }
        int min = Math.min(aLen, bLen);
        for (int i = 0; i < min; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                equal = false;
            } else {
                shmequal = true;
            }
        }
        return equal;
    }

    public static boolean passwordsEqual_unsafeModification(String a, String b) {
        boolean equal = true;
        boolean shmequal = true;
        int aLen = a.length();
        int bLen = b.length();
        if (aLen != bLen) {
            equal = false;
        }
        int min = Math.min(aLen, bLen);
        for (int i = 0; i < min; i++) {
            if (a.charAt(i) != b.charAt(i))
                 equal = false;
            else {
                shmequal = true;
            }
        }
        return equal;
    }
}