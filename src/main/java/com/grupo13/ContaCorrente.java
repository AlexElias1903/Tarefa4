package com.grupo13;
public class ContaCorrente{
    int numeroConta;
    String nomeCorrentista;
    double saldo;
    String categoria;
    public ContaCorrente(int numeroConta, String nomeCorrentista) {
        this.numeroConta = numeroConta;
        this.nomeCorrentista = nomeCorrentista;
        this.saldo = 0;
        this.categoria = "Silver";
    }
    public int getNumeroConta() {
        return this.numeroConta;
    }
    public String getNomeCorrentista() {
        return this.nomeCorrentista;
    }
    public double getSaldo() {
        return this.saldo;
    }
    public String getCategoria() {
        return this.categoria;
    }
    public boolean deposito(double valor){
        if(valor>0){
            if(getCategoria()=="Silver"){
                saldo = saldo + valor;
                if(saldo>=50000){
                    categoria="Gold";
                }
        }else if(getCategoria()=="Gold"){
                saldo = saldo + (valor+valor*0.01);
                if(saldo>=200000){
                    categoria="Platinum";
                }
        }else if(getCategoria()=="Platinum"){
            saldo = saldo +(valor + valor*0.025);
        }   
        return true;
        }
            else{
            return false;
        }
    }
    public boolean retirada(double valor){
        if(valor>saldo){
            return false;
        }else{
            if(getCategoria()=="Platinum"){
                saldo = saldo - valor;
                if(saldo < 100000){
                    categoria = "Gold";
                }
            }else if(getCategoria()=="Gold"){
                saldo = saldo - valor;
                if(saldo < 25000){
                    categoria = "Silver";
                }
            }else if(getCategoria()=="Silver"){
                saldo = saldo - valor;
            }
            return true;
        }
    }
  }
  
