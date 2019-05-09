package org.rif.lumino.explorer.services;

import org.rif.lumino.explorer.models.documents.LuminoNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Component
public class LuminoNodeService {

    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private CommonService commonService;

    public List<LuminoNode> getNodeById(String nodeAddress) {

        Pattern pattern = Pattern.compile(nodeAddress, Pattern.CASE_INSENSITIVE);

        Query query = new Query();
        query.addCriteria(
                new Criteria()
                        .orOperator(
                                new Criteria().where("_id").regex(pattern)));

        List<LuminoNode> luminoNodes = mongoTemplate.find(query, LuminoNode.class, "lumino_node");

        return luminoNodes;
    }

}
