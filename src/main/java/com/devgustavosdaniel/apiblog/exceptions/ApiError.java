package com.devgustavosdaniel.apiblog.exceptions;

import java.time.LocalDateTime;

public record ApiError(
        LocalDateTime timesTemp,
        int status,
        String error,
        String message
) { }
