package metromin.server.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import metromin.server.model.Campaign;

public interface CampaignRepository extends PagingAndSortingRepository<Campaign, Integer> {

    @Query("SELECT c FROM Campaign AS c WHERE c.fundsReceived < c.fundsNeeded")
    Page<Campaign> findAllActive(Pageable p);

    @Query("SELECT c FROM Campaign AS c WHERE c.fundsReceived >= c.fundsNeeded")
    Page<Campaign> findAllComplete(Pageable p);

}