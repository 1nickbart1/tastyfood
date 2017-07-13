package by.tastyfood.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Nikolay on 03.06.2017.
 */
@Entity
@Table(name = "recipies")
public class Recipe implements Serializable,  Comparable<Recipe> {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "text", nullable = false, length = 50)
    private String text;

    @Column(name = "slider_id", nullable = true, length = 50)
    private Long sliderId;

    @ManyToOne()
    @JoinColumn(name ="reciepies_category_id", nullable = false)
    private RecipiesCategory recipiesCategory;

    @Column(name = "image_id", nullable = false, length = 50)
    private Long imageId;

    @Column(name = "cook_time", nullable = false)
    private Integer cookTime;

    @OneToMany(mappedBy = "recipe",fetch = FetchType.LAZY)
    private Set<RecipiesProduct> recipiesProductSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" )
    private Users author;

    @OneToMany(mappedBy = "recipe",fetch = FetchType.LAZY)
    @OrderBy("date ASC")
    private Set<Comments> comments;

    @OneToMany(mappedBy = "recipe",fetch = FetchType.LAZY)
    private Set<RecipeRating> recipeRatings;

    @OneToMany(mappedBy = "recipe",fetch = FetchType.LAZY)
    private Set<FavoriteRecipe> favoriteRecipes;



    public Set<Comments>  getComments() {
        return comments;
    }

    public void setComments(Set<Comments>  comments) {
        this.comments = comments;
    }

    public Set<RecipiesProduct> getRecipiesProductSet() {
        return recipiesProductSet;
    }

    public void setRecipiesProductSet(Set<RecipiesProduct> recipiesProductSet) {
        this.recipiesProductSet = recipiesProductSet;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RecipiesCategory getRecipiesCategory() {
        return recipiesCategory;
    }

    public void setRecipiesCategory(RecipiesCategory recipiesCategory) {

        this.recipiesCategory = recipiesCategory;
    }

    public Long getSliderId() {
        return sliderId;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public void setSliderId(Long sliderId) {
        this.sliderId = sliderId;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (id != null ? !id.equals(recipe.id) : recipe.id != null) return false;
        if (title != null ? !title.equals(recipe.title) : recipe.title != null) return false;
        if (text != null ? !text.equals(recipe.text) : recipe.text != null) return false;
        if (recipiesCategory != null ? !recipiesCategory.equals(recipe.recipiesCategory) : recipe.recipiesCategory != null)
            return false;
        if (imageId != null ? !imageId.equals(recipe.imageId) : recipe.imageId != null) return false;
        return recipiesProductSet != null ? recipiesProductSet.equals(recipe.recipiesProductSet) : recipe.recipiesProductSet == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (recipiesCategory != null ? recipiesCategory.hashCode() : 0);
        result = 31 * result + (imageId != null ? imageId.hashCode() : 0);
        result = 31 * result + (recipiesProductSet != null ? recipiesProductSet.hashCode() : 0);
        return result;
    }
    public int compareTo(Recipe recipeForCompare) {
        if(recipeForCompare.getId() > this.getId()){
            return -1;
        }
        if(recipeForCompare.getId() < this.getId()){
            return 1;
        }

        return 0;
    }

    public Integer getCalculateRecipeRating(){
        Integer resultRating = 0;
        resultRating = getRecipeRatings().stream()
                .mapToInt(recipeRating -> recipeRating.getRating()).sum();

        if (resultRating > 0 ){
            resultRating /= getRecipeRatings().size();
        }

        return  resultRating;
    }

}
