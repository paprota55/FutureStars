package pl.paprota.futurestars.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.paprota.futurestars.models.SumEntity;

@Repository
public interface AddRepository extends CrudRepository<SumEntity, Long> {
    SumEntity findByNumber(int number);
}
