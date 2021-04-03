package Classes;

import java.util.List;

public class Library {
    private String name;
    private Address address;
    private List<Librarian> librarians;

    public Library(String name, Address address, List<Librarian> librarians) {
        this.name = name;
        this.address = address;
        this.librarians = librarians;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public void setLibrarians(List<Librarian> librarians) {
        this.librarians = librarians;
    }
}
