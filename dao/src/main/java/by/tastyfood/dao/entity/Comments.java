package by.tastyfood.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Nikolay on 22.06.2017.
 */
@Entity
@Table(name = "comments" )
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text")
    private  String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "deleted")
    private Integer deleted;

    @ManyToOne()
    @JoinColumn(name = "recipies_id")
    private Recipe recipe;

    @ManyToOne()
    @JoinColumn(name = "users_id")
    private Users user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }

}
