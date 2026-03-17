public class Course {
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // TODO: Task 4 — Обязательно переопредели (иначе HashMap не будет работать!)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // сравниваем ссылки
        if (o == null || getClass() != o.getClass()) return false; // проверяем тип
        Course course = (Course) o; // приводим к Course
        return name.equals(course.name); // сравниваем имена
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Course: " + name;
    }
}
