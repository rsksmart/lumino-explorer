package org.rif.lumino.explorer.services;

import org.rif.lumino.explorer.models.documents.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class FeedService {

  @Autowired private MongoOperations mongoOperations;

  public List<Feed> getLastFeedsByIdWithLimit(Integer limit) {
    Query query = new Query();
    query.limit(limit);
    query.with(new Sort(Sort.Direction.DESC, "id"));

    List<Feed> feeds = mongoOperations.find(query, Feed.class);

    return feeds;
  }

  public List<Feed> getGraterThanId(Long id) {
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").gt(id));
    List<Feed> feeds = mongoOperations.find(query, Feed.class);
    return feeds;
  }
}
