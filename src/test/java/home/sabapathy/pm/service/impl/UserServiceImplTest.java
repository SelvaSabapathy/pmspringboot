package home.sabapathy.pm.service.impl;

import home.sabapathy.pm.service.api.UserService;
import home.sabapathy.pm.service.entity.User;
import home.sabapathy.pm.service.repository.ParentTaskRepository;
import home.sabapathy.pm.service.repository.ProjectRepository;
import home.sabapathy.pm.service.repository.TaskRepository;
import home.sabapathy.pm.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

@Slf4j

@ActiveProfiles("test")

@SpringBootTest
@MockBean({UserRepository.class, ProjectRepository.class, ParentTaskRepository.class, TaskRepository.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private static User user;
    private static List<User> users;

    @BeforeAll
    @DisplayName("Setup Before any test")
    public void beforeAnyTest() {
        assertThat("userRepository is NOT injected", userRepository, is(notNullValue()));
        assertThat("userService is NOT injected", userService, is(notNullValue()));

        user = new User(1, 1, "Selva", "Sabapathy", null, null);
        users = Arrays.asList(new User[]{user});
    }

    @BeforeEach
    @DisplayName("Setup Before every test")
    public void beforeEachTest() {}

    @AfterEach
    @DisplayName("Tear down after every test")
    public void afterEachTest()  {}

    @AfterAll
    @DisplayName("Tear down after all tests")
    public void afterClass() {
        log.debug("Nothing a resource hog to tear down - will garbage collect automatically!");
    }

    @Test
    @DisplayName("UserServiceImpl - Get an User")
    public void testGet() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        assertThat("Get User", userService.get(1L), is(equalTo(user)));
    }

    @Test
    @DisplayName("UserServiceImpl - Get all USERs")
    public void testGetAll() {
        when(userRepository.findAll()).thenReturn(users);
        assertThat("Get All Users", userService.getAll(), is(equalTo(users)));
    }

    @Test
    @Disabled
    @DisplayName("UserServiceImpl - Get an User")
    public void testAdd() {
    }

    @Test
    @Disabled
    @DisplayName("UserServiceImpl - Edit a User")
    public void testEdit() {
    }

    @Test
    @Disabled
    @DisplayName("UserServiceImpl - Delete a User")
    public void testDelete() {
    }

    @Test
    @Disabled
    @DisplayName("UserServiceImpl - Get a User (manager)")
    public void testGetManager() {
    }

    @Test
    @Disabled
    @DisplayName("UserServiceImpl - Get USERs with Unique employee Id")
    public void testAllWithUniqueEmployeeId() {
    }
}
