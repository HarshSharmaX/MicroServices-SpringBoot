package in.harshsharma.microservices.VaccinationCenter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.harshsharma.microservices.VaccinationCenter.Entity.VaccinationCenter;

public interface VaccinationCenterRepo extends JpaRepository<VaccinationCenter, Integer> {

}
