package com.example.cuentaservice.controller;

import com.example.cuentaservice.model.Cuenta;
import com.example.cuentaservice.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
        Cuenta createdCuenta = cuentaService.createCuenta(cuenta);
        return new ResponseEntity<>(createdCuenta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Cuenta getCuentaById(@PathVariable Long id) {
        return cuentaService.findById(id);
    }

    @PutMapping("/{id}")
    public Cuenta updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        cuenta.setId(id);
        return cuentaService.createCuenta(cuenta);
    }

    @PatchMapping("/{id}")
    public Cuenta patchCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        Cuenta existingCuenta = cuentaService.findById(id);
        if (cuenta.getNumeroCuenta() != null) {
            existingCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
        }
        if (cuenta.getTipoCuenta() != null) {
            existingCuenta.setTipoCuenta(cuenta.getTipoCuenta());
        }
        if (cuenta.getSaldoInicial() != 0) {
            existingCuenta.setSaldoInicial(cuenta.getSaldoInicial());
        }
        if (cuenta.isEstado() != existingCuenta.isEstado()) {
            existingCuenta.setEstado(cuenta.isEstado());
        }
        return cuentaService.createCuenta(existingCuenta);
    }

    @DeleteMapping("/{id}")
    public void deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteById(id);
    }
}
