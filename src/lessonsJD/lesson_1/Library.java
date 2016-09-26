package lessonsJD.lesson_1;

public class Library {

    private static Book[] library = new Book[100_000];
    private static Genre[] genres = {Genre.FANTASY, Genre.FOOD, Genre.COMPUTERS, Genre.FICTION, Genre.HISTORY};
    private static String[] authors = new String[5];
    private static Book[][][] books = new Book[5][][];

    private static String[] numbersOfGenresSearch = new String[genres.length];

    private static int FAIL = -1;

    public static void main(String[] args) {

        initLibrary();

        System.out.println(System.currentTimeMillis());
        findStraight("The Magicians");
        System.out.println(System.currentTimeMillis());
        sort();
        System.out.println(System.currentTimeMillis());
        findBookByAuthorAndName("Lev Grossman", "The Magicians").demonstration();
        System.out.println(System.currentTimeMillis());

        printResult(findFiveBooksByGenre(Genre.COMPUTERS));
        printResult(findFiveBooksByGenre(Genre.COMPUTERS));

    }

    private static void initLibrary() {
        library[101] = new Book("A Song Of Ice And Fire", "George R.R. Martin", Genre.FANTASY);
        library[150] = new Book("The Name Of The Wind", "Patrick Rothfuss", Genre.FANTASY);
        library[200] = new Book("The Blade Itself", "Joe Abercrombie", Genre.FANTASY);
        library[201] = new Book("And Fire", "George R.R. Martin", Genre.FANTASY);
        library[250] = new Book("Lord Of The Rings", "J.R.R. Tolkien", Genre.FANTASY);
//        library[300] = new Book("The Magicians", "Lev Grossman", Genre.FANTASY);
        library[350] = new Book("Discworld", "Terry Pratchett", Genre.FANTASY);
        library[351] = new Book("A Song", "George R.R. Martin", Genre.FANTASY);
        library[352] = new Book("A Song", "George R.R. Martin", Genre.COMPUTERS);
        library[353] = new Book("Lord", "J.R.R. Tolkien", Genre.COMPUTERS);

        for (int i = 400; i < 20000; i++) {
            library[i] = new Book("Discworld", "Terry Pratchett", Genre.COMPUTERS);
        }
        for (int i = 20000; i < 40000; i++) {
            library[i] = new Book("The Name Of The Wind", "Patrick Rothfuss", Genre.FOOD);
        }
        for (int i = 40000; i < 70000; i++) {
            library[i] = new Book("The Blade Itself", "Joe Abercrombie", Genre.FICTION);
        }
        for (int i = 70000; i < 100000; i++) {
            library[i] = new Book("The Magicians", "Lev Grossman", Genre.HISTORY);
        }
    }

    private static void printResult(Book[] result) {
        for (Book book : result) {
            if (book != null) {
                book.demonstration();
            }
        }
    }

    public static Book[] findFiveBooksByGenre(Genre genre) {
        int iGenre = find(genres, genre);
        String last = numbersOfGenresSearch[iGenre];
        int iAuthor = 0;
        int iBook = 0;
        if (last != null && !last.trim().isEmpty()) {
            iAuthor = Integer.parseInt(last.split("_")[0]);
            iBook = Integer.parseInt(last.split("_")[1]);
        }

        Book[] result = new Book[5];
        for (int i = 0; i < 5; i++, iBook++) {
            if (iAuthor >= books[iGenre].length) {
                numbersOfGenresSearch[iGenre] = iAuthor + "_" + iBook;
                return result;
            }

            while (books[iGenre][iAuthor] == null || iBook >= books[iGenre][iAuthor].length) {
                iAuthor++;
                iBook = 0;
                if (iAuthor >= books[iGenre].length) {
                    numbersOfGenresSearch[iGenre] = iAuthor + "_" + iBook;
                    return result;
                }
            }
            result[i] = books[iGenre][iAuthor][iBook];
        }
        numbersOfGenresSearch[iGenre] = iAuthor + "_" + iBook;
        return result;
    }

    public static Book findBookByAuthorAndName(String author, String name) {
        int iAuthor = find(authors, author);
        int iBook = FAIL;
        int i = 0;
        if (iAuthor != FAIL) {
            for (; i < books.length && iBook == FAIL; i++) {
                if (books[i] != null && books[i].length > iAuthor) {
                    iBook = find(books[i][iAuthor], name);
                }
            }

            if (iBook != FAIL) {
                return books[i - 1][iAuthor][iBook];
            }
        }

        return null;

    }

//    private static void printBooks() {
//        for (int iGenre = 0; iGenre < books.length; iGenre++) {
//            System.out.println(genres[iGenre]);
//            if (books[iGenre] != null) {
//                for (int iAuthor = 0; iAuthor < books[iGenre].length; iAuthor++) {
//                    if (books[iGenre][iAuthor] != null) {
//                        System.out.println(authors[iAuthor]);
//                        for (Book book : books[iGenre][iAuthor]) {
//                            System.out.println(book.name);
//                        }
//                    }
//
//                }
//            }
//
//        }
//    }

    public static void sort() {
        for (Book book : library) {
            if (book != null) {
                int iGenre = find(genres, book.getGenre());
                int iAuthor = find(authors, book.getAuthor());
                if (iAuthor == FAIL) {
                    iAuthor = findEmpty(authors);
                    if (iAuthor == FAIL) {
                        iAuthor = extendAuthors();
                    }
                    authors[iAuthor] = book.getAuthor();
                }

                if (books[iGenre] == null) {
                    books[iGenre] = new Book[1][];
                }

                while (books[iGenre].length <= iAuthor) {
                    extendBooksAuthors(iGenre);
                }
                int iBook = findEmpty(books[iGenre][iAuthor]);
                if (iBook == FAIL) {
                    iBook = extendBooks(iGenre, iAuthor);
                }
                books[iGenre][iAuthor][iBook] = book;
            }

        }
    }

    private static int extendAuthors() {
        int length = authors.length;
        String[] newArray = new String[length + length / 4 + 1];
        System.arraycopy(authors, 0, newArray, 0, length);
        authors = newArray;
        return length;
    }

    private static int extendBooksAuthors(int iGenre) {

        Book[][] newArray;
        int length = 0;
        if (books[iGenre] != null) {
            length = books[iGenre].length;
            newArray = new Book[length + length / 4 + 1][];
            System.arraycopy(books[iGenre], 0, newArray, 0, length);
            books[iGenre] = newArray;
        } else {
            books[iGenre] = new Book[1][];
        }
        return length;
    }

    private static int extendBooks(int iGenre, int iAuthor) {

        Book[] newArray;
        int length = 0;
        if (books[iGenre][iAuthor] != null) {
            length = books[iGenre][iAuthor].length;
            newArray = new Book[length + length / 4 + 1];
            System.arraycopy(books[iGenre][iAuthor], 0, newArray, 0, length);
            books[iGenre][iAuthor] = newArray;
        } else {
            books[iGenre][iAuthor] = new Book[1];
        }
        return length;
    }

    private static int findEmpty(String[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null || array[i].trim().isEmpty()) {
                    return i;
                }
            }
        }
        return FAIL;
    }

    private static int findEmpty(Book[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        return FAIL;
    }

    private static int find(Book[] array, String query) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null && array[i].getName().equals(query)) {
                    return i;
                }
            }
        }
        return FAIL;
    }

    private static int find(String[] array, String query) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null && array[i].equals(query)) {
                    return i;
                }
            }
        }
        return FAIL;
    }

    private static int find(Genre[] array, Genre query) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null && array[i].equals(query)) {
                    return i;
                }
            }
        }
        return FAIL;
    }

    private static int findStraight(String query) {
        for (int i = 0; i < library.length; i++) {
            if (library[i] != null && library[i].getName().equals(query)) {
                return i;
            }
        }
        return FAIL;
    }
}
