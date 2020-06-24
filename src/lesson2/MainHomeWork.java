package lesson2;

/*
   класс ошика размера
 */
class MyArraySizeException extends Exception {
    private int size;
    public MyArraySizeException (String msg, int size) {
        super(msg);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
/*
    класс ошибка данных
 */
class MyArrayDataException extends Exception {
    private String dateException;
    public MyArrayDataException (String msg, String dateException) {
        super(msg);
        this.dateException = dateException;
    }

    public String getDateException() {
        return dateException;
    }
}

class MainHomeWork {
    public static void main(String[] args) {
/*
        создание массива
 */
        String[][] array = new String[4][];
        array[0] = new String[]{"1", "2", "3", "4"};
        array[1] = new String[]{"1jhh", "2", "3", "4"};
        array[2] = new String[]{"1", "2", "3", "4"};
        array[3] = new String[]{"1", "2", "3", "4"};
/*
        массив с ошибкой размера
 */
//        array[0] = new String[]{"1", "2", "3", "4"};
//        array[1] = new String[]{"1", "2", "3", "4"};
//        array[2] = new String[]{"1", "2", "3", "4", "5"};
//        array[3] = new String[]{"1", "2", "3", "4"};
/*
        блок вызов метода с обработками ошибок
 */
        try {
            System.out.println(calculateNumbersArray(array));
        } catch (MyArraySizeException e) {
            System.out.println(e);
//            System.out.println(e.getSize());
        } catch (MyArrayDataException e) {
            System.out.println(e);
            System.out.println("Ячейка содержит строку: " + e.getDateException());
        }

    }
/*
       МЕТОД СЧЕТА ДАННЫХ МАССИВА
 */
    public static int calculateNumbersArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
/*
        проверка размера
 */
        if (array.length != 4) {
            throw new MyArraySizeException("Ошибка размера массива : размер первого изменения массива отличен " +
                    "от 4, он равен " + array.length, array.length);
        } else {
            for (int i = 0; i < 4; i++) {
                if (array[i].length != 4) {
                    throw new MyArraySizeException("Ошибка размера массива : массив в ячейке ["
                            + i + "] имеет размер " + array[i].length, array[i].length);
                }
            }
        }

//      переменная для подсчета результатае
        int result = 0;

//      итерация массива

        for (int i = 0; i < array.length; i++) {
            for (int ii = 0; ii < array[i].length; ii++) {

 //     проверка возможности перевода в Integer

                for (int numberChar = 0; numberChar < array[i][ii].length(); numberChar++) {
                    if ((int) array[i][ii].charAt(numberChar) > 57 || (int) array[i][ii].charAt(numberChar) < 48) {
                        throw new MyArrayDataException("В масиве в ячеке [" + i + "][" + ii + "] " +
                                "находятся неверные данные.", array[i][ii]);
                    }
                }

 //             перевод в int и сложение
                result += Integer.parseInt(array[i][ii]);
            }
        }
        return result;
    }
}