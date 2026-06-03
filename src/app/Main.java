package app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0),
                new Product("Phone", "Electronics", 500.0)
        );

        Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("1) List products by category");
        productsByCategory.forEach((category, productsList) -> {
            System.out.format("%s:%n", category);
            productsList.forEach(product -> System.out.format("\t%s%n", product.getName()));
        });

        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice))
                );

        System.out.println("\n2) Print average cost for each category");
        averagePriceByCategory.forEach((category, price) -> {
            System.out.printf("%s: %.2f%n", category, price);
        });

        System.out.println("\n3) Find category with max average price");
        averagePriceByCategory.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> {
                    System.out.printf("%s: %.2f", entry.getKey(), entry.getValue());
                });
    }
}
