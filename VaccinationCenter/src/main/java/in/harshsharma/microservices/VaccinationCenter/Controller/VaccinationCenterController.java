package in.harshsharma.microservices.VaccinationCenter.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import in.harshsharma.microservices.VaccinationCenter.Entity.VaccinationCenter;
import in.harshsharma.microservices.VaccinationCenter.Model.Citizen;
import in.harshsharma.microservices.VaccinationCenter.Model.RequiredResponse;
import in.harshsharma.microservices.VaccinationCenter.Repository.VaccinationCenterRepo;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

	@Autowired
	private VaccinationCenterRepo vRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	@PostMapping("/add")
	public ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter vCenter ){
		VaccinationCenter vaccinationCenter = vRepo.save(vCenter);
		return new ResponseEntity<VaccinationCenter>(vaccinationCenter , HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	@HystrixCommand(fallbackMethod = "handleCitizenDownTime")
	public ResponseEntity<RequiredResponse> getAllDataBasedOnCenterId(@PathVariable Integer id){
		RequiredResponse requiredResponse = new RequiredResponse();
		VaccinationCenter center = vRepo.findById(id).get();
		requiredResponse.setCenter(center);
		
		List<Citizen> listOfCitizen = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/"+id, List.class);
		requiredResponse.setCitizens(listOfCitizen);
		
//		List<Citizen> listOfCitizen  = restTemplate.getForObject("http://localhost:8081/citizen/id/"+id, List.class);
//		requiredResponse.setCitizens(listOfCitizen);
		
		return new ResponseEntity<RequiredResponse>(requiredResponse,HttpStatus.OK);
	}
	
	public ResponseEntity<RequiredResponse> handleCitizenDownTime(@PathVariable Integer id){
		RequiredResponse requiredResponse = new RequiredResponse();
		VaccinationCenter center = vRepo.findById(id).get();
		requiredResponse.setCenter(center);
		return new ResponseEntity<RequiredResponse>(requiredResponse,HttpStatus.OK);
	}
	
	
}
