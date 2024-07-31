package com.example.padaria.venda.repository;

import com.example.padaria.venda.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT SUM(v.totalVenda) FROM Venda v")
    Double findTotalVendas();

    @Query("SELECT COUNT(v) FROM Venda v")
    Long findTotalPedidos();

    @Query("SELECT v FROM Venda v WHERE v.dataVenda = :dataVenda")
    List<Venda> findByDataVenda(Date dataVenda);

    @Query("SELECT v FROM Venda v WHERE YEAR(v.dataVenda) = :year AND MONTH(v.dataVenda) = :month")
    List<Venda> findByDataVendaMes(int year, int month);
}
