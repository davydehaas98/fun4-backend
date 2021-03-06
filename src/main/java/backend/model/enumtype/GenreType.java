package backend.model.enumtype;

public enum GenreType {
    ACTION,
    ADVENTURE,
    ANIMATION,
    BIOGRAPHY,
    COMEDY,
    CRIME,
    DOCUMENTARY,
    DRAMA,
    FAMILY,
    FANTASY,
    FILM,
    NOIR,
    HISTORY,
    HORROR,
    MUSIC,
    MUSICAL,
    MYSTERY,
    ROMANCE,
    SCIENCEFICTION,
    SHORT,
    SPORT,
    SUPERHERO,
    THRILLER,
    WAR,
    WESTERN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
