package com.samiayaz.demo.springsecurity.dto;

public record RegisterRequest(String name, String surname, String username, String email, String password) {
}
