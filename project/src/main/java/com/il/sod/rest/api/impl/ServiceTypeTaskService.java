package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.ServiceTypeTask;
import com.il.sod.db.model.repositories.ServiceTypeTaskRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceTypeTaskDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Path("/service-type-task")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/service-type-task", tags = { "service" })
public class ServiceTypeTaskService extends AbstractServiceMutations {
	@Autowired
	ServiceTypeTaskRepository serviceTypeSpecRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = ServiceTypeTaskDTO.class)
	public Response saveServiceTypeTask(ServiceTypeTaskDTO dto) throws SODAPIException {
		try {
			ServiceTypeTask entity = ServiceMapper.INSTANCE.map(dto);
			this.saveEntity(serviceTypeSpecRepository, entity);
			dto = ServiceMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Service Type", response = ServiceTypeTaskDTO.class)
	public Response updateServiceTypeTask(ServiceTypeTaskDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(ServiceTypeTaskDTO dto) throws SODAPIException {
		try {
			ServiceTypeTask entity = ServiceMapper.INSTANCE.map(dto);
			this.updateEntity(serviceTypeSpecRepository, entity);
			dto = ServiceMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = ServiceTypeTaskDTO.class)
	public Response deleteServiceTypeTask(ServiceTypeTaskDTO dto) throws SODAPIException {
		try {
			ServiceTypeTask entity = ServiceMapper.INSTANCE.map(dto);
			this.deleteEntity(serviceTypeSpecRepository, entity.getIdServiceTypeTask());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeTaskDTO.class, responseContainer = "List")
	public Response getServiceTypeTaskList() throws SODAPIException {
		List<ServiceTypeTask> rentityList = this.getEntityList(serviceTypeSpecRepository);
		List<ServiceTypeTaskDTO> list = rentityList.stream().map((i) -> {
			ServiceTypeTaskDTO dto = ServiceMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
