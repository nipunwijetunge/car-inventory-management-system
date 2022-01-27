package lk.informatics.HypeCars.HypeInventoryDB.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import lk.informatics.HypeCars.HypeInventoryDB.Entities.HypeDistricts;

public interface HypeInventoryDistrictsRepository extends JpaRepository<HypeDistricts, String>{

}
