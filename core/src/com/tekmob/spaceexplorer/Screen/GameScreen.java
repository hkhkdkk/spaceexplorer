package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.SpaceExplorer;

import java.util.Iterator;

/**
 * Created by Rahmat Rasyidi Hakim on 11/20/2014.
 */
public class GameScreen extends BaseScreen {

    private SpriteBatch batch;
    private Image background;
    private Skin skin;
    private TextureRegion imgPlane;
    private TextureRegion imgObstacle;
    private TextureRegion imgButtonShield;
    private TextureRegion imgButtonMissile;
    private TextureRegion imgMissilePU;
    private TextureRegion imgShieldPU;
    private TextureRegion imgMissile;
    private TextureRegion imgShield;
    private OrthographicCamera camera;

    private Rectangle plane;
    private Array<Rectangle> obstacle;
    private Array<Rectangle> missile;
    private Array<Rectangle> shield;

    private long lastObstacleSpawnTime = 0, lastMissileSpawnTime = 0, lastShieldSpawnTime = 0;
    private int score = 0;
    private double miles = 0.0;
    private int countShield = 0;
    private int countMissile = 0;
    private float adjustedX;
    private float adjustedY;

    GameState gameState = GameState.Running;


    public GameScreen(SpaceExplorer s){
        super(s);

        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        skin.addRegions(Assets.gameAtlas);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, spaceExplorer.WIDTH, spaceExplorer.HEIGHT);

        Assets.gameMusic.setLooping(true);

        imgPlane = skin.getRegion("ship");
        imgObstacle = skin.getRegion("meteorGrey_big1");
        imgButtonShield = skin.getRegion("shield");
        imgButtonMissile = skin.getRegion("missile");
        imgShieldPU = skin.getRegion("pushield1");
        imgMissilePU = skin.getRegion("pumissile1");
        imgMissile = skin.getRegion("laserRed01");
        imgShield = skin.getRegion("shield1");

        background = new Image(Assets.gameBack);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(background);

        obstacle = new Array<Rectangle>();
        missile = new Array<Rectangle>();
        shield = new Array<Rectangle>();

        resetWorld();
        spawnObstacle();
    }

    private void initPlane(){
        //init plane
        plane = new Rectangle();
        plane.x = spaceExplorer.WIDTH/2;
        plane.y = 120;
        plane.width = imgPlane.getRegionWidth();
        plane.height = imgPlane.getRegionHeight();
    }

    private void resetWorld(){
        score = 0;
        countShield = 0;
        countMissile = 0;
        miles = 0.0;
        obstacle.clear();
        missile.clear();
        shield.clear();
        initPlane();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        drawWorld();
        updateWorld();
        controlinput();
    }

    private void updateWorld(){
        float deltaTime = Gdx.graphics.getDeltaTime();

        if(Gdx.input.justTouched()) {
            if (gameState == GameState.GameOver) {
                gameState = gameState.Running;
                resetWorld();
            }
        }

        spawnEntity();

        Iterator<Rectangle> iter = obstacle.iterator();
        Iterator<Rectangle> iterMissile = missile.iterator();
        Iterator<Rectangle> iterShield = shield.iterator();

        while (iter.hasNext()){
            Rectangle obs = iter.next();
            obs.y -= 350 * deltaTime;

            //hapus obstacle
            if(obs.y + imgObstacle.getRegionHeight() < 0){
                iter.remove();
            }

            //obstacle overlaps dengan plane
            if(obs.overlaps(plane)){
                Assets.hitSound.play();
                iter.remove();
            }

            //obstacle position x dengan tombol missile atau shield
            if(obs.getY() == imgButtonShield.getRegionY() || obs.getY() == imgButtonMissile.getRegionY()){
                iter.remove();
            }

        }

        while(iterShield.hasNext()){
            Rectangle sh = iterShield.next();
            sh.y -= 250 * deltaTime;

            //hapus obstacle
            if(sh.y + imgShieldPU.getRegionHeight() < 0){
                iterShield.remove();
            }

            if(sh.overlaps(plane)){
                countShield++;
                Assets.hitpuSound.play();
                iterShield.remove();
            }
        }

        while(iterMissile.hasNext()){
            Rectangle mis = iterMissile.next();
            mis.y -= 250 * deltaTime;

            //hapus obstacle
            if(mis.y + imgMissilePU.getRegionHeight() < 0){
                iterMissile.remove();
            }

            if(mis.overlaps(plane)){
                countMissile++;
                Assets.hitpuSound.play();
                iterMissile.remove();
            }
        }

    }

    private void spawnEntity(){
        if(TimeUtils.nanoTime() - lastObstacleSpawnTime > MathUtils.random(150000000,200000000)) spawnObstacle();
        if(TimeUtils.nanoTime() - lastMissileSpawnTime > MathUtils.random(1500000000,2000000000)) spawnPUMissile();
        if(TimeUtils.nanoTime() - lastShieldSpawnTime > MathUtils.random(1800000000,2000000000)) spawnPUShield();
    }

    private void drawWorld(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for(Rectangle obs: obstacle){
            batch.draw(imgObstacle, obs.x, obs.y);
        }

        for(Rectangle sh: shield){
            batch.draw(imgShieldPU, sh.x, sh.y);
        }

        for(Rectangle mis: missile){
            batch.draw(imgMissilePU, mis.x, mis.y);
        }
        batch.end();

        batch.begin();
        Assets.spaceHow.draw(batch, miles + " AU", 20, spaceExplorer.HEIGHT-10);
        Assets.spaceHow.draw(batch, score + "", spaceExplorer.WIDTH-200, spaceExplorer.HEIGHT-10);
        batch.draw(imgPlane, plane.x, plane.y);
        batch.draw(imgButtonShield, 20, 10);
        Assets.spaceHow.draw(batch, countShield + "", 140, 50);
        batch.draw(imgButtonMissile, spaceExplorer.WIDTH-120, 10);
        Assets.spaceHow.draw(batch, countMissile + "", spaceExplorer.WIDTH-220, 50);
        batch.end();
    }

    private void controlinput(){
        //maju mundur
        /*adjustedX = Gdx.input.getAccelerometerX()-2f;
        if( adjustedX < - 1.5f ) adjustedX = - 1f;
        else if( adjustedX > 1.5f ) adjustedX = 1f;*/

        if(adjustedX == -1f) plane.y-= 4;
        if(adjustedX == 1f) plane.y+= 4;

        //kiri kanan, y = -2 (left), 0 (still), 2(right)
        adjustedY = Gdx.input.getAccelerometerY();
        if( adjustedY < -1f ) adjustedY = - 1f;
        else if( adjustedY > 1f ) adjustedY = 1f;

        if(adjustedY == -1f) plane.x-= 6;
        if(adjustedY == 1f) plane.x+= 6;

        if (plane.x < 0) plane.x = 0;
        if (plane.x > spaceExplorer.WIDTH - plane.getWidth()) plane.x = spaceExplorer.WIDTH - plane.getWidth();
        if (plane.y < 0) plane.y = 0;
        if (plane.y > spaceExplorer.HEIGHT - plane.getHeight()) plane.y = spaceExplorer.HEIGHT - plane.getHeight();

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            spaceExplorer.getScreenstack().pop();
        }
    }

    private void spawnObstacle(){
        Rectangle obs = new Rectangle();
        obs.x = MathUtils.random(0, spaceExplorer.WIDTH - 101);
        obs.y = spaceExplorer.HEIGHT;
        obs.width = imgObstacle.getRegionWidth();
        obs.height = imgObstacle.getRegionHeight();
        obstacle.add(obs);
        lastObstacleSpawnTime = TimeUtils.nanoTime();
    }

    private void spawnPUMissile(){
        Rectangle mis = new Rectangle();
        mis.x = MathUtils.random(0, spaceExplorer.WIDTH - 51);
        mis.y = spaceExplorer.HEIGHT;
        mis.width = imgMissilePU.getRegionWidth();
        mis.height = imgMissilePU.getRegionHeight();
        missile.add(mis);
        lastMissileSpawnTime = TimeUtils.nanoTime();
    }

    private void spawnPUShield(){
        Rectangle sh = new Rectangle();
        sh.x = MathUtils.random(0, spaceExplorer.WIDTH - 51);
        sh.y = spaceExplorer.HEIGHT;
        sh.width = imgShieldPU.getRegionWidth();
        sh.height = imgShieldPU.getRegionHeight();
        shield.add(sh);
        lastShieldSpawnTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {
        //Assets.gameMusic.play();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        skin.dispose();
        batch.dispose();
        Assets.dispose();
        super.dispose();
    }

    static enum GameState {
        Running, GameOver
    }

}
