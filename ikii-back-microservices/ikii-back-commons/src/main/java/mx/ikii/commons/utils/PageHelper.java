package mx.ikii.commons.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * This class helps the page needs when working with request methods
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
public class PageHelper {

  /**
   * This method pages the content based on a provided page object
   *
   * @param response
   * @param pageable
   * @return
   */
  public static <T> Page<T> createPage(List<T> response, Pageable pageable) {
    int totp = pageable.getPageSize() * (pageable.getPageNumber() + 1);
    Integer max = totp > response.size() ? response.size() : totp;

    return new PageImpl<T>(response.subList(pageable.getPageNumber() * pageable.getPageSize(), max),
        pageable, response.size());
  }

  /**
   * This method creates a page object based on a paged List
   *
   * @param response
   * @param pageable
   * @param size
   * @return
   */
  public static <T> Page<T> createPage(List<T> response, Pageable pageable, Long size) {
    return new PageImpl<T>(Nullable.isNull(response) ? new ArrayList<>() : response, pageable,
        Nullable.isNull(size) ? 0 : size);
  }

}
