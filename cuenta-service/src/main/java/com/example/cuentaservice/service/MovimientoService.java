package com.example.cuentaservice.service;

import com.example.cuentaservice.exception.BalanceInsuficienteException;
import com.example.cuentaservice.exception.ResourceNotFoundException;
import com.example.cuentaservice.model.Cuenta;
import com.example.cuentaservice.model.Movimiento;
import com.example.cuentaservice.repository.CuentaRepository;
import com.example.cuentaservice.repository.MovimientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Transactional
    public Movimiento save(Movimiento movimiento, Long cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow(() -> new ResourceNotFoundException("Cuenta not found"));
        movimiento.setCuenta(cuenta);

        // Verificar saldo disponible
        if ("Retiro".equalsIgnoreCase(movimiento.getTipoMovimiento()) && cuenta.getSaldoInicial() < movimiento.getValor()) {
            throw new BalanceInsuficienteException("Saldo no disponible");
        }

        // Ajustar el saldo de la cuenta en base al tipo de movimiento
        if ("Retiro".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            cuenta.setSaldoInicial(cuenta.getSaldoInicial() - movimiento.getValor());
        } else if ("Deposito".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimiento.getValor());
        }

        movimiento.setSaldo(cuenta.getSaldoInicial());
        movimiento.setFecha(new Date());

        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> findByCuentaAndFechaBetween(Long cuentaId, Date startDate, Date endDate) {
        return movimientoRepository.findByCuentaIdAndFechaBetween(cuentaId, startDate, endDate);
    }

    public Movimiento findById(Long id) {
        return movimientoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movimiento not found"));
    }

    public void deleteById(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Movimiento not found");
        }
        movimientoRepository.deleteById(id);
    }
}
