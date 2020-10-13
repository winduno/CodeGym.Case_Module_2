package sample.models;

public class FootballPlayer {
    private String playerID;
    private String passPort;
    private String name;
    private String nationality;
    private String dateOfBirth;
    private String height;
    private String weight;
    private String number;
    private String position;
    private static int id = 0;

    public FootballPlayer (){
    }

    public FootballPlayer(String name, String nationality, String dateOfBirth, String height,
                          String weight, String number, String position) {

        this.playerID = String.valueOf(id += 1);
        this.name = name;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.number = number;
        this.position = position;
    }

    public String getIdPassPort() {
        return passPort;
    }

    public void setIdPassPort(String idPassPort) {
        this.passPort = idPassPort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIndexPlayer() {
        return number;
    }

    public void setIndexPlayer(String indexPlayer) {
        this.number = indexPlayer;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    @Override
    public String toString() {
        return "Models.FootballPlayer {" +
                "idPassPort='" + passPort + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", indexPlayer='" + number + '\'' +
                '}';
    }
}
