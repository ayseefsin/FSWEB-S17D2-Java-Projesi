package rest;

import enums.Experience;
import jakarta.annotation.PostConstruct;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rto.DeveloperResponse;
import tax.DeveloperTax;
import tax.Taxable;

import java.util.*;

@RestController
@RequestMapping ("/developers")
public class DeveloperController {
    private Map<Integer, Developer> developers;
    private Taxable taxable;
    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable=taxable;
    }
    @PostConstruct
    public void init(){
        developers=new HashMap<>();
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse save(@RequestBody Developer developer){
      Developer createdDeveloper= DeveloperFactory.createdDeveloper(developer,taxable);
      if(Objects.nonNull(createdDeveloper)){
          developers.put(developer.getId(),developer);
      }
      return new DeveloperResponse("created successfully",developer.getId(),developer.getName(),developer.getSalary(),developer.getExperience());
    }
    @GetMapping("/")
    public List<Developer> developersList(){
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public DeveloperResponse getDeveloper(@PathVariable Integer id){
        Developer developer =developers.get(id);
        if(Objects.isNull(developer)){
            return  new DeveloperResponse("developer cannot be found by given id" +id ,id,null,null,null);
        }
        return new DeveloperResponse("got by id successfully",developer.getId(),developer.getName(),developer.getSalary(),developer.getExperience());
    }

    @PutMapping("/{id}")
    public DeveloperResponse update(@PathVariable Integer id, @RequestBody Developer developer){
        if(Objects.isNull(developer)){
            return  new DeveloperResponse("new developer is not valid" +id ,null,null,null,null);
        }
        Developer found= developers.get(id);
        if(Objects.isNull(found)){
            return  new DeveloperResponse("developer cannot found" +id ,null,null,null,null);
        }
        Developer updatedDeveloper = DeveloperFactory.createdDeveloper(developer,taxable);
        this.developers.put(id,updatedDeveloper);
        return new DeveloperResponse("updated successfully",id,updatedDeveloper.getName(),updatedDeveloper.getSalary(),updatedDeveloper.getExperience());
    }
    @DeleteMapping("/{id}")
    public DeveloperResponse delete(@PathVariable Integer id){
        Developer developer =developers.get(id);
        if(Objects.isNull(developer)){
            return  new DeveloperResponse("developer cannot be found by given id" +id ,id,null,null,null);
        }
        developers.remove(id);
        return new DeveloperResponse("delete by id successfully",developer.getId(),developer.getName(),developer.getSalary(),developer.getExperience());
    }

}
