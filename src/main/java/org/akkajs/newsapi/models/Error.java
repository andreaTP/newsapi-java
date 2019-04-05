package org.akkajs.newsapi.models;

import org.akkajs.newsapi.constants.ErrorCodes;

import lombok.Data;

@Data
public class Error {
    private final ErrorCodes code;
    private final String message;
}
