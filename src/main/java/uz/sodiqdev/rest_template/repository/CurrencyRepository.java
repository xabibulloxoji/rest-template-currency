package uz.sodiqdev.rest_template.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sodiqdev.rest_template.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findByCode(String code);

    Currency findByCcy(String ccy);
}
