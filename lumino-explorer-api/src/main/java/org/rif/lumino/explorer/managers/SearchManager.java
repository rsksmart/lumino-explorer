package org.rif.lumino.explorer.managers;

import org.rif.lumino.explorer.models.dto.SearchResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.rif.lumino.explorer.services.SearchService;

@Component
@Service
public class SearchManager {

  @Autowired private SearchService searchService;

  public SearchResultDTO search(String query) {

    return searchService.search(query);
  }
}
