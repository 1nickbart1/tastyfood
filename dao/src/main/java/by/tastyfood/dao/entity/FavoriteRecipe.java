package by.tastyfood.dao.entity;

import javax.persistence.*;

/**
 * Created by Nikolay on 26.06.2017.
 */
@Entity
@Table(name = "favorite_recipe")
public class FavoriteRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "recipies_id", nullable = false)
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteRecipe that = (FavoriteRecipe) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return recipe != null ? recipe.equals(that.recipe) : that.recipe == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        return result;
    }
}
