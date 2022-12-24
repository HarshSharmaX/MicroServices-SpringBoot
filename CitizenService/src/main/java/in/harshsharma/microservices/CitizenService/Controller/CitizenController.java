package in.harshsharma.microservices.CitizenService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.harshsharma.microservices.CitizenService.Entity.Citizen;
import in.harshsharma.microservices.CitizenService.Repositories.CitizenRepo;

@RestController
@RequestMapping("/citizen")
public class CitizenController {

	@Autowired
	private CitizenRepo cRepo;
	
	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("API Called",HttpStatus.OK);
	}
	
	@RequestMapping(path = "/id/{id}")
	public ResponseEntity<List<Citizen>> getById(@PathVariable Integer id) {
		List<Citizen> listCitizen = cRepo.findByVaccinationCenterId(id);
		return new ResponseEntity<>(listCitizen, HttpStatus.OK);
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen) {
		Citizen citizen = cRepo.save(newCitizen);
		return new ResponseEntity<>(citizen, HttpStatus.OK);
	}
}
