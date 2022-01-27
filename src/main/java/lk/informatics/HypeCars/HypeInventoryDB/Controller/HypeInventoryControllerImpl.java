package lk.informatics.HypeCars.HypeInventoryDB.Controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeBranches;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeDetails;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeDistricts;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeProvinces;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeVehicleMakes;
import lk.informatics.HypeCars.HypeInventoryDB.Service.HypeInventoryService;
import lk.informatics.HypeCars.Response.Response;

@RestController
@RequestMapping("/hypeInventory")
public class HypeInventoryControllerImpl implements HypeInventoryController{
	
	@Autowired
	private HypeInventoryService service;

	@Override
	@RequestMapping(value = "/addHypeProvinces", method = RequestMethod.POST)
	public Response addHypeProvinces(@RequestBody HypeProvinces province) {
		try {
			return service.addHypeProvinces(province);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/addHypeDistricts", method = RequestMethod.POST)
	public Response addHypeDistricts(@RequestBody HypeDistricts district) {
		try {
			return service.addHypeDistricts(district);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/addHypeBranches", method = RequestMethod.POST)
	public Response addHypeBranches(@RequestBody HypeBranches branch) {
		try {
			return service.addHypeBranches(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/addVehicleMake", method = RequestMethod.POST)
	public Response addVehicleMakes(@RequestBody HypeVehicleMakes vehicle) {
		try {
			return service.addVehicleMakes(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/addVehicleToInventory", method = RequestMethod.POST)
	public Response addVehicleToInventory(@RequestBody HypeDetails vehicle) {
		try {
			return service.addVehicleToInventory(vehicle);
		} catch (EntityNotFoundException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (JpaObjectRetrievalFailureException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (DataIntegrityViolationException e) {
			return new Response(Response.KEY_VIOLATION, "FAILED", "Duplicate index.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/editVehicleInInventory", method = RequestMethod.POST)
	public Response editVehicleInInventory(@RequestBody HypeDetails vehicle) {
		try {
			return service.editVehicleInInventory(vehicle);
		} catch (EntityNotFoundException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (JpaObjectRetrievalFailureException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (DataIntegrityViolationException e) {
			return new Response(Response.KEY_VIOLATION, "FAILED", "Duplicate index.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/deleteVehicleFromInventory", method = RequestMethod.POST)
	public Response deleteVehicleFromInventory(@RequestBody HypeDetails inventoryId) {
		try {
			return service.deleteVehicleFromInventory(inventoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/getHypeProvinces", method = RequestMethod.GET)
	public Response getHypeProvinces() {
		try {
			return service.getHypeProvinces();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/getHypeDistricts", method = RequestMethod.GET)
	public Response getHypeDistricts(@RequestParam String provinceId) {
		try {
			return service.getHypeDistricts(provinceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/getHypeVehicleMakes", method = RequestMethod.GET)
	public Response getVehicleMakes() {
		try {
			return service.getVehicleMakes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchVehicleByRegNo", method = RequestMethod.GET)
	public Response searchVehicleByRegistrationNo(@RequestParam String regNo) {
		try {
			return service.searchVehicleByRegistrationNo(regNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchVehicleByMake", method = RequestMethod.GET)
	public Response searchVehicleByMake(@RequestParam String make) {
		HypeVehicleMakes makeMatcher = new HypeVehicleMakes();
		makeMatcher.setCompanyName(make);
		
		try {
			return service.searchVehicleByMake(makeMatcher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchVehicleByBranch", method = RequestMethod.GET)
	public Response searchVehicleByBranch(@RequestParam String branchId) {
		HypeBranches branch = new HypeBranches();
		branch.setBranchId(branchId);
		
		try {
			return service.searchVehicleByBranch(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/uplaodVehicleImages", method = RequestMethod.POST)
	public Response saveImagesInLocalStorage(@RequestBody MultipartFile[] files, String vehicleRegNo) {
		try {
			return service.saveImagesInLocalStorage(files, vehicleRegNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
