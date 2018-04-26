package camt.se234.project;

import camt.se234.project.dao.UserDao;
import camt.se234.project.dao.UserDaoImpl;
import camt.se234.project.entity.User;
import camt.se234.project.service.AuthenticationServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {
    UserDaoImpl userDao;
    AuthenticationServiceImpl AuthenService;
    @Before
    public void setup(){
        userDao = mock(UserDaoImpl.class);
        AuthenService = new AuthenticationServiceImpl();
        AuthenService.setUserDao(userDao);
    }

    @Test
    public void testAuthenticationServiceImpl(){
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User( 5957747L,"CherPrang","Onigiri360","Sembatsu"));
        mockUsers.add(new User( 5957748L,"PunBnk","BNK48","Sembatsu"));
        mockUsers.add(new User( 5957749L,"KhunKai","IwantNoodle","Sembatsu"));

        when(userDao.getUser("CherPrang","Onigiri360")).thenReturn(mockUsers.get(0));
        when(userDao.getUser("PunBnk","BNK48")).thenReturn(mockUsers.get(1));
        when(userDao.getUser("KhunKai","IwantNoodle")).thenReturn(mockUsers.get(2));

        assertThat(AuthenService.authenticate("CherPrang","Onigiri360"),is(new User( 5957747L,"CherPrang","Onigiri360","Sembatsu")));
        assertThat(AuthenService.authenticate("PunBnk","BNK48"),is(new User( 5957748L,"PunBnk","BNK48","Sembatsu")));
        assertThat(AuthenService.authenticate("KhunKai","IwantNoodle"),is(new User( 5957749L,"KhunKai","IwantNoodle","Sembatsu")));

        assertThat(AuthenService.authenticate("CherPrang","123456"),is(nullValue()));
        assertThat(AuthenService.authenticate("asjdlajsdl","Onigiri360"),is(nullValue()));


    }
}
