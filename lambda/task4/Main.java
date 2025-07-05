import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long underageCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + underageCount);

        List<String> conscripts = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getSurname)
                .collect(Collectors.toList());
        System.out.println("Количество призывников: " + conscripts.size());

        List<Person> workablePeople = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> {
                    if (person.getSex() == Sex.WOMAN) {
                        return person.getAge() >= 18 && person.getAge() <= 60;
                    } else {
                        return person.getAge() >= 18 && person.getAge() <= 65;
                    }
                })
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.toList());
        System.out.println("Количество работоспособных с высшим образованием: " + workablePeople.size());
    }
}