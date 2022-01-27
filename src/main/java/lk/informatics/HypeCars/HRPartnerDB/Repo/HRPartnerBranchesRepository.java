package lk.informatics.HypeCars.HRPartnerDB.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.informatics.HypeCars.HRPartnerDB.Entities.HRBranches;

@Repository
public interface HRPartnerBranchesRepository extends JpaRepository<HRBranches, String>{
	
}
