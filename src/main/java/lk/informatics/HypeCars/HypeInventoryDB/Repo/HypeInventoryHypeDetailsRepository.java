package lk.informatics.HypeCars.HypeInventoryDB.Repo;

import java.nio.file.Path;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeBranches;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeDetails;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeVehicleMakes;

public interface HypeInventoryHypeDetailsRepository extends JpaRepository<HypeDetails, String>{
	
	@Query("SELECT COUNT(d) FROM HypeDetails d")
	public int checkIfTableIsEmpty();
	
	@Query("SELECT MAX(d.sequence) FROM HypeDetails d")
	public int getMaxSequenceNumber();
	
	@Query("SELECT d FROM HypeDetails d WHERE d.vehicleRegNumber = ?1")
	public List<HypeDetails> getInventoryByVehicleRegNo(String vehicleRegNo);
	
	@Query("SELECT d FROM HypeDetails d WHERE d.inventoryId = ?1")
	public HypeDetails getInventoryByInventoryId(String inventoryId);
	
	@Query("SELECT d.sequence FROM HypeDetails d WHERE d.inventoryId = ?1")
	public int getSeqNumberByInventoryId(String inventoryId);
	
	@Query("SELECT d FROM HypeDetails d WHERE d.vehicleRegNumber = ?1")
	public List<HypeDetails> findVehicleByRegistrationNo(String regNo);
	
	@Query("SELECT d FROM HypeDetails d WHERE d.makeId = ?1")
	public List<HypeDetails> findVehicleByMake(HypeVehicleMakes make);
	
	@Query("SELECT d FROM HypeDetails d WHERE d.branchId = ?1")
	public List<HypeDetails> findVehicleByBranch(HypeBranches branch);
	
	@Transactional
	@Modifying
	@Query("UPDATE HypeDetails d SET d.image = ?1 WHERE d.vehicleRegNumber = ?2")
	public void updateImagePathByVehicleRegNo(String path, String regNo);
}
