import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();

        isSumBetween(12, 14);
        printNumberSign(0);
        System.out.println(isNegative(-1));
        printStringTimes("Hello", 3);

        System.out.println(isLeapYear(2000));
        System.out.println(isLeapYear(1900));
        System.out.println(isLeapYear(2025));

        int[] a10 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        flipZeroArray(a10);
        System.out.println(Arrays.toString(a10));
        System.out.println(Arrays.toString(arr1To100()));

        int[] a12 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyLessThan6by2(a12);
        System.out.println(Arrays.toString(a12));

        int[][] m = fillDiagonals(5);
        for (int[] row : m) System.out.println(Arrays.toString(row));

        System.out.println(Arrays.toString(createArray(5, 42)));

    }


    //1
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    //2
    public static void checkSumSign() {
        int a = 5;
        int b = 10;
        if ((a + b) >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    //3
    public static void printColor() {
        int value = 10;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else if (value > 100) {
            System.out.println("Зеленый");
        }
    }

    //4
    public static void compareNumbers() {
        int a = 10;
        int b = 11;
        if (a >= b) {
            System.out.println("a>=b");
        } else {
            System.out.println("a<=b");
        }
    }

    //5
    public static boolean isSumBetween(int a, int b) {
        int s = a + b;
        return s >= 10 && s <= 20;
    }

    //6
    public static void printNumberSign(int x) {
        if (x >= 0) {
            System.out.println(x + "Положительное число");
        } else {
            System.out.println(x + "Отрицательное число");
        }
    }

    //7
    public static boolean isNegative(int x) {
        return x < 0;
    }

    //8
    public static void printStringTimes(String s, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(s);
        }
    }

    //9
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }

    //10
    public static void flipZeroArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) arr[i] = 1;
            else if (arr[i] == 1) arr[i] = 0;
        }
    }

    //11
    public static int[] arr1To100() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    //12
    public static void multiplyLessThan6by2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] *= 2;
        }
    }

    //13
    public static int[][] fillDiagonals(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i][i] = 1;
            matrix[i][n - 1 - i] = 1;
        }
        return matrix;
    }

    //14
    public static int[] createArray(int len, int initialValue) {
        int[] arr = new int[len];
        Arrays.fill(arr, initialValue);
        return arr;
    }
}