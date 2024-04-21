import org.example.Car;
import org.example.CarPostgresRepository;
import org.example.CarRepository;
import org.example.CreateCarInput;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class TestCarRepository {
    @ClassRule
    public static DockerComposeContainer<?> environment = new DockerComposeContainer(new File("docker-compose.yml"))
            .withLocalCompose(true);

    private static CarRepository carRepository;

    @BeforeAll
    static void before() {
        environment.start();
        carRepository = new CarPostgresRepository(
            DSL.using(
                TestableDatabaseConnection.getConnection(),
                SQLDialect.POSTGRES)
        );
    }

    @Test
    void testCanInsertNewCar() {
        final CreateCarInput createdCarInput = new CreateCarInput("brand", 0);

        Car createdCar = carRepository.create(createdCarInput);

        assertEquals(createdCar.brand(), createdCarInput.brand());
        assertEquals(createdCar.price(), createdCarInput.price());
    }

    @Test
    void testCannotFetchUnknownCar() {
        final long unknownId = 1000;

        Optional<Car> fetchedCar = carRepository.fetch(unknownId);

        Assertions.assertFalse(fetchedCar.isPresent());
    }

    @Test
    void testCanFetchCarFromExistingId() {
        final CreateCarInput createdCarInput = new CreateCarInput("brand", 1);

        Car createdCar = carRepository.create(createdCarInput);
        Optional<Car> fetchedCar = carRepository.fetch(createdCar.id());

        assertTrue(fetchedCar.isPresent());
        assertEquals(createdCar, fetchedCar.get());
    }

}
