package study.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import study.demo.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByTextContaining(String text);
}
