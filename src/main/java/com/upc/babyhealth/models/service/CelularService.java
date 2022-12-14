package com.upc.babyhealth.models.service;


import com.upc.babyhealth.models.entity.Celular;
import com.upc.babyhealth.models.entity.request.CelularPostRequest;
import com.upc.babyhealth.models.entity.request.CelularPutRequest;

import java.util.List;

public interface CelularService {
    public Celular insert(CelularPostRequest request);
    public Celular update(Long id, CelularPutRequest request);
    public boolean changeState(Long usuarioId, Long activeId);
    public Celular findActive(Long usuarioId);
    public List<Celular> findByUsuarioId(Long usuarioId);
}
