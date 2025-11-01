import java.lang.reflect.Array;
import java.util.*;

public class Main {
    private static void testSet() {
        MyHashSet<String> mySet = new MyHashSet<String>();
        for (int i = 97; i < 108; i++) {
            mySet.add("" + (char)i);
        }
        System.out.printf("mySet size: %d\n", mySet.size());
        System.out.printf("Remove \"b\": %b\n", mySet.remove("b"));
        System.out.printf("Remove \"z\": %b\n", mySet.remove("z"));
        System.out.printf("Add \"y\": %b\n", mySet.add("y"));
        System.out.printf("mySet size: %d\n", mySet.size());
    }

    private static void testList() {
        MyArrayList<String> myList = new MyArrayList<String>();
        System.out.printf("Add \"123\": %b\n", myList.add("123"));
        System.out.printf("Add \"1234\": %b\n", myList.add("1234"));
        System.out.printf("Get 1: %s\n", myList.get(1));
        System.out.printf("Remove 1: %s\n", myList.remove(1));
        System.out.printf("AddAll: %b\n", myList.addAll(Arrays.asList(new String[] {"234", "452", "762"})));
        System.out.printf("myList size: %d\n", myList.size());
    }

    private static void testStudent() {
        List<Book> books = new ArrayList<Book>();
        books.addAll(Arrays.asList(
                new Book("Преступление и наказание", 672, 1866),
                new Book("Война и мир", 1300, 1869),
                new Book("Анна Каренина", 864, 1877),
                new Book("Мастер и Маргарита", 480, 1967),
                new Book("Авиатор", 320, 2016),
                new Book("Отцы и дети", 352, 1862),
                new Book("Идиот", 608, 1869),
                new Book("Лавр", 512, 2012),
                new Book("Тихий Дон", 1200, 1940),
                new Book("Доктор Живаго", 592, 1957),
                new Book("Мёртвые души", 416, 1842),
                new Book("Герой нашего времени", 224, 1840),
                new Book("Сердце Пармы", 704, 2001),
                new Book("Собачье сердце", 160, 1925),
                new Book("Золотой телёнок", 384, 1931),
                new Book("Двенадцать стульев", 320, 1928),
                new Book("Петербург", 400, 1916),
                new Book("Чевенгур", 512, 1928),
                new Book("Зулейха открывает глаза", 480, 2015),
                new Book("Один день Ивана Денисовича", 144, 1962)));
        List<Student> students = new ArrayList<Student>();
        students.addAll(Arrays.asList(
                new Student("Иван Иванов", (short) 4, books.subList(0, 5)),
                new Student("Григорий Пушнов", (short) 2, books.subList(5, 10)),
                new Student("Лидия Волкова", (short) 3, books.subList(10, 15)),
                new Student("Андрей Грунин", (short) 1, books.subList(15, 20))
        ));

        students.stream()
                .peek(System.out::println)
                .map(st -> st.books)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(b -> b.pages))
                .distinct()
                .filter(b -> b.year > 2000)
                .limit(3)
                .findAny()
                .ifPresentOrElse(
                        b -> System.out.println(b.year),
                        () -> System.out.println("Книга отсутствует")
                );
    }

    public static void main(String[] args) {
        System.out.println("\nПроверка HashSet");
        testSet();
        System.out.println("\nПроверка ArrayList");
        testList();
        System.out.println("\nПроверка Stream");
        testStudent();
    }
}