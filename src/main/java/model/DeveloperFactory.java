package model;

import enums.Experience;
import tax.Taxable;

public class DeveloperFactory {
    public static Developer createdDeveloper (Developer developer, Taxable taxable){
        Developer createdDeveloper = null;
        if(developer.getExperience().equals(Experience.JUNIOR)){
           createdDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(), developer.getSalary()*(1- taxable.getSimpleTaxRate()));

        }else if(developer.getExperience().equals(Experience.MID)){
            createdDeveloper = new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary()*(1- taxable.getMiddleTaxRate()));

        }else if(developer.getExperience()==Experience.SENIOR){
            createdDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(), developer.getSalary()*(1- taxable.getUpperTaxRate()));

        }
        return createdDeveloper;
    }
}
