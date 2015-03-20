import com.dianping.rotate.shop.exception.POIMessageException;

/**
 * Created by zaza on 15/3/20.
 */
public class ExceptionTest {
    public static  void exceptionTest() throws POIMessageException{
        throw new NullPointerException();
    }

    public static  void main(String[] args){
        try{
            exceptionTest();
        }catch(POIMessageException ex){
            System.out.println("POIMessageException");
        }catch(NullPointerException ex){
            System.out.println("NullPointException");
        }catch(Exception ex){
            System.out.println("Exception");
        }

    }
}
