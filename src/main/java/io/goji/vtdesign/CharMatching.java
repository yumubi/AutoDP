package io.goji.vtdesign;

import java.util.concurrent.Callable;

public class CharMatching implements Callable<String> {


    private String inputString;
    private String dicString;

    public CharMatching(String inputString, String dicString) {
        this.inputString = inputString;
        this.dicString = dicString;
    }



    @Override
    public String call() throws Exception {
        return Dp.match(inputString, dicString);
    }
}
