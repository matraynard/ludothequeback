package com.example.concurrent;

public class OneThread extends Thread {
    public void run(){
        int n = 0;
        while (n++ < 100){
            System.out.println("Il est bô mon batô.");
            try {
                Thread.sleep(10);
            }
            catch(InterruptedException e){

            }
        }
    }

}