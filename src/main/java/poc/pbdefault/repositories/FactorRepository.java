package poc.pbdefault.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poc.pbdefault.domain.Factors;

public interface FactorRepository extends CrudRepository<Factors, Long> {
	public List<Factors> findByLabel(String label);
}
