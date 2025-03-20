package com.example.demo.mapper;

import com.example.demo.dto.VoteRequestDTO;
import com.example.demo.dto.VoteResponseDTO;
import com.example.demo.entity.Vote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    VoteResponseDTO voteToVoteResponseDTO(Vote vote);

    Vote voteRequestDTOToVote(VoteRequestDTO voteRequestDTO);
}

