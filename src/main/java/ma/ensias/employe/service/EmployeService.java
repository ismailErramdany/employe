package ma.ensias.employe.service;

import ma.ensias.employe.entite.Employe;
import ma.ensias.employe.reponse.EmployeReponse;
import ma.ensias.employe.repository.EmployeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class EmployeService {

    @Autowired
    private EmployeRepo employeRepo;

    // added
    @PersistenceContext
    private EntityManager entityManager;
    // added
    public List<?> searchByName(String name) {
        String sql = "SELECT * FROM employe WHERE name = '" + name + "'";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    @Autowired
    private ModelMapper mapper;

    public EmployeReponse getEmployeById(int id) {
        Employe employe = employeRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employé introuvable"));
        return mapper.map(employe, EmployeReponse.class);
    }
}