package kz.nbt.repo;

import kz.nbt.entity.CRM;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CRMRepo extends CrudRepository<CRM,Long> {


    CRM findAllByClientid(String clientid);


}
