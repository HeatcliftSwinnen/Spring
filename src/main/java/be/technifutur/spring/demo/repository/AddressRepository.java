package be.technifutur.spring.demo.repository;


import be.technifutur.spring.demo.models.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
