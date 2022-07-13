package other.pojo;

import java.util.Objects;

@SuppressWarnings("all")
public class Teacher {

    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != getClass()) return false;
        Teacher another = (Teacher) o;
        return Objects.equals(this.name, another.name);
    }

    @Override
    public String toString() {
        return String.format("Teacher(name = %s)", name);
    }

}
