package kz.nbt.repo;

import kz.nbt.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoryRepo extends CrudRepository<History,Long> {



}
