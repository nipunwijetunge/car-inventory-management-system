package lk.informatics.HypeCars.HypeInventoryDB.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeBranches;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeDetails;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeDistricts;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeProvinces;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeVehicleMakes;
import lk.informatics.HypeCars.Response.Response;

public interface HypeInventoryController {

	// temporary methods to add default data
	public Response addHypeProvinces(@RequestBody HypeProvinces province);
	public Response addHypeDistricts(@RequestBody HypeDistricts district);
	public Response addHypeBranches(@RequestBody HypeBranches branch);
	public Response addVehicleMakes(@RequestBody HypeVehicleMakes vehicle);
	
	// add, edit and delete vehicles in inventory
	public Response addVehicleToInventory(@RequestBody HypeDetails vehicle);
	public Response editVehicleInInventory(@RequestBody HypeDetails vehicle);
	public Response deleteVehicleFromInventory(@RequestBody HypeDetails inventoryId);
	
	// get default data
	public Response getHypeProvinces();
	public Response getHypeDistricts(@RequestParam String provinceId);
	public Response getVehicleMakes();
	
	// search vehicle by registration no, make or branch
	public Response searchVehicleByRegistrationNo(@RequestParam String regNo);
	public Response searchVehicleByMake(@RequestParam String makeId);
	public Response searchVehicleByBranch(@RequestParam String branchId);
	
	// saving several images
	public Response saveImagesInLocalStorage(@RequestBody MultipartFile[] files, String vehicleRegNo);
}
