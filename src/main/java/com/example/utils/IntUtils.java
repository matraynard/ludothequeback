package com.example.utils;

import java.util.concurrent.ThreadLocalRandom;

public class IntUtils {
    public static int getRandomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}