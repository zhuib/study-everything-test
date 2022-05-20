package top.iaminlearn.stream.optional;

import top.iaminlearn.stream.stream.Author;

import java.util.Optional;

/**
 * Date: 2022/5/17 1:11
 */
public class OptionalDemo {

    public static void main(String[] args) {
        Optional<Author> author = getAuthorOptional();
        author.ifPresent(author1 -> System.out.println(author1.getName()));

    }

    private static Author getAuthor() {
        Author author = new Author(1L, "蒙多", 33, "一个菜刀中明悟哲理的祖安仁", null);
        return author;
    }
    private static Optional<Author> getAuthorOptional() {
        Author author = new Author(1L, "蒙多", 33, "一个菜刀中明悟哲理的祖安仁", null);
        return Optional.ofNullable(author);
    }
}
