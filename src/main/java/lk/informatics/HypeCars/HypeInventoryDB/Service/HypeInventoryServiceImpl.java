package lk.informatics.HypeCars.HypeInventoryDB.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeBranches;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeDetails;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeDistricts;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeProvinces;
import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeVehicleMakes;
import lk.informatics.HypeCars.HypeInventoryDB.Repo.HypeInventoryBranchesRepository;
import lk.informatics.HypeCars.HypeInventoryDB.Repo.HypeInventoryDistrictsRepository;
import lk.informatics.HypeCars.HypeInventoryDB.Repo.HypeInventoryHypeDetailsRepository;
import lk.informatics.HypeCars.HypeInventoryDB.Repo.HypeInventoryProvincesRepository;
import lk.informatics.HypeCars.HypeInventoryDB.Repo.HypeInventoryVehicleMakesRepository;
import lk.informatics.HypeCars.Response.Response;

@Service
public class HypeInventoryServiceImpl implements HypeInventoryService{
	
	private static DecimalFormat decimalFormatter = new DecimalFormat("00000");
	
	@Autowired
	HypeInventoryProvincesRepository hypeInventoryProvincesRepo;
	
	@Autowired
	HypeInventoryDistrictsRepository hypeInventoryDistrictsRepo;
	
	@Autowired
	HypeInventoryBranchesRepository hypeInventoryBranchesRepo;
	
	@Autowired
	HypeInventoryVehicleMakesRepository hypeInventoryVehicleMakesRepo;
	
	@Autowired
	HypeInventoryHypeDetailsRepository hypeInventoryHypeDetailsRepo;
	
	private Response validateUserNic(String nic) {
		if (nic.matches("^[0-9]{9}[x|X|v|V]|[0-9]{12}$")) {
			return new Response(Response.SUCCESS, "SUCCESS", "NIC number is valid");
		} else {
			return new Response(Response.ERROR, "FAILED", "NIC number is invalid");
		}
	}
	
	private Response validateRegNo(String regNo) {
		if (regNo.matches("^([SP|sp|WP|wp]\s([a-zA-Z]{2,3}))-[0-9]{4}$")) {
			return new Response(Response.SUCCESS, "SUCCESS", "Valid registration number.");
		} else {
			return new Response(Response.ERROR, "FAILED", "Invalid registration number.");
		}
	}

	@Override
	public Response addHypeProvinces(HypeProvinces province) {
		HypeProvinces provinceTest = hypeInventoryProvincesRepo.save(province);
		
		if (provinceTest != null) {
			return new Response(Response.SUCCESS, "SUCCESS", "Hype province saved successfully.");
		} else {
			return new Response(Response.ERROR, "SUCCESS", "Failed to save hype province.");
		}

	}

	@Override
	public Response addHypeDistricts(HypeDistricts district) {
		HypeDistricts districtTest = hypeInventoryDistrictsRepo.save(district);
		
		if (districtTest != null) {
			return new Response(Response.SUCCESS, "SUCCESS", "Hype district saved successfully.");
		} else {
			return new Response(Response.ERROR, "FAILED", "Failed to save hype district.");
		}

	}

	@Override
	public Response addHypeBranches(HypeBranches branch) {
		HypeBranches branchTest = hypeInventoryBranchesRepo.save(branch);
		
		if (branchTest != null) {
			return new Response(Response.SUCCESS, "SUCCESS", "Hype branch saved successfully.");
		} else {
			return new Response(Response.ERROR, "FAILED", "Failed to save hype branch.");
		}

	}

	@Override
	public Response addVehicleMakes(HypeVehicleMakes vehicle) {
		int seqNo = hypeInventoryVehicleMakesRepo.checkIfTableIsEmpty();
		
		HypeVehicleMakes example = new HypeVehicleMakes();
		example.setCompanyName(vehicle.getCompanyName());
		Example<HypeVehicleMakes> matcher = Example.of(example, ExampleMatcher.matchingAny());
		List<HypeVehicleMakes> makeTest = hypeInventoryVehicleMakesRepo.findAll(matcher);
		
		if (makeTest.isEmpty()) {
			if (seqNo == 0) {
				String makeId = "MK-"+1;
				
				vehicle.setMakeId(makeId);
				vehicle.setSequence(1);

			} else {
				int lastSeqNo = hypeInventoryVehicleMakesRepo.getMaxSequenceNumber();
				int x = lastSeqNo+1;
				String makeId = "MK-"+x;
				
				vehicle.setMakeId(makeId);
				vehicle.setSequence(x);
			}
			
			HypeVehicleMakes carTest = hypeInventoryVehicleMakesRepo.save(vehicle);
			List<HypeVehicleMakes> returnList = new ArrayList<HypeVehicleMakes>();
			returnList.add(carTest);
			
			if (carTest != null) {
				return new Response(Response.SUCCESS, "SUCCESS", "Vehicle makes added successfully.", returnList);
			} else {
				return new Response(Response.ERROR, "FAILED", "Failed to add vehicle make.");
			}
		
		} else {
			return new Response(Response.DATA_ALREADY_EXISTS, "FAILED", "Make already exists.");
		} 
		
	}

	@Override
	public Response addVehicleToInventory(HypeDetails vehicle) throws EntityNotFoundException, JpaObjectRetrievalFailureException{
		int isTableEmpty = hypeInventoryHypeDetailsRepo.checkIfTableIsEmpty();
		
//		HypeDetails inventory = new HypeDetails();
//		inventory.setVehicleRegNumber(vehicle.getVehicleRegNumber());
//		Example<HypeDetails> matcher = Example.of(inventory, ExampleMatcher.matchingAny());
		List<HypeDetails> isInvExists = hypeInventoryHypeDetailsRepo.getInventoryByVehicleRegNo(vehicle.getVehicleRegNumber());
		
		if (isInvExists.isEmpty()) {
				
			if (isTableEmpty == 0) {
				String formattedSeqNo = decimalFormatter.format(1);
				vehicle.setInventoryId("INV-"+formattedSeqNo);
				vehicle.setSequence(1);
				
			} else {
				int lastSeqNo = hypeInventoryHypeDetailsRepo.getMaxSequenceNumber();
				int x = lastSeqNo+1;
				String formattedSeqNo = decimalFormatter.format(x);
				
				vehicle.setInventoryId("INV-"+formattedSeqNo);
				vehicle.setSequence(x);
			}
			
			HypeDetails inventoryTest = hypeInventoryHypeDetailsRepo.save(vehicle);
			List<HypeDetails> returnList = new ArrayList<HypeDetails>();
			returnList.add(inventoryTest);
			
			if (inventoryTest != null) {
				return new Response(Response.SUCCESS, "SUCCESS", "Inventory added successfully.", returnList);
			
			} else {
				return new Response(Response.ERROR, "FAILED", "Failed to add inventory..");
			}
			
		} else {
			return new Response(Response.DATA_ALREADY_EXISTS, "FAILED", "Inventory already exists.");
		}
		
	}

	@Override
	public Response editVehicleInInventory(HypeDetails vehicle) throws EntityNotFoundException, JpaObjectRetrievalFailureException{
		HypeDetails detailtest = hypeInventoryHypeDetailsRepo.getInventoryByInventoryId(vehicle.getInventoryId());
		
		if (detailtest != null) {
			if (detailtest.isAvailable()) {
				
				int seqNo = hypeInventoryHypeDetailsRepo.getSeqNumberByInventoryId(vehicle.getInventoryId());
				vehicle.setSequence(seqNo);
				
				hypeInventoryHypeDetailsRepo.save(vehicle);
				
				return new Response(Response.SUCCESS, "SUCCESS", "Inventory updated successfully.");
			} else {
				return new Response(Response.NOT_AVAILABLE, "FAILED", "Inventory is not available.");
			}
			
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found.");
		}
		
	}

	@Override
	public Response deleteVehicleFromInventory(HypeDetails inventoryId) {
		HypeDetails inventory = hypeInventoryHypeDetailsRepo.getInventoryByInventoryId(inventoryId.getInventoryId());
		
		if (inventory != null) {
			if(inventory.isAvailable()) {
				inventory.setAvailable(false);
				
				hypeInventoryHypeDetailsRepo.save(inventory);
			
				return new Response(Response.SUCCESS, "SUCCESS", "Vehicle deleted from the inventory.");
			
			} else {
				return new Response(Response.NOT_AVAILABLE, "FAILED", "Already not available.");
			}
		
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found.");
		
		}
		
	}

	@Override
	public Response getHypeProvinces() {
		List<HypeProvinces> provinces = new ArrayList<HypeProvinces>();
		
		hypeInventoryProvincesRepo.findAll()
			.forEach(provinces::add);
		
		if (!provinces.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Hype provinces list.", provinces);
		
		} else {
			return new Response(Response.ERROR, "FAILED", "No data found.");
		}

	}

	@Override
	public Response getHypeDistricts(String provinceId) {
		List<HypeDistricts> districts = new ArrayList<HypeDistricts>();
		
		HypeDistricts district = new HypeDistricts();
		district.setProvinceId(new HypeProvinces(provinceId));
		
		Example<HypeDistricts> matcher = Example.of(district, ExampleMatcher.matchingAll());
		
		hypeInventoryDistrictsRepo.findAll(matcher)
			.forEach(districts::add);
		
		if (!districts.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Hype districts list.", districts);
		
		} else {
			return new Response(Response.ERROR, "FAILED", "No data found.");
		} 
		
	}

	@Override
	public Response getVehicleMakes() {
		List<HypeVehicleMakes> makes = new ArrayList<HypeVehicleMakes>();
		
		hypeInventoryVehicleMakesRepo.findAll()
			.forEach(makes::add);
		
		if (!makes.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Hype vehicle makes list.", makes);
		
		} else {
			return new Response(Response.ERROR, "FAILED", "No data found.");
		}

	}
	
	private static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        fileInputStreamReader.close();
        
        return new String(Base64.getEncoder().encodeToString(bytes));
	}

	@Override
	public Response searchVehicleByRegistrationNo(String regNo) {
		List<HypeDetails> vehicles = hypeInventoryHypeDetailsRepo.findVehicleByRegistrationNo(regNo);
		
		for (HypeDetails hypeDetails : vehicles) {
			try {
				String base64String = encodeFileToBase64Binary(new File(hypeDetails.getImage()));
				hypeDetails.setImage(base64String);;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!vehicles.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Vehicles list", vehicles);
		
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found.");
		}
		
	}

	@Override
	public Response searchVehicleByMake(HypeVehicleMakes make) {
		HypeVehicleMakes makeTest = hypeInventoryVehicleMakesRepo.findMakeIdByCompanyName(make.getCompanyName());
		
		List<HypeDetails> vehicles = hypeInventoryHypeDetailsRepo.findVehicleByMake(makeTest);
		
		for (HypeDetails hypeDetails : vehicles) {
			try {
				String base64String = encodeFileToBase64Binary(new File(hypeDetails.getImage()));
				hypeDetails.setImage(base64String);;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!vehicles.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Vehicles list", vehicles);
		
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found.");
		}

	}

	@Override
	public Response searchVehicleByBranch(HypeBranches branch) {
		List<HypeDetails> vehicles = hypeInventoryHypeDetailsRepo.findVehicleByBranch(branch);
		
		for (HypeDetails hypeDetails : vehicles) {
			try {
				String base64String = encodeFileToBase64Binary(new File(hypeDetails.getImage()));
				hypeDetails.setImage(base64String);;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!vehicles.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Vehicles list", vehicles);
		
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found.");
		}
		
	}

	@Override
	public Response saveImagesInLocalStorage(MultipartFile[] files, String vehicleRegNo) {
		
		List<HypeDetails> vehicles = hypeInventoryHypeDetailsRepo.findVehicleByRegistrationNo(vehicleRegNo);
		
//		List<MultipartFile> imagesListTest = new ArrayList<MultipartFile>();
//		Arrays.asList(files)
//			.stream()
//			.forEach(imagesListTest::add);
		
		if (!vehicles.isEmpty()) {
			String RESOURCES_DIR = "CAR_IMAGES";
			
			String newFile = RESOURCES_DIR + "/" + vehicleRegNo + "-" + java.time.LocalDate.now();
			File image = new File(newFile);
			image.mkdir();
			
			for (MultipartFile multipartFile : files) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				
				try {
					
					if (fileName.contains("..")) {
						return new Response(Response.ERROR, "FAILED", "File name contains invalid path sequence : "+ fileName);
					}
					
					Path path = Paths.get(newFile, multipartFile.getOriginalFilename());
					try {
						Files.write(path, multipartFile.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					hypeInventoryHypeDetailsRepo.updateImagePathByVehicleRegNo(path.toString(), vehicleRegNo);
					
					return new Response(Response.SUCCESS, "SUCCESS", path.toString());
				
				} catch (Exception e) {
					e.printStackTrace();
					return new Response(Response.ERROR, "FAILED", "Could not store file");
				}
			}
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching vehicle found.");
		}
		
		return new Response(Response.ERROR, "FAILED", "Something is wrong");
	}

}
