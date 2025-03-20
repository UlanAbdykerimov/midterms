package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.entity.Vote;
import com.example.demo.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    // Cast a vote for a menu item
    @PostMapping("/cast")
    public ResponseEntity<Vote> castVote(@RequestParam Long userId, @RequestParam Long menuItemId) {
        try {
            Vote vote = voteService.castVote(userId, menuItemId);
            return ResponseEntity.ok(vote);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);  // Handle the exception (already voted)
        }
    }

    // Get all votes for a menu item
    @GetMapping("/menu/{menuItemId}")
    public ResponseEntity<List<Vote>> getVotesByMenuItemId(@PathVariable MenuItem menuItemId) {
        List<Vote> votes = voteService.getVotesByMenuItemId(menuItemId);
        return ResponseEntity.ok(votes);
    }

    // Get all votes by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Vote>> getVotesByUserId(@PathVariable Long userId) {
        List<Vote> votes = voteService.getVotesByUserId(userId);
        return ResponseEntity.ok(votes);
    }

    // Get vote by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVoteById(@PathVariable Long id) {
        Optional<Vote> vote = voteService.getVoteById(id);
        return vote.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete vote
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long id) {
        voteService.deleteVote(id);
        return ResponseEntity.noContent().build();
    }
}

