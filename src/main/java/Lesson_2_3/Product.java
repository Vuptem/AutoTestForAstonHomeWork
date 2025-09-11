package Lesson_2_3;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Product {
    private final String name;
    private final LocalDate productionDate;
    private final String manufacturer;
    private final String originCountry;
    private final double price;
    private boolean reserved;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Product(String name,
                   String productionDateStr,
                   String manufacturer,
                   String originCountry,
                   double price,
                   boolean reserved){
        this.name = name;
        this.productionDate = LocalDate.parse(productionDateStr, DTF);
        this.manufacturer = manufacturer;
        this.originCountry = originCountry;
        this.price = price;
        this.reserved = reserved;
    }

    public void printInfo(){
        System.out.println("Товар: " + name);
        System.out.println("Дата производства: " + productionDate.format(DTF));
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна просихождения: " + originCountry);
        System.out.println("Цена: " + price);
        System.out.println("Забронирован " + (reserved ? "Да" : "Нет"));
        System.out.println("-------------------------------------");
    }

    public boolean isReserved(){return reserved;}
    public void setReserved(boolean reserved){this.reserved = reserved;}
    public LocalDate getProductionDate(){return productionDate;}
}
