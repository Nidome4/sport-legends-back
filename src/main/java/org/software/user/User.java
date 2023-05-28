package org.software.user;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_type")
    private int userType;
    private String username;
    private String email;
    private String password;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at")
    private LocalDateTime  updatedAt = LocalDateTime.now();

    public User() {
    }

    public User(long id, int userType, String username, String email, String password) {
        this.id = id;
        this.userType = userType;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void updateAll(User user2){
        if(user2.getEmail() != null && user2.getEmail() !=""){
            this.email = user2.getEmail();
        }
        if(user2.getUserType() != 0){
            this.userType = user2.getUserType();
        }
        if(user2.getUsername() != null && user2.getUsername() !=""){
            this.username = user2.getUsername();
        }
        if(user2.getPassword() != null && user2.getPassword() !=""){
            this.password = user2.getPassword();
        }
    }
}
