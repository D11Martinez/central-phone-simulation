package central.telephone.simulation.repositories;

import central.telephone.simulation.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Query(value = "SELECT count(u)>0 FROM public.users AS u WHERE u.username = ?1", nativeQuery = true)
    public boolean exists(String username);
}
