package ru.kravchenko.android.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.utils.Regions;

public abstract class Sprite extends Rectangle {

    protected float angle;

    protected float scale = 1f;

    protected TextureRegion[] regions;

    protected int frame;

    private boolean isDestroyed;

    public Sprite() {

    }

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public Sprite(TextureRegion region, int rows, int cols, int frame) {
        regions = Regions.split(region, rows, cols, frame);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame], // текущий регион
                getLeft(), getBottom(), // точка отрисовки
                halfWidth, halfHeight, // точка вращений
                getWidth(), getHeight(), // ширина и выстота
                scale, scale, // масштаб по оси Х и У
                angle // угол вращения
        );
    }

    public void setHeigthProportion(float heigth) {
        setHeight(heigth);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(heigth * aspect);
    }

    public void resize(Rectangle worldBounds) { }

    public void update(float delta) { }

    public boolean touchDown(Vector2 touch, int pointer) { return false; }

    public boolean touchUp(Vector2 touch, int pointer) { return false; }

    public boolean touchDragged(Vector2 touch, int pointer) { return false; }

    public float getAngle() { return angle; }

    public void setAngle(float angle) { this.angle = angle; }

    public float getScale() { return scale; }

    public void setScale(float scale) { this.scale = scale; }

    public boolean isDestroyed() { return isDestroyed; }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

}
