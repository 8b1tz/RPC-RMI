package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

	private static final long serialVersionUID = 1L;
	private Map<String, Double> saldoContas;
    private List<Conta> contas = new ArrayList<>();
    
    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        saldoContas.put("1", 100.0);
        saldoContas.put("2", 156.0);
        saldoContas.put("3", 950.0);
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return saldoContas.get(conta);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }

	@Override
	public String cadastroConta(String numero, Double saldo) throws RemoteException {
		List<Conta> retorno = contas.stream().filter(c -> c.getNumero().equals(numero)).collect(Collectors.toList());
		if(!retorno.isEmpty()){
			return "conta já existe";
		}
		else {
			contas.add(new Conta(numero, saldo));
			return "criado com sucesso";
		}
	}

}
