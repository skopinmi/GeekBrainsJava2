package Lesson5;

import java.util.Arrays;

public class HomeWork {
    public static void main(String[] args) {
        float[] array = createArray(10000000);
        method1(array);
        System.out.println("--------");
        method2(array);
        System.out.println("--------");
        method3(array, 10);
    }

    public static float [] createArray (int size) {
        float [] array = new float [size];
        Arrays.fill(array, 1);
        return array;
    }

    public static void method1 (float [] array ) {
        long t = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Method 1 time = " + (System.currentTimeMillis() - t));
    }

    public static void method2 (float [] array ) {
        long t = System.currentTimeMillis();
        int halfOfArray = array.length / 2;
        float [] array1 = new float[halfOfArray];
        float [] array2 = new float[halfOfArray];
        System.arraycopy(array, 0, array1, 0, array1.length);
        System.arraycopy(array, halfOfArray - 1, array2, 0, array2.length);
        System.out.println("first part of method 2 = " + (System.currentTimeMillis() - t));
        long time = System.currentTimeMillis();
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < array1.length; i++) {
//                    array1[i] = (float) (array1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//                }
//            }
//        });
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < array2.length; i++) {
//                    array2[i] = (float) (array2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//                }
//            }
//        });
//        t1.start();;
//        t2.start();;
        makeThread(array1);
        makeThread(array2);

        System.out.println("second part of method 2 = " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        System.arraycopy(array1, 0, array, 0, halfOfArray);
        System.arraycopy(array2, 0, array, halfOfArray, array2.length);
        System.out.println("third part of method 2 = " + (System.currentTimeMillis() - time));

        System.out.println("Method 2 time= " + (System.currentTimeMillis() - t));
    }

    private static void makeThread (float [] array) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < array.length; i++) {
                    array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f
                            + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        t1.start();
    }

    public static void method3 (float [] array, int number ) {
        long t = System.currentTimeMillis();
        float[][] arrayOfArrays = null;
        try {
             arrayOfArrays = splitArray(array, number);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("first part of method 3 = " + (System.currentTimeMillis() - t));
        long time = System.currentTimeMillis();

        for (float[] arrayOfArray : arrayOfArrays) {
            makeThread(arrayOfArray);
        }
        System.out.println("second part of method 3 = " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        float[] arraysFromArrays = arrayFromArrays(arrayOfArrays);
        System.out.println("third part of method 3 = " + (System.currentTimeMillis() - time));
        System.out.println("Method 3 time= " + (System.currentTimeMillis() - t));
    }

    private static float [][] splitArray (float[] array , int number) throws Exception {
        float [][] resultOfArray;
        resultOfArray = new float[number][];
        if (array.length % number == 0) {
            for (int i = 0; i < number; i++) {
                resultOfArray[i] = new float[array.length/number];
                System.arraycopy(array, i * array.length / number, resultOfArray[i], 0, resultOfArray[i].length);
            }
        } else {
            throw new Exception("Массив не делится на равные " + number);
        }
        return resultOfArray;
    }
    private static float [] arrayFromArrays (float[][] array) {
        int size = 0;
        for (int i = 0; i < array.length; i++) {
            size += array[i].length;
        }
        float [] resultArray = new float[size];
        int start = 0;
        for (int i = 0; i < array.length; i++) {
            int finish = start + array[i].length;
            System.out.println(finish);
            System.arraycopy(array[i], 0, resultArray, start, finish);
            start = finish;
            System.out.println(start);
        }
        return resultArray;
    }
}
