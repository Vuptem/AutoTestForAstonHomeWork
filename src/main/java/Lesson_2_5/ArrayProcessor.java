package Lesson_2_5;

public class ArrayProcessor {
    public static int processArray(String[][] arr)
        throws MyArraySizeException, MyArrayDataException {
        if(arr.length != 4){
            throw new MyArraySizeException("Неверное количество строк: " + arr.length);
        }
        for(int i = 0; i<arr.length; i++){
            if(arr[i].length !=4){
                throw new MyArraySizeException(
                        "Неверное количество столбоцв в строке" + i
                        + ": " + arr[i].length);
            }
        }
        int sum = 0;

        for (int i = 0; i<arr.length; i++){
            for(int j = 0; j<arr[i].length; j++){
                try{
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e){
                    throw new MyArrayDataException(i,j, arr[i][j]);
                }
            }
        }
        return sum;
    }
}
