package com.dev.soulScript_Api.exception;

public class JournalEntryNotFoundException extends RuntimeException{
    public JournalEntryNotFoundException(String message){
        super(message);
    }
}
