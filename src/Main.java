import java.text.CollationKey;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();

        // ====================== TASK 1 ======================
        // TODO: Добавь минимум 5 студентов (ключ = ID)
        // Сделай минимум два студента с одинаковым GPA (для Task 3)
        students.put("12345", new Student("meka", 4.0, 18));
        students.put("13425", new Student("beka", 4.0, 17));
        students.put("14523", new Student("deka", 3.5, 18));
        students.put("23145", new Student("zeko", 3.8, 17));
        students.put("45321", new Student("neko", 2.9, 18));

        // TODO: Напечатай всех студентов (ID + объект)
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }

        // TODO: Найди студента по ID и выведи его
        Student found = findById(students, "12345");

        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Student not found");
        }

        // TODO: Удали одного студента по ID
        deleteById(students,"12345");

        // TODO: Обнови GPA у одного студента
        setGpa(students, "14523", 3.8);

        // ====================== SORTING (IMPORTANT) ======================
        // TODO: Создай ArrayList из всех студентов (students.values())
        List<Student> lstStudents = new ArrayList<>();
        for (Map.Entry<String, Student> entry: students.entrySet()) {
            lstStudents.add(entry.getValue());
        }

        // TODO 6a: Отсортируй по GPA (natural ordering) и выведи
        Collections.sort(lstStudents);
        for (Student s : lstStudents) {
            System.out.println(s.getName() + " → GPA: " + s.getGpa());
        }

        // TODO 6b: Отсортируй по имени (Comparator) и выведи
        Comparator<Student> nameComparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Collections.sort(lstStudents, nameComparator);
        for (Student s : lstStudents) {
            System.out.println(s.getName() + " → GPA: " + s.getGpa());
        }

        // ====================== TASK 2 ======================
        System.out.println("\n=== Task 2: Top 3 by GPA ===");
        // TODO: Создай новый список, отсортируй по GPA по убыванию, выведи первые 3
        List<Student> lstGpa = new ArrayList<>(students.values());
        Comparator<Student> gpaComparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o2.getGpa(), o1.getGpa()); // убывание
            }
        };
        Collections.sort(lstGpa, gpaComparator);
        for (int i = 0; i < 3 && i < lstGpa.size(); i++) {
            System.out.println(lstGpa.get(i));
        }

        // ====================== TASK 3 ======================
        System.out.println("\n=== Task 3: Students with same GPA ===");
        // TODO: Сгруппируй студентов по GPA и выведи только те, где больше 1 студента
        HashMap<Double, List<Student>> gpaGroups = new HashMap<>();

        for (Student s : students.values()) {
            double gpa = s.getGpa();
            if (!gpaGroups.containsKey(gpa)) {
                gpaGroups.put(gpa, new ArrayList<>());
            }
            gpaGroups.get(gpa).add(s);
        }

        for (Map.Entry<Double, List<Student>> entry : gpaGroups.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("GPA: " + entry.getKey());
                for (Student s : entry.getValue()) {
                    System.out.println("  " + s);
                }
            }
        }

        // ====================== TASK 4 ======================
        System.out.println("\n=== Task 4: Courses ===");
        HashMap<Course, List<Student>> courseMap = new HashMap<>();
        // TODO: Создай 2–3 курса, добавь студентов, выведи всё
        Course math = new Course("math");
        Course cs = new Course("cs");
        Course java = new Course("java");
        courseMap.put(math, lstStudents); courseMap.put(cs, lstStudents); courseMap.put(java, lstStudents);
        for (Map.Entry<Course, List<Student>> entry : courseMap.entrySet()) {
            System.out.println("Course " + entry.getKey() + ": ");
            for (Student s : entry.getValue()) {
                System.out.println(s);
            }
        }

        // ====================== TASK 5 ======================
        System.out.println("\n=== Task 5: GPA desc + Name ===");
        // TODO: Создай Comparator (GPA убывание → если равно, то имя возрастание) и отсортируй
        Comparator<Student> multipleSorting = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int result = Double.compare(o2.getGpa(), o1.getGpa()); // GPA по убыванию
                if (result != 0) {
                    return result;
                }else {
                    return o1.getName().compareTo(o2.getName()); // Name по возрастанию
                }
            }
        };
        Collections.sort(lstStudents, multipleSorting);
        for (Student s : lstStudents) {
            System.out.println(s);
        }
    }

    public static Student findById(HashMap<String, Student> students, String id) {
        return students.get(id);
    }

    public static void deleteById(HashMap<String, Student> students, String id) {
        students.remove(id);
    }

    public static void setGpa(HashMap<String, Student> students, String id, double newGpa) {
        Student student = findById(students, id);
        if (student != null) {
            student.setGpa(newGpa);
        }
    }
}



