package  com.app.heroes;

public  class Coordinates {
    
    private     int     longitude;
    private     int     latitude;
    
    public Coordinates(int longitude, int latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public  void    setLongitude(int longitude)
    {
        this.longitude = longitude;
    }

    public  void    setLatitude(int latitude)
    {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public void moveLeft() {
        this.latitude--;
    }

    public void moveRight() {
        this.latitude++;
    }

    public void moveUp() {
        this.longitude--;
    }

    public void moveDown() {
        this.longitude++;
    }
}