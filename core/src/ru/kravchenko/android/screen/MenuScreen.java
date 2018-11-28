package ru.kravchenko.android.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private Texture img;
    private Vector2 position;
    private Vector2 speed;
    private Vector2 touchMouse;
    private float stopDotX;

    @Override
    public void show() {
        super.show();
        img = new Texture("dot.png");
        position = new Vector2(0,0);
        speed = new Vector2(0, 0);
        touchMouse = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, position.x , position.y);
        batch.end();
        position.add(speed);
        stopDraw();
        log();
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        touchMouse.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println();
        System.out.println("TOUCHMOUSE**** X = " + touchMouse.x + " Y = " + touchMouse.y);
        stopDotX = touchMouse.x;
        System.out.println();
        correctSpeed();
        return false;
    }

    /**
     * Метод корректировки скорости
     * объекта, по сути управляет перемещением в пространстве.
     */

    public void correctSpeed () {
        if(touchMouse.x != 0) {
            touchMouse.sub(position).nor();
            speed.set(touchMouse);
        }
    }

    /**
     * Метод остановки объекта
     * по маркеру stopDotX.
     */

    public void stopDraw(){
       if (Math.round(position.x) == stopDotX) {
            speed.x = 0;
            speed.y = 0;
            System.out.println("STOP MACHINE!!!");
        }

    }

    /**
     * Метод логер.
     */

    public void log() {
        touchMouse.nor();
        System.out.println(" position.x " + position.x + " position.y = " + position.y);
        System.out.println(" speed.x " + speed.x + " speed.y = " + speed.y);
        System.out.println(" touchMouse.x " + touchMouse.x + " touchMouse.y = " + touchMouse.y);
        System.out.println(" Tm = " + stopDotX);
    }

}
