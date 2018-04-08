package com.expos.services;


import com.expos.models.UsersEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void save(UsersEntity user);
    UsersEntity login(String username, String password);
    boolean exists(String username);

//    List<UsersEntity> list();


//    @Autowired
//    private static final SessionFactory ourSessionFactory;
//
//    static {
//        try {
//            ourSessionFactory = new Configuration().
//                    configure("com/expos/hibernate/hibernate.cfg.xml").
//                    buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }
//
//
//    public void print() {
//        ourSessionFactory.getCurrentSession().get(UsersEntity.class, "username");
//    }


}
