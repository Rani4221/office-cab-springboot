package demo.project.pd.workshop.demostatement.repository;


import demo.project.pd.workshop.demostatement.model.OfficeCab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeCabRepository extends JpaRepository<OfficeCab,Long> {

    List<OfficeCab> findByNameIgnoreCase(String name);

    List<OfficeCab> findByNameStartsWith(String name);

    List<OfficeCab> findByEmailIdContains(String emailId);

    List<OfficeCab> findByContactNumber(Long contactNumber);
}
