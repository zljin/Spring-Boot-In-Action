package com.zljin.basejava.jdk8;

import com.zljin.basejava.base.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Builder<T> {
    private final Supplier<T> instantiator;
    private List<Consumer<T>> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> Builder<T> of(Supplier<T> instantiator) {
        return new Builder<>(instantiator);
    }

    public <U> Builder<T> with(BiConsumer<T, U> biConsumer, U u) {
        modifiers.add(instance -> biConsumer.accept(instance, u));
        return this;
    }

    public T builder() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }

    public static void main(String[] args) {
        Account account = Builder.of(Account::new)
                .with(Account::setBalance, 1000)
                .with(Account::setFirstname, "leonard")
                .with(Account::setAccountNumber, "66666").builder();
        System.out.println(account.getBalance());
    }
}
