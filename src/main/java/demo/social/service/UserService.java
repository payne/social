package demo.social.service;

import demo.social.domain.User;
import demo.social.model.UserDTO;
import demo.social.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setHandle(user.getHandle());
        userDTO.setFriendships(user.getFriendshipUsers() == null ? null : user.getFriendshipUsers().stream()
                .map(User::getId).collect(Collectors.toList()));
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setHandle(userDTO.getHandle());
        if (userDTO.getFriendships() != null) {
            final List<User> friendships = userRepository.findAllById(userDTO.getFriendships());
            if (friendships.size() != userDTO.getFriendships().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of friendships not found");
            }
            user.setFriendshipUsers(friendships.stream().collect(Collectors.toSet()));
        }
        return user;
    }

}
