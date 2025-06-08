import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class DayBackground extends Actor {
    public DayBackground() {
        setImage("diaDIA.png");
    }

    /**
     * hmmmm... si 
     * Este método se llama cuando el actor es añadido al mundo.
     * Aquí es donde escalaremos la imagen.
     * esta un poco loco 😅
     */
    @Override
    protected void addedToWorld(World world) {
        GreenfootImage image = getImage();
        image.scale(world.getWidth(), world.getHeight());
    }
}