package other.pojo;

import java.util.Objects;

@SuppressWarnings("all")
public class Teacher
{

    private String name;

    public Teacher(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Teacher another = (Teacher) o;
        return Objects.equals(name.toLowerCase(), another.name.toLowerCase());
    }

    @Override
    public String toString()
    {
        return String.format("Teacher(name = %s)", name);
    }
}
