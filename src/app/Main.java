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

        Map<String, List<Product>> groupedIntoCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("1) List products by category");
        groupedIntoCategory.forEach((category, productsList) -> {
            System.out.format("%s:%n", category);
            productsList.forEach(product -> System.out.format("\t%s%n", product.getName()));
        });

        System.out.println("\n2) Average cost of goods in each category");
        groupedIntoCategory.forEach((category, listProducts) -> {
            System.out.format("%s: ", category);
            double averageCost = listProducts.stream()
                    .collect(Collectors.averagingDouble(Product::getPrice));
            System.out.format("%.2f%n", averageCost);
        });

        System.out.println("\n3) Category of goods with the highest average price");
        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)
                ));

        averagePriceByCategory.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> {
                    System.out.printf("%s: %.2f", entry.getKey(), entry.getValue());
                });
    }
}
