package co.com.transacciones.app.service;

import java.util.List;
import java.util.Optional;

import co.com.transacciones.app.component.AccountDTO;
import co.com.transacciones.app.model.Cuenta;

public interface AccountService {
	
	public abstract List<Cuenta> getAllAccounts();
    public abstract Optional<Cuenta> getAccountById(int id);
    public abstract Cuenta saveAccount(AccountDTO account);
    public abstract Cuenta updateAccount(Cuenta account);
    public abstract void deleteAccount(int id);
}
