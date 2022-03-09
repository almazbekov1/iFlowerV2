//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(
        name = "users"
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "full_name"
    )
    private String fullName;
    @Column(
            name = "phone_number"
    )
    private String phoneNumber;
    @Column(
            name = "email",
            unique = true
    )
    private String email;
    @Column(
            name = "password"
    )
    private String password;
    @CreatedDate
    private Date date = new Date();
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )}
    )
    private List<Role> roles;

    public User() {
    }

    public User(String fullName, String phoneNumber, String email, String password) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String fullName, String phoneNumber, String email) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Date getDate() {
        return this.date;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }
}
