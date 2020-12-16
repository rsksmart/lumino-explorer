package org.rif.lumino.explorer.repositories;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rif.lumino.explorer.models.documents.LuminoHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class LuminoHubRepositoryTest {
    @Autowired
    private LuminoHubRepository luminoHubRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void retrievesMinimum() {
        mongoTemplate.getDb().drop();

        final String hubAddress1 = "http://localhost:4321";
        final String hubAddress2 = "http://localhost:4322";
        final String hubAddress3 = "http://localhost:4323";

        final LuminoHub hub1 = new LuminoHub(hubAddress1, 33);
        final LuminoHub hub2 = new LuminoHub(hubAddress2, 11);
        final LuminoHub hub3 = new LuminoHub(hubAddress3, 22);

        hub1.addConnection("someaddress_1");
        hub1.addConnection("someaddress_2");
        hub1.addConnection("someaddress_3");
        hub1.addConnection("someaddress_4");

        hub2.addConnection("someaddress_other_1");
        hub2.addConnection("someaddress_other_2");
        hub2.addConnection("someaddress_other_3");
        hub2.addConnection("someaddress_other_4");

        mongoTemplate.insert(Arrays.asList(hub1, hub2, hub3), LuminoHub.class);

        Optional<LuminoHub> actual = luminoHubRepository.findFirstByOrderByConnectionCount();

        Assertions.assertThat(actual).isPresent();
        Assertions.assertThat(actual.get().getUrl()).isEqualTo(hubAddress3);
    }

}
