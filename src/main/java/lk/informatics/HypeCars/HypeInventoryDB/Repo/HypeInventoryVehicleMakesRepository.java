package lk.informatics.HypeCars.HypeInventoryDB.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeVehicleMakes;

public interface HypeInventoryVehicleMakesRepository extends JpaRepository<HypeVehicleMakes, String>{
	
	@Query("SELECT COUNT(v) FROM HypeVehicleMakes v")
	public int checkIfTableIsEmpty();
	
	@Query("SELECT MAX(v.sequence) FROM HypeVehicleMakes v")
	public int getMaxSequenceNumber();
	
	@Query("SELECT v FROM HypeVehicleMakes v WHERE v.companyName = ?1")
	public HypeVehicleMakes findMakeIdByCompanyName(String companyName);
}
