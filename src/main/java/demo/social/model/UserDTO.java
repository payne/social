package demo.social.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String handle;

    private List<Long> friendships;

    public UserDTO() {
        friendships = new ArrayList<>();
    }

    public UserDTO(String n, String h) {
        this();
        this.name=n;
        this.handle=h;
    }

    public void addFriendShip(Long friendId) {
        this.friendships.add(friendId);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(final String handle) {
        this.handle = handle;
    }

    public List<Long> getFriendships() {
        return friendships;
    }

    public void setFriendships(final List<Long> friendships) {
        this.friendships = friendships;
    }

}
