package edu.ilkiv.comon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffWithRouteResponse {
    private PersonalDataDTO personalData;
    private PersonnelCredentialsDTO credentials;
    private List<RouteSheetDTO> routeSheets;
}