package com.example.demo.repository;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.User;
import com.example.demo.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByUserId (Long userId);
    List<Vote> findByUserIdAndVoteTimeAfter(Long userId, LocalDateTime dateTime);
    Optional<Vote> findByUserIdAndMenuItemId(User userId, MenuItem menuItemId);
    List<Vote> findByMenuItemId(MenuItem menuItemId);
}


