package rto;

import enums.Experience;
//record sınıfı getter setter ları kendi oluşturuyor.
public record DeveloperResponse(String message, Integer id, String name, Double salary, Experience experience) {

}
