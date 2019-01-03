package com.hamilton.nathan.nhamiltonfinal;

public interface GameUpdate
{
    void setCakeCount(int cakeCount);
    void nextLevel(int level);
    void setLevel(int level);
    void win();
    void lose();
    //void sound(String sound);
}
