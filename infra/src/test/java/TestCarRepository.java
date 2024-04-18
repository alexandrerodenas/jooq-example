import org.example.Car;
import org.example.CarPostgresRepository;
import org.example.CarRepository;
import org.example.CreateCar;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class TestCarRepository {
    @ClassRule
    public static DockerComposeContainer<?> environment =
            new DockerComposeContainer(new File("docker-compose.yml"))
                    .withLocalCompose(true);

    @BeforeAll
    static void before(){
        environment.start();
    }

    @Test
    void testCanInsertNewCar() {
        DSLContext dslContext = DSL.using(
                TestableDatabaseConnection.getConnection(),
                SQLDialect.POSTGRES
        );

        final CreateCar createCar = new CreateCar("brand", 0);
        final CarRepository carRepository = new CarPostgresRepository(dslContext);

        Car car = carRepository.create(createCar);

        assertEquals(createCar.brand(), car.brand());
        assertEquals(createCar.price(), car.price());
    }

}
