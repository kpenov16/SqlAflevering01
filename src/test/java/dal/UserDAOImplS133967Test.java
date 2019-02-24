package dal;

import dal.IUserDAO.DALException;
import dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserDAOImplS133967Test {
    private IUserDAO userDAO = null;
    @Before
    public void beforeEach(){
        userDAO = new UserDAOImplS133967();
    }

    @Test
    public void givenCreateNewUser_returnNewUserWasCreated() throws DALException {
        //arrange
        UserDTO user = new UserDTO();
        user.setUserName("Uncle Bob");
        user.setIni("bob");
        user.setUserId(Integer.MAX_VALUE-1);
        user.addRole("Admin");
        user.addRole("Foreman");

        //act
        userDAO.createUser(user);

        //assert
        assertEquals(user.toString(), userDAO.getUser(user.getUserId()).toString());
        assertEquals(1, userDAO.getUserList().size());

        //clean up
        userDAO.deleteUser(user.getUserId());
    }

    @Test
    public void givenUpdateExistingUser_returnUpdatedUser() throws DALException {
        //arrange
        UserDTO user = new UserDTO();
        user.setUserName("Uncle Bob");
        user.setIni("bob");
        user.setUserId(Integer.MAX_VALUE-1);
        user.addRole("Admin");
        user.addRole("Foreman");
        userDAO.createUser(user);

        user.setUserName("Bill Gates");
        user.setIni("bil");
        user.setRoles(new ArrayList<String>(){{add("Operator");add("Pharmacist");}});

        //act
        userDAO.updateUser(user);

        //assert
        assertEquals(user.toString(), userDAO.getUser(user.getUserId()).toString());

        //clean up
        userDAO.deleteUser(user.getUserId());
    }

}
