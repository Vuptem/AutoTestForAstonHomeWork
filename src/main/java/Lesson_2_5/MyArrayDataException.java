package Lesson_2_5;

public class MyArrayDataException extends Exception{
    public MyArrayDataException(int row, int col, String value){
        super("Ошибка преобразования в ячейке [" + row + "][" + col + "], значение: " + value);
    }
}
