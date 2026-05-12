import java.util.*;
import java.util.stream.*;

public class LambdaLab4 {

    // 1 Фільтрація непарних чисел зі списку
    public static List<Integer> getOddNumbers(List<Integer> list) {
        return list.stream().filter(x -> x % 2 != 0).toList();
    }

    // 2 Обчислення середнього значення списку
    public static double getAverage(List<Double> list) {
        return list.stream().mapToDouble(x -> x).average().orElse(0);
    }

    // 3 Сортування рядків за алфавітом
    public static List<String> sortAlphabetically(List<String> list) {
        return list.stream().sorted(String::compareTo).toList();
    }

    // 4 Сума всіх парних чисел
    public static int sumEven(List<Integer> list) {
        return list.stream().filter(x -> x % 2 == 0).reduce(0, Integer::sum);
    }

    // 5 Обчислення факторіалу числа
    public static int factorial(int n) {
        return IntStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
    }

    // 6 Сума всіх елементів списку
    public static int sumAll(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }
    // Добуток всіх елементів списку
    public static int multiplyAll(List<Integer> list) {
        return list.stream().reduce(1, (a, b) -> a * b);
    }

    // 7 Квадрат кожного числа
    public static List<Integer> squares(List<Integer> list) {
        return list.stream().map(x -> x * x).toList();
    }

    // 8 Сортування рядків за довжиною
    public static List<String> sortByLength(List<String> list) {
        return list.stream().sorted(Comparator.comparingInt(String::length)).toList();
    }

    // 9 Підрахунок кількості слів у рядку
    public static long countWords(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .filter(s -> !s.isEmpty())
                .count();
    }

    // 10 Пошук першого непорожнього рядка
    public static Optional<String> firstNonEmpty(List<String> list) {
        return list.stream().filter(s -> !s.isEmpty()).findFirst();
    }

    // 11 Перевірка: чи всі рядки з великої літери
    public static boolean allStartUpper(List<String> list) {
        return list.stream()
                .allMatch(s -> !s.isEmpty() && Character.isUpperCase(s.charAt(0)));
    }

    // 12 Друге за величиною число
    public static Optional<Integer> secondLargest(List<Integer> list) {
        return list.stream()
                .distinct()
                .sorted((a, b) -> b - a)
                .skip(1)
                .findFirst();
    }

    // 13 Найбільше парне число
    public static Optional<Integer> maxEven(List<Integer> list) {
        return list.stream()
                .filter(x -> x % 2 == 0)
                .max(Integer::compare);
    }

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);
        List<String> words = Arrays.asList("Java", "stream", "", "Lambda", "Code");

        System.out.println("1: " + getOddNumbers(nums));
        System.out.println("2: " + getAverage(doubles));
        System.out.println("3: " + sortAlphabetically(words));
        System.out.println("4: " + sumEven(nums));
        System.out.println("5: " + factorial(5));
        System.out.println("6 sum: " + sumAll(nums));
        System.out.println("6 multiply: " + multiplyAll(nums));
        System.out.println("7: " + squares(nums));
        System.out.println("8: " + sortByLength(words));
        System.out.println("9: " + countWords("Hello world Java stream"));
        System.out.println("10: " + firstNonEmpty(words).orElse("Немає"));
        System.out.println("11: " + allStartUpper(words));
        System.out.println("12: " + secondLargest(nums).orElse(null));
        System.out.println("13: " + maxEven(nums).orElse(null));
    }
}