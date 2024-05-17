package com.example.bean;

import com.example.entity.Game;
//TODO maybe delete this class, not used
public class GameComplete {

    private Game game;
    private Long pageCount;

    public GameComplete(Game game, Long pageCount){
        this.game = game;
        this.pageCount = pageCount;
    }

    public Game getBook() {
        return game;
    }

    public void setBook(Game game) {
        this.game = game;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }
}