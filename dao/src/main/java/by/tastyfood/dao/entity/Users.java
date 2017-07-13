package by.tastyfood.dao.entity;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nikolay on 19.06.2017.
 */
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Integer enabled;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "external_avatar")
    private String externalAvatar;

    @ManyToOne()
    @JoinColumn(name = "roles_id")
    private Roles role;

    @OneToMany(mappedBy = "author")
    private Set<Recipe> recipies;

    @Column(name = "image_id", columnDefinition = "default '-1'")
    private Long avatarId;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Comments>  comments;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<RecipeRating> recipeRatings;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<FavoriteRecipe> favoriteRecipes;



    public Set<Comments>  getComments() {
        return comments;
    }

    public void setComments(Set<Comments>  comments) {
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Set<Recipe> getRecipies() {
        return recipies;
    }

    public void setRecipies(Set<Recipe> recipies) {
        this.recipies = recipies;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getExternalAvatar() {
        return externalAvatar;
    }

    public void setExternalAvatar(String externalAvatar) {
        this.externalAvatar = externalAvatar;
    }

    public Set<RecipeRating> getRecipeRatings() {
        return recipeRatings;
    }

    public void setRecipeRatings(Set<RecipeRating> recipeRatings) {
        this.recipeRatings = recipeRatings;
    }

    public Set<FavoriteRecipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(Set<FavoriteRecipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    @PrePersist
    protected void onCreate() {
       if(StringUtils.isEmpty(displayName)){
           displayName = username;
       }
    }
}
