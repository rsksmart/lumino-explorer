package org.rif.lumino.explorer.documents;

import org.rif.lumino.explorer.Application;
import org.rif.lumino.explorer.models.documents.EventJobMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.rif.lumino.explorer.repositories.EventJobDataRepository;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CreateJobMetadata {

  @Autowired EventJobDataRepository eventJobDataRepository;

  @Test
  public void insertInitial() {
    EventJobMetadata initial =
        new EventJobMetadata("1", new BigInteger("0"), new BigInteger("0"), 500);
    eventJobDataRepository.save(initial);
  }
}
