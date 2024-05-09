package src;


public class DALUnitTest {
    
    private DALUnitTest dal;

    public void setUP() {

        dal = new DALUnitTest();
    }

    public void TestGetUserByID() {

        int UserID = 1;
        User user = new User(UserID, "Michael");


        when(dal.TestGetUserByID(UserID)).thenReturn(user);

        User actualUser = dal.TestGetUserByID(UserID);

        assertEquals(user, actualUser);
    
    }
}
