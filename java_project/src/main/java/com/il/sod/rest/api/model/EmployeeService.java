package com.il.sod.rest.api.model;

import com.il.sod.db.dao.IEmployeeDAO;
import com.il.sod.db.model.entities.Employee;
import com.il.sod.db.model.repositories.EmployeeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.EmployeeMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.EmployeeDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/employees", tags = {"employees"})
public class EmployeeService extends AbstractServiceMutations {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private IEmployeeDAO employeeDAO;

  @POST
  @ApiOperation(value = "Create Employee", response = EmployeeDTO.class)
  public Response saveEmployee(EmployeeDTO dto) throws SODAPIException {
    Employee entity = EmployeeMapper.INSTANCE.map(dto);
    this.saveEntity(employeeRepository, entity);
    dto = EmployeeMapper.INSTANCE.map(entity);
    return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);

  }

  @PUT
  @ApiOperation(value = "Update Employee", response = EmployeeDTO.class)
  public Response updateEmployee(EmployeeDTO dto) throws SODAPIException {
    Employee entity = EmployeeMapper.INSTANCE.map(dto);
    this.updateEntity(employeeRepository, entity);
    dto = EmployeeMapper.INSTANCE.map(entity);
    return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);
  }

  @DELETE
  @Path("/{id}")
  @ApiOperation(value = "Delete Item", response = GeneralResponseMessage.class)
  public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
    Employee entity = employeeRepository.findOne(Integer.valueOf(id));
    if (entity == null) {
      throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
    }
    this.softDeleteEntity(employeeRepository, entity.getId());
    return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
            Response.Status.OK);
  }

  @GET
  @ApiOperation(value = "Get Employee list", response = EmployeeDTO.class, responseContainer = "List")
  public Response getEmployeeList(@QueryParam("email") String email) throws SODAPIException {
    List<Employee> entityList = null;
    if (!StringUtils.isEmpty(email)) {
      entityList = employeeDAO.findByEmail(email);
    } else {
      entityList = this.getEntityList(employeeRepository);
    }
    List<EmployeeDTO> list = entityList.stream().map(EmployeeMapper.INSTANCE::map)
            .filter(DeletablePredicate.isActive())
            .collect(Collectors.toList());
    return ConvertUtils.castEntityAsResponse(list);
  }

  @GET
  @Path("/byId/{idEmployee}")
  @ApiOperation(value = "Get Employee list", response = EmployeeDTO.class)
  public Response getEmployeeById(@PathParam("idEmployee") String idEmployee) throws SODAPIException {
    Employee employee = this.getEntity(employeeRepository, Integer.valueOf(idEmployee));
    if (employee == null) {
      throw new SODAPIException(Response.Status.NO_CONTENT, "No employee found");
    }
    EmployeeDTO dto = EmployeeMapper.INSTANCE.map(employee);
    return ConvertUtils.castEntityAsResponse(dto);
  }
}
