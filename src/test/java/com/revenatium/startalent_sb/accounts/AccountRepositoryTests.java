package com.revenatium.startalent_sb.accounts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("debería devolver cuentas cuando el nombre existe")
    void shouldReturnAccounts_WhenNameExists() {
        Account account = new Account();
        account.setName("John Doe");
        accountRepository.save(account);

        List<Account> accounts = accountRepository.findByName("John Doe");

        assertThat(accounts).isNotEmpty();
        assertThat(accounts.getFirst().getName()).isEqualTo("John Doe");
    }

    @Test
    @DisplayName("debería devolver una lista vacía cuando el nombre no existe")
    void shouldReturnEmptyList_WhenNameDoesNotExist() {
        List<Account> accounts = accountRepository.findByName("Nonexistent Name");

        assertThat(accounts).isEmpty();
    }

    @Test
    @DisplayName("debería devolver una lista vacía cuando el nombre es nulo")
    void shouldReturnEmptyList_WhenNameIsNull() {
        List<Account> accounts = accountRepository.findByName(null);

        assertThat(accounts).isEmpty();
    }

    @Test
    @DisplayName("debería devolver una lista vacía cuando el nombre está vacío")
    void shouldReturnEmptyList_WhenNameIsEmpty() {
        List<Account> accounts = accountRepository.findByName("");

        assertThat(accounts).isEmpty();
    }
}
