package signal;

/**
 * Date: 2021/8/3 20:48
 */
public class Test
{
    public static void main(String[] args) {
        secret("asd123");
    }

    public static void secret(String s) {
        int len = s.length();
        if (len <= 8) {
            char c = s.charAt(0);
            int[] num = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            for (int i = 0; i <= num.length; i++) {
                if (num[i] != c) {
                    for (int j= 0; j<=s.length();j++) {
                        if (s.contains("A-Za-z") || s.contains("0-9")) {
                            System.out.println("·ûºÏÒªÇó");
                        }
                    }
                }
            }
        }
    }
}
