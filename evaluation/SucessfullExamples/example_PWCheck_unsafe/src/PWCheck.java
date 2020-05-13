public class PWCheck {
    /*public static boolean pwcheck1_unsafe(byte[] guess, byte[] pw) {
        if (guess.length != pw.length) {
            return false;
        }

        int i;

        for (i = 0; i < guess.length; i++) {
            if (guess[i] != pw[i]) {
                return false;
            }
        }

        return true;

    }*/

    public static boolean pwcheck1_unsafe(byte[] guess, byte[] pw) {
        boolean $1 = true;
        if (guess.length != pw.length) {
            $1 = false;
        }
        int i;
        for (i = 0; i < guess.length; i++) {
            if ((i < guess.length) && (i < pw.length) && guess[i] != pw[i]) {
                $1 = false;
            }
        }
        return $1;
    }

    /*public static boolean pwcheck2_unsafe(byte[] guess, byte[] pw) {
        if (guess.length != pw.length) {
            return false;
        }

        int i;
        boolean unused;
        boolean matches = true;
        for (i = 0; i < guess.length; i++) {
            if (i < pw.length) {
                if (guess[i] != pw[i]) {
                    matches = false;
                } else {
                    unused = true;
                }
            } else {
                unused = false;
                unused = true;
            }
        }
        return matches;
    }*/

    /*public static boolean pwcheck3_safe(byte[] guess, byte[] pw) {
        int i;
        boolean unused;
        boolean matches = true;
        for (i = 0; i < guess.length; i++) {
            if (i < pw.length) {
                if (guess[i] != pw[i]) {
                    matches = false;
                } else {
                    unused = true;
                }
            } else {
                unused = false;
                unused = true;
            }
        }

        return matches;

    }*/
}
