package com.isoft.medical.inspection.service.mapper;


import com.isoft.medical.inspection.domain.*;
import com.isoft.medical.inspection.service.dto.LicenseCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LicenseCategory} and its DTO {@link LicenseCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LicenseCategoryMapper extends EntityMapper<LicenseCategoryDTO, LicenseCategory> {



    default LicenseCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        LicenseCategory licenseCategory = new LicenseCategory();
        licenseCategory.setId(id);
        return licenseCategory;
    }
}
