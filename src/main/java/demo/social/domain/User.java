package demo.social.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String handle;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "friendship",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id_parent")
    )
    private Set<User> friendshipUsers;

    @ManyToMany(mappedBy = "friendshipUsers")
    private Set<User> friendshipUsersParent;

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

    public Set<User> getFriendshipUsers() {
        return friendshipUsers;
    }

    public void setFriendshipUsers(final Set<User> friendshipUsers) {
        this.friendshipUsers = friendshipUsers;
    }

    public Set<User> getFriendshipUsersParent() {
        return friendshipUsersParent;
    }

    public void setFriendshipUsersParent(final Set<User> friendshipUsersParent) {
        this.friendshipUsersParent = friendshipUsersParent;
    }

}
