package com.example.cuentaservice.controller;

import com.example.cuentaservice.model.Movimiento;
import com.example.cuentaservice.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping("/movimientos")
    public Movimiento createMovimiento(@RequestBody Movimiento movimiento, @RequestParam("cuentaId") Long cuentaId) {
        return movimientoService.save(movimiento, cuentaId);
    }

    @GetMapping("/{id}")
    public Movimiento getMovimientoById(@PathVariable Long id) {
        return movimientoService.findById(id);
    }

    @PutMapping("/{id}")
    public Movimiento updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        movimiento.setId(id);
        return movimientoService.save(movimiento, movimiento.getCuenta().getId());
    }

    @PatchMapping("/{id}")
    public Movimiento patchMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        Movimiento existingMovimiento = movimientoService.findById(id);
        if (movimiento.getTipoMovimiento() != null) {
            existingMovimiento.setTipoMovimiento(movimiento.getTipoMovimiento());
        }
        if (movimiento.getValor() != 0) {
            existingMovimiento.setValor(movimiento.getValor());
        }
        if (movimiento.getSaldo() != 0) {
            existingMovimiento.setSaldo(movimiento.getSaldo());
        }
        return movimientoService.save(existingMovimiento, existingMovimiento.getCuenta().getId());
    }

    @DeleteMapping("/{id}")
    public void deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteById(id);
    }

    @GetMapping("/reportes")
    public List<Movimiento> getReporte(
            @RequestParam("cuentaId") Long cuentaId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return movimientoService.findByCuentaAndFechaBetween(cuentaId, startDate, endDate);
    }
}
