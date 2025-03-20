package com.example.demo.mapper;

import com.example.demo.dto.VoteRequestDTO;
import com.example.demo.dto.VoteResponseDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.Vote;
import com.example.demo.entity.User;
import com.example.demo.entity.MenuItem;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class VoteMapperTest {

    private final VoteMapper voteMapper = Mappers.getMapper(VoteMapper.class);

    @Test
    void shouldConvertVoteToVoteResponseDTO() {
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password", Role.STUDENT);
        MenuItem menuItem = new MenuItem(1L, "Pizza", "Delicious pizza", 5.99, true);
        Vote vote = new Vote(1L, user, menuItem, LocalDateTime.now());

        VoteResponseDTO voteResponseDTO = voteMapper.voteToVoteResponseDTO(vote);

        assertThat(voteResponseDTO).isNotNull();
        assertThat(voteResponseDTO.getUserId()).isEqualTo(user.getId());
        assertThat(voteResponseDTO.getMenuItemId()).isEqualTo(menuItem.getId());
    }

    @Test
    void shouldConvertVoteRequestDTOToVote() {
        VoteRequestDTO voteRequestDTO = new VoteRequestDTO(1L);

        Vote vote = voteMapper.voteRequestDTOToVote(voteRequestDTO);

        assertThat(vote).isNotNull();
    }
}

