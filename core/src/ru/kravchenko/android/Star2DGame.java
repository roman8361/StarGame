package ru.kravchenko.android;

import com.badlogic.gdx.Game;

import ru.kravchenko.android.screen.MenuScreen;

public class Star2DGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }

}
