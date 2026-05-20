package lab7;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RxJavaLab7 {

    enum Status {
        DELIVERED, PENDING, CANCELLED
    }

    record Order(String id, Status status, double amount) {
    }

    record Product(String name, double priceUsd) {
    }

    record FoodOrder(String orderId, List<String> items) {
    }

    record ServiceCall(String serviceName, int delayMs) {
    }

    public static void main(String[] args) throws Exception {
        task1_1_FunctionalStyle();
        task1_2_CompareStyles();

        task2_1_FirstObservable();
        task2_2_ColdVsHotObservable();

        task3_1_MapAndFilter();
        task3_2_FlatMapAndConcatMap();

        task4_1_Single();
        task4_2_MaybeAndCompletable();

        task5_1_Schedulers();
        task5_2_ParallelProcessing();

        task6_1_Debounce();
        task6_2_BufferAndBackpressure();

        task7_1_ErrorHandling();
        task7_2_RetryWithBackoff();
    }

    // 1.1. Імперативний стиль -> функціональний стиль
    private static void task1_1_FunctionalStyle() {
        List<Order> orders = Arrays.asList(
                new Order("O-001", Status.DELIVERED, 1500.00),
                new Order("O-002", Status.PENDING, 300.00),
                new Order("O-003", Status.CANCELLED, 75.00),
                new Order("O-004", Status.DELIVERED, 2200.00),
                new Order("O-005", Status.PENDING, 450.00),
                new Order("O-006", Status.DELIVERED, 980.00)
        );

        long count = orders.stream()
                .filter(order -> order.status() == Status.DELIVERED)
                .count();

        double totalDelivered = orders.stream()
                .filter(order -> order.status() == Status.DELIVERED)
                .mapToDouble(Order::amount)
                .sum();

        System.out.println("Виконаних замовлень: " + count);
        System.out.println("Загальна сума: " + totalDelivered);
    }

    // 1.2. Імперативний, функціональний та реактивний підходи
    private static void task1_2_CompareStyles() {
        List<String> cities = Arrays.asList(
                "Київ", "Харків", "Одеса", "Дніпро", "Запоріжжя",
                "Кривий Ріг", "Миколаїв", "Херсон", "Кропивницький",
                "Черкаси", "Суми", "Хмельницький", "Чернівці", "Каховка"
        );

        List<String> imperativeResult = new ArrayList<>();

        for (String city : cities) {
            if (city.startsWith("К")) {
                imperativeResult.add(city.toUpperCase());
            }
        }

        Collections.sort(imperativeResult);

        for (String city : imperativeResult) {
            System.out.println(city);
        }

        cities.stream()
                .filter(city -> city.startsWith("К"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        Observable.fromIterable(cities)
                .filter(city -> city.startsWith("К"))
                .map(String::toUpperCase)
                .sorted()
                .subscribe(System.out::println);
    }

    // 2.1. Перший Observable
    private static void task2_1_FirstObservable() {
        Observable<String> atm = Observable.just(
                "Вставте картку",
                "Введіть PIN-код",
                "Оберіть суму: 500 грн",
                "Видача готівки...",
                "Дякуємо! Заберіть картку"
        );

        atm.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("[БАНКОМАТ] Сесію розпочато");
            }

            @Override
            public void onNext(String step) {
                System.out.println(">> " + step);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("[БАНКОМАТ] Помилка: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("[БАНКОМАТ] Сесію завершено");
            }
        });
    }

    // 2.2. Холодний та гарячий Observable
    private static void task2_2_ColdVsHotObservable() throws InterruptedException {
        List<String> results = Arrays.asList(
                "Динамо 2:1 Шахтар",
                "Шахтар 3:0 Металіст",
                "Дніпро 1:1 Зоря",
                "Карпати 0:2 Рух",
                "Полісся 3:2 Верес"
        );

        Observable<String> cold = Observable.fromIterable(results);

        cold.subscribe(match -> System.out.println("Підписник 1: " + match));
        cold.subscribe(match -> System.out.println("Підписник 2: " + match));

        ConnectableObservable<String> hot = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(results.size())
                .map(index -> results.get(index.intValue()))
                .publish();

        hot.subscribe(match -> System.out.println("Гарячий підписник 1: " + match));

        hot.connect();

        Thread.sleep(2_000);

        hot.subscribe(match -> System.out.println("Гарячий підписник 2: " + match));

        Thread.sleep(5_000);
    }

    // 3.1. map() та filter()
    private static void task3_1_MapAndFilter() {
        List<Product> products = Arrays.asList(
                new Product("Навушники Sony", 49.99),
                new Product("Клавіатура Logitech", 129.00),
                new Product("Монітор LG 27\"", 399.00),
                new Product("USB-хаб Anker", 35.00),
                new Product("Веб-камера Logitech", 149.00),
                new Product("Килимок для миші", 18.00),
                new Product("SSD Samsung 1TB", 110.00)
        );

        double rate = 41.5;

        Observable.fromIterable(products)
                .filter(product -> product.priceUsd() > 100)
                .map(product -> String.format(
                        "%s -- %.2f грн (є в наявності)",
                        product.name(),
                        product.priceUsd() * rate
                ))
                .subscribe(System.out::println);
    }

    // 3.2. flatMap() та concatMap()
    private static void task3_2_FlatMapAndConcatMap() throws InterruptedException {
        List<FoodOrder> orders = Arrays.asList(
                new FoodOrder("ZAM-01", Arrays.asList("Піца Маргарита", "Кола 0.5л")),
                new FoodOrder("ZAM-02", Arrays.asList("Борщ", "Вареники", "Компот")),
                new FoodOrder("ZAM-03", Arrays.asList("Суші-сет 20шт", "Місо-суп"))
        );

        Observable.fromIterable(orders)
                .flatMap(order -> Observable.fromIterable(order.items()))
                .subscribe(item -> System.out.println(">> " + item));

        CountDownLatch flatLatch = new CountDownLatch(1);

        Observable.fromIterable(orders)
                .flatMap(order -> Observable.timer(500, TimeUnit.MILLISECONDS)
                        .flatMap(timer -> Observable.fromIterable(order.items())))
                .subscribe(
                        item -> System.out.println("flatMap: " + item),
                        error -> System.out.println(error.getMessage()),
                        flatLatch::countDown
                );

        flatLatch.await();

        CountDownLatch concatLatch = new CountDownLatch(1);

        Observable.fromIterable(orders)
                .concatMap(order -> Observable.timer(500, TimeUnit.MILLISECONDS)
                        .flatMap(timer -> Observable.fromIterable(order.items())))
                .subscribe(
                        item -> System.out.println("concatMap: " + item),
                        error -> System.out.println(error.getMessage()),
                        concatLatch::countDown
                );

        concatLatch.await();
    }

    // 4.1. Single
    private static void task4_1_Single() {
        getUserById(42)
                .subscribe(
                        user -> System.out.println("(+) Знайдено: " + user),
                        error -> System.out.println("(-) Помилка: " + error.getMessage())
                );

        getUserById(-1)
                .subscribe(
                        user -> System.out.println("(+) Знайдено: " + user),
                        error -> System.out.println("(-) Помилка: " + error.getMessage())
                );
    }

    private static Single<String> getUserById(int id) {
        if (id > 0) {
            return Single.just("Користувач #" + id + ": Іван Франко");
        }

        return Single.error(new IllegalArgumentException("ID не може бути від'ємним або нульовим"));
    }

    // 4.2. Maybe та Completable
    private static void task4_2_MaybeAndCompletable() {
        findInCache("user:1")
                .defaultIfEmpty("Завантажено з БД")
                .subscribe(
                        value -> System.out.println("[КЕШ (+)] Знайдено: " + value),
                        error -> System.out.println("[КЕШ (!)] Помилка: " + error.getMessage())
                );

        findInCache("user:2")
                .defaultIfEmpty("Завантажено з БД")
                .subscribe(
                        value -> System.out.println("[КЕШ (-)] Кеш-міс. Значення: " + value),
                        error -> System.out.println("[КЕШ (!)] Помилка: " + error.getMessage())
                );

        findInCache("user:error")
                .defaultIfEmpty("Завантажено з БД")
                .subscribe(
                        value -> System.out.println("[КЕШ (+)] Знайдено: " + value),
                        error -> System.out.println("[КЕШ (!)] Помилка: " + error.getMessage())
                );

        registerUser(false)
                .subscribe(
                        token -> {
                            System.out.println("[ТОКЕН] Токен: " + token);
                            System.out.println("(+) Реєстрацію завершено успішно!");
                        },
                        error -> System.out.println("(-) Помилка реєстрації: " + error.getMessage())
                );

        registerUser(true)
                .subscribe(
                        token -> {
                            System.out.println("[ТОКЕН] Токен: " + token);
                            System.out.println("(+) Реєстрацію завершено успішно!");
                        },
                        error -> System.out.println("(-) Помилка реєстрації: " + error.getMessage())
                );
    }

    private static Maybe<String> findInCache(String key) {
        return switch (key) {
            case "user:1" -> Maybe.just("{'name':'Леся','age':28}");
            case "user:2" -> Maybe.empty();
            case "user:error" -> Maybe.error(new RuntimeException("Redis недоступний"));
            default -> Maybe.empty();
        };
    }

    private static Single<String> registerUser(boolean databaseError) {
        return validateInput()
                .andThen(saveToDatabase(databaseError))
                .andThen(generateToken());
    }

    private static Completable validateInput() {
        return Completable.fromAction(() -> {
            System.out.println("[ПОШУК] Перевірка даних...");
            System.out.println("(+) Дані валідні");
        });
    }

    private static Completable saveToDatabase(boolean withError) {
        return Completable.fromAction(() -> {
            System.out.println("[DB] Збереження в БД...");

            if (withError) {
                throw new RuntimeException("Помилка при збереженні в БД");
            }

            System.out.println("(+) Збережено");
        });
    }

    private static Single<String> generateToken() {
        return Single.just("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.demo");
    }

    // 5.1. subscribeOn() та observeOn()
    private static void task5_1_Schedulers() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Observable<String> images = Observable.just(
                "photo_1.jpg", "photo_2.jpg", "photo_3.jpg"
        );

        images
                .flatMap(image -> downloadImage(image)
                        .subscribeOn(Schedulers.io())
                        .toObservable())
                .observeOn(Schedulers.computation())
                .map(RxJavaLab7::compressImage)
                .observeOn(Schedulers.trampoline())
                .subscribe(
                        RxJavaLab7::displayImage,
                        error -> System.out.println(error.getMessage()),
                        latch::countDown
                );

        latch.await();
    }

    private static Single<String> downloadImage(String imageName) {
        return Single.fromCallable(() -> {
            Thread.sleep(1_000);
            System.out.println("[" + Thread.currentThread().getName() + "] [ЗАВАНТ] Завантаження: " + imageName);
            return imageName;
        });
    }

    private static String compressImage(String imageName) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("[" + Thread.currentThread().getName() + "] [СТИСК] Стиснення: " + imageName);
        return imageName;
    }

    private static void displayImage(String imageName) {
        System.out.println("[" + Thread.currentThread().getName() + "] [ФОТО] Відображення: " + imageName);
    }

    // 5.2. Послідовна та паралельна обробка
    private static void task5_2_ParallelProcessing() throws InterruptedException {
        List<ServiceCall> services = Arrays.asList(
                new ServiceCall("UserService", 800),
                new ServiceCall("OrderService", 1200),
                new ServiceCall("RecommendationService", 600)
        );

        long sequentialStart = System.currentTimeMillis();

        CountDownLatch sequentialLatch = new CountDownLatch(1);

        Observable.fromIterable(services)
                .concatMap(service -> callService(service).toObservable())
                .subscribe(
                        System.out::println,
                        error -> System.out.println(error.getMessage()),
                        sequentialLatch::countDown
                );

        sequentialLatch.await();

        long sequentialTime = System.currentTimeMillis() - sequentialStart;
        System.out.println("Загальний час послідовно: ~" + sequentialTime + " мс");

        long parallelStart = System.currentTimeMillis();

        CountDownLatch parallelLatch = new CountDownLatch(1);

        Observable.fromIterable(services)
                .flatMap(service -> callService(service)
                        .subscribeOn(Schedulers.io())
                        .toObservable())
                .subscribe(
                        System.out::println,
                        error -> System.out.println(error.getMessage()),
                        parallelLatch::countDown
                );

        parallelLatch.await();

        long parallelTime = System.currentTimeMillis() - parallelStart;
        System.out.println("Загальний час паралельно: ~" + parallelTime + " мс");
    }

    private static Single<String> callService(ServiceCall service) {
        return Single.fromCallable(() -> {
            Thread.sleep(service.delayMs());
            return "[" + Thread.currentThread().getName() + "] (+) "
                    + service.serviceName()
                    + " відповів за "
                    + service.delayMs()
                    + " мс";
        });
    }

    // 6.1. debounce()
    private static void task6_1_Debounce() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Observable<String> keystrokes = Observable.create(emitter -> {
            String[] inputs = {"К", "Ки", "Киї", "Київ", "Київ ", "Київ К", "Київ Ки"};
            long[] delays = {50, 80, 120, 100, 400, 60, 100};

            for (int i = 0; i < inputs.length; i++) {
                Thread.sleep(delays[i]);
                emitter.onNext(inputs[i]);
            }

            emitter.onComplete();
        });

        keystrokes
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribe(
                        query -> System.out.println("[ПОШУК] Запит до API: \"" + query + "\""),
                        error -> System.out.println(error.getMessage()),
                        latch::countDown
                );

        latch.await();
    }

    // 6.2. buffer() та Flowable Backpressure
    private static void task6_2_BufferAndBackpressure() throws InterruptedException {
        Observable<String> events = Observable.fromArray(
                "LOGIN:user1", "CLICK:btn_buy", "VIEW:product_42",
                "LOGIN:user2", "LOGOUT:user1", "CLICK:btn_cart",
                "VIEW:product_7", "LOGIN:user3", "CLICK:btn_pay",
                "LOGOUT:user2", "LOGIN:user4", "VIEW:product_1"
        );

        AtomicInteger batchNumber = new AtomicInteger(1);
        AtomicInteger savedCount = new AtomicInteger(0);

        events
                .buffer(5)
                .subscribe(batch -> {
                    savedCount.addAndGet(batch.size());
                    System.out.println("[DB] Batch INSERT #" + batchNumber.getAndIncrement() + ": " + batch);
                });

        System.out.println("(+) Збережено подій: " + savedCount.get());

        CountDownLatch latch = new CountDownLatch(1);

        AtomicInteger processed = new AtomicInteger(0);
        AtomicInteger dropped = new AtomicInteger(0);

        Flowable<Integer> fastProducer = Flowable.create(emitter -> {
            for (int i = 1; i <= 1000; i++) {
                emitter.onNext(i);
            }

            emitter.onComplete();
        }, BackpressureStrategy.MISSING);

        fastProducer
                .onBackpressureDrop(item -> dropped.incrementAndGet())
                .observeOn(Schedulers.io(), false, 128)
                .subscribe(
                        item -> {
                            Thread.sleep(10);
                            processed.incrementAndGet();
                        },
                        error -> System.out.println(error.getMessage()),
                        latch::countDown
                );

        latch.await();

        System.out.println("[ЗВІТ] Оброблено: " + processed.get());
        System.out.println("[ЗВІТ] Відкинуто: " + dropped.get());
        System.out.println("(!) Стратегія DROP: частину елементів втрачено");
    }

    // 7.1. onErrorReturn та onErrorResumeNext
    private static void task7_1_ErrorHandling() {
        createCurrencyService()
                .onErrorReturn(error -> "Використовується кешований курс: USD -> UAH: 41.00")
                .subscribe(System.out::println);

        createCurrencyService()
                .onErrorResumeNext((Throwable error) -> Observable.fromArray(
                        "JPY -> UAH: 0.27",
                        "PLN -> UAH: 10.30"
                ))
                .subscribe(System.out::println);
    }

    private static Observable<String> createCurrencyService() {
        return Observable.create(emitter -> {
            emitter.onNext("USD -> UAH: 41.50");
            emitter.onNext("EUR -> UAH: 44.20");
            emitter.onError(new RuntimeException("Сервіс тимчасово недоступний"));
        });
    }

    // 7.2. retryWhen() з exponential backoff
    private static void task7_2_RetryWithBackoff() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        AtomicInteger attemptCount = new AtomicInteger(0);

        Observable<String> unstableApiCall = Observable.create(emitter -> {
            int attempt = attemptCount.incrementAndGet();

            System.out.println("[ПОВТОР] Спроба #" + attempt);

            if (attempt < 4) {
                emitter.onError(new IOException("Connection timeout"));
            } else {
                emitter.onNext("(+) Відповідь API: {status: 'ok', data: [...]}");
                emitter.onComplete();
            }
        });

        unstableApiCall
                .retryWhen(errors -> errors
                        .zipWith(
                                Observable.range(1, 3),
                                (Throwable error, Integer retryCount) -> retryCount
                        )
                        .flatMap((Integer retryCount) -> {
                            long delay = (long) Math.pow(2, retryCount - 1);
                            System.out.println("Очікуємо " + delay + " сек перед повтором...");
                            return Observable.timer(delay, TimeUnit.SECONDS);
                        }))
                .subscribe(
                        System.out::println,
                        error -> {
                            System.out.println("(-) Помилка після всіх спроб: " + error.getMessage());
                            latch.countDown();
                        },
                        latch::countDown
                );

        latch.await();
    }
}