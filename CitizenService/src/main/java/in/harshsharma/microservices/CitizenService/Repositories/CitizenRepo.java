package in.harshsharma.microservices.CitizenService.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.harshsharma.microservices.CitizenService.Entity.Citizen;

public interface CitizenRepo extends JpaRepository<Citizen , Integer> {

	
	public List<Citizen> findByVaccinationCenterId(Integer id);
}
