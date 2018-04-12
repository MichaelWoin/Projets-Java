public class PriseClient 
{
    String nomMachine;
    int numeroPort;
    String formatDate;
    
    PriseClient(String nomMachine, int numeroPort)
    {
        this.nomMachine=nomMachine;
        this.numeroPort=numeroPort;
    }
    
    public void setFormatDate(String formatDate)
    {
        this.formatDate=formatDate;
    }

    public String getFormatDate()
    {
        return formatDate;
    }
    
    
    
}
