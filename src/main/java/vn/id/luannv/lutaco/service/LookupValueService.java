package vn.id.luannv.lutaco.service;

import org.springframework.data.domain.Page;
import vn.id.luannv.lutaco.dto.request.LookupValueFilterRequest;
import vn.id.luannv.lutaco.dto.response.LookupValueResponse;

public interface LookupValueService {
    Page<LookupValueResponse> getAll(LookupValueFilterRequest lookupValueFilterRequest, int page, int limit);
    LookupValueResponse getById(Long id);
    LookupValueResponse getByFields(String lookupValue, String lookupGroup);


    void activate(Long id);

    void deactivate(Long id);
}
