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
        mongoTemplate.insert(Arrays.asList(
                new LuminoHub("http://localhost:4321", 33),
                new LuminoHub("http://localhost:4322", 11),
                new LuminoHub("http://localhost:4323", 22)), LuminoHub.class);
        Optional<LuminoHub> actual = luminoHubRepository.findFirstByOrderByConnectionCount();

        Assertions.assertThat(actual).isPresent();
        Assertions.assertThat(actual.get().getUrl()).isEqualTo("http://localhost:4322");
    }

}
