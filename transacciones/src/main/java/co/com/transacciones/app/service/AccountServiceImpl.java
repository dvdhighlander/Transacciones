package co.com.transacciones.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import co.com.transacciones.app.component.AccountDTO;
import co.com.transacciones.app.component.ClientDTO;
import co.com.transacciones.app.component.UserClient;
import co.com.transacciones.app.model.Cuenta;
import co.com.transacciones.app.repository.AccountRepository;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public List<Cuenta> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Optional<Cuenta> getAccountById(int id) {
			return accountRepository.findById(id);
	}

	@Override
	public Cuenta saveAccount(AccountDTO account) {
		UserClient client= new UserClient(WebClient.builder());
		Mono<ClientDTO> oo= client.findbyId(account.idCliente());
		if(oo.hasElement().block()) {
			Cuenta newAccount= new Cuenta();
			newAccount.setEstado(account.estado());
			newAccount.setNumeroCuenta(account.numeroCuenta());
			newAccount.setSaldoInicial(account.saldoInicial());
			newAccount.setTipoCuenta(account.tipoCuenta());
			newAccount.setIdCliente(account.idCliente());
			return accountRepository.save(newAccount);
		}else {
			return null;
		}
		
	}

	@Override
	public Cuenta updateAccount(Cuenta account) {
		return accountRepository.save(account);
	}

	@Override
	public void deleteAccount(int id) {
		accountRepository.deleteById(id);
		
	}

}
