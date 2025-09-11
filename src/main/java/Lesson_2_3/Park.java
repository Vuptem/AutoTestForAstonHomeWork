package Lesson_2_3;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Park {
    public class  Attraction  {
        private final String name;
        private final LocalTime openTime;
        private final LocalTime closeTime;
        private final double price;

        public Attraction (String name,LocalTime openTime, LocalTime closeTime, double price){
            this.name = name;
            this.openTime = openTime;
            this.closeTime = closeTime;
            this.price = price;
        }

        public boolean isOpenAt(LocalTime time){
            return !time.isBefore(openTime) && !time.isAfter(closeTime);
        }

        public void printInfo(){
            System.out.println("Аттракцион: "+name);
            System.out.println("Время работы: "+openTime + "-" + closeTime);
            System.out.println("Стоимость: "+price);
            System.out.println("-------------------------------------");
        }
    }
    private final String parkName;
    private final List<Attraction> attractions = new ArrayList<>();

    public Park (String parkName){
        this.parkName = parkName;
    }
    public Attraction addAttraction(String name,
                                    String openHHmm,
                                    String closeHHmm,
                                    double price) {
        Attraction a = new Attraction(
                name,
                LocalTime.parse(openHHmm),
                LocalTime.parse(closeHHmm),
                price
        );
        attractions.add(a);
        return a;
    }

    public void printAllAttractions(){
        System.out.println("Парк: "+ parkName);
        System.out.println("Всего аттракционов: "+ attractions.size());
        System.out.println("=====================================");
        for (Attraction a : attractions){
            a.printInfo();
        }
    }

    public  List<Attraction> getAttractions(){
        return attractions;
    }

}
