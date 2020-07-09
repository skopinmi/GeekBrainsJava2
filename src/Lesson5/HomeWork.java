package Lesson5;

import java.util.Arrays;

public class HomeWork {

    static final int SIZE = 10000000;

    public static void main(String[] args) {

        float[] array =  createArray(SIZE);
        System.out.println("Выполнение задачи в 1 потоке.");
        method1(array);
        System.out.println("\n");

        float[] array1 = createArray(SIZE);
        System.out.println("Деление задачи на 2 потока. ");
        method2(array1);
        System.out.println(" \n");

        float[] array2 = createArray(SIZE);
        int n1 = 10;
        System.out.println("Деление задачи на N = " + n1 + " потоков с учетом разбиения массива на N " +
                "количество одинаковых.");
        method3(array2, n1);
        System.out.println(" \n");

        float[] array3 = createArray(SIZE);
        int n2 = 36;
        System.out.println("Деление задачи на N = " + n2 + " потоков с учетом разбиения массива на " + (n2 - 1) +
                        " количество одинаковых и один с остатками данных.");
        method4(array3, 11);
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
/*
    далее пошла эволюция многопоточного решения этой задачи!
    наделал исключений с обработкой - для вычисления ошибок с соответствием размеров массивов
 */
    public static void method2 (float [] array ) {
        long t = System.currentTimeMillis();
        int halfOfArray = array.length / 2;
        float [] array1 = new float[halfOfArray];
        float [] array2 = new float[halfOfArray];
        System.arraycopy(array, 0, array1, 0, array1.length);
        System.arraycopy(array, halfOfArray - 1, array2, 0, array2.length);
        System.out.println("first part of method 2 = " + (System.currentTimeMillis() - t));
        long time = System.currentTimeMillis();
/*
        код венесен в отдельный метод создание 1 потока с вычислением - makeThread(float array);
 */
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
/*
        создано 2 потока
 */
        try {
            makeThread(array1);
            makeThread(array2);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("second part of method 2 = " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        System.arraycopy(array1, 0, array, 0, halfOfArray);
        System.arraycopy(array2, 0, array, halfOfArray, array2.length);
        System.out.println("third part of method 2 = " + (System.currentTimeMillis() - time));

        System.out.println("Method 2 time= " + (System.currentTimeMillis() - t));
    }
/*
    запуск потока для вычисления
 */
    private static void makeThread (float [] array) throws InterruptedException {
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
        t1.join();
    }
/*
    многопоточное вычисление
    можно делить 1 массив на различное количество равнозначных подмассивов и запускать соответствующее
    количество потоков
    делит массив методом private static float [][] splitArray (float[] array , int number) throws Exception
    последний требует доработки - делит только на равные части, при отсутствии возможности выбрасывает исключение
    измерял скорость выполния в 3-х частях
    деление на подмассивы
    вычисления в потоках
    объединение массивов
 */
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
            try {
                makeThread(arrayOfArray);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println("second part of method 3 = " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();

        try {
            arrayFromArrays(arrayOfArrays, array);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("third part of method 3 = " + (System.currentTimeMillis() - time));
        System.out.println("Method 3 time= " + (System.currentTimeMillis() - t));
    }
/*
    деление одного массива на N количество равных массивов
    при невозможности разделить на равные части выбрасывает исключение
    возвращает результат в 2-х мерном массиве
 */
    public static float [][] splitArray (float[] array , int number) throws Exception {
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
/*
    сшивает все масивы (размеры не важны) из 2-х мерного в один массив
 */
    public static void arrayFromArrays (float[][] arrays, float [] array) throws Exception {
        int i = 0;
        for (float[] a : arrays) {
            i += a.length;
        }
        if (i != array.length) {
            throw new Exception("Несоответствие размеров массивов в аргументах метода");
        }

        int start = 0;
        for (float[] floats : arrays) {
            System.arraycopy(floats, 0, array, start, floats.length);
            start += floats.length;
        }
    }

/*
      деление одного массива на N - 1 количество равных массивов и один с остатком
      возвращает результат в 2-х мерном массиве
      выбрасывает исключение при размере массива меньше N
*/
    public static float [][] splitArray2 (float[] array , int number) throws Exception {
        float[][] resultOfArray;
        resultOfArray = new float[number][];
        if (array.length < number) {
            throw new Exception("Размер массива меньше " + number);
        }
        if (array.length % number == 0) {
            for (int i = 0; i < number; i++) {
                resultOfArray[i] = new float[array.length / number];
                System.arraycopy(array, i * array.length / number, resultOfArray[i], 0, resultOfArray[i].length);
            }
        } else {
            boolean doIt = true;
            int i = 1;
            while (doIt){
                i++;
                if ((array.length - i) % (number - 1) == 0) {
                    for (int ii = 0; ii < number - 1; ii++) {
                        resultOfArray[ii] = new float[(array.length - i)/ (number - 1)];
                        System.arraycopy(array, ii * array.length / number, resultOfArray[ii],
                                0, resultOfArray[ii].length);
                    }
                    resultOfArray[number - 1] = new float[i];
                    System.arraycopy(array, array.length - i, resultOfArray[number - 1], 0, i);
                    doIt = false;
                }
            }
        }
        return resultOfArray;
    }
/*
       многопоточное вычисление код тот же что и метод 3 , но делит массив
       методом private static float [][] splitArray2 (float[] array , int number)
       можно делить 1 массив на N - 1 количество равнозначных подмассивов  и один массив с остальными данными
       и запускать N количество потоков для вычисления
       по сути дублирование кода method 3, но несколько изменена логика
*/
    public static void method4 (float [] array, int number ) {
        long t = System.currentTimeMillis();
        float[][] arrayOfArrays = null;
        try {
            arrayOfArrays = splitArray2(array, number);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("first part of method 4 = " + (System.currentTimeMillis() - t));
        long time = System.currentTimeMillis();

        for (float[] arrayOfArray : arrayOfArrays) {
            try {
                makeThread(arrayOfArray);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println("second part of method 4 = " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();

        try {
            arrayFromArrays(arrayOfArrays, array);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("third part of method 4 = " + (System.currentTimeMillis() - time));
        System.out.println("Method 4 time= " + (System.currentTimeMillis() - t));
    }
}
