class MoreSanity$Modification {
    public static boolean array_safe(int[] a, int taint) {
        int t;
        if (taint < 0) {
            int i = a.length - 1;
            while (i >= 0) {
                t = a[i];
                i--;
            } 
        } else {
            int i = 0;
            while (i < a.length) {
                t = a[i] / 2;
                i++;
            } 
        }
        return false;
    }

    public static boolean array_unsafe(int[] a, int taint) {
        System.out.println(a.length);
        int t;
        if (taint < 0) {
            int i = a.length - 1;
            while (i >= 0) {
                t = a[i];
                i--;
            } 
        } else {
            int i = 0;
            t = a[i] / 2;
            i = a.length;
        }
        return false;
    }

    public static boolean array_unsafe$Modification(int[] a, int taint) {
        int $2;
        System.out.println(a.length);
        int t;
        if (taint < 0) {
            int i = a.length - 1;
            while (i >= 0) {
                t = a[i];
                i--;
            } 
            int $3 = 0;
            $2 = a[$3] / 2;
            $3 = a.length;
        } else {
            int i = 0;
            t = a[i] / 2;
            i = a.length;
            int $1 = a.length - 1;
            while ($1 >= 0) {
                $2 = a[$1];
                $1--;
            } 
        }
        return false;
    }

    public static boolean loopAndbranch_safe(int a, int taint) {
        int i = a;
        if (taint < 0) {
            while (i > 0) {
                i--;
            } 
        } else {
            taint = taint + 10;
            if (taint >= 10) {
                int j = a;
                while (j > 0) {
                    j--;
                } 
            } else if (a < 0) {
                int k = a;
                while (k > 0)
                    k--;

            }
        }
        return true;
    }

    public static boolean loopAndbranch_unsafe(int a, int taint) {
        int i = a;
        if (taint < 0) {
            while (i > 0) {
                i--;
            } 
        } else {
            taint = taint - 10;
            if (taint >= 10) {
                int j = a;
                while (j > 0) {
                    j--;
                } 
            } else if (a < 0) {
                int k = a;
                while (k > 0)
                    k--;

            }
        }
        return true;
    }
}