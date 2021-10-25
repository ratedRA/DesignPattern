package patterns.creational.impl;

import patterns.creational.CreationalPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The “constructor pattern”, as the name defines, is a class-based pattern that uses the constructors present
 * in the class to create specific types of objects.
 *
 * #USAGES -
 * You can use it when you want to create multiple instances of the same template, since the instances can share methods but can still be different.
 * Some examples of where it can be useful include:
 *
 * libraries
 *
 * plugins
 */
public class ConstructorPattern implements CreationalPattern {

    public static void main(String[] args) {
        Person person1 = new Person("John", Gender.MALE, 23);
        Person person2 = new Person("Johny", Gender.MALE, 24);
        Person person3 = new Person("Tess", Gender.FEMALE, 24);
        System.out.println(new GenderGroup().toString());
    }

    private static class Person{
        private String name;
        private Gender gender;
        private int age;

        public Person(String name, Gender gender, int age) {
            this.name = name;
            this.gender = gender;
            this.age = age;
            GenderGroup.addPerson(gender, this);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    ", age=" + age +
                    '}';
        }
    }

    private enum Gender{
        MALE,
        FEMALE;
    }

    private static class GenderGroup{

        private static Map<Gender, List<Person>> genderByPersonMap = new HashMap<>();

        private static void addPerson(Gender gender, Person person){
            if(genderByPersonMap.containsKey(gender)){
                genderByPersonMap.get(gender).add(person);
            } else{
                List<Person> personList = new ArrayList<>();
                personList.add(person);
                genderByPersonMap.put(gender, personList);
            }
        }

        @Override
        public String toString() {
            Map<Gender, String> genderByName = genderByPersonMap.entrySet().stream().collect(Collectors.toMap(m -> m.getKey(), m -> {
                String valueJoined = null;
                List<String> names = m.getValue().stream().map(p -> p.name).collect(Collectors.toList());
                valueJoined = String.join(",", names);
                return valueJoined;
            }));
            String strValue = genderByName.entrySet().stream().map(m -> m.getKey() + "=" + m.getValue()).collect(Collectors.joining("&"));
            return strValue;
        }
    }
}
