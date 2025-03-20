package com.example.demo.service;

import com.example.demo.entity.Vote;
import com.example.demo.repository.VoteRepository;
import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    // Cast a vote for a menu item
    public Vote castVote(Long userId, Long menuItemId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<MenuItem> menuItemOptional = menuItemRepository.findById(menuItemId);

        if (userOptional.isPresent() && menuItemOptional.isPresent()) {
            // Check if user has voted within the last week
            LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
            List<Vote> userVotes = voteRepository.findByUserIdAndVoteTimeAfter(userId, oneWeekAgo);
            if (!userVotes.isEmpty()) {
                throw new IllegalStateException("You have already voted in the last week.");
            }

            Vote vote = new Vote();
            vote.setUser(userOptional.get());
            vote.setMenuItem(menuItemOptional.get());
            vote.setVoteTime(LocalDateTime.now());

            return voteRepository.save(vote);
        }
        return null;
    }

    // Get vote by ID
    public Optional<Vote> getVoteById(Long id) {
        return voteRepository.findById(id);
    }

    // Get all votes for a specific menu item
    public List<Vote> getVotesByMenuItemId(MenuItem menuItemId) {
        return voteRepository.findByMenuItemId(menuItemId);
    }

    // Get all votes by user
    public List<Vote> getVotesByUserId(Long userId) {
        return voteRepository.findByUserId(userId);
    }

    // Delete a vote by ID
    public void deleteVote(Long id) {
        voteRepository.deleteById(id);
    }

    // Check if user has already voted for a menu item
    public boolean hasUserVoted(User userId, MenuItem menuItemId) {
        Optional<Vote> votes = voteRepository.findByUserIdAndMenuItemId(userId, menuItemId);
        return votes.isPresent();
    }
}

