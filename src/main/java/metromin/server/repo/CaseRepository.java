package metromin.server.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import metromin.server.model.Case;

public interface CaseRepository extends PagingAndSortingRepository<Case, Integer> {

    Page<Case> findByAssigneeIdIsNull(Pageable p);

}