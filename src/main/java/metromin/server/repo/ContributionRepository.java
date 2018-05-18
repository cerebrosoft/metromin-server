package metromin.server.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import metromin.server.model.Contribution;

public interface ContributionRepository extends PagingAndSortingRepository<Contribution, Integer> {

}