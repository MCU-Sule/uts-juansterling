package com.example.UTS_2072009.Model;

public class WatchList {
    private int idwatchlist;
    private int lastwatch;
    private int favourite;

    public String getDurasi () {
        return String.valueOf(lastwatch) + '/'+ movie_idmovie.getDurasi();
    }

    private Movie movie_idmovie;
    private User user_iduser;

    public int getIdwatchlist() {
        return idwatchlist;
    }

    public void setIdwatchlist(int idwatchlist) {
        this.idwatchlist = idwatchlist;
    }

    public int getLastwatch() {
        return lastwatch;
    }

    public void setLastwatch(int lastwatch) {
        this.lastwatch = lastwatch;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public Movie getMovie_idmovie() {
        return movie_idmovie;
    }

    public void setMovie_idmovie(Movie movie_idmovie) {
        this.movie_idmovie = movie_idmovie;
    }

    public User getUser_iduser() {
        return user_iduser;
    }

    public void setUser_iduser(User user_iduser) {
        this.user_iduser = user_iduser;
    }

    public WatchList(int idwatchlist, int lastwatch, int favourite, Movie movie_idmovie, User user_iduser) {
        this.idwatchlist = idwatchlist;
        this.lastwatch = lastwatch;
        this.favourite = favourite;
        this.movie_idmovie = movie_idmovie;
        this.user_iduser = user_iduser;
    }
    public boolean getBoolFav(){
        boolean bool;
        if(favourite == 1){
            bool = true;
        }else{
            bool = false;
        }
        return bool;
    }
}
