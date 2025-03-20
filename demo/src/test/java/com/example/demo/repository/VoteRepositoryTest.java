package com.example.demo.repository;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.User;
import com.example.demo.entity.Vote;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VoteRepositoryTest {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    @DisplayName("Проверка, голосовал ли студент за блюдо")
    void testFindByUserAndMenuItem() {
        User user = new User();
        user.setEmail("voter@alatoo.edu.kg");
        userRepository.save(user);

        MenuItem menuItem = new MenuItem(null, "Манты", "Манты с говядиной", 150.0, false);
        menuItemRepository.save(menuItem);

        Vote vote = new Vote(null, user, menuItem, LocalDateTime.now());
        voteRepository.save(vote);

        Optional<Vote> foundVote = voteRepository.findByUserAndMenuItem(user, menuItem);
        assertThat(foundVote).isPresent();
    }

    @Test
    @DisplayName("Получение всех голосов за блюдо")
    void testFindByMenuItem() {
        MenuItem menuItem = new MenuItem(null, "Бешбармак", "Традиционный бешбармак", 200.0, false);
        menuItemRepository.save(menuItem);

        User user1 = new User();
        user1.setEmail("user1@alatoo.edu.kg");
        userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("user2@alatoo.edu.kg");
        userRepository.save(user2);

        voteRepository.saveAll(List.of(
                new Vote(null, user1, menuItem, LocalDateTime.now()),
                new Vote(null, user2, menuItem, LocalDateTime.now())
        ));

        List<Vote> votes = voteRepository.findByMenuItem(menuItem);
        assertThat(votes).hasSize(2);
    }
}
