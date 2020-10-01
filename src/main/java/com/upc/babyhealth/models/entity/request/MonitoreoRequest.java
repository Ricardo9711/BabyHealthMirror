package com.upc.babyhealth.models.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoreoRequest {
    private String usuarioCreacion;
}
