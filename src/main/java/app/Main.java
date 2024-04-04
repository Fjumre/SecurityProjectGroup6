package app;

import app.config.ApplicationConfig;
import app.config.HibernateConfig;
import app.controllers.ISecurityController;
import app.controllers.SecurityController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.RouteRole;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.get;

public class Main {

    private static ISecurityController securityController = new SecurityController();
    private static ObjectMapper om = new ObjectMapper();
    public static void main(String[] args) {

        startServer(7007);

    }

    public static void startServer(int port){

        ObjectMapper om = new ObjectMapper();
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig
                .initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .setRoute(getSecurityRoutes())
                .setupAccessManager()
                .setRoute(getSecuredRoutes())
                .setRoute(() -> {


                })
                .checkSecurityRoles();
    }

<<<<<<< Updated upstream
=======
    public static void getRoutes(){
        before(securityController.authenticate());
        path("/events", () -> {
            path("/", () -> {
                before(securityController.authenticate());
                get("", eventController.getAllEvents(), Role.ANYONE);
                get("{id}", eventController.getEventById(), Role.ANYONE);
                post("create", eventController.createEvent(), Role.ANYONE);
                put("update/{id}", eventController.updateEvent(), Role.ANYONE);
                delete("delete/{id}", eventController.deleteEvent(), Role.ANYONE);
                get("allregistrations/{event_id}", eventController.getAllRegistrationsForEvent(), Role.ANYONE);
                get("registration/{event_id}", eventController.getRegistrationById(), Role.ANYONE);
                put("registrations/{event_id}", eventController.getRegistrationById(), Role.ANYONE);
                post("eventregistration/{event_id}", eventController.registerUserForEvent(), Role.ANYONE);
                post("removeuserevent/{event_id}", eventController.removeUserFromEvent(), Role.ANYONE);
                get("error", ctx -> {
                    throw new Exception(String.valueOf(ApplicationConfig.getInstance().setExceptionHandling()));
                });
            });
    });
    }

    public static void getUserRoutes(){
        before(securityController.authenticate());
        path("/user", () -> {
            path("/", () -> {
            before(securityController.authenticate());
            get("/all", userController.getAllUsers(), Role.ANYONE);
            get("/{id}", userController.getUserById(), Role.ANYONE);
            post("/create", userController.createUser(), Role.ANYONE);
            put("/update/{id}", userController.updateUser(), Role.ANYONE);
            delete("/delete/{id}", userController.deleteUser(), Role.ANYONE);
            post("/logout", userController.logout(), Role.USER, Role.ADMIN, Role.INSTRUCTOR);
            get("/error", ctx -> {
                throw new Exception(String.valueOf(ApplicationConfig.getInstance().setExceptionHandling()));
            });
        });
    });
    }
>>>>>>> Stashed changes

    public static void closeServer () {
        ApplicationConfig.getInstance().stopServer();
    }


    public static EndpointGroup getSecurityRoutes() {
        return ()->{
            path("/auth", ()->{
                post("/login", securityController.login(),Role.ANYONE);
                post("/register", securityController.register(),Role.ANYONE);
                post("/resetpassword", securityController.resetOfPassword(), Role.USER, Role.ADMIN, Role.INSTRUCTOR);
            });
        };
    }

    public static EndpointGroup getSecuredRoutes(){
        return ()->{
            path("/protected", ()->{
                before(securityController.authenticate());
                get("/user",(ctx)->ctx.json(om.createObjectNode().put("msg",  "Hello from USER Protected")),Role.USER);
                get("/instructor",(ctx)->ctx.json(om.createObjectNode().put("msg",  "Hello from INSTRUCTOR Protected")),Role.INSTRUCTOR);
                get("/admin",(ctx)->ctx.json(om.createObjectNode().put("msg",  "Hello from ADMIN Protected")),Role.ADMIN);
            });
        };
    }

    public enum Role implements RouteRole {
        ANYONE,
        USER,
        ADMIN,
        INSTRUCTOR

    }
}