package metromin.server.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import metromin.server.model.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {
    
    Page<Message> findByCaseId(Pageable page, Integer caseId);

}