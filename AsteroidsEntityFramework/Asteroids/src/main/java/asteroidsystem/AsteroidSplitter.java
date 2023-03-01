package asteroidsystem;

import asteroids.common.Asteroid;
import asteroids.common.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class AsteroidSplitter implements IAsteroidSplitter {
    @Override
    public void createSplitAsteroid(Entity entity, World world) {
        PositionPart otherPos = entity.getPart(PositionPart.class);
        LifePart otherLife = entity.getPart(LifePart.class);
        float radians = otherPos.getRadians();
        int radius = 0;
        float speed = 5;
        int life = otherLife.getLife() - 1;
        if (life == 1) {
            radius = 6;
            speed = (float) Math.random() * 30f + 70f;
        } else if (life == 2) {
            radius = 10;
            speed = (float) Math.random() * 10f + 50f;
        } else if (life <= 0) {
            world.removeEntity(entity);
            return;
        }

        Entity asteroid1 = new Asteroid();

        asteroid1.setRadius(radius);
        float radians1 = radians - 0.5f;

        addValues(entity, world, otherPos, speed, life, asteroid1, radians1);

        Entity asteroid2 = new Asteroid();

        asteroid2.setRadius(radius);
        float radians2 = radians + 0.5f;

        addValues(entity, world, otherPos, speed, life, asteroid2, radians2);

        world.removeEntity(entity);

    }

    private void addValues(Entity entity, World world, PositionPart otherPos, float speed, int life, Entity asteroid1, float radians1) {
        float by1 = (float) sin(radians1) * entity.getRadius() * asteroid1.getRadius();
        float bx1 = (float) cos(radians1) * entity.getRadius() * asteroid1.getRadius();

        PositionPart astPositionPart1 = new PositionPart(otherPos.getX() + bx1, otherPos.getY() + by1, radians1);
        asteroid1.add(new MovingPart(0, 5000, speed, 0));
        asteroid1.add(astPositionPart1);
        asteroid1.add(new LifePart(life));

        world.addEntity(asteroid1);
    }
}
