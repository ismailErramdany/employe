package ma.ensias.employe.controller;

import ma.ensias.employe.reponse.EmployeReponse;
import ma.ensias.employe.service.EmployeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @GetMapping("/employes/{id}")
    public ResponseEntity<EmployeReponse> getEmployeDetails(@PathVariable("id") int id) {
        EmployeReponse employe = employeService.getEmployeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employe);
    }
    // added
    @GetMapping("/employes/search")
    public List<?> search(@RequestParam("name") String name) {
        return employeService.searchByName(name);
    }
}