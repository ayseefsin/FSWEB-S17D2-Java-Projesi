package model;

import enums.Experience;

public class SeniorDeveloper extends Developer{
    public SeniorDeveloper(int id, String name, double salary) {
        super(id, name, salary, Experience.SENIOR);
    }
}
