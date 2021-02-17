package demo.social;

import demo.social.model.UserDTO;
import demo.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class SocialApplication implements CommandLineRunner {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SocialApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserDTO bert = new UserDTO("Bert","BertMan");
        UserDTO ernie = new UserDTO("Ernie","EpicErnie");
        UserDTO bigBird = new UserDTO("Big Bird","TallBird");
        List<UserDTO> people = Arrays.asList(bert,ernie,bigBird);
        people.forEach(p -> {
            p.setId(userService.create(p));
            System.out.println("Created person with id="+p.getId());
        });
        // Bert is friends with everyone
        bert.addFriendShip(ernie.getId());
        bert.addFriendShip(bigBird.getId());
        // Ernie is friends with only Bert
        ernie.addFriendShip(bert.getId());

        userService.update(bert.getId(),bert);
        userService.update(ernie.getId(),ernie);
    }
}
