import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class NightBackground extends Actor {
    public NightBackground() {
        setImage("diaNOCHE.png");
    }

    /**
     * de igual manera usamos la funcion de esta imagen
     * con la de la otra o la anterior 
     */
    @Override
    protected void addedToWorld(World world) {
        GreenfootImage image = getImage();
        image.scale(world.getWidth(), world.getHeight());
    }
}