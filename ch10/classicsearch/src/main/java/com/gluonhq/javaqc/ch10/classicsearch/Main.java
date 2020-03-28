package com.gluonhq.javaqc.ch10.classicsearch;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main (String[] args) {
        System.err.println("Hello, classical search");
        Main main = new Main();
        main.complexSearch();
    }
    
    void complexSearch() {
        List<Person> persons = prepareDatabase();
        Person target = findPersonByAgeAndCountry(persons, 29, "Mexico");
        System.out.println("Result of complex search = "+target.getName());
    }
    
    Person findPersonByAgeAndCountry(List<Person> persons, int age, String country) {
        return persons.stream()
                .filter(p -> {return (p.getAge() == age && p.getCountry().equals(country));})
                .findFirst().get();
    }
    
    List<Person> prepareDatabase() {
        List<Person> persons = new LinkedList<>();
        persons.add(new Person("Alice", 42, "Nigeria"));
        persons.add(new Person("Bob", 36, "Australia"));
        persons.add(new Person("Eve", 85, "USA"));
        persons.add(new Person("Niels", 18, "Greece"));
        persons.add(new Person("Albert", 29, "Mexico"));
        persons.add(new Person("Roger", 29, "Belgium"));
        persons.add(new Person("Marie", 15, "Russia"));
        persons.add(new Person("Janice", 52, "China"));
        return persons;
    }

}

