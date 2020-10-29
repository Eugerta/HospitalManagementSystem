package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import system.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication,Integer>{

	@Query(value="update medication set quantity =?2 where id=?1 ",nativeQuery = true)
     void updateMedicationQuantity(int medId, int quantity);
	
	@Query(value="select quantity from medication where id=?1 ",nativeQuery = true)
	int getQuantityById(int medId);
	
}
