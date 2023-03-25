package com.example.jpa_relacje_26_zadaniekoncowe.recipe;

import com.example.jpa_relacje_26_zadaniekoncowe.category.Category;
import jakarta.persistence.*;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int likes;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Category category;

    public Recipe(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.likes = 0;
        this.category = category;
    }

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", likes=" + likes +
                '}';
    }
}
