package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.DispositivoXRepository;
import com.upc.babyhealth.models.entity.Dispositivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.upc.babyhealth.models.dao.DispositivoRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Service
public class DispositivoService implements com.upc.babyhealth.models.service.DispositivoService {
	@Autowired
	private DispositivoRepository dispositivoRepo;
	@Autowired
	private DispositivoXRepository dispositivoXRepository;

	@Override
	public List<Dispositivo> findAll() {
		return null;
	}

	@Override
	public ResponseEntity save(Dispositivo dispositivo) {

		try{

		Dispositivo newDispositivo = dispositivoRepo.save(dispositivo);

		/*
		if(newDispositivo != null){

			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/babyhealth_dev?serverTimezone=UTC&useLegacyDatetimeCode=false");
			dataSource.setUsername("root");

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("SP_NEW_TABLE_DISPOSITIVOX");
			Map<String, Object> out = jdbcCall.execute(newDispositivo.getIdDispositivo());

		}
		*/

		dispositivoXRepository.SP_NEW_TABLE_DISPOSITIVOX(newDispositivo.getIdDispositivo());

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(newDispositivo);
		}catch(Exception e){
			return ResponseEntity
					.status(HttpStatus.NOT_ACCEPTABLE)
					.body(e.getStackTrace());
		}

		//return newDispositivo;

	}

	@Override
	public Dispositivo findById(Long id) {
		return dispositivoRepo.findById(id).orElse(null);
	}

	@Override
	public Dispositivo update(Dispositivo dispositivo) {
		//TODO
		return dispositivoRepo.save(dispositivo);
	}
}
