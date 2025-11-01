import java.util.List;

public class Student {
    public String name;
    public short course;
    public List<Book> books;

    public Student(String n, short c, List<Book> b) {
        this.name = n;
        this.course = c;
        this.books = b;
    }

    @Override
    public String toString() {
        return String.format("Студент %s %d-го курса обучения", this.name, this.course);
    }
}
