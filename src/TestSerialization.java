import org.junit.Test;

public class TestSerialization {
    @Test
    public void writeUser(){
        WriteObject.write();
    }


    @Test
    public void readUser(){
        ReadObject.read();
    }
}
