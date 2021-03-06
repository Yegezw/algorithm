package other.pojo;

import java.util.Objects;

@SuppressWarnings("all")
public class Student implements Comparable<Student> {

    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != getClass()) return false;
        Student another = (Student) o;
        return Objects.equals(this.name, another.name);
    }

    @Override
    public int compareTo(Student another) {
        return this.score - another.score;
    }

    @Override
    public String toString() {
        return String.format("Student(name = %s, score = %d)", name, score);
    }

}
