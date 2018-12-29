package com.fun4.checkers.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Movie {

  @Id
  @GeneratedValue
  private long id;
  @Column(unique = true, nullable = false)
  private String title;
  private Date releaseDate;

  @ManyToMany
  private Collection<Genre> genres = new ArrayList<>();

  public Movie(String title, Date releaseDate, Collection<Genre> genres) {
    this.title = title;
    this.releaseDate = releaseDate;
    this.genres = genres;
  }
}
