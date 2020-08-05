public abstract class UsernamePasswordLoginModule$Modification {
    protected static boolean validatePassword_safe(String inputPassword, String expectedPassword) {
        if ((inputPassword == null) || (expectedPassword == null))
            return false;

        boolean valid = false;
        valid = slowEquals(inputPassword, expectedPassword);
        return valid;
    }

    protected static boolean validatePassword_unsafe(String inputPassword, String expectedPassword) {
        if ((inputPassword == null) || (expectedPassword == null))
            return false;

        boolean valid = false;
        valid = equals(inputPassword, expectedPassword);
        return valid;
    }

    private static boolean slowEquals(String stinga, String stringb) {
        int aLength = stinga.length();
        int bLength = stringb.length();
        int diff = aLength ^ bLength;
        int lenght = Math.min(aLength, bLength);
        for (int i = 0; i < lenght; i++) {
            diff |= stinga.charAt(i) ^ stringb.charAt(i);
        }
        return diff == 0;
    }

    public static boolean equals(String a, String b) {
        if (a == b)
            return true;

        int n = a.length();
        if (n == b.length()) {
            char[] v1 = a.toCharArray();
            char[] v2 = b.toCharArray();
            int i = 0;
            int j = 0;
            while ((n--) != 0) {
                if (v1[i++] != v2[j++])
                    return false;

            } 
            return true;
        }
        return false;
    }

    public static boolean equals$Modification(String a, String b) {
        boolean $2 = false;
        boolean $1 = false;
        if (a == b) {
            $1 = true;
        } else {
            $2 = true;
        }
        int n = a.length();
        int $6 = n;
        if (n == b.length()) {
            char[] v1 = a.toCharArray();
            char[] v2 = b.toCharArray();
            int i = 0;
            int j = 0;
            $1 = true;
            while ((n--) != 0) {
                if (((i < v1.length) && (j < v2.length)) && (v1[i++] != v2[j++])) {
                    $1 = false;
                } else {
                    $2 = false;
                }
            } 
        } else {
            char[] $3 = a.toCharArray();
            int $4 = 0;
            int $5 = 0;
            $2 = true;
            while (($6--) != 0) {
            } 
        }
        return $1;
    }
}