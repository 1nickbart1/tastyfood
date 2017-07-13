package by.tastyfood.dao.entity;

import javax.persistence.*;

/**
 * Created by Nikolay on 23.06.2017.
 */
@Entity
@Table(name = "recipe_rating")
public class RecipeRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @ManyToOne()
    @JoinColumn(name = "recipies_id")
    private Recipe recipe;

    @ManyToOne()
    @JoinColumn(name = "users_id")
    private Users user;

    public RecipeRating(){
        super();
    }
    public RecipeRating(Users user, Recipe recipe){
        super();
        this.user = user;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
