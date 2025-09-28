package study.demo.controller;

import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.demo.model.Quote;
import study.demo.repository.QuoteRepository;

@RestController
@RequestMapping("/api/quotes")
@CrossOrigin(origins = "http://localhost:3000")
public class QuoteController {

    private final QuoteRepository repo;
    private final Random random = new Random();

    public QuoteController(QuoteRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/random")
    public Quote getRandomQuote() {
        List<Quote> all = repo.findAll();
        if (all.isEmpty()) {
            return new Quote("⚠️ No quotes found in database.");
        }
        return all.get(random.nextInt(all.size()));
    }
}

