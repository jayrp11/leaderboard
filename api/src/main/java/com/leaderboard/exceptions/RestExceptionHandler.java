package com.leaderboard.exceptions;

import com.leaderboard.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(LeaderboardException.class)
    public ResponseEntity<ErrorResponse> handleLeaderboardException(LeaderboardException e) {
        logger.error("Exception occured", e);
        return ResponseEntity
                .status(500)
                .body(ErrorResponse.builder().message("Error while generating response.").build());
    }
}
