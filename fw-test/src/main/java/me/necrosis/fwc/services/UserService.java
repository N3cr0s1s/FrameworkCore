package me.necrosis.fwc.services;

import me.necrosis.fwc.entities.User;

public interface UserService {

    User createUser(String firstName, String lastName, int age);

    boolean deleteUserByFirstName(String firstName);
}
