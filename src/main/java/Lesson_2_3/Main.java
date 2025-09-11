package Lesson_2_3;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 15 Pro", "20.09.2024", "Apple Inc.", "USA", 1199, false);
        productsArray[2] = new Product("PlayStation 5", "10.10.2023", "Sony", "Japan", 699, false);
        productsArray[3] = new Product("Xiaomi Mi Band 9", "05.08.2025", "Xiaomi", "China", 79, true);
        productsArray[4] = new Product("Kindle Paperwhite", "11.03.2025", "Amazon", "USA", 149, false);

        System.out.println("=== Товары ===");
        Arrays.stream(productsArray).forEach(Product::printInfo);

        Park park = new Park("DreamLand");
        park.addAttraction("Колесо обозрения", "10:00", "22:00", 8.5);
        park.addAttraction("Американские горки", "11:00", "21:30", 12.0);
        park.addAttraction("Комната страха", "12:00", "20:00", 7.0);

        System.out.println("=== Аттракционы парка ===");
        park.printAllAttractions();

        var now = java.time.LocalTime.now();
        System.out.println("Проверка открытости на текущее время " + now);
        park.getAttractions().forEach(a ->
                System.out.println(a+" Открыт сейчас? " + a.isOpenAt(now))
        );

        Product phone = new Product(
                "Samsung S25 Ultra",
                "01.02.2025",
                "Samsung Corp.",
                "Korea",
                5599,
                true);

        if(phone.isReserved()){
            System.out.println("Телефон уже забронирован!");
        }else{
            System.out.println("Телефон свободен, бронируем...");
            phone.setReserved(true);
        }

        System.out.println("Дата производства: " + phone.getProductionDate());

        if(phone.getProductionDate().isAfter(java.time.LocalDate.of(2024, 12, 31))){
            System.out.println("Это новый товар (после 2024 года).");
        }
    }
}