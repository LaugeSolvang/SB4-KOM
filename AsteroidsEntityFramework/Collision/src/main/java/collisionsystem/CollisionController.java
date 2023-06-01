package collisionsystem;

import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.data.entityparts.LifePart;
import common.data.entityparts.PositionPart;

import common.services.IPostEntityProcessingService;

public class CollisionController implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        // Loop through all pairs of entities in the world
        for (Entity firstEntity : world.getEntities()) {
            for (Entity secondEntity : world.getEntities()) {
                // Skip the iteration if the entities are identical
                if (firstEntity.getID().equals(secondEntity.getID())) {
                    continue;
                }
                // Check for collision between the two entities
                if (isColliding(firstEntity, secondEntity)) {
                    handleCollision(firstEntity, secondEntity, world);
                }
            }
        }
    }

    boolean isColliding(Entity firstEntity, Entity secondEntity) {
        // Get position parts and calculate distance between centers
        PositionPart firstPosition = firstEntity.getPart(PositionPart.class);
        PositionPart secondPosition = secondEntity.getPart(PositionPart.class);
        float dx = firstPosition.getX() - secondPosition.getX();
        float dy = firstPosition.getY() - secondPosition.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        // Check if the distance is less than the sum of the radii
        float sumRadii = firstEntity.getRadius() + secondEntity.getRadius();
        return (distance < sumRadii);
    }

    void handleCollision(Entity firstEntity, Entity secondEntity, World world) {
        // Reduce life of the first entity and mark as hit
        LifePart lifePart = firstEntity.getPart(LifePart.class);
        if (lifePart.getLife() > 0) {
            lifePart.setLife(lifePart.getLife() - 1);
            lifePart.setIsHit(true);
            if (lifePart.getLife() <= 0) {
                // Remove entity from the world if its life is 0 or less
                world.removeEntity(firstEntity);
            }
        }
    }
}
