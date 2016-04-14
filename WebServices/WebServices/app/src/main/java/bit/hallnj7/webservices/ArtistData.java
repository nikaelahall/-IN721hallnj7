package bit.hallnj7.webservices;

public class ArtistData
{
    String name;
    String listenerCount;

    public ArtistData(String name, String listenerCount)
    {
        this.name = name;
        this.listenerCount = listenerCount;
    }

    public String getName() {return name;}
    public String getListenerCount() {return listenerCount;}
}
