package src;
public class BLLUnitTest {
    
    private BLL bll;
    private DAL mockedDAL;


    public void setUp() {

        mockedDAL = mock(DAL.class);
        bll = new BLL(mockedDAL);
    }
    


}
