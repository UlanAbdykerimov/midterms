package com.example.demo.repository;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.User;
import com.example.demo.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUserAndMenuItem(User user, MenuItem menuItem); // Проверить, голосовал ли пользователь

    List<Vote> findByMenuItem(MenuItem menuItem); // Все голоса за блюдо
}

