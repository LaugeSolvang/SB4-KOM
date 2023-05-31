package collisionsystem;

import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.data.entityparts.LifePart;
import common.data.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class CollisionControllerTest {

    private GameData gameData;
    private World world;
    private CollisionController collisionController;

    private Entity firstEntity;
    private Entity secondEntity;

    private PositionPart firstPosition;
    private PositionPart secondPosition;

    private LifePart lifePart;
    private LifePart lifePart2;

    @BeforeEach
    public void setUp() {
        gameData = mock(GameData.class);
        world = mock(World.class);
        collisionController = new CollisionController();

        firstEntity = mock(Entity.class);
        secondEntity = mock(Entity.class);

        when(world.getEntities()).thenReturn(Arrays.asList(firstEntity, secondEntity));
        when(firstEntity.getID()).thenReturn("1");
        when(secondEntity.getID()).thenReturn("2");

        firstPosition = mock(PositionPart.class);
        when(firstEntity.getPart(PositionPart.class)).thenReturn(firstPosition);

        secondPosition = mock(PositionPart.class);
        when(secondEntity.getPart(PositionPart.class)).thenReturn(secondPosition);

        lifePart = mock(LifePart.class);
        when(firstEntity.getPart(LifePart.class)).thenReturn(lifePart);

        lifePart2 = mock(LifePart.class);
        when(secondEntity.getPart(LifePart.class)).thenReturn(lifePart2);
    }

    @Test
    public void testProcess() {
        setEntityPositionAndRadius(firstEntity, firstPosition, 0.0f, 0.0f, 1.0F);
        setEntityPositionAndRadius(secondEntity, secondPosition, 0.0f, 0.0f, 1.0F);

        collisionController.process(gameData, world);

        verify(firstEntity, times(2)).getPart(PositionPart.class);
        verify(secondEntity, times(2)).getPart(PositionPart.class);
    }

    @Test
    public void testEntitiesColliding() {
        setEntityPositionAndRadius(firstEntity, firstPosition, 0.0f, 0.0f, 1.0F);
        setEntityPositionAndRadius(secondEntity, secondPosition, 0.0f, 0.0f, 1.0F);
        firstEntity.setRadius(1.0F);
        secondEntity.setRadius(1.0F);

        when(lifePart.getLife()).thenReturn(2);
        when(lifePart2.getLife()).thenReturn(2);

        collisionController.process(gameData, world);

        verify(lifePart, times(1)).setLife(anyInt());
        verify(lifePart, times(1)).setIsHit(anyBoolean());
        verify(world, times(0)).removeEntity(any(Entity.class));
    }

    @Test
    public void testEntitiesNotColliding() {
        setEntityPositionAndRadius(firstEntity, firstPosition, 2f, 2f, 1F);
        setEntityPositionAndRadius(secondEntity, secondPosition, 0f, 0f, 1F);
        setEntityLifePart(lifePart,1);
        setEntityLifePart(lifePart2,1);

        collisionController.process(gameData, world);

        verify(lifePart, never()).setIsHit(anyBoolean());
    }

    private void setEntityPositionAndRadius(Entity entity, PositionPart positionPart, float x, float y, float radius) {
        when(positionPart.getX()).thenReturn(x);
        when(positionPart.getY()).thenReturn(y);
        when(entity.getRadius()).thenReturn(radius);
    }
    private void setEntityLifePart(LifePart lifePart, int life) {
        when(lifePart.getLife()).thenReturn(life);
    }
}