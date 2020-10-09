package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.MonitoreoRepository;
import com.upc.babyhealth.models.entity.*;
import com.upc.babyhealth.models.entity.request.AlertaRequest;
import com.upc.babyhealth.models.entity.request.MonitoreoPutRequest;
import com.upc.babyhealth.models.entity.request.MonitoreoRequest;
import com.upc.babyhealth.models.service.ContraccionService;
import com.upc.babyhealth.models.service.GestanteService;
import com.upc.babyhealth.models.service.ObstetraService;
import com.upc.babyhealth.models.service.TipoAlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MonitoreoService implements com.upc.babyhealth.models.service.MonitoreoService {

    @Autowired
    private MonitoreoRepository monitoreRepository;
    @Autowired
    private GestanteService gestanteService;
    @Autowired
    private PushNotificationService pushNotificationService;
    @Autowired
    private ObstetraService obstetraService;
    @Autowired
    private AlertaService alertaService;
    @Autowired
    private TipoAlertaService tipoAlertaService;
    @Autowired
    private ContraccionService contraccionService;
    @Autowired
    private CelularService celularService;

    @Override
    public List<Monitoreo> findBySemanaAndGestante(Integer semana, Long gestanteId) {
        Gestante gestante = new Gestante();
        gestante.setId(gestanteId);
        return monitoreRepository.findBySemanaGestacionAndGestante(semana, gestante);
    }

    @Override
    public List<Monitoreo> findByGestante(Long gestanteId) {
        return monitoreRepository.findByGestante_Id(gestanteId);
    }


    @Override
    public Monitoreo findLastMonitoreo(Long gestanteId) {
        Gestante gestante = new Gestante();
        gestante.setId(gestanteId);
        return monitoreRepository.findTopByGestanteAndEstadoOrderByFechaCreacionDesc(gestante, MonitoreoEstadoEnum.F );
    }

    @Override
    public Monitoreo findById(Long monitoreoId) {
        return monitoreRepository.findById(monitoreoId).orElse(null);
    }


    @Override
    public Monitoreo save(MonitoreoRequest monitoreoRequest, Long gestanteId) {
        Gestante gestante = gestanteService.findOne(gestanteId);
        //TODO EXCEPTION
        //if(gestante==null)
            //return new Exception();
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setGestante(gestante);
        monitoreo.setFechaCreacion(ZonedDateTime.now(ZoneId.of("America/Lima")));
        monitoreo.setFechaInicio(monitoreo.getFechaCreacion());
        monitoreo.setEstado(MonitoreoEstadoEnum.I);
        monitoreo.setUsuarioCreacion(monitoreoRequest.getUsuarioCreacion());
        monitoreo.setCantidadMovFetales(0);
        monitoreo.setCantidadContracciones(0);

        return monitoreRepository.save(monitoreo);
    }

    @Override
    public Monitoreo update(MonitoreoPutRequest monitoreoRequest, Long gestanteId, Long monitoreoId) {
        Monitoreo existingMonitoreo = monitoreRepository.findById(monitoreoId).orElse(null);
        if(existingMonitoreo != null){
            //if(monitoreoRequest.getContraccionesPromedio() != null && !monitoreoRequest.getContraccionesPromedio().toString().equals(""))
            //  existingMonitoreo.setContraccionesPromedio(monitoreoRequest.getContraccionesPromedio());
            if(monitoreoRequest.getFrecuenciaPromedio() != null && !monitoreoRequest.getFrecuenciaPromedio().toString().equals(""))
                existingMonitoreo.setFrecuenciaPromedio(monitoreoRequest.getFrecuenciaPromedio());
            if(monitoreoRequest.getTiempoEcPromedio() != null && !monitoreoRequest.getTiempoEcPromedio().toString().equals(""))
                existingMonitoreo.setTiempoEcPromedio(monitoreoRequest.getTiempoEcPromedio());
            if(monitoreoRequest.getIntensidadPromedio() != null && !monitoreoRequest.getIntensidadPromedio().toString().equals(""))
                existingMonitoreo.setIntensidadPromedio(monitoreoRequest.getIntensidadPromedio());
            if(monitoreoRequest.getFechaModificacion() != null && !monitoreoRequest.getFechaModificacion().toString().equals(""))
                existingMonitoreo.setFechaModificacion(monitoreoRequest.getFechaModificacion());
            if(monitoreoRequest.getUsuarioModificacion() != null && !monitoreoRequest.getUsuarioModificacion().equals(""))
                existingMonitoreo.setUsuarioModificacion(monitoreoRequest.getUsuarioModificacion());
            if(monitoreoRequest.getFechaFin() != null && !monitoreoRequest.getFechaFin().toString().equals("")){
                if(existingMonitoreo.getFechaFin() == null){
                    //ya ha terminado el monitoreo
                    Gestante g = gestanteService.findOne(gestanteId);
                    String nombreGestante = g.getNombres()+" "+g.getApellidoPaterno()+" "+g.getApellidoMaterno();
                    Obstetra o = obstetraService.findById(g.getObstetra().getId());
                    boolean fine = true;

                    //Logica de monitoreos
                    Long diff = ChronoUnit.DAYS.between(g.getFechaInicioGestacion(), ZonedDateTime.now(ZoneId.of("America/Lima")));
                    Double semanas = Double.valueOf(diff)/7;
                    existingMonitoreo.setSemanaGestacion(semanas.intValue());

                    //duracion promedio
                    List<Contraccion> contracciones = contraccionService.findByMonitoreoId(existingMonitoreo.getIdMonitoreo());


                    double promedio = 0;
                    if(contracciones.size() > 0){
                        for (Contraccion c: contracciones) {
                            promedio += c.getDuracion();
                            if ( c.getNivel() == "MODERADO" || c.getNivel() == "ALTO" )
                                fine = false;
                        }
                        promedio = promedio / contracciones.size();
                    }
                    existingMonitoreo.setDuracionPromedio(promedio);

                    //cantidad contracciones
                    existingMonitoreo.setCantidadContracciones(contracciones.size());
                    existingMonitoreo.setFrecuenciaPromedio((double) contracciones.size());

                    //Promedio de intervalo entre contracciones
                    double intervaloPromedio = 0;
                    for(int i = 1; i < contracciones.size(); i++){
                        intervaloPromedio += ChronoUnit.SECONDS.between(contracciones.get(i-1).getFechaFin(),contracciones.get(i).getFechaInicio());
                    }
                    if(contracciones.size() > 2){
                        intervaloPromedio = intervaloPromedio / (contracciones.size() - 1);
                        intervaloPromedio = Math.round(intervaloPromedio*10.0)/(10.0);
                    }
                    existingMonitoreo.setTiempoEcPromedio(intervaloPromedio);

                    //Calcular promedio de intensidad
                    double intensidadPromedio = 0;
                    if(contracciones.size()>0)
                    {
                        for(int i = 0; i < contracciones.size();i++){
                            intensidadPromedio += contracciones.get(i).getIntensidad();
                        }
                        intensidadPromedio = intensidadPromedio / contracciones.size();
                    }
                    existingMonitoreo.setIntensidadPromedio(intensidadPromedio);

                    //Alertar
                    AlertaRequest a = new AlertaRequest();
                    a.setGestanteId(g.getId());
                    a.setUsuarioCreacion("MASTER");
                    //obtener tokens
                    //a partir de la gestante/obstetra (usuario) obtener el celular y luego el token
                    Usuario uObst;
                    Usuario uGest;
                    uObst = o.getUsuario();
                    uGest = g.getUsuario();
                    Celular celGest = celularService.findActive(uGest.getIdUsuario());
                    Celular celObst = celularService.findActive(uObst.getIdUsuario());
                    a.setGestanteToken( celGest.getFirebaseToken());
                    a.setObstetraToken( celObst.getFirebaseToken());

                    a.setTipoAlerta("MONITOREO");
                    existingMonitoreo.setEstadoGestante("ESTABLE");

                    if( (existingMonitoreo.getCantidadMovFetales() < g.getPatronMovimientos()) || !fine)  {
                        a.setTipoAlerta("EMERGENCIA");
                        existingMonitoreo.setEstadoGestante("EMERGENCIA");
                    }
                    else if(semanas < 30){
                        if(existingMonitoreo.getCantidadContracciones() > 2){
                            //alertar emergencia
                            a.setTipoAlerta("EMERGENCIA");
                            existingMonitoreo.setEstadoGestante("EMERGENCIA");
                        }
                    }
                    else if( semanas >= 30 ){
                        if(existingMonitoreo.getCantidadContracciones() == 3 || existingMonitoreo.getCantidadContracciones() == 4)
                        {
                            //alertar emergencia
                            a.setTipoAlerta("EMERGENCIA");
                            existingMonitoreo.setEstadoGestante("EMERGENCIA");
                        }
                        else if(existingMonitoreo.getCantidadContracciones() > 5){
                            //alertar labor de parto
                            a.setTipoAlerta("LABOR DE PARTO");
                            existingMonitoreo.setEstadoGestante("LABOR DE PARTO");
                        }
                    }

                    alertaService.sendAlert(a);
                }
                existingMonitoreo.setFechaFin(monitoreoRequest.getFechaFin());
                existingMonitoreo.setEstado(MonitoreoEstadoEnum.F);
                existingMonitoreo.setEstadoGestante("ESTABLE");
                existingMonitoreo.setFechaModificacion(ZonedDateTime.now(ZoneId.of("America/Lima")));
            }

            return monitoreRepository.save(existingMonitoreo);
        }
        return null;
    }

    @Override
    public Monitoreo addMovFetal(Long monitoreoId) {
        Monitoreo m = monitoreRepository.findById(monitoreoId).orElse(null);
        if(m != null){
            m.setCantidadMovFetales(m.getCantidadMovFetales()+1);
            monitoreRepository.save(m);
        }

        return m;
    }
}
