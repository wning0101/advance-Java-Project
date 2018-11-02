package edu.pdx.cs410J.tennis;

public class Player {
    private int score;
    private int point;

    Player(int score)
    {
        this.score = score;
    }

    public int getScore(){
        if (this.point == 1)
            this.score = 15;
        else if (this.point == 2)
            this.score = 30;
        else if (this.point == 3)
            this.score = 40;
        else if (this.point == 5)
            this.score = 45;
        else if (this.point == 4)
            this.score = 50;
        else
            this.score = 0;

        return this.score;
    }

    public void winScore(){
        this.point++;
    }
    public void winAdv(){
        this.point +=2;
    }
    public void loseAdv(){
        this.point -=2;
    }
}
