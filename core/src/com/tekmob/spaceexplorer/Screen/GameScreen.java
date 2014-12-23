package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.SpaceExplorer;

import java.util.Iterator;

/**
 * Created by Rahmat Rasyidi Hakim on 11/20/2014.
 */
public class GameScreen extends BaseScreen {

    private static final int VELOCITY_PLANE = 7;
    private static final int INIT_X_SHIP = 1280/2;
    private static final int INIT_Y_SHIP = 120;

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
    private Image buttonMissile;
    private Image buttonShield;
    private OrthographicCamera camera;
    private Plane ship;

    private Rectangle rectObstacle;
    private Rectangle rectMissile;
    private Rectangle rectPowerUpMissile;
    private Rectangle rectShield;
    private Rectangle rectPowerUpShield;
    private Rectangle rectShip;

    private Array<Obstacle> obstacles;
    private Array<PUMissile> powerUpMissile;
    private Array<PUShield> powerUpShield;
    private Array<Missile> missile;

    private long lastObstacleSpawnTime = 0, lastMissileSpawnTime = 0, lastShieldSpawnTime = 0;
    private int score = 0;
    private double miles = 0.0;
    private int countShield = 0;
    private int countMissile = 0;
    private float adjustedY;
    private int obstacleVelocity = 0;
    private int powerupVelocity = 0;
    private int tempScore = 0;
    private float timer = 0;
    private boolean drawMissile = false;
    private boolean gameOver = false, activedMissile = false, activedShield = false;


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
        background.setSize(SpaceExplorer.WIDTH, SpaceExplorer.HEIGHT);

        rectObstacle = new Rectangle();
        rectMissile = new Rectangle();
        rectPowerUpMissile = new Rectangle();
        rectShield = new Rectangle();
        rectPowerUpShield = new Rectangle();
        rectShip = new Rectangle();
        rectShield = new Rectangle();

        obstacles = new Array<Obstacle>();
        powerUpMissile = new Array<PUMissile>();
        powerUpShield = new Array<PUShield>();
        missile = new Array<Missile>();

        //setup button missile
        buttonMissile = new Image(imgButtonMissile);
        buttonMissile.setPosition(spaceExplorer.WIDTH-120,10);

        buttonShield = new Image(imgButtonShield);
        buttonShield.setPosition(20,10);

        stage.addActor(background);
        stage.addActor(buttonMissile);
        stage.addActor(buttonShield);

        resetWorld();
        createButtonPowerUp();
        spawnObstacle();
    }

    private void initShip(){
        ship = new Plane(INIT_X_SHIP, INIT_Y_SHIP, imgPlane);
    }

    private void resetWorld(){
        score = 0;
        countShield = 0;
        countMissile = 0;
        miles = 0.0;
        timer = 0;
        tempScore = 0;
        activedMissile = false;
        activedShield = false;

        obstacles.clear();
        powerUpMissile.clear();
        powerUpShield.clear();
        initShip();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        drawWorld();
        updateWorld();
        onBackScreen();
    }

    private void updateWorld(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        timer += deltaTime;

        rectShip.set(ship.position.x, ship.position.y, ship.image.getRegionWidth(), ship.image.getRegionHeight());
        rectShield.set(ship.position.x, ship.position.y, imgShield.getRegionWidth(), imgShield.getRegionHeight());

        if(!gameOver){
            powerupVelocity = 250;
            obstacleVelocity = 300;

            if (timer >= 1) {
                score++;
                tempScore++;
                timer -= 1;
            }

            if(tempScore >= 60){
                miles++;
                tempScore = 0;
            }

            controlInput();
        }

        if (gameOver) {
            powerupVelocity = 0;
            obstacleVelocity = 0;
            if(Gdx.input.justTouched()) {
                gameOver = false;
                resetWorld();
            }
        }

        spawnEntity();

        Iterator<Obstacle> iterObstacle = obstacles.iterator();
        Iterator<PUMissile> iterPowerUpMissile = powerUpMissile.iterator();
        Iterator<PUShield> iterPowerUpShield = powerUpShield.iterator();
        Iterator<Missile> iterMissile = missile.iterator();

        while (iterObstacle.hasNext()){
            Obstacle o = iterObstacle.next();
            o.position.y -= obstacleVelocity * deltaTime;
            rectObstacle.set(o.position.x, o.position.y, o.image.getRegionWidth(), o.image.getRegionHeight());

            if(o.position.y + o.image.getRegionHeight() < 0){
                iterObstacle.remove();
            }

            if(rectObstacle.overlaps(rectShip)){
                if(!activedShield){
                    Assets.hitSound.play();
                    iterObstacle.remove();
                    gameOver = true;
                }
                if(activedShield){
                    iterObstacle.remove();
                    activedShield = false;
                }
            }

            while (iterMissile.hasNext()){
                Missile mis = iterMissile.next();
                mis.position.y += powerupVelocity * deltaTime;

                if(mis.position.y > spaceExplorer.HEIGHT){
                    iterMissile.remove();
                }

                if(mis.getBounds().overlaps(o.getBounds())){
                    iterMissile.remove();
                    iterObstacle.remove();
                }

                if(mis == null){
                    drawMissile = false;
                }
            }
        }

        while (iterPowerUpMissile.hasNext()){
            PUMissile mis = iterPowerUpMissile.next();
            mis.position.y -= powerupVelocity * deltaTime;
            rectPowerUpMissile.set(mis.position.x, mis.position.y, mis.image.getRegionWidth(), mis.image.getRegionHeight());

            if(mis.position.y + mis.image.getRegionHeight() < 0){
                iterPowerUpMissile.remove();
            }

            if(rectPowerUpMissile.overlaps(rectShip)){
                countMissile++;
                score += score + 2;
                Assets.hitpuSound.play();
                iterPowerUpMissile.remove();
            }
        }

        while (iterPowerUpShield.hasNext()){
            PUShield sh = iterPowerUpShield.next();
            sh.position.y -= powerupVelocity * deltaTime;
            rectPowerUpShield.set(sh.position.x, sh.position.y, sh.image.getRegionWidth(), sh.image.getRegionHeight());

            if(sh.position.y + sh.image.getRegionHeight() < 0){
                iterPowerUpShield.remove();
            }

            if(rectPowerUpShield.overlaps(rectShip)){
                countShield++;
                score += score + 2;
                Assets.hitpuSound.play();
                iterPowerUpShield.remove();
            }
        }

    }

    private void spawnEntity(){
        if(TimeUtils.nanoTime() - lastObstacleSpawnTime > MathUtils.random(150000000,200000000)) spawnObstacle();
        if(TimeUtils.nanoTime() - lastMissileSpawnTime > MathUtils.random(1500000000,2000000000)) spawnPUMissile();
        if(TimeUtils.nanoTime() - lastShieldSpawnTime > MathUtils.random(1800000000,2000000000)) spawnPUShield();
        if(activedMissile) {
            spawnMisille();
            drawMissile = true;
            activedMissile = false;
        }
    }

    private void drawWorld(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

            for(Obstacle obs:obstacles){
                batch.draw(obs.image, obs.position.x, obs.position.y);
            }

            for(PUMissile mis:powerUpMissile){
                batch.draw(mis.image, mis.position.x, mis.position.y);
            }

            for(PUShield sh:powerUpShield){
                batch.draw(sh.image, sh.position.x, sh.position.y);
            }

            if(drawMissile) {
                for (Missile mis : missile) {
                    batch.draw(mis.image, mis.position.x, mis.position.y);
                }
            }

        batch.end();

        batch.begin();
            Assets.spaceHow.draw(batch, miles + " AU", 20, spaceExplorer.HEIGHT - 10);
            Assets.spaceHow.draw(batch, score + "", spaceExplorer.WIDTH-200, spaceExplorer.HEIGHT-10);
            batch.draw(imgPlane, ship.position.x, ship.position.y);
            if(activedShield)
            batch.draw(imgShield, ship.position.x - 10, ship.position.y);
            buttonShield.draw(batch,1f);
            buttonMissile.draw(batch,1f);
            Assets.spaceHow.draw(batch, countShield + "", 140, 50);
            Assets.spaceHow.draw(batch, countMissile + "", spaceExplorer.WIDTH-200, 50);
            if(gameOver)
            batch.draw(Assets.gameOver, Gdx.graphics.getWidth()/2 - Assets.gameOver.getWidth()/2, Gdx.graphics.getHeight()/2 - Assets.gameOver.getHeight()/2);
        batch.end();
    }

    private void controlInput(){
        //maju mundur
        /*adjustedX = Gdx.input.getAccelerometerX()-2f;
        if( adjustedX < - 1.5f ) adjustedX = - 1f;
        else if( adjustedX > 1.5f ) adjustedX = 1f;*/

        adjustedY = Gdx.input.getAccelerometerY();
        if( adjustedY < -1f ) ship.position.x -= Math.abs(adjustedY) + VELOCITY_PLANE;
        else if( adjustedY > 1f ) ship.position.x += Math.abs(adjustedY) + VELOCITY_PLANE;

        /*
        if(adjustedY == -1f) plane.x-= (Math.abs(adjustedY) + VELOCITY_PLANE);
        if(adjustedY == 1f) plane.x+= adjustedY + VELOCITY_PLANE;*/

        if (ship.position.x < 0) ship.position.x = 0;
        if (ship.position.x > spaceExplorer.WIDTH - ship.image.getRegionWidth()) ship.position.x = spaceExplorer.WIDTH - ship.image.getRegionWidth();
        if (ship.position.y < 0) ship.position.y = 0;
        if (ship.position.y > spaceExplorer.HEIGHT - ship.image.getRegionHeight()) ship.position.y = spaceExplorer.HEIGHT - ship.image.getRegionHeight();
    }

    private void createButtonPowerUp(){
        buttonMissile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (countMissile != 0 && !activedMissile) {
                    countMissile--;
                    activedMissile = true;
                }
            }
        });

        buttonShield.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(countShield != 0 && !activedShield) {
                    countShield--;
                    activedShield = true;
                }
            }
        });
    }

    private void spawnObstacle(){
        obstacles.add(new Obstacle(MathUtils.random(0, spaceExplorer.WIDTH - 101),spaceExplorer.HEIGHT, imgObstacle));
        lastObstacleSpawnTime = TimeUtils.nanoTime();
    }

    private void spawnPUMissile(){
        powerUpMissile.add(new PUMissile(MathUtils.random(0, spaceExplorer.WIDTH - 51), spaceExplorer.HEIGHT, imgMissilePU));
        lastMissileSpawnTime = TimeUtils.nanoTime();
    }

    private void spawnPUShield(){
        powerUpShield.add(new PUShield(MathUtils.random(0, spaceExplorer.WIDTH - 51), spaceExplorer.HEIGHT, imgShieldPU));
        lastShieldSpawnTime = TimeUtils.nanoTime();
    }

    private void spawnMisille(){
        missile.add(new Missile(ship.position.x+(ship.image.getRegionWidth()/2), ship.position.y+10+(ship.image.getRegionHeight()/2), imgMissile));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        //Assets.gameMusic.play();
    }

    @Override
    public void dispose() {
        skin.dispose();
        batch.dispose();
        super.dispose();
    }

    static class Obstacle{
        Vector2 position = new Vector2();
        TextureRegion image;

        public Obstacle(float x, float y, TextureRegion image){
            this.position.x = x;
            this.position.y = y;
            this.image = image;
        }
        public Rectangle getBounds(){
            return new Rectangle(position.x, position.y, image.getRegionWidth(), image.getRegionHeight());
        }
    }

    static class Missile{
        Vector2 position = new Vector2();
        TextureRegion image;

        public Missile(float x, float y, TextureRegion image){
            this.position.x = x;
            this.position.y = y;
            this.image = image;
        }

        public Rectangle getBounds(){
            return new Rectangle(position.x, position.y, image.getRegionWidth(), image.getRegionHeight());
        }
    }

    static class PUMissile{
        Vector2 position = new Vector2();
        TextureRegion image;

        public PUMissile(float x, float y, TextureRegion image){
            this.position.x = x;
            this.position.y = y;
            this.image = image;
        }

        public Rectangle getBounds(){
            return new Rectangle(position.x, position.y, image.getRegionWidth(), image.getRegionHeight());
        }
    }

    static class PUShield{
        Vector2 position = new Vector2();
        TextureRegion image;

        public PUShield(float x, float y, TextureRegion image){
            this.position.x = x;
            this.position.y = y;
            this.image = image;
        }

        public Rectangle getBounds(){
            return new Rectangle(position.x, position.y, image.getRegionWidth(), image.getRegionHeight());
        }
    }

    static class Plane{
        Vector2 position = new Vector2();
        TextureRegion image;

        public Plane(float x, float y, TextureRegion image){
            this.position.x = x;
            this.position.y = y;
            this.image = image;
        }

        public Rectangle getBounds(){
            return new Rectangle(position.x, position.y, image.getRegionWidth(), image.getRegionHeight());
        }
    }
}
