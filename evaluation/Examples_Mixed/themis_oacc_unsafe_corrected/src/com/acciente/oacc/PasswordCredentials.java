/*
 * Copyright 2009-2017, Acciente LLC
 *
 * Acciente LLC licenses this file to you under the
 * Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.acciente.oacc;

import java.util.Arrays;

/**
 * This is a {@link Credentials} implementation that may be used by an {@link AuthenticationProvider} that provides
 * password-based authentication. The built-in {@link AuthenticationProvider} requires that the {@link Credentials}
 * object passed be an instance of this class. If you implement a custom password-based {@link AuthenticationProvider},
 * it is recommended that you accept instances of this class, but you are not required to. If you accept instances of
 * this class it allows switching to your implementation from the built-in implementation without any changes in the
 * calling code. .
 */
public abstract class PasswordCredentials implements Credentials {
    /**
     * Returns the password contained in this credentials instance
     *
     * @return a password as a char array
     */
    public abstract char[] getPassword();

    public static PasswordCredentials newInstance(char[] password) {
        return new Impl(password);
    }

    private static class Impl extends PasswordCredentials {
        private final char[] password;

        private Impl(char[] password) {
            this.password = password;
        }

        @Override
        public char[] getPassword() {
            return password;
        }

        @Override
        /*public boolean equals(Object other) {
            boolean $1;
            if (PasswordCredentials.Impl.this == other) {
                $1 = true;
            }
            if ((other == null) || (getClass() != other.getClass())) {
                $1 = false;
            }
            PasswordCredentials.Impl impl = ((PasswordCredentials.Impl) (other));
            $1 = ArraysIsEquals(password, impl.password);
            return $1;
        }*/
        public boolean equals(Object other) {
            boolean $2;
            boolean $1;
            if (PasswordCredentials.Impl.this == other) {
                $1 = true;
            } else {
                $2 = true;
            }
            if ((other == null) || (getClass() != other.getClass())) {
                $1 = false;
            } else {
                $2 = false;
            }
            PasswordCredentials.Impl impl = ((PasswordCredentials.Impl) (other));
            $1 = ArraysIsEquals(password, impl.password);
            return $1;
        }

        /* YN: inline */
        /*public static boolean ArraysIsEquals(char[] a, char[] a2) {
            if (a == a2)
                return true;
            if (a == null || a2 == null)
                return false;

            int length = a.length;
            if (a2.length != length)
                return false;

            for (int i = 0; i < length; i++)
                if (a[i] != a2[i])
                    return false;

            return true;
        }*/
        public static boolean ArraysIsEquals(char[] a, char[] a2) {
            boolean $2 = true;
            boolean $1 = true;
            if (a == a2) {
                $1 = true;
            } else {
                $2 = true;
            }
            if ((a == null) || (a2 == null)) {
                $1 = false;
            } else {
                $2 = false;
            }
            int length = 0;
            if (a != null) {
                length = a.length;
            }
            if ((a2 != null) && (a2.length != length)) {
                $1 = false;
            } else {
                $2 = false;
            }
            for (int i = 0; i < length; i++) {
                if ((((a != null) && (i < a.length)) && ((a2 != null) && (i < a2.length))) && (a[i] != a2[i])) {
                    $1 = false;
                } else {
                    $2 = false;
                }
            }
            return $1;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(password);
        }
    }
}
