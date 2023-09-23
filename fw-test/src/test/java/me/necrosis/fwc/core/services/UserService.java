package me.necrosis.fwc.core.services;

import me.necrosis.fwc.core.entities.User;

public interface UserService {

    User createUser(String firstName, String lastName, int age);

    boolean deleteUserByFirstName(String firstName);
}
