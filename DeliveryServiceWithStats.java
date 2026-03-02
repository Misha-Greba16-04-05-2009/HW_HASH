import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DeliveryServiceWithStats {
    public static void main(String[] args) {
        Map<Address, Integer> costPerAddress = new HashMap<>();

        costPerAddress.put(new Address("Россия", "Москва"), 150);
        costPerAddress.put(new Address("Россия", "Казань"), 200);
        costPerAddress.put(new Address("Россия", "Новосибирск"), 250);
        costPerAddress.put(new Address("США", "Нью-Йорк"), 500);
        costPerAddress.put(new Address("США", "Лос-Анджелес"), 550);
        costPerAddress.put(new Address("Германия", "Берлин"), 300);
        costPerAddress.put(new Address("Франция", "Париж"), 320);
        costPerAddress.put(new Address("Италия", "Рим"), 280);
        costPerAddress.put(new Address("Италия", "Милан"), 290);

        Scanner scanner = new Scanner(System.in);
        int totalCost = 0;

        Set<String> uniqueCountries = new HashSet<>();

        System.out.println("Сервис доставки товаров по миру");
        System.out.println("Для завершения работы введите 'end' в поле страны");

        while (true) {
            System.out.println("\n--- Заполнение нового заказа ---");

            System.out.print("Введите страну: ");
            String country = scanner.nextLine().trim();

            if (country.equalsIgnoreCase("end")) {
                break;
            }

            System.out.print("Введите город: ");
            String city = scanner.nextLine().trim();

            System.out.print("Введите вес (кг): ");
            double weight;
            try {
                weight = Double.parseDouble(scanner.nextLine().trim());
                if (weight <= 0) {
                    System.out.println("Ошибка: вес должен быть положительным числом.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число для веса.");
                continue;
            }

            Address deliveryAddress = new Address(country, city);

            Integer pricePerKg = costPerAddress.get(deliveryAddress);

            if (pricePerKg == null) {
                System.out.println("Доставки по этому адресу нет");
            } else {
                int deliveryCost = (int) (pricePerKg * weight);
                totalCost += deliveryCost;

                uniqueCountries.add(country);

                System.out.printf("Стоимость доставки составит: %d руб.%n", deliveryCost);
                System.out.printf("Общая стоимость всех доставок: %d руб.%n", totalCost);
                System.out.printf("Доставки оформлены в %d уникальных стран%n", uniqueCountries.size());
            }
        }

        System.out.println("\nПрограмма завершена.");
        System.out.println("Итоговая сумма всех доставок: " + totalCost + " руб.");
        System.out.println("Всего доставки были оформлены в " + uniqueCountries.size() + " уникальных стран:");

        if (!uniqueCountries.isEmpty()) {
            int count = 1;
            for (String country : uniqueCountries) {
                System.out.println(count++ + ". " + country);
            }
        }

        scanner.close();
    }
}