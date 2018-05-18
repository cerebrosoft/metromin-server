package metromin.server.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import metromin.server.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

}