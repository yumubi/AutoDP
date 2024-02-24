package io.goji.vtdesign;

public class Dp {
    public static String match(String input, String dic) {
        if(input == null ||dic == null|| input.isEmpty() || dic.isEmpty() ||input.length() > dic.length() ){
            return null;
        }



        char[] inputChars = input.toUpperCase().toCharArray();
        char[] dicChars = dic.toUpperCase().toCharArray();
        int[][] dp = new int[dicChars.length][inputChars.length];

        dp[0][0]=dicChars[0]==inputChars[0]?1:0;

        for (int i = 1; i < dicChars.length; i++) {
            dp[i][0] = Math.max(dicChars[i] == inputChars[0] ? 1 : 0,dp[i-1][0]);

        }
        for (int i = 1; i < inputChars.length; i++) {
            dp[0][i] = Math.max(dicChars[0] == inputChars[i] ? 1 : 0,dp[0][i-1]);
        }

        for (int i = 1; i < dicChars.length; i++) {
            for (int j = 1; j < inputChars.length; j++) {
                if (dicChars[i] == inputChars[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }


            }
        }


        return ((dp[dicChars.length - 1][inputChars.length - 1]*100/inputChars.length)>50) ? dic : null;
    }
}