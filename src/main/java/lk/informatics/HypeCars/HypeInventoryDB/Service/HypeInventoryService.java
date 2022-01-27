package lk.informatics.HypeCars.HypeInventoryDB.Service;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.multipart.MultipartFile;

import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeBranches;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeDetails;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeDistricts;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeProvinces;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeVehicleMakes;
import lk.informatics.HypeCars.Response.Response;

public interface HypeInventoryService {
	
	// temporary methods to add default data
	public Response addHypeProvinces(HypeProvinces province);
	public Response addHypeDistricts(HypeDistricts district);
	public Response addHypeBranches(HypeBranches branch);
	public Response addVehicleMakes(HypeVehicleMakes vehicle);
	
	// add, edit and delete vehicles in inventory
	public Response addVehicleToInventory(HypeDetails vehicle) throws EntityNotFoundException, JpaObjectRetrievalFailureException;
	public Response editVehicleInInventory(HypeDetails vehicle) throws EntityNotFoundException, JpaObjectRetrievalFailureException, DataIntegrityViolationException;
	public Response deleteVehicleFromInventory(HypeDetails inventoryId);
	
	// get default data
	public Response getHypeProvinces();
	public Response getHypeDistricts(String provinceId);
	public Response getVehicleMakes();
	
	// search vehicle by registration no, make or branch
	public Response searchVehicleByRegistrationNo(String regNo);
	public Response searchVehicleByMake(HypeVehicleMakes make);
	public Response searchVehicleByBranch(HypeBranches branch);
	
	// saving several images
	public Response saveImagesInLocalStorage(MultipartFile[] files, String vehicleRegNo);
}
