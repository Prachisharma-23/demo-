package study.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import study.demo.model.Growth;

@Repository
public interface GrowthRepository extends JpaRepository<Growth, Long> {
}
