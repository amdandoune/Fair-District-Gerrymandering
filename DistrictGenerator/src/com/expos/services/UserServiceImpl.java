package com.expos.services;

import com.expos.dao.UserDao;
import com.expos.models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ResourceLoader resourceLoader;

    @Transactional
    public List<UsersEntity> getUserList() {
        return userDao.list();
    }

    @Transactional
    public void save(UsersEntity user) {
        userDao.save(user);
    }

    @Transactional
    public UsersEntity login(String username, String password) {
        List<UsersEntity> list = userDao.list();
        for (UsersEntity user : list) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Transactional
    public boolean exists(String username) {
        List<UsersEntity> list = userDao.list();
        for (UsersEntity user : list) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public UsersEntity getUser(String username) {
        UsersEntity temp = null;
        List<UsersEntity> list = userDao.list();
        for (UsersEntity user : list) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return temp;
    }

    @Transactional
    public void update(UsersEntity user) {
        userDao.update(user);
    }

    @Transactional
    public void delete(UsersEntity user) {
        userDao.delete(user);
    }

    @Transactional
    public String encrypt(String toEncrypt) {
        MessageDigest encryptor = null;
        try {
            encryptor = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        encryptor.update(toEncrypt.getBytes());

        byte byteData[] = encryptor.digest();

        //convert the byte to hex format method 1
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            stringBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }

    public List<Integer> getVisited() {
        return userDao.getVisisted();
    }

    public String getProperties() {
        File file = null;
        try {
            file = new File(resourceLoader.getResource("/WEB-INF/application.properties").getURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public void writeProperties(String updatedProperties) {
        File file = null;
        try {
            file = new File(resourceLoader.getResource("/WEB-INF/application.properties").getURI());
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath(), false);
            fileWriter.write(updatedProperties);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
