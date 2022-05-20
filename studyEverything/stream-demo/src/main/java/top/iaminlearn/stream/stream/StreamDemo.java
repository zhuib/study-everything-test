package top.iaminlearn.stream.stream;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date: 2022/5/16 11:23
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Author> authors = getAuthors();
//        System.out.println(authors);
            test010();
//            test09();
//            test08();
//            test07();
//            test06();
//            test05();
//            test04();
//        test03();
//            test02();
//            test01();
//        test(authors);
    }

    private static void test010() {
        Integer min = getAuthors().stream()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return result > element ? element : result;
                    }
                });
        Optional<Integer> minOptional = getAuthors().stream()
                .map(author -> author.getAge())
                .reduce(new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return result > element ? element : result;
                    }
                });
        System.out.println(min);
        minOptional.ifPresent(System.out::println);
    }

    private static void test09() {
        Integer max = getAuthors().stream()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return result < element ? element : result;
                    }
                });
        System.out.println(max);
    }

    private static void test08() {
        Integer sum = getAuthors().stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return result + element;
                    }
                });
        System.out.println(sum);
    }

    private static void test07() {
        Optional<Author> optional = getAuthors().stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        optional.ifPresent(author -> System.out.println(author.getName()));
    }

    private static void test06() {
        Map<String, List<Book>> map = getAuthors().stream()
                .distinct()
                .collect(Collectors.toMap(new Function<Author, String>() {
                    @Override
                    public String apply(Author author) {
                        return author.getName();
                    }
                }, new Function<Author, List<Book>>() {
                    @Override
                    public List<Book> apply(Author author) {
                        return author.getBooks();
                    }
                }));
        System.out.println(map);
    }

    private static void test05() {
        Set<Book> books = getAuthors().stream()
                .flatMap(author -> author.getBooks().stream())
                .collect(Collectors.toSet());
        System.out.println(books);
    }

    private static void test04() {
        List<String> collect = getAuthors().stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void test03() {
        Optional<Integer> max = getAuthors().stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((o1, o2) -> o1 - o2);
        Optional<Integer> min = getAuthors().stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((o1, o2) -> o1 - o2);
        System.out.println(max.get());
        System.out.println(min.get());


    }

    private static void test02() {
        getAuthors().stream()
                .parallel()
                .peek(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getName());
                    }
                })
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split("，")))
                .distinct()
                .forEach(category -> System.out.println(category));
    }

    private static void test01() {
       getAuthors().stream()
                .flatMap(new Function<Author, Stream<Book>>() {
                    @Override
                    public Stream<Book> apply(Author author) {
                        return author.getBooks().stream();
                    }
                })
                .distinct()
                .forEach(new Consumer<Book>() {
                    @Override
                    public void accept(Book book) {
                        System.out.println(book);
                    }
                });
    }

    private static void test(List<Author> authors) {
        authors.stream()
                .distinct()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static List<Author> getAuthors() {
        // 数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个菜刀中明悟哲理的祖安仁", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

        // 书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L,"刀的两侧是光明与黑暗","哲学，爱情",88,"用一把刀划分了爱情"));
        books1.add(new Book(2L,"一个不能死在同一把刀下","个人成长，爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(4L,"吹或不吹","爱情，个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }
}
