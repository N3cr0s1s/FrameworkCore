package me.necrosis.fwc;

import lombok.extern.slf4j.Slf4j;
import me.necrosis.fwc.entities.User;
import me.necrosis.fwc.services.TransientService;
import me.necrosis.fwc.services.UserService;

import java.util.UUID;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Main started");

        TestFramework testFramework = new TestFramework();
//        UserService instance = testFramework.getInjector().getInstance(UserService.class);
//        User user = instance.createUser("Test", "Yes", 20);
//        boolean b = instance.deleteUserByFirstName("Test");
//
//        log.info("{} deleted? {}",user.getFirstName(),b);
//
//        TransientService instance1 = testFramework.getInjector().getInstance(TransientService.class);
//        UUID uuid = instance1.getUUID();
//        log.info(uuid.toString());
//
//        TransientService instance2 = testFramework.getInjector().getInstance(TransientService.class);
//        UUID uuid1 = instance2.getUUID();
//        log.info(uuid1.toString());
//
//        log.info("--");
//
//        log.info("UUIDS are same? : {}", uuid.equals(uuid1));
    }
}
