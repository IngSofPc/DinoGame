import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class DinoWorld extends World {
    private static final int WORLD_WIDTH = 800;
    private static final int WORLD_HEIGHT = 400;
    private static final int CELL_SIZE = 1;
    
    private static final int ACTS_PER_SECOND = 50; 
    private static final int DAY_NIGHT_CYCLE_SECONDS = 10; 
    private static final int DAY_NIGHT_INTERVAL = DAY_NIGHT_CYCLE_SECONDS * ACTS_PER_SECOND;
    private static final int OBSTACLE_SPAWN_INTERVAL_ACTS = 60; 

    
    private static final int ROCK_OBSTACLE_PROBABILITY_THRESHOLD = 70; 
    private static final int ROCK_OBSTACLE_Y_OFFSET_FROM_BOTTOM = 30; 
    private static final int FALLING_ROCK_X_MARGIN = 50;

    private static final int SCORE_DISPLAY_X = 100;
    private static final int SCORE_DISPLAY_Y = 30;

    private int score;
    private int timer;
    private boolean isDay;
    private DayBackground dayBackground;
    private NightBackground nightBackground;
    private DinoGodzilla player;

    public DinoWorld() {
        super(WORLD_WIDTH, WORLD_HEIGHT, CELL_SIZE);
        prepare();
    }

    private void prepare() {
        score = 0;
        timer = 0;
        isDay = true;

        dayBackground = new DayBackground();
        nightBackground = new NightBackground();
        addObject(dayBackground, getWidth() / 2, getHeight() / 2);

        player = new DinoGodzilla();
       
        addObject(player, 100, getHeight() - 50);

        setPaintOrder(DinoGodzilla.class, RockObstacle.class, FallingFireRock.class, DayBackground.class, NightBackground.class);
        showText("Score: " + score, SCORE_DISPLAY_X, SCORE_DISPLAY_Y);
    }

    public void act() {
        if (Greenfoot.isKeyDown("escape")) { 
            Greenfoot.stop();
        }
        
        timer++;
        score++; 
        handleDayNightCycle();
        spawnObstacles();
        updateScoreDisplay();
    }

    private void handleDayNightCycle() {
        if (timer % DAY_NIGHT_INTERVAL == 0) {
            isDay = !isDay;
            if (isDay) {
                if (nightBackground.getWorld() != null) removeObject(nightBackground);
                addObject(dayBackground, getWidth() / 2, getHeight() / 2);
            } else {
                if (dayBackground.getWorld() != null) removeObject(dayBackground);
                addObject(nightBackground, getWidth() / 2, getHeight() / 2);
            }
        }
    }

    private void spawnObstacles() {
        if (timer % OBSTACLE_SPAWN_INTERVAL_ACTS == 0) {
            int random = Greenfoot.getRandomNumber(100);
            if (random < ROCK_OBSTACLE_PROBABILITY_THRESHOLD) {
                RockObstacle rock = new RockObstacle();
                addObject(rock, getWidth() - 1, getHeight() - ROCK_OBSTACLE_Y_OFFSET_FROM_BOTTOM - (rock.getImage().getHeight() / 2) );
            } else {
                int spawnX = Greenfoot.getRandomNumber(getWidth() - FALLING_ROCK_X_MARGIN) + (FALLING_ROCK_X_MARGIN / 2);
                addObject(new FallingFireRock(), spawnX, 0);
                //FallingFireRock es un error creo, pta (no atencion) 
            }
        }
    }

    private void updateScoreDisplay() {
        showText("Score: " + score, SCORE_DISPLAY_X, SCORE_DISPLAY_Y);
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }

    public void gameOver() {
        showText("Game Over! Final Score: " + score, getWidth() / 2, getHeight() / 2);
        Greenfoot.stop();
    }
}