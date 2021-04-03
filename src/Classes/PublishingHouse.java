package Classes;

public class PublishingHouse {
    private String name;
    private String description;

    public PublishingHouse(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public PublishingHouse(){
        this.name = "";
        this.description = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PublishingHouse{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
