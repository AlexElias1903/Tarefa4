package com.grupo13;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaCorrenteTest 
{
    private ContaCorrente contaCorrente;

    @BeforeEach
    public void setup(){
        contaCorrente = new ContaCorrente(111,"fulano");
    }
    //conta inicia zerada e na categoria "silver"
    @Test
    public void contaInicial(){
        assertEquals(111,contaCorrente.getNumeroConta());
        assertEquals("fulano",contaCorrente.getNomeCorrentista());
        assertEquals("Silver",contaCorrente.getCategoria());
        assertEquals(0,contaCorrente.getSaldo());
    }

    @Test
    public void depositoNulo(){
        assertFalse(contaCorrente.deposito(0));
        assertEquals("Silver",contaCorrente.getCategoria());
    }

    @Test
    public void contaSilver(){
        contaCorrente.deposito(10000);
        assertEquals("Silver",contaCorrente.getCategoria());
        assertEquals(10000,contaCorrente.getSaldo());
    }
    @Test
    public void contaUpgradeGoldLimit(){
        contaCorrente.deposito(50000);
        assertEquals("Gold",contaCorrente.getCategoria());
        contaCorrente.deposito(1000);
        assertEquals(51010,contaCorrente.getSaldo());
    }

    @Test
    public void contaUpgradeGoldValorAlto(){
        contaCorrente.deposito(600000);
        assertEquals("Gold",contaCorrente.getCategoria());
    }

    @Test
    public void contaUpgradePlatinum(){
        contaCorrente.deposito(200000);
        contaCorrente.deposito(1000);//1010, pois a conta é Gold ainda/depois do deposito virou platinum
        assertEquals("Platinum",contaCorrente.getCategoria());
        contaCorrente.deposito(1000);//1025, pois a conta virou platinium
        assertEquals(202035,contaCorrente.getSaldo());
    }
    
    @Test
    public void retiradaLimit(){
        contaCorrente.deposito(20000);
        contaCorrente.retirada(20000);
        assertEquals(0,contaCorrente.getSaldo());
    }

    @Test
    public void retiradaIlegal(){
        contaCorrente.deposito(20000);
        assertFalse(contaCorrente.retirada(20001));//retirada invalida
        assertEquals(20000,contaCorrente.getSaldo());
    }

    @Test
    public void contaPlatiniumRetrocesso(){
        contaCorrente.deposito(200000);
        contaCorrente.deposito(1000);
        assertEquals("Platinum",contaCorrente.getCategoria());
        contaCorrente.retirada(110000);//valor da conta menor que 100000
        assertEquals("Gold",contaCorrente.getCategoria());
    }

    @Test
    public void contaPlatiniumRetrocessoTotal(){
        contaCorrente.deposito(200000);
        contaCorrente.deposito(1000);
        assertEquals("Platinum",contaCorrente.getCategoria());
        contaCorrente.retirada(110000);
        assertEquals("Gold",contaCorrente.getCategoria());
        contaCorrente.retirada(80000);
        System.out.println(contaCorrente.getSaldo());
        assertEquals("Silver",contaCorrente.getCategoria());
    }

    @Test
    public void contaGoldRetrocesso(){
        contaCorrente.deposito(80000);
        contaCorrente.deposito(1000);
        assertEquals("Gold",contaCorrente.getCategoria());
        contaCorrente.retirada(70000);
        assertEquals("Silver",contaCorrente.getCategoria());
    }
}
