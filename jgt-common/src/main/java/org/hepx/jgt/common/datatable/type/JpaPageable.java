package org.hepx.jgt.common.datatable.type;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * JPA Pageable
 * @author: Koala
 * @Date: 14-8-29 下午3:22
 * @Version: 1.0
 */
public interface JpaPageable {

    public Pageable buildPageable();

    public Sort buildSort();

}
