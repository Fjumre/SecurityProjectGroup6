//package app;
//
//import app.config.HibernateConfig;
//import app.dao.EventDAO;
//import app.dao.UserDAO;
//import app.model.Event;
//import app.model.Location;
//import app.model.User;
//import jakarta.persistence.EntityManagerFactory;
//
//import javax.swing.text.html.parser.Entity;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//public class Main2 {
//
//    public static void main(String[] args) {
//        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
//        UserDAO dao = new UserDAO(emf);
//        EventDAO eventDAO = new EventDAO(emf);
//
//User user1 = dao.createUser("f", "1244", "gg@ddddd.dk", 5533112);
//        User user = dao.createUser("ffhhf", "1234", "aaa@g.dk", 44545256);
//
//        System.out.println(user.getName());
//        Event event = new Event
//                ("Yoga", "Yoga for beginners",
//                        LocalDateTime.of(LocalDate.of(2021, 10, 10),
//                                LocalTime.of(10, 0)), LocalTime.of(10, 0),
//                        60, 20, Location.Aalborg, "Bibi", 100,
//                        "Active", null);
//        Event createdEvent = eventDAO.create(event);
//
//        try {
//            User verifiedUser = dao.verifyUser("fhhff", "1234");
//
//            User updatedUser = dao.addRoleToUser("fhhff", "instructor");
//            System.out.println("Role added to user: " + updatedUser.getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//
//
//        }
//    }
//
//
//}
