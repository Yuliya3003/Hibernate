package homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Program {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()) {

            // Добавление данных
            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();
                Course course = new Course("Java Programming", 30);
                session.save(course);
                session.getTransaction().commit();
                System.out.println("Course inserted: " + course);
            }

            // Чтение данных
            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();
                Course course = session.get(Course.class, 1); // Получение курса с ID = 1
                session.getTransaction().commit();
                System.out.println("Course read: " + course);
            }

            // Обновление данных
            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();
                Course course = session.get(Course.class, 1); // Получение курса с ID = 1
                if (course != null) {
                    course.setTitle("Advanced Java Programming");
                    session.update(course);
                }
                session.getTransaction().commit();
                System.out.println("Course updated: " + course);
            }

             // Удаление данных
            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();
                Course course = session.get(Course.class, 1); // Получение курса с ID = 1
                if (course != null) {
                    session.delete(course);
                }
                session.getTransaction().commit();
                System.out.println("Course deleted: " + course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

