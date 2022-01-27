package lk.informatics.HypeCars.HypeInventoryDB.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeBranches;

@Repository
public interface HypeInventoryBranchesRepository extends JpaRepository<HypeBranches, String>{

}
