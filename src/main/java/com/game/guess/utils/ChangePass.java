package com.game.guess.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class ChangePass {
    private static int PBKDF2IterCount = 1000; // default for Rfc2898DeriveBytes
    private static int PBKDF2SubkeyLength = 256 / 8; // 256 bits
    private static int SaltSize = 128 / 8; // 128 bits

    public static void main(String[] args) throws Exception {
        String username = "V10078057";
        String password = "111111";
        String hashPassword = hashPassword(username, password);

        String Sql = "UPDATE BC_user_info SET LOGIN_PWD = '" + hashPassword +
                "',status ='ACTV', PASSWORD_FAIL_COUNT ='0', TIME_LOCK = null WHERE USER_NAME = '"+username+"';";
        System.out.println(Sql);
        // System.out.println(verifyHashedPassword(hashPassword, userId.toUpperCase().trim(), password));

    }
    public static String hashPassword(String userName,String password) {
        try {
            String hashPass = userName + password;
            byte[] salt = new byte[SaltSize];
            byte[] subkey;
            KeySpec spec = new PBEKeySpec(hashPass.toCharArray(), salt, PBKDF2IterCount, PBKDF2SubkeyLength * 8);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            subkey = factory.generateSecret(spec).getEncoded();
            byte[] outputBytes = new byte[1 + SaltSize + PBKDF2SubkeyLength];
            System.arraycopy(salt, 0, outputBytes, 1, SaltSize);
            System.arraycopy(subkey, 0, outputBytes, 1 + SaltSize, PBKDF2SubkeyLength);
            return Base64.getEncoder().encodeToString(outputBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean verifyHashedPassword(String hashedPassword,String userName, String passwordNew) {
        try {
            String hashNewPass = userName + passwordNew;
            byte[] hashedPasswordBytes = Base64.getDecoder().decode(hashedPassword);
            if (hashedPasswordBytes.length != (1 + SaltSize + PBKDF2SubkeyLength) || hashedPasswordBytes[0] != 0x00)
                return false;
            byte[] salt = new byte[SaltSize];
            System.arraycopy(hashedPasswordBytes, 1, salt, 0, SaltSize);
            byte[] storedSubkey = new byte[PBKDF2SubkeyLength];
            System.arraycopy(hashedPasswordBytes, 1 + SaltSize, storedSubkey, 0, PBKDF2SubkeyLength);
            byte[] generatedSubkey;
            KeySpec spec = new PBEKeySpec(hashNewPass.toCharArray(), salt, PBKDF2IterCount, PBKDF2SubkeyLength * 8);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            generatedSubkey = factory.generateSecret(spec).getEncoded();
            return Arrays.equals(storedSubkey, generatedSubkey);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
