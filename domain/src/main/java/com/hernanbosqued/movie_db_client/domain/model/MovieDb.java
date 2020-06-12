package com.hernanbosqued.movie_db_client.domain.model;

import com.google.gson.annotations.SerializedName;

public class MovieDb{

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("popularity")
    public float popularity;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("adult")
    public boolean adult;

    //@SerializedName("belongs_to_collection")
    //public Collection belongsToCollection;

    @SerializedName("budget")
    public long budget;

    //@SerializedName("genres")
    //public List<Genre> genres;

    @SerializedName("homepage")
    public String homepage;

    @SerializedName("overview")
    public String overview;

    // todo still there??
    @SerializedName("imdb_id")
    public String imdbID;

    @SerializedName("original_language")
    public String originalLanguage;

    //@SerializedName("production_companies")
    //public List<ProductionCompany> productionCompanies;

    //@SerializedName("production_countries")
    //ivate List<ProductionCountry> productionCountries;

    @SerializedName("revenue")
    public long revenue;

    @SerializedName("runtime")
    public int runtime;

    //@SerializedName("spoken_languages")
    //public List<Language> spokenLanguages;

    @SerializedName("tagline")
    public String tagline;

    @SerializedName("rating")
    public float userRating;

    @SerializedName("vote_average")
    public float voteAverage;

    @SerializedName("vote_count")
    public int voteCount;

    @SerializedName("status")
    public String status;

    // Appendable responses
    //@SerializedName("alternative_titles")
    //public MoviesAlternativeTitles alternativeTitles;

    //@SerializedName("credits")
    //public Credits credits;

    //@SerializedName("images")
    //public MovieImages images;

    // note: it seems to be a flaw in their api, because a paged result would be more consistent
    //@SerializedName("keywords")
    //public MovieKeywords keywords;

    //@SerializedName("release_dates")
    //public TmdbMovies.ReleaseInfoResults releases;

    //@SerializedName("videos")
    //public Video.Results videos;

    //@SerializedName("translations")
    //public MovieTranslations translations;

//    @SerializedName("similar")
//    public ResultsPage<MovieDb> similarMovies;
//
//    @SerializedName("recommendations")
//    public ResultsPage<MovieDb> recommendedMovies;
//
//    @SerializedName("reviews")
//    public ResultsPage<Reviews> reviews;
//
//    @SerializedName("lists")
//    public ResultsPage<MovieList> lists;
}
