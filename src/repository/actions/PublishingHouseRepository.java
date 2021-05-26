package repository.actions;

import models.Category;
import models.PublishingHouse;

import java.util.List;

public interface PublishingHouseRepository {

    List<PublishingHouse> retrieveAllPublishingHouses();
    int addPublishingHouse(PublishingHouse publishingHouse);
    void updatePublishingHouse(int publishingHouseId, PublishingHouse newPublishingHouse);
    void deletePublishingHouse(int publishingHouseId);
    PublishingHouse getPublishingHouseForBook(int bookId);
    int getPublishingHouseId(PublishingHouse publishingHouse);
    PublishingHouse getByName(String name);
}
