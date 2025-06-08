import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class DinoGodzilla extends Actor {
    private static final int GRAVITY = 1;
    private static final int JUMP_STRENGTH = 18;
    private int ySpeed = 0;
    private boolean onGround = false;
    private int groundLevel;

    public DinoGodzilla() {
        setImage("dinosaurio.png"); 
        escalarImagen(0.15);
    }

    private void escalarImagen(double factorEscala) {
        GreenfootImage imagen = getImage();
        int nuevoAncho = (int) (imagen.getWidth() * factorEscala);
        int nuevoAlto = (int) (imagen.getHeight() * factorEscala);
        imagen.scale(nuevoAncho, nuevoAlto);
    }

    @Override
    protected void addedToWorld(World world) {
        groundLevel = world.getHeight() - (getImage().getHeight() / 2) - 5;
    }

    public void act() {
        handleKeyPresses();
        applyGravity();
        checkFall();
        checkCollisions();
    }

    private void handleKeyPresses() {
        if (onGround && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("up"))) {
            ySpeed = -JUMP_STRENGTH;
            onGround = false;
        }
    }

    private void applyGravity() {
        setLocation(getX(), getY() + ySpeed);
        ySpeed += GRAVITY;
    }

    private void checkFall() {
        if (getY() >= groundLevel) {
            ySpeed = 0;
            setLocation(getX(), groundLevel);
            onGround = true;
        } else {
            onGround = false;
        }
    }

    private void checkCollisions() {
        int halfWidth = getImage().getWidth() / 2;
        int halfHeight = getImage().getHeight() / 2;
        Actor rockAtFeetFront = getOneObjectAtOffset(halfWidth - 5, halfHeight - 2, RockObstacle.class);
        Actor rockAtFeetCenter = getOneObjectAtOffset(0, halfHeight - 2, RockObstacle.class);
        Actor fireRock = getOneIntersectingObject(FallingFireRock.class);
        if (rockAtFeetFront != null || rockAtFeetCenter != null || fireRock != null) {
            DinoWorld world = (DinoWorld) getWorld();
            world.gameOver();
        }
    }
}