package com.il.sod.rest.api.web.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.Menu;
import com.il.sod.db.model.repositories.MenuRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SpecificObjectsMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.MenuDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/app-utils")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/app-utils", tags = { "app-utils" })
public class AppUtilsService extends AbstractServiceMutations {
	
	final static Logger LOGGER = LoggerFactory.getLogger(AppUtilsService.class);
	
	@Autowired
	MenuRepository menuRepository;
	
	@GET
	@Path("/menu")
	@ApiOperation(value = "Get Menu Options", response = MenuDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getMenu() throws SODAPIException {
		List<Menu> entities = menuRepository.findAll();
		List<MenuDTO> dtos = entities.stream().map(i -> {return SpecificObjectsMapper.INSTANCE.map(i);}).collect(Collectors.toList());
		return this.castEntityAsResponse(dtos);
	}
	
	@GET
	@Path("/menu/{accessLevel}")
	@ApiOperation(value = "Get Menu Options", response = MenuDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getMenu(@PathParam("accessLevel") String accessLevel) throws SODAPIException {
		List<Menu> entities = menuRepository.findAll();
		int accessL = Integer.valueOf(accessLevel);
		List<MenuDTO> dtos = entities.stream()
				.filter(i -> i.getAccessLevel() > accessL)
				.map(i -> {return SpecificObjectsMapper.INSTANCE.map(i);})
				.collect(Collectors.toList());
		return this.castEntityAsResponse(dtos);
	}
}