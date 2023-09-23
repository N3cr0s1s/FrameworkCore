package me.necrosis.fwc.core.services.impl;

import com.google.inject.Inject;
import me.necrosis.fwc.core.services.DataAccess;
import me.necrosis.fwc.core.entities.User;
import me.necrosis.fwc.core.services.UserService;

public class UserServiceImpl implements UserService {

    private final DataAccess dataAccess;

    @Inject
    public UserServiceImpl(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public User createUser(String firstName, String lastName, int age) {
        User user = User.builder()
                .age(age)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        return this.dataAccess.saveEntity(user);
    }

    @Override
    public boolean deleteUserByFirstName(String firstName) {
        return this.dataAccess.deleteEntityWhere(User.class, x -> x.getFirstName().equals(firstName));
    }
}
