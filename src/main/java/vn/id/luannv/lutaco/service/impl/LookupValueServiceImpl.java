package vn.id.luannv.lutaco.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.id.luannv.lutaco.dto.request.LookupValueFilterRequest;
import vn.id.luannv.lutaco.dto.response.LookupValueResponse;
import vn.id.luannv.lutaco.entity.LookupValue;
import vn.id.luannv.lutaco.exception.ApiException;
import vn.id.luannv.lutaco.exception.ErrorCode;
import vn.id.luannv.lutaco.mapper.LookupValueMapper;
import vn.id.luannv.lutaco.repository.LookupValueRepository;
import vn.id.luannv.lutaco.service.LookupValueService;
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LookupValueServiceImpl implements LookupValueService {
    LookupValueRepository lookupValueRepository;
    LookupValueMapper lookupValueMapper;

    @Override
    public LookupValueResponse getById(Long id) {
        return lookupValueRepository.findById(id)
                .map(lookupValueMapper::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.LOOKUP_VALUE_NOT_FOUND));
    }

    @Override
    public Page<LookupValueResponse> getAll(LookupValueFilterRequest lookupValueFilterRequest, int page, int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        return lookupValueRepository.findAllByFilters(lookupValueFilterRequest, pageable).map(lookupValueMapper::toResponse);
    }

    @Override
    public void activate(Long id) {
        LookupValue entity = lookupValueRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.LOOKUP_VALUE_NOT_FOUND));
        entity.setStatusFlg((byte) 1);
        lookupValueRepository.save(entity);
    }

    @Override
    public void deactivate(Long id) {
        LookupValue entity = lookupValueRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.LOOKUP_VALUE_NOT_FOUND));
        entity.setStatusFlg((byte) 0);
        lookupValueRepository.save(entity);
    }

    @Override
    public LookupValueResponse getByFields(String lookupValue, String lookupGroup) {
        return lookupValueRepository.findByValueAndOptionalGroup(lookupValue, lookupGroup)
                .map(lookupValueMapper::toResponse)
                .orElse(null);
    }
}
