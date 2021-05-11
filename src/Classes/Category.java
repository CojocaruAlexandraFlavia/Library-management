package Classes;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category(){
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
