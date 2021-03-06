package com.il.sod.services.crud;

import com.il.sod.TestUtils;
import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.serve.WServiceCategoryDTO;
import com.il.sod.rest.dto.specifics.OrderTasksInfoDTO;
import com.il.sod.services.cruds.OrdersSv;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by cesaregb on 6/18/17.
 */
public class OrderSvTest extends SpringTestConfiguration {

  private final static Logger LOGGER = getLogger(OrderSvTest.class);

  @Autowired
  OrdersSv ordersSv;

  @Test
  public void testPayOrder() throws SODAPIException {
    OrderDTO dto = new OrderDTO();
    dto.setIdOrder(2);
    dto.setPaymentStatus(true);
    OrderDTO response = ordersSv.updateOrder(dto);
    Assert.assertThat("Some value!!!! ", response.getPaymentStatus(), Matchers.is(true));
  }

  @Test
  public void getOrderDetails() throws SODAPIException {
    OrderTasksInfoDTO dto = ordersSv.getOrderTaskInfo(12);
    dto.getServices().forEach(s -> {
      LOGGER.info(s.getServiceDescription());
    });
  }

  @Test
  public void testGetPreorderService() throws SODAPIException {
    List<WServiceCategoryDTO> list =  ordersSv.getOrderTypes();
    list.forEach(t-> LOGGER.info(t.toString()));
  }

  @Test
  public void testGetOrdersByDate() {
    LocalDate date = LocalDate.now();
    List<OrderDTO> list = ordersSv.getOrderByDate(TestUtils.toTimestamp(date));
    LOGGER.info("Orders: {}", list.size());
  }

  @Test
  public void testGetOrdersBetweenDates() {
    LocalDate dateA = LocalDate.now().minusDays(100);
    LocalDate dateB = LocalDate.now();
    List<OrderDTO> list = ordersSv.getOrderBetweenDates(TestUtils.toTimestamp(dateA), TestUtils.toTimestamp(dateB));
    LOGGER.info("Orders: {}", list.size());
  }
}
