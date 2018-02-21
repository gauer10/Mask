package sample;

public class CheckValue
{
    public boolean checkCode(String kod, int lenght, int endConstant, String constant)
    {
        if (kod.length() == lenght)
        {
            if (kod.substring(0, endConstant).equals(constant))
                return true;
            else
                return false;
        }
        else
            return false;
    }
    public boolean checkCode2(String kod, int lenght)
    {
        if (kod.length() == lenght)
        {
            return true;
        }
        else
            return false;
    }
}
